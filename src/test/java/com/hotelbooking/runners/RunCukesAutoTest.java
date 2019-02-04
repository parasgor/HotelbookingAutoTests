package com.hotelbooking.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/cheese", "json:target/cucumber/cucumber-cheese.json"},
        features = {"src/test/resources/features"},
        tags = {"@Regression"},
        glue = {"com.hotelbooking.stepdefs", "com.hotelbooking.hooks"}
)
public class RunCukesAutoTest {

}

