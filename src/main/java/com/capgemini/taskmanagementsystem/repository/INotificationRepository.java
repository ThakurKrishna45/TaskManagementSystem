package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface INotificationRepository extends JpaRepository<Notification,Integer> {


    @Query("select e from Notification e where e.user.userId=:id")
    List<Notification> findByUserUserID(Integer id);

}
