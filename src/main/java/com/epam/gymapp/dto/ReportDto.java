package com.epam.gymapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

	private String trainerUserName;
	private String trainerFirstName;
	private String trainerLastName;
	private Boolean traineeStatus;
	private Integer traineeDuration;
	@JsonIgnore
	private List<TrainingDtoForWrite> trainings;
}
