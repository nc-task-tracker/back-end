package com.example.demo.repository;

import com.example.demo.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember,String > {

    @Query(value = "delete from project_members pm where pm.project_id=:projectId " +
            "and pm.members_id=:memberId", nativeQuery = true)
    void deleteProjectMember(@Param("projectId") String projectId,
                             @Param("memberId") String memberId);

//    @Query(value = "", nativeQuery = true)
//    ProjectMember findByMemberIdAndProjectIdAndLogin(@Param("memberId") String memberId,
//                                                     @Param("projectId") String projectId,
//                                                     @Param("login") String login);

    Optional<ProjectMember> findById(String id);
}

