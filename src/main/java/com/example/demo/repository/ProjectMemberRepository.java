package com.example.demo.repository;

import com.example.demo.model.ProjectMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember,String > {
    Optional<ProjectMember> findById(String id);

    @Query(value = "select * from project_member pm where pm.id in(select pmm.members_id from project_members pmm where" +
            " pmm.project_id=:id)",nativeQuery = true)
    Page<ProjectMember> findPageDataByProjectId(@Param("id") String id, Pageable pageable);
}

