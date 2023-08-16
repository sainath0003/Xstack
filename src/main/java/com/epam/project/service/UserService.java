package com.epam.project.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.UserDto;
import com.epam.project.entity.User;
import com.epam.project.repo.UserRepo;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	ModelMapper mapper;
	private static Logger logger = LogManager.getLogger(UserService.class);
	@Autowired
	private UserRepo dbOperations;

	@Override
	public UserDto add(UserDto userdto) {
		try {
			User user = new User(userdto);
			user = dbOperations.save(user);
			return new UserDto(user);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("User Not Added");
		}

	}

	@Override
	public UserDto update(UserDto userdto) {
		try {
			User user = new User(userdto);
			user = dbOperations.save(user);
			return new UserDto(user);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("User Not Updated");
		}

	}

	@Override
	public UserDto remove(Integer id) {
		User user;
		try {
			user = dbOperations.findById(id).orElseThrow();
			dbOperations.deleteById(id);
			return new UserDto(user);

		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("User Not Removed");
		}

	}

	@Override
	public UserDto view(Integer id) {

		try {

			return new UserDto(dbOperations.findById(id).orElseThrow());
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("No User Found");
		}

	}

	@Override
	public List<User> viewAll() {
		try {
			return dbOperations.findAll();

		} catch (NullPointerException | IllegalArgumentException | PersistenceException e) {
			throw new InvalidInputException("No Useres Present");
		}

	}

	@Override
	public boolean checkUser(UserDto userdto) {

		boolean result = true;
		User dbUser;
		try {
			User user = new User(userdto);
			dbUser = new User(view(user.getUserId()));
			result = user.getPassword().equals(dbUser.getPassword());
			return result;
		} catch (NullPointerException | PersistenceException | InvalidInputException | IllegalArgumentException
				| NoSuchElementException e) {
			throw new InvalidInputException("Enter Correct values");
		}

	}

	public UserDto save(UserDto userdto) {

		try {
			User user = new User(userdto);
			user.setUserType("Client");
			if (user.getPassword().length() < 6
					|| !(user.getEmailId().contains(".com") || user.getEmailId().contains("@)"))) {
				throw new InvalidInputException("Enter Correct Values");

			}
			userdto = add(userdto);
			logger.info(user);
			logger.info("User Registered Sucessfully");
			return userdto;
		} catch (NullPointerException | NoResultException | InvalidInputException | NoSuchElementException e) {
			logger.info("User Not Registeres");
			throw new InvalidInputException("Enter Correct Values");

		}

	}

}
