package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
    User findUserById(String id);

    @Query(value = "select * from new_schema.user u where u.id not in(select v.user_id from project_assigner v join project p on p.id=:projectId and" +
            " p.id=v.project_id)", nativeQuery = true)
    List<User> getNotProjectAssigners(@Param("projectId") String id);
}
