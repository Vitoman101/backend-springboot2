package com.viktor.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;

@Entity
@Table(name="sections")
public class Section {
	
	private long id;
	private String title;
	private String description;
	
	public Section() {
		
	}

	public Section(long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
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

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
}