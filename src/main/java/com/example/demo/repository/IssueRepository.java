package com.example.demo.repository;

import com.example.demo.model.Issue;
import com.example.demo.model.ModelForSearch;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, String>,
        QuerydslPredicateExecutor<Issue>,
        JpaRepository<Issue, String> {
    Issue findIssueById(String id);

//    @Query(" select pr from Profile as pr join issue as i on i.assigne_id = pr.id where upper(pr.firstName) like upper(?1)" )
////    @Query("select iss from Issue iss where upper(iss.assignee) like upper(?1) or exists (select p from Profile p where p.user = u and upper(p.firstName) like upper(?1))")
//    List<UserProfile> findProfileBySubstring(String substring, Sort sort);
//
//    @Query(" select pr.firstName from Profile as pr join issue as i on i.assigne = pr.id where upper(pr.firstName) like upper(?1)" )
//    @Query("SELECT profile.first_name\n" +
//            "FROM profile inner join\n" +
//            "         issue On new_schema.profile.id = issue.assignee_id\n" +
//            "where upper(profile.first_name) like  upper(?1)")
//    List<ModelForSearch> searchByName(String substring, Sort sort);


}
