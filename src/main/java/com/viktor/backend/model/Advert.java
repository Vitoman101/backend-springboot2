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
@Table(name = "adverts")
public class Advert {
	
	private long id;
	private String title;
	private String description;
	private Date datePosted;
	private int pricePerUnit;		//Price per unit is how much money it costs per unitOfMeasure
	private String unitOfMeasure;	//Unit of measure is used for string because it can be either number of minutes or a string 
	private String nicknamePoster;
	private String phone;
	//private User user;
	private Course course;

	public Advert() {
		
	}
	
	public Advert(String title, String desc, Date datePosted, int pricePerUnit, String unitOfMeasure,
				  String nicknamePoster,String phone , Course course) {
		this.title = title;
		this.description = desc;
		this.datePosted = datePosted;
		this.pricePerUnit = pricePerUnit;
		this.unitOfMeasure = unitOfMeasure;
		this.nicknamePoster = nicknamePoster;
		this.course = course;
		this.phone = phone;
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
	
	@Column(name = "date_posted", nullable = false)
	public Date getDatePosted() {
		return datePosted;
	}
	
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	
	@Column(name = "price_per_unit", nullable = false)
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	
	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	@Column(name = "unit_of_measure", nullable = false)
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Column(name = "nickname_poster", nullable = false)
	public String getNicknamePoster() { return nicknamePoster; }

	public void setNicknamePoster(String nicknamePoster) {this.nicknamePoster = nicknamePoster; }

	@Column(name = "phone", nullable = false)
	public String getPhone() { return phone; }

	public void setPhone(String phone) { this.phone = phone; }

	/*
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	 */
	
	@ManyToOne
    @JoinColumn(name="course_id", nullable=false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Advert [id=" + id + ", title=" + title + ", description=" + description + ", datePosted=" + datePosted
				+ ", pricePerUnit=" + pricePerUnit + ", unitOfMeasure=" + unitOfMeasure + ", nicknamePoster=" + nicknamePoster + ", phone=" + phone + ", course="
				+ course.getId() + "]";
	}
	
}