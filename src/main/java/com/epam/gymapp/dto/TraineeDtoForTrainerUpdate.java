package com.epam.gymapp.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TraineeDtoForTrainerUpdate {

	private String userName;

	private List<String> trainers;
}
