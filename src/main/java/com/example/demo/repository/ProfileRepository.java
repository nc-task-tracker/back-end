package com.example.demo.repository;

import com.example.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Profile findProfileById(String id);

    @Query(value = "select * from new_schema.profile p where p.id in(select ppp.profileid from new_schema.project_member ppp" +
            " where ppp.projectid=:id)",nativeQuery = true)
    List<Profile> findProjectMembersByProjectId(@PathVariable(name = "id") String id);

    @Query(value = "select * from profile p where p.id not in (select pm.profile_id from" +
            " project_member pm where pm.id in(select pmm.members_id from project_members pmm" +
            " join project_member on pm.id=pmm.members_id and pmm.project_id=:id))", nativeQuery = true)
    List<Profile> getPossibleProjectMembers(@Param("id") String id);
}
