package com.example.demo.repository;

import com.example.demo.model.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, String> {
    Parameter findParameterById(String id);
}
