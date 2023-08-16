package com.epam.project.commontools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getFormatterLogger(InvalidInputException.class);

	public InvalidInputException(String message) {
		logger.info(message);
	}

}