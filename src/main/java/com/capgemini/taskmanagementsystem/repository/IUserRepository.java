package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
}
