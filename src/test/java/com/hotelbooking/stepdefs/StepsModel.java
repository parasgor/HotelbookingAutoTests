package com.hotelbooking.stepdefs;

import com.hotelbooking.util.TestDataContext;
import cucumber.api.Scenario;
//import lv.iljapavlovs.cucumber.config.ResourceManager;


public class StepsModel {
    protected TestDataContext resourceManager;

    protected Scenario scenario;

    public StepsModel(TestDataContext resourceManager) throws Exception {
        this.resourceManager = resourceManager;
        this.scenario = resourceManager.testInfo.getCurrentScenario();

    }

    public StepsModel(){
        System.out.println("I am default constructor");
    }


}
