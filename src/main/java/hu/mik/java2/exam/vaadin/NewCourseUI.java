package hu.mik.java2.exam.vaadin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.mik.java2.exam.dao.KurzusDaoImpl;
import hu.mik.java2.exam.entities.Course;

@SpringUI(path="/newcourse")
public class NewCourseUI extends UI {

	@Autowired
	private KurzusDaoImpl kurzusDaoImpl;
	
	@Override
	protected void init(VaadinRequest request) {
		
		final TextField courseNameField = new TextField("Név: ");
		final TextField courseKreditField = new TextField("Kredit: ");
		final TextField courseMaxSzam = new TextField ("Max létszám:");
		final Button savebutton = new Button("Mentés");
		
		courseNameField.setWidth("200px");
		courseNameField.setIcon(FontAwesome.USER);	
		courseKreditField.setWidth("200px");
		courseKreditField.setIcon(FontAwesome.USER);
		courseMaxSzam.setWidth("200px");
		courseMaxSzam.setIcon(FontAwesome.USER);
		
		
		VerticalLayout courseLayout = new VerticalLayout();
		courseLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		courseLayout.addComponents(courseNameField,courseKreditField,courseMaxSzam,savebutton);
		
	 
		savebutton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				try{
				kurzusDaoImpl.save(new Course(courseNameField.getValue(),Integer.parseInt(courseKreditField.getValue()),
						Integer.parseInt(courseMaxSzam.getValue())));	
			
				}
				catch (Exception e) {
						System.out.println("Sikertelen mentés");
				}
				}
				
		});
		
		
		setContent(courseLayout);
		
		
	}

}
