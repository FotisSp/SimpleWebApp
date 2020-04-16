package org.webApp.models;

import java.util.HashMap;
import java.util.Map;

import org.webApp.entities.User;

public class UsersSet {
	private Map<Integer,User> users;

	public UsersSet() {
		users = new HashMap<Integer,User>();
	}
	
	public Map<Integer,User> getMap() {
		return users;
	}
	
	public void addUser(User u) {
		users.put(u.getId(), u);
	}
	
	public User getUser(int id) { 
		return users.get(id);
	}
}
