package com.hotelbooking.hooks;


import com.hotelbooking.core.DriverBase;
import com.hotelbooking.stepdefs.StepsModel;
import com.hotelbooking.util.TestDataContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
//import lv.iljapavlovs.cucumber.config.ResourceManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.MDC;

@Slf4j
public class Hooks  extends StepsModel {

    private Scenario scenario ;

    public Hooks(TestDataContext resourceManager) throws Exception {
        super(resourceManager);
        this.resourceManager = resourceManager;
    }

    public Hooks() {
        super();
        this.resourceManager = TestDataContext.getInstance();
    }

    @Before
    public void setup(Scenario scenario) throws Exception {
        MDC.put("scenarioId", "scenarioId:" + UUID.randomUUID().toString());
        String sessionId = ((RemoteWebDriver) DriverBase.getDriver()).getSessionId().toString();
        log.info("Starting Scenario: \"" + scenario.getName() + "\" with Session ID: " + sessionId);
        DriverBase.getDriver().manage().deleteAllCookies();
       // DriverBase.getDriver().manage().window().maximize();
        resourceManager.testInfo.setCurrentScenario(scenario);

    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverBase.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException | ClassCastException wde) {
                log.error(wde.getMessage());
            }
        }
        log.info(String.format("Ending Scenario: \"%s\"", scenario.getName()) + " result: " + (scenario.isFailed() ? "FAILED" : "PASSED"));
        DriverBase.closeDriverObjects();
    }
}
