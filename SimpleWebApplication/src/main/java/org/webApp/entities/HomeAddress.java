package org.webApp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "home_address")
public class HomeAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
    @Column(name="homeAddress", length=255)
	private String address;

	@JsonIgnore 		//TODO says that it is for infinite loop ??!?!?
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String homeAddress) {
		this.address = homeAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
