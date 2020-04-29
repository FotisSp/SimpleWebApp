package org.webApp.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Fotis Spanopoulos
 *
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat dateFormat = null;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;
	private String gender;
	private Date birthdate;
	
	/**
	 * FetchType Lazy to load on demand , Eager to pre-load among all others
	 */
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private HomeAddress homeAddresses;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private WorkAddress workAddresses;
	
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
	
	public String getBirthdate() { 
		dateFormat= new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
		return dateFormat.format(birthdate);
	}
	
	public void setBirthdate(String birthdate) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			this.birthdate = dateFormat.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getHomeAddress() {
		return homeAddresses.getAddress();
	}
	
	public void setHomeAddress(HomeAddress address) {
		homeAddresses = address;
	}
	
	public String getWorkAddress() {
		return workAddresses.getAddress();
	}
	
	public void setWorkAddress(WorkAddress address) {
		workAddresses = address;
	}
}
