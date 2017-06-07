package hu.mik.java2.exam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kurzus")
public class Course {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "kredit")
	private String kredit;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String name, String kredit) {
		super();
		this.name = name;
		this.kredit = kredit;
	}

	@Override
	public String toString() {
		return "Kurzus [name=" + name + ", kredit=" + kredit + "]";
	}
	
	
	
}
