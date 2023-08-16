package com.epam.project.dao;

import java.util.List;

import com.epam.project.entity.User;

public interface UsersDaoInterface {
	
	
	public User insert(User user);

	public User update(User user);

	public User remove(User user);

	public User view(int id);

	public List<User> viewAll();
}
