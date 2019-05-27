package com.example.demo.repository;

import com.example.demo.model.Filter;
import com.example.demo.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, String>{
    Profile findProfileById(String id);


  //  List<Filter> findAllByFilters(String id);
}
