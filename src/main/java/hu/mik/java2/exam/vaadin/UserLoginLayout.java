package hu.mik.java2.exam.vaadin;

import static org.mockito.Matchers.charThat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import groovyjarjarcommonscli.OptionGroup;
import hu.mik.java2.exam.dao.AdminDaoImpl;
import hu.mik.java2.exam.dao.DiakDaoImpl;
import hu.mik.java2.exam.entities.Admin;
import hu.mik.java2.exam.entities.Student;

@SuppressWarnings("serial")
@Component // kell, hogy menjen az autowired én
public class UserLoginLayout extends VerticalLayout {

	@Autowired
	private AdminDaoImpl adminDaoImpl;
	@Autowired
	private DiakDaoImpl diakDaoImpl;

	public UserLoginLayout() {

		// admindaoimpl.save(new Admin("alma","alma"));

		final TextField studentNameField = new TextField("Felhasználónév: ");
		final PasswordField passwordField = new PasswordField("Jelszó:");
		Button loginButton = new Button("Belépés");

		studentNameField.setWidth("200px");
		studentNameField.setIcon(FontAwesome.USER);
		
		passwordField.setWidth("200px");
		passwordField.setIcon(FontAwesome.KEY);

		Label title = new Label("Better than neptun!");
		title.setWidth("100px");
		
		RadioButtonGroup <String> rdbUsers = new RadioButtonGroup<>("User types");
		rdbUsers.setItems("Admin","Teacher","Student");
		rdbUsers.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		
		
		
		HorizontalLayout horizonLayout = new HorizontalLayout();
		horizonLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		horizonLayout.addComponents(title);
		horizonLayout.addComponent(rdbUsers);
		
		
		VerticalLayout loginLayout = new VerticalLayout();
		loginLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		loginLayout.addComponents(studentNameField, passwordField, loginButton);

		loginButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				

				try {
					
					if(rdbUsers.getSelectedItem().get() == "Admin"){	
						Admin admin = UserLoginLayout.this.adminDaoImpl.chkAdminByName(studentNameField.getValue(),
								passwordField.getValue());
						System.out.println("Létezik");
						
						Page.getCurrent().setLocation("/admin");
						setDefault();
						}
					
					if(rdbUsers.getSelectedItem().get() == "Student"){	
						UserLoginLayout.this.diakDaoImpl.chkByStudent(studentNameField.getValue(),
								passwordField.getValue());
						System.out.println("Létezik");
						
						Page.getCurrent().setLocation("/studentUI");
						setDefault();
						}
					
					
					
					System.out.println(rdbUsers.getSelectedItem().get());
					
				} catch (Exception e) {
					// TODO
					// handle exception
					System.out.println("Nem Létezik");
					System.out.println(rdbUsers.getSelectedItem().get());
				}
			}

			private void setDefault() {
				rdbUsers.isSelected(null);
				studentNameField.clear();
				passwordField.clear();
				
				
			}
		});
		
		
		addComponents(horizonLayout, loginLayout);
		setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
	}
	
	@PostConstruct
	public void alma(){
	   
	   //adminDaoImpl.save(new Admin("adam","admin","admin"));
	}
	//a postconstruct a konstruktor után fut le
}
