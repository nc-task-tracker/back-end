package com.example.demo.service;

import com.example.demo.model.Parameter;
import com.example.demo.model.ParameterType;

import java.util.List;

public interface ParameterService {
//    Parameter saveParameter(Parameter parameter);
    Parameter saveParameter(Parameter parameter, String filterId);
    Parameter getParameterById(String id);
    Parameter updateParameter(Parameter parameter);
    List<Parameter> getAllParameters();
    void deleteParameter(String id);
}
