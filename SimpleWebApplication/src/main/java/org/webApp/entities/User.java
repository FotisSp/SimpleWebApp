package org.webApp.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	private String gender;
	
	@Type(type="date")
	private Date birthdate;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<HomeAddress> homeAddresses;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<WorkAddress> workAddresses;
	
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
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthdate() {	// TODO 
//		dateFormat = new SimpleDateFormat("dd MMM yyyy");
		return dateFormat.format(birthdate);
	}
	
	public void setBirthdate(String birthdate) {
		try {
			this.birthdate = dateFormat.parse(birthdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Set<HomeAddress> getHomeAddresses() {
		return homeAddresses;
	}
	
	public void setHomeAddresses(Set<HomeAddress> homeAddresses) {
		this.homeAddresses = homeAddresses;
	}
	
	public Set<WorkAddress> getWorkAddresses() {
		return workAddresses;
	}
	
	public void setWorkAddresses(Set<WorkAddress> workAddresses) {
		this.workAddresses = workAddresses;
	}
}
