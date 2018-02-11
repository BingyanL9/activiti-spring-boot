package com.activiti.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
  
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
        .addResourceLocations("classpath:/static/js/");
    registry.addResourceHandler("/css/**").setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
        .addResourceLocations("classpath:/static/css/");
    registry.addResourceHandler("/fonts/**").setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
        .addResourceLocations("classpath:/static/fonts/");
    registry.addResourceHandler("/images/**").setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
        .addResourceLocations("classpath:/static/images/");
  }

  
  

}
