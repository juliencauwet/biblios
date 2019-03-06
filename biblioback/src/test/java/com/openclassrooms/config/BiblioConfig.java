package com.openclassrooms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


//@Configuration
//@EnableJpaRepositories(basePackages = "com.openclassrooms")
//@PropertySource("application-test.properties")
//@EnableTransactionManagement
public class BiblioConfig {

  //  @Autowired
  //  private Environment env;
//
  //  @Bean
  //  public DataSource dataSource() {
  //      DriverManagerDataSource dataSource = new DriverManagerDataSource();
  //      dataSource.setDriverClassName(env.getProperty("org.h2.Driver"));
  //      dataSource.setUrl(env.getProperty("jdbc:h2:~/test"));
  //      dataSource.setUsername(env.getProperty("sa"));
  //      dataSource.setPassword(env.getProperty(""));
//
  //      return dataSource;
  //  }
}
