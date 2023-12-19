package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jwt.JWTAuthenticationRequest;
import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class JWTController {
	
	@Autowired(required = true)
    private JWTService jwtService;
	
	@Autowired(required = true)
    private AuthenticationManager authenticationManager;
	

	@Autowired(required = true)
	private UserService userService;

    @PostMapping
    public Object getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
        	User user = userService.getOneByEmail(authRequest.getUserName());
            String token =  jwtService.generateToken(user.getEmail(), user.getUserName(), user.getId(), user.getRole().getName());
            JSONObject jsonObject = new JSONObject("{\"token\": \"" + token + "\"}");
            jsonObject.put("token",token );
            return jsonObject.toMap();//devuelve token por body
        }
        else {
            throw new UserNotFoundException("Invalid user credentials");
        }
    }
}
