package hu.mik.java2.exam.dao;

import java.util.List;
import java.util.Set;

import hu.mik.java2.exam.entities.Admin;
import hu.mik.java2.exam.entities.Student;
import hu.mik.java2.exam.entities.Teacher;

public interface DiakDao {
	
	public List<Student> findAll();
	
	public Student save(Student diak);
	
	public void delete(Set<Student> studentList);

	public Student chkByStudent(String name, String password);
	
	public void Update(Student student);
}
