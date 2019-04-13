package com.example.demo.repository;

import com.example.demo.model.Dashboard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends CrudRepository<Dashboard, String> {
    Dashboard findDashboardById(String id);
}
