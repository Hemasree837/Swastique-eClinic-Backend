package com.clinic.model;
import jakarta.persistence.*;
@Entity
public class Doctor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String name; public String specialization; public String phone;public String availableSlots;
 public Integer experience; public String imageUrl; public boolean onLeave=false;
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getSpecialization(){return specialization;} public void setSpecialization(String v){specialization=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public Integer getExperience(){return experience;} public void setExperience(Integer v){experience=v;}
 public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
 public boolean isOnLeave(){return onLeave;} public void setOnLeave(boolean v){onLeave=v;}
 public String getAvailableSlots() {
	    return availableSlots;
	}

	public void setAvailableSlots(String availableSlots) {
	    this.availableSlots = availableSlots;
	}
}
