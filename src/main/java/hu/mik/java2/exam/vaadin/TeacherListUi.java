package hu.mik.java2.exam.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


import hu.mik.java2.exam.dao.AdminDaoImpl;
import hu.mik.java2.exam.dao.TeacherDaoImpl;
import hu.mik.java2.exam.entities.Student;
import hu.mik.java2.exam.entities.Teacher;

@SpringUI(path = "/teacherlist")
public class TeacherListUi extends UI{
	
	
	private Grid<Teacher> grid = new Grid<>();
	
	@Autowired
	private TeacherDaoImpl teacherDaoImpl;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout teacherLayout = new VerticalLayout();
		
		
		try{
			List<Teacher> teacherlist = teacherDaoImpl.findAll();
			grid.setItems(teacherlist);
			grid.addColumn(Teacher::getName).setCaption("Name");
			grid.addColumn(Teacher::getUsername).setCaption("Username");
			grid.addColumn(Teacher::getTanszek).setCaption("Programme");
			teacherLayout.addComponent(grid);
			setContent(teacherLayout);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
