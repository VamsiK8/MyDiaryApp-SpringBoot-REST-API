package com.vk.mydiaryrestapi.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // to automatically run classes
@EnableWebSecurity
public class SecurityConfig {

	/* In-Memory User Details Manager
	@Bean // To automatically call functions
	public InMemoryUserDetailsManager configureInMemoryUsers() {
		
		// Loading each individual user details
		// UserDetails is interface , so we are using Spring-Security-->'User'class
		UserDetails u1 = User.builder().username("vk").password("{noop}123").roles("Manager","Employee").build();
		UserDetails u2 = User.builder().username("vk1").password("{noop}123").roles("Admin").build();
		UserDetails u3 = User.builder().username("vk2").password("{noop}123").roles("Employee").build();
		
	 InMemoryUserDetailsManager inMemoryUserDetails =  new InMemoryUserDetailsManager(u1, u2, u3);
	 
	 return inMemoryUserDetails;
	}
	*/
	
	// DB User Details Manager
	@Bean
	public UserDetailsManager configureDBUsers(DataSource dataSource) { // dataSource maps to db tables
		
		UserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource); // UserDetailsManager is interface, so using JdbcUserDetailsManager class 
		
		return userDetailsManager; //pass - v123
	}
	
	@Bean
	public SecurityFilterChain authorizeUsers(HttpSecurity http) throws Exception {
		http.
		authorizeHttpRequests((authorize) -> { 
		        authorize
		       
		        .requestMatchers(HttpMethod.GET, "/entries/**").hasAnyRole("MANAGER", "ADMIN") //or hasAuthority("ROLE_MANAGER")   //Single Authority for One HTTPMethod
		        
		        // Multiple authorities for One HTTPMethod
		        //.hasAnyRole("MANAGER","ADMIN") OR .hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN")
		        
		        .requestMatchers(HttpMethod.PUT, "/entries/**").hasAuthority("ROLE_MANAGER")
		        .requestMatchers(HttpMethod.PATCH, "/entries/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
		        .requestMatchers(HttpMethod.DELETE, "/entries/**").hasAuthority("ROLE_MANAGER")
				.anyRequest().authenticated();
		})
		.httpBasic(withDefaults())
		.csrf(csrf -> csrf.disable());
		
		return http.build();
	}

	
}
