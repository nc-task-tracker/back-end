package com.example.demo.repository;

import com.example.demo.model.IssueRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRoleRepository extends CrudRepository<IssueRole, String> {
    IssueRole findIssueRoleById(String id);
}
