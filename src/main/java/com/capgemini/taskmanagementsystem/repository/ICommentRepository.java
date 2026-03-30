package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Integer> {
//    @Query("select e from Comment e where e.task.taskId=:taskId")
    List<Comment> findByTaskTaskID(Integer taskID);
}
