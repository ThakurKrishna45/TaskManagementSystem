package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByPriorityAndStatus(String priority, String status);
    @Query("select e from Task e where e.ProjectId=projectId")
    List<Task> findByProjectProjectId(@Param("projectId")Integer projectId);
}
