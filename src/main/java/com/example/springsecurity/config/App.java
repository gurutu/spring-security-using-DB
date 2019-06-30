//package com.example.springsecurity.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
//import lombok.SneakyThrows;
//import lombok.extern.log4j.Log4j2;
//
//@Configuration
//@PropertySource("classpath:persistence-mysql.properties")
//@Log4j2
//public class App{
//	
//	@Autowired Environment env;
//	
//	
////	@Bean
////	public DataSourceBuilder getDataSource() {
////		 DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
////	     dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
////	     dataSourceBuilder.url("jdbc:mysql://localhost:3306/spring_security_demo?useSSL=false");
////	     dataSourceBuilder.username("root");
////	     dataSourceBuilder.password("root");
////	     dataSourceBuilder.build();
////	     return DataSourceBuilder.create().build();
////	    }
//	
////	@Bean
////	public DataSource getDataSource() {
////		
////		        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
////		        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
////		        dataSourceBuilder.url("jdbc:mysql://localhost:3306/spring_security_demo?useSSL=false");
////		        dataSourceBuilder.username("root");
////		        dataSourceBuilder.password("root");
////		        return dataSourceBuilder.build();
//		    
////		ComboPooledDataSource data=new ComboPooledDataSource();
////		log.info("My JDBC DRIVER"+env.getProperty("jdbc.driver"));
////		log.info("My JDBC DRIVER "+env.getProperty("jdbc.url"));	
////		log.info("My JDBC DRIVER "+env.getProperty("jdbc.user"));
////		log.info("My JDBC DRIVER "+env.getProperty("jdbc.password"));
////		data.setDriverClass(env.getProperty("jdbc.driver"));
////		data.setJdbcUrl(env.getProperty("jdbc.url"));
////		data.setUser(env.getProperty("jdbc.user"));
////		data.setPassword(env.getProperty("jdbc.password"));
//		
//		
//		//log.info(" Thhis is my connection ",data.getConnection());
//		
//		
////    	data.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
////		data.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
////		data.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
////		data.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
//		//return DataSourceBuilder.create().build();
//		   //     }
//	
//	
//}
