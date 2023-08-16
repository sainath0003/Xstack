package com.epam.gymapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingDtoForNewTraining {

	private String traineeUserName;
	private String trainerUserName;
	private String trainingName;
	private String trainingType;
	private Integer trainingDuration;

}
