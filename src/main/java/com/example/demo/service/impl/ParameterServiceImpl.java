package com.example.demo.service.impl;

import com.example.demo.model.Parameter;
import com.example.demo.repository.ParameterRepository;
import com.example.demo.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private ParameterRepository repository;

    @Autowired
    public ParameterServiceImpl(ParameterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Parameter saveParameter(Parameter parameter) {
        return repository.save(parameter);
    }

    @Override
    public Parameter getParameterById(String id) {
        return repository.findParameterById(id);
    }

    @Override
    public Parameter updateParameter(Parameter parameter) {
        return repository.save(parameter);
    }

    @Override
    public List<Parameter> getAllParameters() {
        return (List<Parameter>) repository.findAll();
    }

    @Override
    public void deleteParameter(String id) {
        repository.deleteById(id);
    }
}
