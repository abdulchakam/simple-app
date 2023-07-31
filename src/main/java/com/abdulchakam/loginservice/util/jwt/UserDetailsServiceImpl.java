package com.abdulchakam.loginservice.util.jwt;

import com.abdulchakam.loginservice.exception.DataNotFoundException;
import com.abdulchakam.loginservice.model.User;
import com.abdulchakam.loginservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(user)) {
            throw new DataNotFoundException("Username is not found");
        }

        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getUsername());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(grantedAuthority));
    }
}
