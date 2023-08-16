package com.epam.javaproject.mokito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.UserDto;
import com.epam.project.entity.User;
import com.epam.project.repo.UserRepo;
import com.epam.project.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
	private UserRepo userDao;

	@InjectMocks
	private UserService userService;

	@Test
	void testAddUser() {

		User user = new User("admin", "sai1", "sai1", "sai1", "sai1");
		UserDto userDto = new UserDto(user);
		Mockito.when(userDao.save(any())).thenReturn(user);
		UserDto retrievedUser = userService.add(userDto);
		assertEquals(user.getUserId(), retrievedUser.getUserId());

	}

	@Test
	void testAddNullUser() {

		//Mockito.when(userDao.save(null)).thenThrow(NullPointerException.class);
		assertThrows(RuntimeException.class, () -> userService.add(null));

	}
	@Test
	void testUpdateUser() {

		User user = new User("admin", "sai1", "sai1", "sai1", "sai1");
		UserDto userDto = new UserDto(user);
		Mockito.when(userDao.save(any())).thenReturn(user);
		UserDto retrievedUser = userService.update(userDto);
		assertEquals(user.getUserId(), retrievedUser.getUserId());

	}

	@Test
	void testUpdateNullUser() {

		//Mockito.when(userDao.save(null)).thenThrow(NullPointerException.class);
		assertThrows(InvalidInputException.class, () -> userService.update(null));

	}

	@Test
	void testRemoveUser() {

		Optional<User> user = Optional.ofNullable(new User("admin", "sai1", "sai1", "sai1", "sai1"));
		Mockito.when(userDao.findById(anyInt())).thenReturn(user);
		
		UserDto retrievedUser = userService.remove(1);
		Mockito.verify(userDao).deleteById(anyInt());

		assertEquals(user.get().getUserId(), retrievedUser.getUserId());

	}

	@Test
	void testRemoveNullUser() {

		Mockito.when(userDao.findById(null)).thenThrow(NullPointerException.class);
		assertThrows(RuntimeException.class, () -> userService.remove(null));

	}

	@Test
	void testViewUser() {

		Optional<User> user = Optional.ofNullable(new User("admin", "sai1", "sai1", "sai1", "sai1"));
		user.get().setUserId(1);
		Mockito.when(userDao.findById(anyInt())).thenReturn(user);
		UserDto retrievedUser = userService.view(1);
		assertEquals(user.get().getUserId(), retrievedUser.getUserId());

	}

	@Test
	void testViewNullUser() {

		Mockito.when(userDao.findById(null)).thenThrow(NullPointerException.class);
		assertThrows(RuntimeException.class, () -> userService.view(null));

	}


	@Test
	void testCheckUser() {
		Optional<User> user = Optional.ofNullable(new User("admin", "sai1", "sai1", "sai1", "sai1"));
		user.get().setUserId(1);
		UserDto userDto = new UserDto(user.get());
		Mockito.when(userDao.findById(anyInt())).thenReturn(user);
		boolean result = userService.checkUser(userDto);
		assertEquals(true, result);

	}

	@Test
	void testCheckNullUser() {
		
		assertThrows(InvalidInputException.class, () -> userService.checkUser(null));
	}

	@Test
	void testSaveUser() {
	
		Optional<User> user = Optional.ofNullable(new User("admin", "sai1", "sai1", "sai1", "sai1"));
		user.get().setUserId(1);
		user.get().setUserType("admin");
		user.get().setPassword("123456");
		user.get().setEmailId("123@gmail.com");
		UserDto userDto = new UserDto(user.get());
		Mockito.when(userDao.save(any())).thenReturn(user.get());
		UserDto result = userService.save(userDto);
		assertEquals(user.get().getUserId(), result.getUserId());

	}
	
	@Test
	void testSaveUserWrongPassword() {
	
		Optional<User> user = Optional.ofNullable(new User("admin", "sai1", "sai1", "sai1", "sai1"));
		user.get().setUserId(1);
		user.get().setUserType("admin");
		user.get().setPassword("123");
		UserDto userDto = new UserDto(user.get());
		assertThrows(InvalidInputException.class, () -> userService.save(userDto));
	
	}
	@Test
	void testSaveUserWrongEmail() {
	
		Optional<User> user = Optional.ofNullable(new User("admin", "sai1", "sai1", "sai1", "sai1"));
		user.get().setUserId(1);
		user.get().setUserType("admin");
		user.get().setPassword("123456");
		user.get().setEmailId("123");
		UserDto userDto = new UserDto(user.get());
		assertThrows(InvalidInputException.class, () -> userService.save(userDto));
	
	}
	@Test
	void testSaveNullUser() {
		assertThrows(InvalidInputException.class, () -> userService.save(null));
	}
	@Test
	void testViewAllQuiz() {
		User user1 = new User();
		user1.setUserId(1);
		User user2 = new User();
		user2.setUserId(2);

		List<User> usersList = Arrays.asList(user1, user2);
		Mockito.when(userDao.findAll()).thenReturn(usersList);
		List<User> users= userService.viewAll();
		assertEquals(usersList,users);

	}
	

	@Test
	void testViewAllwithNoUsers() {
		Mockito.when(userDao.findAll()).thenThrow(NullPointerException.class);
		assertThrows(InvalidInputException.class, () -> userService.viewAll());
	}
}
