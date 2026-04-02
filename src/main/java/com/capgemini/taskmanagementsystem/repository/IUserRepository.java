package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {
//    @Query("select e from User e where e.Username=:username")
    List<User> findByUsername(@Param("username") String username);
}
