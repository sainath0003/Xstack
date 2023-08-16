package com.epam.gymapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.gymapp.conveter.TrainingDtoConverter;
import com.epam.gymapp.dto.ReportDto;
import com.epam.gymapp.exception.TrainerNotFoundException;
import com.epam.gymapp.model.Report;
import com.epam.gymapp.model.Trainer;
import com.epam.gymapp.proxy.NotificationService;
import com.epam.gymapp.service.TrainerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gymapp/reportservice")
@Slf4j
public class ReportController {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	TrainerService trainerService;
	@Autowired
	TrainingDtoConverter converter;
	@Autowired
	NotificationService notificationService;
	String reportsUrl = "http://localhost:8082/reportservice";

	@GetMapping("/view")
	public ResponseEntity<List<Report>> create(@RequestParam String trainerUserName) {

		log.info("Entered  create in ReportController");
		ParameterizedTypeReference<List<Report>> parameterizedType = new ParameterizedTypeReference<List<Report>>() {
		};
		ResponseEntity<List<Report>> response = restTemplate.exchange(
				reportsUrl + "/view?trainerUserName=" + trainerUserName, HttpMethod.GET, null, parameterizedType);
		log.info("Exited  create in ReportController");
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}

	@PostMapping("/save")
	public ResponseEntity<Report> save(@RequestBody ReportDto reportDto) {

		log.info("Entered  save in ReportController");
		Trainer trainer = trainerService.getTrainerByUserName(reportDto.getTrainerUserName());
		if (trainer != null) {
			reportDto.setTrainings(
					trainer.getTrainingsList().stream().map(t -> converter.toTrainingDtoForWrite(t)).toList());
			HttpEntity<ReportDto> requestEntity = new HttpEntity<>(reportDto);
			ResponseEntity<Report> response = restTemplate.exchange(reportsUrl + "/save", HttpMethod.POST,
					requestEntity, Report.class);
			log.info("Exited  save in ReportController");

			notificationService.sendEmail(reportDto.getTrainerUserName(), "Training Report", reportDto.toString());
			return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		} else {
			throw new TrainerNotFoundException("Trainer with given UserName is Not Present");
		}

	}

}
