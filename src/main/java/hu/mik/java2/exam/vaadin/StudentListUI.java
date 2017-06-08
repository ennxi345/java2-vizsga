package hu.mik.java2.exam.vaadin;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	private Grid<Student> grid = new Grid<>();
	private Set<Student> setstudentlist;
	private Iterator<Student> iteratorStudentList;
	private Student student = null;

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
			
			Button modifyButton = new Button("Módosít");
			Button deleteButton = new Button("Törlés");
			
			
			
			modifyButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					try {
						rebootgrid();
						
						
						Window subWindow = new Window("Update");
						subWindow.setHeight("700px");
						subWindow.setWidth("800px");
						subWindow.addCloseListener(new Window.CloseListener() {
							
							@Override
							public void windowClose(com.vaadin.ui.Window.CloseEvent e) {
								modifyButton.setEnabled(true);
								deleteButton.setEnabled(true);	
						
								setstudentlist=null;
								iteratorStudentList=null;
								
							}
						});
						
				        VerticalLayout subContent = new VerticalLayout();
				        subWindow.setContent(subContent);
				        
				        student = iteratorStudentList.next();
				        Label namelbl = new Label(student.getName());
				        Button btnModify = new Button("Módosítás");
				        Button btnNextPerson = new Button("Következő");
				        subContent.addComponents(namelbl,
						        new TextField("Név: "),
								new TextField("Felhasználónév: "),
								new PasswordField("Jelszó:"),
								new TextField("Szak:"),
								new TextField("SzületésiÉV"),btnModify,btnNextPerson);
				 
				        btnModify.addClickListener(new ClickListener() {
							
							@Override
							public void buttonClick(ClickEvent event) {
								if(student !=null){
									System.out.println(student.getName());
									diakDaoImpl.Update(student);
								}
							}});
				        
				        btnNextPerson.addClickListener(new ClickListener() {
							
							@Override
							public void buttonClick(ClickEvent event) {  		
								if(!setstudentlist.isEmpty()){
									if(iteratorStudentList.hasNext()){
										student = iteratorStudentList.next();
										namelbl.setValue(student.getName());
									}
									
								}
								
							}
						});
				        
				        
				        
				        
				        subWindow.center();
				        addWindow(subWindow);
				        setFocusedComponent(subWindow);
				        modifyButton.setEnabled(false);
						deleteButton.setEnabled(false);
					} catch (Exception e) {
						System.out.println("Sikertelen módosítás");
					}
				}
			});
			
			deleteButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					try {	
						rebootgrid();
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

	protected void rebootgrid() {
		setstudentlist=null;
		iteratorStudentList=null;
		
		setstudentlist = grid.getSelectedItems();
		iteratorStudentList = setstudentlist.iterator();
		
	}

}
