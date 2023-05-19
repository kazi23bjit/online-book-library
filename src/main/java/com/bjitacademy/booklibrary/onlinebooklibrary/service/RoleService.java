package com.bjitacademy.booklibrary.onlinebooklibrary.service;

import com.bjitacademy.booklibrary.onlinebooklibrary.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRoles(String name);
    void addRole(String name);
}
