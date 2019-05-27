package com.example.demo.repository;

import com.example.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Project findDashboardById(String id);
    Profile findProfileById(String id);
    Profile findProfileByUserId(String user_id);

    @Query(value = "select * from profile p where p.id not in (select pm.profile_id from" +
            " project_member pm where pm.id in(select pmm.members_id from project_members pmm" +
            " join project_member on pm.id=pmm.members_id and pmm.project_id=:id))", nativeQuery = true)
    List<Profile> getPossibleProjectMembers(@Param("id") String id);
}
