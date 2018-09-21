package com.herokuapp.auto;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "json:target/cucumber-report.json",
        features = {"src/test/resources/Features"},
        glue = {"com.herokuapp.auto"},
        tags = {"@Run"})
public class RunCukesTest {
}