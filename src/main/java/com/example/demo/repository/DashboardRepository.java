package com.example.demo.repository;

import com.example.demo.model.Dashboard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends CrudRepository<Dashboard, String> {
    Dashboard findDashboardById(String id);
    List<Dashboard> findAllByUser_Id(String id);
    Boolean existsByName(String name);
}
