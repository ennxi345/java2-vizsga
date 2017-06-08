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
	private Integer kredit;
	
	@Column(name="maxletszam")
	private Integer maxLetszam;
	
	public Course() {
	}

	public Course(String name, Integer kredit,Integer maxLetszam) {
		super();
		this.name = name;
		this.kredit = kredit;
		this.maxLetszam = maxLetszam;
	}

	public Integer getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public Integer getKredit(){
		return kredit;
	}
	
	public Integer getMaxLetszam(){
		return maxLetszam;
	}
	
	@Override
	public String toString() {
		return "Kurzus [name=" + name + ", kredit=" + kredit + "]";
	}
	
	
	
}
