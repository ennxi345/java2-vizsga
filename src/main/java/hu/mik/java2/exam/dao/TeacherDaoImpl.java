package hu.mik.java2.exam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import groovy.time.BaseDuration.From;
import hu.mik.java2.exam.entities.Student;
import hu.mik.java2.exam.entities.Teacher;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherDaoImpl implements TeacherDao {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public List<Teacher> findAll() {
		return this.entitymanager
			.createQuery("SELECT t FROM teacher t" , Teacher.class)
			.getResultList();
				
	}

	@Override
	public Teacher save(Teacher teacher) {
		if(teacher.getId() == null) {
			this.entitymanager.persist(teacher);
			
			return teacher;
		} else {
			return this.entitymanager.merge(teacher);
		}
	}

	@Override
	public void delete(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
