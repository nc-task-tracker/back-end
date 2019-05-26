package com.example.demo.repository;

import com.example.demo.model.Identificator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificatorRepository extends CrudRepository<Identificator, String>, JpaRepository<Identificator, String> {

    //@Query("select u from User u where upper(u.login) like upper(?1) or exists (select p from Profile p where p.user = u and upper(p.firstName) like upper(?1))")
    Identificator findIdentificatorById(Integer id);
}
