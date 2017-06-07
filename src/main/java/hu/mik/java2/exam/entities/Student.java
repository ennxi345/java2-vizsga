package hu.mik.java2.exam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diak")
public class Student extends Person{

	@Column(name = "szak")
	private String szak;
	
	@Column(name = "kredit")
	private Integer kredit;
	
	@Column(name = "szulev")
	private Integer szulev;
	
	public Student() {
		super();
	}

	public Student(String name ,String username, String password,String szak,Integer szulev) {
		super(name,username,password);
		this.szak = szak;
		this.szulev=szulev;
	}

	public String getSzak() {
		return szak;
	}
	
	public Integer getSzulev() {
		return szulev;
	}
	
	
	public Integer getKredit() {
		return kredit;
	}

}
