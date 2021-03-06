package com.aurrix.phonebook.global.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(
            "**/v2/api-docs",
            "**/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**")
        .permitAll()
        .antMatchers("/**")
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .disable()
        .csrf()
        .disable()
        .httpBasic();
  }
}
