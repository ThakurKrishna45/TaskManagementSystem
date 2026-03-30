package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification,Integer> {


}
