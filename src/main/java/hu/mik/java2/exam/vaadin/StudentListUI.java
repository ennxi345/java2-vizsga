package hu.mik.java2.exam.vaadin;

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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.mik.java2.exam.dao.DiakDaoImpl;
import hu.mik.java2.exam.entities.Student;


@SpringUI(path = "/studentlist")
public class StudentListUI extends UI{
	
	@Autowired
	private DiakDaoImpl diakDaoImpl;
	
	private Grid<Student> grid = new Grid<>();

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
			
			grid.setSelectionMode(SelectionMode.SINGLE);
			
			
			Button modifyButton = new Button("Módosít");
			Button deleteButton = new Button("Törlés");
			
			
			modifyButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					try {
						
						System.out.println("Sikeres módostás!");
					} catch (Exception e) {
						System.out.println("Sikertelen módosítás");
					}
				}
			});
			
			deleteButton.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					try {
						Set<Student> s = grid.getSelectedItems();
						diakDaoImpl.delete(s);
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
