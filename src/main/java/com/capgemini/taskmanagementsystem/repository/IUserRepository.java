package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Integer> {
}
