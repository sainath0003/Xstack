package com.epam.gymapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GymappApplicationTests {

	@Test
	void contextLoads() {
	 String text="hello";
	 assertEquals("hello", text);
	}

}
