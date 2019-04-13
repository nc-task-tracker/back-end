package com.example.demo.controller;

import com.example.demo.dto.ParameterDto;
import com.example.demo.model.Parameter;
import com.example.demo.service.ParameterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/parameter" )
public class ParameterController {
    private ParameterService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ParameterController(ParameterService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ParameterDto getParameterById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getParameterById(id), ParameterDto.class);
    }

    @GetMapping(value = "/all")
    public List<ParameterDto> getAllParameters() {
        List<ParameterDto> parametersDto = new ArrayList<>();
        List<Parameter> parameters = service.getAllParameters();
        for(Parameter item : parameters) {
            parametersDto.add(modelMapper.map(item, ParameterDto.class));
        }
        return parametersDto;
    }

    @PostMapping
    public Parameter saveParameter(@RequestBody Parameter parameter) {
        return service.saveParameter(parameter);
    }

    @PutMapping
    public ParameterDto updateParameter(@RequestBody ParameterDto parameterForUpdate) {
        Parameter parameter = modelMapper.map(service.getParameterById(parameterForUpdate.getId()), Parameter.class);
        return modelMapper.map(service.updateParameter(parameter), ParameterDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteParameter(@PathVariable(name = "id") String id) {
        service.deleteParameter(id);
        return ResponseEntity.noContent().build();
    }
}
