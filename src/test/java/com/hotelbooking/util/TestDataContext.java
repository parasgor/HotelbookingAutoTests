package com.hotelbooking.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TestDataContext {

    private static final TestDataContext INSTANCE = new TestDataContext();
    private Map<String, Object> testDataMap = new HashMap<>();

    public TestDataContext() {
        this.testDataMap.put("Test", "Test");
    }

    public static TestDataContext getInstance() {
        return INSTANCE;
    }

    public void setTestData(String name, Object object){
        testDataMap.put(name, object);
    }

    public Object getTestData(String name) throws Exception{
        if(!testDataMap.containsKey(name)){
            throw new Exception(name + " does not exist. Please set data before get.");
        }
        return testDataMap.get(name);
    }
}