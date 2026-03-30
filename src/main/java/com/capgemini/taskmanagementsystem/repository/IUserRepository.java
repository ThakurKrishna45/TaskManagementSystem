package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Integer> {
}
