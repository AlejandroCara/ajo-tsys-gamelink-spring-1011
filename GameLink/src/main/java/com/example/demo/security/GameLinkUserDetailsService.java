package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.IUserDAO;


@Component
public class GameLinkUserDetailsService implements UserDetailsService {

    @Autowired(required = true)
    private IUserDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(GameLinkUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
