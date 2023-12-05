package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class GameLinkSecurityConfig {

    private static final String[] SECURED_ADMIN_URLs = {"/party/all"};
    private static final String[] SECURED_USER_URLs = {"/game/all"};
    private static final String[] SECURED_EVENT_MANAGER_URLs = {"/event/**"};

    private static final String[] UN_SECURED_URLs = {
            "/login/**",
            "/user/add",
            "/user/test"
    };

    @Autowired(required = true)
    private JWTAuthenticationFilter authenticationFilter;

    @Autowired(required = true)
    private GameLinkUserDetailsService userDetailsService;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
        		.authorizeHttpRequests(
        				auth -> auth
        				.requestMatchers(UN_SECURED_URLs).permitAll()
        				.requestMatchers(SECURED_USER_URLs).hasAnyAuthority("USER", "ADMIN")
        				.requestMatchers(SECURED_ADMIN_URLs).hasAuthority("ADMIN")
        				.anyRequest().authenticated())
        		.sessionManagement()
        		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		.and()
        		.authenticationProvider(authenticationProvider())
        		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        		.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}