package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.repository.IUserRepository;
import com.capgemini.taskmanagementsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
