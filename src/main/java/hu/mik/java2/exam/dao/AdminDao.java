package hu.mik.java2.exam.dao;

import hu.mik.java2.exam.entities.Admin;

public interface AdminDao {

	public Admin save(Admin admin);
	
	public Admin chkAdminByName(String name, String password);
}
