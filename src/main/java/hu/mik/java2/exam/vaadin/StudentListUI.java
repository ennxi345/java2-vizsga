package hu.mik.java2.exam.vaadin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;


import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.mik.java2.exam.dao.DiakDaoImpl;
import hu.mik.java2.exam.entities.Student;

@SpringUI(path = "/studentlist")
public class StudentListUI extends UI{
	
	@Autowired
	private DiakDaoImpl diakDaoImpl;
	//
	
	private Grid<Student> grid = new Grid<>();
	private Set<Student> setstudentlist;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout studentListLayout = new VerticalLayout();
	
		
		try{
			List<Student> studentlist = diakDaoImpl.findAll();
			grid.setItems(studentlist);
			grid.setSizeFull();
			grid.addColumn(Student::getName).setCaption("Name");
			grid.addColumn(Student::getUsername).setCaption("Username");
			grid.addColumn(Student::getSzak).setCaption("Programme");
			grid.addColumn(Student::getSzulev).setCaption("Birth Date");
			
			grid.setSelectionMode(SelectionMode.MULTI);
			
			grid.addSelectionListener(new SelectionListener() {

				@Override
				public void selectionChange(SelectionEvent event) {
					setstudentlist = grid.getSelectedItems();
				}
				});
			
			
			Button modifyButton = new Button("Módosít");
			Button deleteButton = new Button("Törlés");
			
			
			
			modifyButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					try {
						modifyButton.setEnabled(false);
						deleteButton.setEnabled(false);
						
						Window subWindow = new Window("Update");
						subWindow.setHeight("500px");
						subWindow.setWidth("800px");
						subWindow.addCloseListener(new Window.CloseListener() {
							
							@Override
							public void windowClose(com.vaadin.ui.Window.CloseEvent e) {
								modifyButton.setEnabled(true);
								deleteButton.setEnabled(true);	
							}
						});
						
				        VerticalLayout subContent = new VerticalLayout();
				        subWindow.setContent(subContent);

				        //
				        String valami = "";
				        // Put some components in it
				        subContent.addComponents(
				        new Label(""),
				        new TextField("Név: "),
						new TextField("Felhasználónév: "),
						new PasswordField("Jelszó:"),
						new TextField("Szak:"),
						new TextField("SzületésiÉV"));
				        Button btnModify = new Button("Módosítás");
				        Button btnNextPerson = new Button("Következő");
				        subContent.addComponent(btnModify);
				  
				        btnModify.addClickListener(new ClickListener() {
							
							@Override
							public void buttonClick(ClickEvent event) {
								
								
							}});
				        
				        btnNextPerson.addClickListener(new ClickListener() {
							
							@Override
							public void buttonClick(ClickEvent event) {
								Student student = setstudentlist.stream().findFirst().get();
								
								if(student != null){
									//set, de ehhez kellenének setter metódusok is
								}
							}
						});
				        
				        
				        
				        
				        subWindow.center();
				        addWindow(subWindow);
				        setFocusedComponent(subWindow);
					} catch (Exception e) {
						System.out.println("Sikertelen módosítás");
					}
				}
			});
			
			deleteButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					try {	
						diakDaoImpl.delete(setstudentlist);
					} catch (Exception e) {
						System.out.println("Sikertelen törlés");
					}
				}
			});
			
			studentListLayout.addComponents(grid,deleteButton,modifyButton);
			setContent(studentListLayout);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

}
