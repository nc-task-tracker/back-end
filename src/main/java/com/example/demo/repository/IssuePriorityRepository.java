package com.example.demo.repository;

import com.example.demo.model.IssuePriority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuePriorityRepository extends CrudRepository<IssuePriority, String> {
    IssuePriority findIssuePriorityById(String id);
}
