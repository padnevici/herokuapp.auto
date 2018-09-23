package com.herokuapp.auto.testDataUtils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScenarioContext {
    private Map<DataKey, Object> testData = new HashMap<>();

    public void saveTestData(DataKey dataKey, Object value) {
        testData.put(dataKey, value);
    }

    public Object getTestData(DataKey dataKey) {
        return testData.get(dataKey);
    }
}
