package com.example.demo.service;

import com.example.demo.model.ParameterValue;

import java.util.List;

public interface ParameterValueService {
    ParameterValue saveParameterValue(ParameterValue parameterValue);
    ParameterValue getParameterValueById(String id);
    ParameterValue updateParameterValue(ParameterValue parameterValue);
    List<ParameterValue> getAllParameterValues();
    void deleteParameterValue(String id);
}
