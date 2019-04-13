package com.example.demo.repository;

import com.example.demo.model.Filter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends CrudRepository<Filter, String> {
    Filter findFilterById(String id);
}
