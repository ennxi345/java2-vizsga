package hu.mik.java2.exam.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.mik.java2.exam.dao.DiakDaoImpl;
import hu.mik.java2.exam.entities.Student;

@SpringUI(path = "/newteacher")
public class NewTeacherUI extends UI {

	@Autowired
	private DiakDaoImpl diakDaoImpl;
	
	@Override
	protected void init(VaadinRequest request) {
		final TextField teacherNameField = new TextField("Név: ");
		final TextField teacherUserNameField = new TextField("Felhasználónév: ");
		final PasswordField passwordField = new PasswordField("Jelszó:");
		final TextField programmeField = new TextField("Tanszék:");
		final TextField birthyearField = new TextField("SzületésiÉV");
		Button saveButton = new Button("Mentés");

		teacherNameField.setWidth("200px");
		teacherNameField.setIcon(FontAwesome.USER);

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
		saveLayout.addComponents(title,teacherNameField,teacherUserNameField, passwordField,programmeField,birthyearField, saveButton);

	saveButton.addClickListener(new ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {

			try {
				diakDaoImpl.save(new Student(teacherNameField.getValue(),teacherUserNameField.getValue(),passwordField.getValue(),
						programmeField.getValue(),Integer.parseInt(birthyearField.getValue())));
			} catch (Exception e) {
				System.out.println("Sikertelen mentés");
			}
		}
	});
	setContent(saveLayout);
	}
}
