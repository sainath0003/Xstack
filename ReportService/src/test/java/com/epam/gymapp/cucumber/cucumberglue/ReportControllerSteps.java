package com.epam.gymapp.cucumber.cucumberglue;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.gymapp.dto.ReportDto;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportControllerSteps {

	@Autowired
	private TestRestTemplate restTemplate; // Autowire TestRestTemplate for making HTTP requests

	private ReportDto reportDto;
	private ResponseEntity<ReportDto> response;

	@Given("^the report data$")
	public void the_report_data(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		Map<String, String> reportData = data.get(0);

		reportDto = new ReportDto();
		reportDto.setTrainerUserName(reportData.get("trainerUserName"));
		reportDto.setTrainerFirstName(reportData.get("trainerFirstName"));
		reportDto.setTrainerLastName(reportData.get("trainerLastName"));
		reportDto.setTrainerStatus(Boolean.parseBoolean(reportData.get("trainerStatus")));
		reportDto.setTraineeDuration(Integer.parseInt(reportData.get("traineeDuration")));
	}

	@When("^the client sends a POST request to /reportservice/save with the data$")
	public void the_client_sends_a_POST_request_to_reportservice_save_with_the_data() {
		// Set the request headers (if needed)
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		// Create an HTTP entity with the reportDto and headers
		HttpEntity<ReportDto> requestEntity = new HttpEntity<>(reportDto, headers);

		// Send the POST request and capture the response
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/reportservice/save");
		response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, ReportDto.class);
	}

	@Then("^the response status code should be 200$")
	public void the_response_status_code_should_be_200() {
		// assert. the response status code
		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
	}

	@Then("^the response should contain the report data$")
	public void the_response_should_contain_the_report_data() {
		// assert. the response body or other details as needed
		ReportDto createdReport = response.getBody();
		assertNotNull(createdReport);
		assertEquals(reportDto.getTrainerUserName(), createdReport.getTrainerUserName());
		assertEquals(reportDto.getTrainerFirstName(), createdReport.getTrainerFirstName());
		// Add assert.ions for other properties if needed
	}
}
