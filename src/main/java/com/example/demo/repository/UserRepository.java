package com.example.demo.repository;

import com.example.demo.model.Project;
import com.example.demo.model.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>, QuerydslPredicateExecutor<User> {
    Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);
    User findByLogin(String login);
    User findUserById(String id);
}
