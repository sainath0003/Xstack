package com.epam.project.service;

import java.util.List;

import com.epam.project.dto.UserDto;
import com.epam.project.entity.User;

public interface UserServiceInterface {

	public UserDto add(UserDto user);

	public UserDto remove(Integer id);

	public UserDto update(UserDto user);

	public UserDto view(Integer id);
	
	public List<User> viewAll();

	public UserDto save(UserDto user);

	public boolean checkUser(UserDto user);

	

}
