package hu.mik.java2.exam.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.mik.java2.exam.entities.Admin;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AdminDaoImpl implements AdminDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Admin save(Admin admin) {
		if (admin.getId() == null) {
			this.entityManager.persist(admin);

			return admin;
		} else {
			return this.entityManager.merge(admin);
		}
	}

	@Override
	public Admin chkAdminByName(String username, String password) {
		return this.entityManager
				.createQuery("SELECT a FROM Admin a WHERE a.username LIKE :username AND a.password = :password",
						Admin.class)
				// belépéskor név és jelszó párossal ellenőrizz, ne csak névvel
				// nem tudom, hogy ez a metódus csak a loginnál érdekes-e
				// igen szerintem elég csak ott
				.setParameter("username", username).setParameter("password", password).getSingleResult();

	}

}
