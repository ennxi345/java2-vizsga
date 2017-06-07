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
				.createQuery("SELECT d FROM Course d", Course.class)
				.getResultList();
		
	}

	@Override
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course chkByCourse(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
