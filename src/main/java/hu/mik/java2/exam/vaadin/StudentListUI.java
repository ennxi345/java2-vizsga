package hu.mik.java2.exam.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
		
		studentListLayout.addComponent(grid);
		try{
			List<Student> studentlist = diakDaoImpl.findAll();
			grid.setItems(studentlist);
			grid.addColumn(Student::getName).setCaption("Name");
			grid.addColumn(Student::getUsername).setCaption("Username");
			grid.addColumn(Student::getSzak).setCaption("Programme");
			grid.addColumn(Student::getSzulev).setCaption("Birth Date");
			studentListLayout.addComponent(grid);
			setContent(studentListLayout);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

}
