package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface INotificationRepository extends JpaRepository<Notification,Integer> {

    List<Notification> findAllNotificationsByCreatedAt(LocalDateTime date);

    @Query("SELECT n from Notification n where n.user.userId IN " +
            "(SELECT m.user.userId from Task m where m.project.projectId = :projectId)")
    List<Notification> getAllNotificationsOfAProject(Integer projectId);


    List<Notification> findAllNotificationsBetweenDates(LocalDateTime start, LocalDateTime end);

    List<Notification> findAllNotificationsByUserID(Integer id);
}
