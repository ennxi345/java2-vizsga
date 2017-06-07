package hu.mik.java2.exam.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.mik.java2.exam.dao.AdminDaoImpl;
import hu.mik.java2.exam.dao.DiakDaoImpl;
import hu.mik.java2.exam.entities.Admin;
import hu.mik.java2.exam.entities.Student;

@SpringUI(path = "/newstudent")
public class NewStudentUI extends UI{

	@Autowired
	private DiakDaoImpl diakDaoImpl;
	
	@Override
	protected void init(VaadinRequest request) {
		
		final TextField studentNameField = new TextField("Név: ");
		final TextField studentUserNameField = new TextField("Felhasználónév: ");
		final PasswordField passwordField = new PasswordField("Jelszó:");
		final TextField programmeField = new TextField("Szak:");
		final TextField birthyearField = new TextField("SzületésiÉV");
		Button saveButton = new Button("Mentés");


		studentNameField.setWidth("200px");
		studentNameField.setIcon(FontAwesome.USER);
		
		studentUserNameField.setWidth("200px");
		studentUserNameField.setIcon(FontAwesome.USER);

		passwordField.setWidth("200px");
		passwordField.setIcon(FontAwesome.KEY);
		
		programmeField.setWidth("200px");
		programmeField.setIcon(FontAwesome.SUITCASE);
		
		birthyearField.setWidth("200px");
		birthyearField.setIcon(FontAwesome.GIFT);

		Label title = new Label("Felhasználó bevitele:");
		title.setWidth("100px");


		VerticalLayout saveLayout = new VerticalLayout();
		saveLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		saveLayout.addComponents(title,studentNameField,studentUserNameField, passwordField,programmeField,birthyearField, saveButton);

		saveButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					diakDaoImpl.save(new Student(studentNameField.getValue(),studentUserNameField.getValue(),passwordField.getValue(),
							programmeField.getValue(),Integer.parseInt(birthyearField.getValue())));
					System.out.println("Sikeres mentés!");
				} catch (Exception e) {
					System.out.println("Sikertelen mentés");
				}
			}
		});
		setContent(saveLayout);
	}

}
