package com.epam.gymapp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrainerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TrainerNotFoundException(String message) {
		super(message);
		log.info(message);
	}
}
