package com.abdulchakam.loginservice.service;

import com.abdulchakam.loginservice.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> show();
    User update(User user);
    void delete(Long id);
}
