package hu.mik.java2.exam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person{
	
	private String tanszek;

	public Teacher(String name,String username,String password,String tanszek) {
		super(name,username, password);
		this.tanszek = tanszek;
	}
	
	
	
	

}
