package com.epam.gymapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.gymapp.dto.ReportDto;
import com.epam.gymapp.exception.ReportException;
import com.epam.gymapp.kafka.KafkaProducer;
import com.epam.gymapp.model.Report;
import com.epam.gymapp.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportRepository reportRepository;
	@Autowired
	KafkaProducer kafkaProducer;

	@Override
	public Report saveReport(ReportDto reportDto) {

		log.info("Entered  saveReport in ReportServiceImpl");
		try {
			log.info(reportDto.toString());
			return reportRepository.save(new Report(reportDto));
		} catch (Exception e) {
			throw new ReportException("Report creation failed,please give correct Input");
		}

	}

	@Override
	public List<Report> getReport(String trainerUserName) {
		log.info("Entered  getReport in ReportServiceImpl");
		try {
			return kafkaProducer.sendReportList(reportRepository.findByTrainerUserName(trainerUserName));

		} catch (Exception e) {
			throw new ReportException("Reports with give UserName are not present");
		}

	}

}
