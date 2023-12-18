package com.example.demo.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.User;

import lombok.Data;


@Data
public class GameLinkUserDetails implements UserDetails {
	
	private int id;
    private String userName;
    private String name;
    private String password; 
    private List<GrantedAuthority> authorities;

    public GameLinkUserDetails(User user) {
    	id = user.getId();
        userName = user.getEmail();
        name = user.getUserName();
        password = user.getPassword();
        authorities = Arrays.stream(user.getRole().getName()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
}
