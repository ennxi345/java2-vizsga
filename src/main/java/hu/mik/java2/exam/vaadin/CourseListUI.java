package hu.mik.java2.exam.vaadin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import hu.mik.java2.exam.dao.KurzusDaoImpl;
import hu.mik.java2.exam.entities.Course;
import hu.mik.java2.exam.entities.Student;


@SpringUI(path="/courselist")
public class CourseListUI extends UI {

	@Autowired
	private KurzusDaoImpl kurzusdaoImpl;
	Grid<Course> grid = new Grid<>();
	
	@Override
	protected void init(VaadinRequest request) {
	
		VerticalLayout courseLayout = new VerticalLayout();
	
		
	try{
		List<Course> coruselist = kurzusdaoImpl.findAll();
		grid.setItems(coruselist);
		grid.setSizeFull();
		grid.addColumn(Course::getName).setCaption("Name");
		grid.addColumn(Course::getKredit).setCaption("Kredit");
		grid.addColumn(Course::getMaxLetszam).setCaption("Max létszám");
		grid.setSelectionMode(SelectionMode.MULTI);		

		
		courseLayout.addComponents(grid);
		setContent(courseLayout);
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	}
}
