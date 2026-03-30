package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByTaskTaskId(Integer taskId);
}
