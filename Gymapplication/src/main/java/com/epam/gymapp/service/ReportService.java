package com.epam.gymapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.epam.gymapp.conveter.TrainingDtoConverter;
import com.epam.gymapp.dto.ReportDto;
import com.epam.gymapp.kafka.KafkaProducer;
import com.epam.gymapp.model.Trainer;
import com.epam.gymapp.model.User;

@Service
public class ReportService {

	@Autowired
	TrainerService trainerService;

	@Autowired
	Notificationservice notificationService;
	@Autowired
	KafkaProducer kafkaProducer;
	@Autowired
	TrainingDtoConverter converter;

	@Scheduled(fixedDelay = 50000)
	public void sendReport() {
		List<Trainer> trainers = new ArrayList<>();

		trainers.add(trainerService.getTrainerByUserName("saiprani123@gmail.com"));
		// trainers.add(trainerService.viewAllTrainers().get(0));
		ReportDto reportDto = new ReportDto();
		for (Trainer trainer : trainers) {

			reportDto = getReport(trainer);
			kafkaProducer.sendMessage(reportDto);
			notificationService.sendEmail(reportDto.getTrainerUserName(), "Your Training Report", reportDto.toString());

		}

	}

	public ReportDto getReport(Trainer trainer) {
		User user = trainer.getUser();

		return new ReportDto(trainer.getUserName(), user.getFirstName(), user.getLastName(), true,
				LocalDate.now().getDayOfMonth() - user.getCreatedDate().getDayOfMonth(), null);
	}
}
