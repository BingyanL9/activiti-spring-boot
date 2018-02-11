package com.activiti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.activiti.service.UserDetailedService;

@Configuration
@EnableWebSecurity
@ComponentScan("com.activiti.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserDetailedService userDetailedService;
  
  
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeRequests()
        .anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login")
        .defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll().and().logout()
        .logoutSuccessUrl("/login?logout=true").permitAll();

    http.headers().frameOptions().disable();

    http.headers().cacheControl().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/fonts/**", "/js/**", "/images/**");
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setPasswordEncoder(passwordEncoder());
//    authProvider.setUserDetailsService(userDetailedService);
//    ReflectionSaltSource saltSource = new ReflectionSaltSource();
//    saltSource.setUserPropertyToUse("salt");
//    authProvider.setSaltSource(saltSource);
//    auth.authenticationProvider(authProvider);
      auth.userDetailsService(userDetailedService);
  }

  @Bean
  public Md5PasswordEncoder passwordEncoder() {
    return new Md5PasswordEncoder();
  }
  
  

}
