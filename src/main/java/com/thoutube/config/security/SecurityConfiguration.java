package com.thoutube.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AuthenticationService authService;

    // Authentication configurations
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Authorization configurations
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/videos").permitAll()
                                .antMatchers(HttpMethod.GET, "/videos/*").permitAll()
                                .antMatchers(HttpMethod.GET, "/posts").permitAll()
                                .antMatchers(HttpMethod.GET, "/posts/*").permitAll()
                                .anyRequest().authenticated()
                                .and().formLogin();
    }

    // Static resources configurations
    @Override
    public void configure(WebSecurity web) throws Exception {
        
    }
}