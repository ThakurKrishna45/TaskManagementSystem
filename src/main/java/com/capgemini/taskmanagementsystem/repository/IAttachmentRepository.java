package com.capgemini.taskmanagementsystem.repository;

import com.capgemini.taskmanagementsystem.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttachmentRepository extends JpaRepository<Attachment,Integer> {
}
