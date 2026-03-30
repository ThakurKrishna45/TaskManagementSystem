package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface ITaskRepository extends JpaRepository<Task,Integer> {
    @Query("SELECT t FROM Task t LEFT JOIN FETCH " +
            "t.project LEFT JOIN FETCH t.user WHERE t.priority = :priority" +
            " AND t.status = :status")
    List<Task> findByPriorityAndStatus(String priority, String status);

    List<Task> findByCategories_CategoryName(String categoryName);
    @Query("select e from Task e where e.project.projectId=:projectId")
    List<Task> findByProjectProjectId(@Param("projectId")Integer projectId);
}
