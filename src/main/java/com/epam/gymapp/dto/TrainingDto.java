package com.epam.gymapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingDto {

	private String userName;
	private String periodFrom;
	private String periodTo;
	private String trainerName;
	private String trainingType;
}
