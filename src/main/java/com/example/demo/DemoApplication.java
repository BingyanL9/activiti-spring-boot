package com.example.demo;

import java.sql.SQLException;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootApplication
public class DemoApplication {
  
   @Autowired
   PlatformTransactionManager transactionManager;
   
   @Autowired
   DruidDataSource druidDataSource;

	public static void main(String[] args) {
	    startH2Server();
		SpringApplication.run(DemoApplication.class, args);
	}
	
	 private static void startH2Server() {
	    try {
	      Server h2Server = Server.createTcpServer().start();
	      if (h2Server.isRunning(true)) {
	        System.out.println("H2 server was started and is running.");
	      } else {
	        throw new RuntimeException("Could not start H2 server.");
	      }
	    } catch (SQLException e) {
	      throw new RuntimeException("Failed to start H2 server: ", e);
	    }
	  }
	 
	 @Bean
	  public SpringProcessEngineConfiguration getProcessEngineConfiguration(){
	      SpringProcessEngineConfiguration config =  
	                             new SpringProcessEngineConfiguration();
	      config.setDataSource(druidDataSource);
	      config.setTransactionManager(transactionManager);
	      config.setDatabaseType("h2");

	      config.setDatabaseSchemaUpdate("true");
	      return config;
	  }
	 
	 
}
