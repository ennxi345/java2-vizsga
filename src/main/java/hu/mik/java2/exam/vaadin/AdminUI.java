package hu.mik.java2.exam.vaadin;

import javax.activation.CommandInfo;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/admin")
public class AdminUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		Label adminLabel = new Label("Admin felület");
		
		VerticalLayout verticallayout = new VerticalLayout();
		verticallayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		
		MenuBar barmenu = new MenuBar();
		MenuItem student = barmenu.addItem("Diákok ", null,null);
		MenuItem teacher = barmenu.addItem("Tanárok ", null,null);
		MenuItem course = barmenu.addItem("Kurzus ", null,null);
		
		teacher.addItem("Tanárok listája", new Command() {
			
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/teacherlist");	
			}
		});
		teacher.addItem("Tanár felvétel", new Command(){

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/newteacher");
				
			}
			
		});
		teacher.addItem("Tanár módosítás", new Command(){

			@Override
			public void menuSelected(MenuItem selectedItem) {
				
					Page.getCurrent().setLocation("/newteacher");
					
				}
			
		});
		
		student.addItem("Diákok Listája", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/studentlist");	
			}
		});
		student.addItem("Diák felvétele", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/newstudent");	
			}
		});
		
		student.addItem("Diák(ok) módosítása", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/studentmodify");	
			}
		});
		
		
		
		course.addItem("Kurzus lista", new Command(){

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/courselist");
				
			}
			
			
		});
		
		
		
		course.addItem("Kurzus felvétele", new Command(){

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Page.getCurrent().setLocation("/newcourse");
				
			}});
			
			
			
	
			
		
		course.addItem("Kurzus módosítása", null);
		
		
		verticallayout.addComponents(barmenu,adminLabel);
		setContent(verticallayout);
		
	
	}

}
