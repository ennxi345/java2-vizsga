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

				// na szóval itt akarom leellenörizni hogy megeggyezik-e a
				// felhasználónév az admin táblában és ha igen akkor navigáljon
				// át Admin felületre ami az admin view csak gey probléma van
				// hogy csak Ui ban engedi a AdminDaiImpl metódusát meghívni
				/*
				 * if(admindaoimpl.chkAdminbyname(studentNameField.getValue())
				 * != null){ System.out.println("Létezik"); }else{
				 * 
				 * System.out.println("Nem Létezik"); }
				 */

				// jajj
				// ne csinálj ilyen kevert nevű dolgot
				// vagy az egész kód legyen angol, vagy az egész magyar
				// refactor megoldja egyszerűen

				// azért nem érted el, mert anonymus inner class-on belül nem
				// volt adminDaoImpl field. ilyenkor a "külső" osztály neve.this
				// kell, hogy elérd

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
					
					//teszt jelleggel, ide rendesen be kell kötni a spring security-t, hogy ne engedjen átmenni a MainUI-ra, ha nincs belépve
					
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
		// nem szereted a this-t :(
		// de igen csak kapkofdok mártt 
		// ez nem jó megközelités. inkább legyen nem kész, mint ronda ok
		// no mindegy, nézzük meg, hogy mi történik
		addComponents(horizonLayout, loginLayout);
		setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
	}
	
	@PostConstruct
	public void alma(){
	   
	   adminDaoImpl.save(new Admin("Ádám","admin6","admin"));
	}
	//a postconstruct a konstruktor után fut le
}
