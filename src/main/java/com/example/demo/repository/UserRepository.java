package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String>, JpaRepository<User, String> {
    Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);
    User findByLogin(String login);
    User findUserById(String id);

    @Query("select u from User u where upper(u.login) like upper(?1) or exists (select p from Profile p where p.user = u and upper(p.firstName) like upper(?1))")
    List<User> findUserProfileBySubstring(String substring, Sort sort);
}
