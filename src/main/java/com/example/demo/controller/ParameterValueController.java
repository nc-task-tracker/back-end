package com.example.demo.controller;

import com.example.demo.dto.ParameterValueDto;
import com.example.demo.model.ParameterValue;
import com.example.demo.service.ParameterValueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/parameter_value" )
public class ParameterValueController {
    private ParameterValueService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ParameterValueController(ParameterValueService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ParameterValueDto getParameterValueById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getParameterValueById(id), ParameterValueDto.class);
    }

    @GetMapping(value = "/all")
    public List<ParameterValueDto> getAllParameterValues() {
        List<ParameterValueDto> parameterValuesDto = new ArrayList<>();
        List<ParameterValue> parameterValues = service.getAllParameterValues();
        for(ParameterValue item : parameterValues) {
            parameterValuesDto.add(modelMapper.map(item, ParameterValueDto.class));
        }
        return parameterValuesDto;
    }

    @PostMapping
    public ParameterValue saveParameterValue(@RequestBody ParameterValue parameterValue) {
        return service.saveParameterValue(parameterValue);
    }

    @PutMapping
    public ParameterValueDto updateParameterValue(@RequestBody ParameterValueDto parameterValueForUpdate) {
        ParameterValue parameterValue = modelMapper.map(service.getParameterValueById(parameterValueForUpdate.getId()), ParameterValue.class);
        return modelMapper.map(service.updateParameterValue(parameterValue), ParameterValueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteParameterValue(@PathVariable(name = "id") String id) {
        service.deleteParameterValue(id);
        return ResponseEntity.noContent().build();
    }
}
