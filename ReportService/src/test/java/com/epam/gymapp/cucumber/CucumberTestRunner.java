package com.epam.gymapp.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/features/"},
        plugin = {"pretty"},
        glue = {"com.epam.gymapp.cucumber.cucumberglue"})
public class CucumberTestRunner {
}
