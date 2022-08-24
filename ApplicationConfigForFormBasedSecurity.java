package com.alom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alom.service.AuthenticationSuccessHandlerImpl;

@Configuration
@EnableWebSecurity
public class ApplicationConfigForFormBasedSecurity extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/admin-dashboard/**").hasRole("ADMIN")
			.antMatchers("/user-dashboard/**").hasRole("USER")
			.antMatchers("/").hasAnyRole("USER","ADMIN")
			.antMatchers("/admin-dashboard/**").access("hasRole('ROLE_ADMIN')") 
			.antMatchers("/user-dashboard/**").access("hasRole('ROLE_USER')")
	        .anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/security-checking").permitAll()
			.usernameParameter("user_name")
			.passwordParameter("password")
			.successHandler(getAuthentication())
			.and()
			.csrf().disable()
			.exceptionHandling().accessDeniedPage("/access-denied")
			;
		
				
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("$2a$10$Qh8NNMZsjZofS5UfJZI6f.GlU0YoY/zihgqzqnP7vqZI1fYlBbISa")
			.roles("USER").and()
			.withUser("admin")
			.password("$2a$10$qtYepVJkt37mri344oWUSe8XCfiLcYfhuFBUioGa5OfRG1.81RUja")
			.roles("ADMIN");
	}

	@Bean
	public AuthenticationSuccessHandlerImpl getAuthentication() {
		return new AuthenticationSuccessHandlerImpl();

	}
	
}
