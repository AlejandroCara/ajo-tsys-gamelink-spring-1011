package com.example.demo.security;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class GameLinkSecurityConfig {

	private static final String[] SECURED_ADMIN_URLs = { 
			"/game/**", 
			"/user/**", 
			"/tag/**", 
			"/party/**", 
			"/message/**",
			"/game_role/**", 
			"/game_game_role/**", 
			"/user_party_game_role/**", 
			"/swagger-ui/**" 
	};

	private static final String[] SECURED_USER_URLs = { 
			"/user/profile", 
			"/user/update", 
			"/game/all", 
			"/game/id/**",
			"/party/all",
			"/party/id/**", 
			"/party/join/**", 
			"/party/leave/**", 
			"/party/add", 
			"/party/own", 
			"/party/own/id/**", 
			"/party/own/update/**",
			"/party/own/delete/**", 
			"/party/members/**", 
			"/message/party/**", 
			"/message/party/write/**",
			"/message/id/**", 
			"/message/update/**", 
			"/message/delete/**", 
			"/event/all", 
			"/event/id/**",
			"/game_role/all", 
			"/game_role/id/**", 
			"/tag/all", 
			"/tag/id/**" 
	};

	private static final String[] SECURED_EVENT_MANAGER_URLs = { "/event/**" };

	private static final String[] UN_SECURED_URLs = { "/login/**", "/user/add" };

	@Autowired(required = true)
	private JWTAuthenticationFilter authenticationFilter;

	@Autowired(required = true)
	private GameLinkUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		var authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://main.d2oub417gkrd2h.amplifyapp.com/", "*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS","DELETE", "PUT"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(auth -> auth.requestMatchers(UN_SECURED_URLs).permitAll()
						.requestMatchers(SECURED_USER_URLs).hasAnyAuthority("USER", "ADMIN", "EVENT_MANAGER")
						.requestMatchers(SECURED_EVENT_MANAGER_URLs).hasAnyAuthority("ADMIN", "EVENT_MANAGER")
						.requestMatchers(SECURED_ADMIN_URLs).hasAuthority("ADMIN").anyRequest().authenticated())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}