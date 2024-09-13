package com.JavaWizard.VirtualMuseum.service;

import com.JavaWizard.VirtualMuseum.dto.UserDto;
import com.JavaWizard.VirtualMuseum.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers(); // Ensure this method is correctly implemented
}
