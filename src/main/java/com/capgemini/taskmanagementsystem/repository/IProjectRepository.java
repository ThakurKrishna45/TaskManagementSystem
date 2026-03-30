package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface IProjectRepository extends JpaRepository<Project,Integer> {
    @Query("select p from Project p where p.startDate<=:endDate and p.endDate>=:startDate")
    List<Project> findProjectByTimeDuration(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);
    @Query("select p from Project p where p.user.userId=:userId")
    List<Project> findAllByUserId(Integer userId);

}
