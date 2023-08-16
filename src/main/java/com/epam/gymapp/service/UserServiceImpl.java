package com.epam.gymapp.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.gymapp.dto.UserDto;
import com.epam.gymapp.dto.UserDtoForPasswordChange;
import com.epam.gymapp.exception.UserNotFoundException;
import com.epam.gymapp.model.User;
import com.epam.gymapp.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	Random random = new Random();
	User user;

	public User save(User user) {
		log.info("Entered  save in UserServiceImpl");
		user.setPassword(random.doubles(6).toString());
		userRepository.save(user);
		log.info("Exited  save in UserServiceImpl");
		return userRepository.save(user);

	}

	public User update(User user) {

		log.info("Entered  update in UserServiceImpl");
		User dbUser = userRepository.findByUserName(user.getUserName())
				.orElseThrow(() -> new UserNotFoundException("User with given UserName does not Exists "));
		user.setUserId(dbUser.getUserId());
		user.setUserName(dbUser.getUserName());
		log.info("Exited  update in UserServiceImpl");
		return userRepository.save(user);
	}

	public User view(String userName) {
		log.info("Entered  view in UserServiceImpl");
		return userRepository.findByUserName(userName).orElse(null);
	}

	public Boolean login(UserDto userDto) {
		log.info("Entered  login in UserServiceImpl");
		Boolean result = false;
		user = userRepository.findByUserName(userDto.getUserName()).orElse(null);

		if (user != null && user.getPassword().equals(userDto.getPassword())) {
			result = true;
		}
		log.info("Exited  login in UserServiceImpl");
		return result;
	}

	public boolean changeLogin(UserDtoForPasswordChange userDto) {
		log.info("Entered  changeLogin in UserServiceImpl");
		Boolean result = false;
		user = userRepository.findByUserName(userDto.getUserName())
				.orElseThrow(() -> new UserNotFoundException("User with given UserName does not Exists "));

		if (user != null && user.getPassword().equals(userDto.getOldPassword())) {
			result = true;
			user.setPassword(userDto.getNewPassword());
			userRepository.save(user);
		}
		log.info("Exited  changeLogin in UserServiceImpl");
		return result;
	}

	public void delete(String userName) {
		log.info("Entered  delete in UserServiceImpl");
		userRepository.deleteByUserName(userName);

	}
}
