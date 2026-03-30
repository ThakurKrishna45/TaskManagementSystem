package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole,Integer> {
}
