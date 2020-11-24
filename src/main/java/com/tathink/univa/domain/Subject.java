package com.tathink.univa.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;

@Entity(name = "subject")
@DynamicInsert
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSIGNED INT")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "subject")
	private Set<SolutionSubject> soluions;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private Set<Subject> child_subject;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<SolutionSubject> getSoluions() {
		return soluions;
	}
	public void setSoluions(Set<SolutionSubject> soluions) {
		this.soluions = soluions;
	}
	public Set<Subject> getChild_subject() {
		return child_subject;
	}
	public void setChild_subject(Set<Subject> child_subject) {
		this.child_subject = child_subject;
	}
	
}
