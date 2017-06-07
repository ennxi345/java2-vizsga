package hu.mik.java2.exam.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import hu.mik.java2.exam.dao.AdminDaoImpl;

@SpringUI(path = "/teacherlist")
public class TeacherListUi extends UI{
	
	@Autowired
	private AdminDaoImpl adminDaoImpl;

	@Override
	protected void init(VaadinRequest request) {
		
		
	}

}
