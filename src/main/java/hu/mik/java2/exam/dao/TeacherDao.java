package hu.mik.java2.exam.dao;

import java.util.List;

import hu.mik.java2.exam.entities.Student;
import hu.mik.java2.exam.entities.Teacher;

public interface TeacherDao {
	
	public List<Teacher> findAll();
	
	public Teacher save(Teacher teacher);
	
	public void delete(Teacher teacher);
}
