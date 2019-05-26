package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {
    //Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);
    User findByLogin(String login);
    User findUserById(String id);

    @Query("select u from User u where upper(u.login) like upper(?1) or exists (select p from Profile p where p.user = u and upper(p.fullName) like upper(?1))")
    List<User> findUserProfileBySubstring(String substring, Sort sort);


    @Query(value = "select * from new_schema.user u where u.id not in(select v.user_id from project_assigner v join project p on p.id=:projectId and" +
            " p.id=v.project_id)", nativeQuery = true)
    List<User> getNotProjectAssigners(@Param("projectId") String id);

}

