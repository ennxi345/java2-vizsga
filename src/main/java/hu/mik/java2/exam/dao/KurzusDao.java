package hu.mik.java2.exam.dao;

import java.util.List;

import hu.mik.java2.exam.entities.Course;
import hu.mik.java2.exam.entities.Student;

public interface KurzusDao {

public List<Course> findAll();
	
	public Course save(Course course);
	
	public void delete(Course course);
	
}
