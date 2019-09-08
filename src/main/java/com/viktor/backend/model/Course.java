package com.viktor.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name = "courses")
public class Course {
	
	private long id;
	private String title;
	private String code;
	
	public Course() {
	
	}
	
	public Course(long id, String title, String code) {
		this.title = title;
		this.code = code;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "code", nullable = false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", code=" + code + "]";
	}
	
	
}