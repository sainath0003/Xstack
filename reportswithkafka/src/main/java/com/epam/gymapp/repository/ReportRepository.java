package com.epam.gymapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.gymapp.model.Report;

public interface ReportRepository extends MongoRepository<Report, String> {
	List<Report> findByTrainerUserName(String trainerUserName);
}
