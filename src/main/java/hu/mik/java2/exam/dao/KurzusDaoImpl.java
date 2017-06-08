package hu.mik.java2.exam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.mik.java2.exam.entities.Course;
import hu.mik.java2.exam.entities.Student;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class KurzusDaoImpl implements KurzusDao {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public List<Course> findAll() {
		return this.entitymanager
				.createQuery("SELECT c FROM Course c", Course.class)
				.getResultList();
		
	}

	@Override
	public Course save(Course course) {
		if(course.getId() == null) {
			this.entitymanager.persist(course);
			
			return course;
		} else {
			return this.entitymanager.merge(course);
		}
	}

	@Override
	public void delete(Course course) {
		
	}

	

}
