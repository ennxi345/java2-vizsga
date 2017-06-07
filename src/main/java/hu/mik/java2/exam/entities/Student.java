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
	
	@Column(name = "tanar")
	private String tanar;
	
	
	@Column(name = "kredit")
	private Integer kredit;
	
	@Column(name = "szulev")
	private Integer szulev;
	
	public Student() {
		super();
	}

	public Student(String name ,String password,String szak,Integer szulev) {
		super(name,password);
		this.szak = szak;
		this.szulev=szulev;
	}

	public String getSzak() {
		return szak;
	}

	public String getTanar() {
		return tanar;
	}
	
	public Integer getKredit() {
		return kredit;
	}

}
