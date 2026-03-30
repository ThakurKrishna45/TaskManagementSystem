package com.capgemini.taskmanagementsystem.service.implementation;

import com.capgemini.taskmanagementsystem.repository.IUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRoleServiceImpl {

    final private IUserRoleRepository userRoleRepository;

}
