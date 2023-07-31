package com.abdulchakam.loginservice.service;

import com.abdulchakam.loginservice.model.User;
import com.abdulchakam.loginservice.repository.UserRepository;
import com.abdulchakam.loginservice.util.BCryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        log.info("Start create user");

        try {
            user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
            return userRepository.save(user);

        } catch (Exception e) {
            log.error("Error when create user : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> show() {
        log.info("Start show user");
        try {
            return userRepository.findAll();

        } catch (Exception e) {
            log.error("Error when show user");
            throw e;
        }
    }

    @Override
    public User update(User user) {
        log.info("Start update user");
        try {
            user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
            return userRepository.save(user);

        } catch (Exception e) {
            log.error("Error when update user : {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Start delete user");
        try {
            userRepository.deleteById(id);

        } catch (Exception e) {
            log.error("Error when delete user : {}", e.getMessage());
            throw e;
        }
    }
}
