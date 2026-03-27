package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.entity.Project;
import com.capgemini.taskmanagementsystem.entity.User;
import com.capgemini.taskmanagementsystem.exception.ResourseNotFoundException;
import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;
//
//    @Transactional
//    public List<Project> getAllProjectByUser(Integer userId){
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()){
//            return user.get().getProjects();
//        }
//        else{
//            throw new ResourseNotFoundException("No Project Found for that User");
//        }

//    }
}
