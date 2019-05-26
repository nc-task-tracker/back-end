package com.example.demo.repository;

import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, String> {
    Project findDashboardById(String id);
    Profile findProfileById(String id);

}
