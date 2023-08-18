package com.epam.gymapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDtoForTrainerUpdate {

	private String userName;

	private List<String> trainers;
}
