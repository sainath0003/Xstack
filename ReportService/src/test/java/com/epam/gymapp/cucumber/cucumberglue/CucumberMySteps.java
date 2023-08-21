package com.epam.gymapp.cucumber.cucumberglue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.epam.gymapp.model.Report;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberMySteps {
	public class RunCucumberTest {
	}

	@LocalServerPort
	String port;
	ResponseEntity<Void> lastResponse;

	@When("the client calls endpoint {string}")
	public void whenClientCalls(String url) {

		try {

			ParameterizedTypeReference<Void> parameterizedType = new ParameterizedTypeReference<>() {
			};
			lastResponse = new RestTemplate().exchange(
					"http://localhost:" + port + "/reportservice/view?trainerUserName=" + url, HttpMethod.GET, null,
					parameterizedType);

		} catch (HttpClientErrorException httpClientErrorException) {

			httpClientErrorException.printStackTrace();
		}
	}

	@Then("response status code is {int}")
	public void thenStatusCodee(int expected) {

		Assertions.assertNotNull(lastResponse);
		Assertions.assertNotNull(lastResponse.getStatusCode());
		assertEquals(lastResponse.getStatusCode().value(), expected);
	}

	@Then("response status code is not present")
	public void thenStatusCodeeIsNotPresent() {
		Assertions.assertNull(lastResponse);
	}

}
