package com.atyeti.StockApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.atyeti.StockApplication.serviceImpl.UserSecurityService;

@Configuration
@EnableWebSecurity 
@EnableWebMvc

public class WebConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private  UserSecurityService userSecurityService;
	
	@Bean
	 public BCryptPasswordEncoder passwordEncoder() {
		 BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		 return encoder;
	 }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	
	
	http.csrf().disable().cors().disable().formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
	.loginPage("/login").permitAll().and()
	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll().and().rememberMe();
	http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

	}
	
	
	 public static final String[] PUBLIC_MATCHERS= {
			 "/css/**",	 "/css/main.css",
			 "/js/**",
			 "/img/**",
			 "/",
			 "/login",
			 "/register",
			 "/fonts/**",
			 "/register",
			 "/newUser"
	 };
	
	 
	
	 

}
