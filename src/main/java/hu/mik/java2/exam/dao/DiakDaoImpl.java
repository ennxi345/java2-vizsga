package hu.mik.java2.exam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.mik.java2.exam.entities.Admin;
import hu.mik.java2.exam.entities.Student;
import hu.mik.java2.exam.entities.Teacher;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DiakDaoImpl implements DiakDao{
	
	@PersistenceContext
	private EntityManager entitymanager;

	@Override
	public List<Student> findAll() {
		return this.entitymanager
				.createQuery("SELECT d FROM Student d", Student.class)
				.getResultList();
	}

	@Override
	public Student save(Student diak) {
		if(diak.getId() == null) {
			this.entitymanager.persist(diak);
			
			return diak;
		} else {
			return this.entitymanager.merge(diak);
		}
	}
	
	

	@Override
	public void delete(Student diak) {
		if(!this.entitymanager.contains(diak)) {
			this.entitymanager.merge(diak);
		}
		else{
		this.entitymanager.remove(diak);	
		}
	}
	
	@Override
	public Student chkByStudent(String name, String password) {
		return this.entitymanager
				.createQuery("SELECT d FROM diak d WHERE d.name LIKE :name AND d.password = :password", Student.class)
				// belépéskor név és jelszó párossal ellenőrizz, ne csak névvel
				// nem tudom, hogy ez a metódus csak a loginnál érdekes-e
				// igen szerintem elég csak ott
				.setParameter("name", name).setParameter("password", password).getSingleResult();

	}

}
