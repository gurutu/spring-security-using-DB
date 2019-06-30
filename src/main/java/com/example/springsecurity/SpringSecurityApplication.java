package com.example.springsecurity;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}

//@Configuration
//@PropertySource("classpath:persistence-mysql.properties")
//@Log4j2
//class DataConfigFile {
//	@Autowired
//	Environment env;
//
//	@SneakyThrows
//	@Bean
//	public DataSource getDataSource() {
//
//		ComboPooledDataSource data = new ComboPooledDataSource();
//		log.info("My JDBC DRIVER" + env.getProperty("jdbc.driver"));
//		log.info("My JDBC DRIVER " + env.getProperty("jdbc.url"));
//		log.info("My JDBC DRIVER " + env.getProperty("jdbc.user"));
//		log.info("My JDBC DRIVER " + env.getProperty("jdbc.password"));
//		data.setDriverClass(env.getProperty("jdbc.driver"));
//		data.setJdbcUrl(env.getProperty("jdbc.url"));
//		data.setUser(env.getProperty("jdbc.user"));
//		data.setPassword(env.getProperty("jdbc.password"));
//
//		//log.info(" Thhis is my connection ", data.getConnection());
//
//		data.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
//		data.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
//		data.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
//		data.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
//		return data;
//	}
//}


@Configuration
//@EnableAutoConfiguration(exclude={DataSourceConfig.class})
class DataSourceConfig {


	@Bean
	public DataSource getDataSource() {
		
		        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
		        dataSourceBuilder.url("jdbc:mysql://localhost:3306/spring_security_demo?useSSL=false");
		        dataSourceBuilder.username("root");
		        dataSourceBuilder.password("root");
		        return dataSourceBuilder.build();
	}
}


@Configuration
@EnableWebSecurity
class DemoConfigration extends WebSecurityConfigurerAdapter {
	
	@Autowired DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
        auth.jdbcAuthentication().dataSource(dataSource);
		//auth.
	//	inMemoryAuthentication()
		//     .withUser("pranav").password("{noop}test123").roles("EMPLOYEE","ADMIN");
//		auth.inMemoryAuthentication()
//		     .withUser("pranav12").password("{noop}test123").roles("EMPLOYEE");
//		auth.inMemoryAuthentication()
//	         .withUser("raghu").password("{noop}test123").roles("MANAGER");

//		UserBuilder user=User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication()
//		.withUser(user.username("pranav").password("test123").roles("EMPLOYEE"))
//		//auth.inMemoryAuthentication()
//		.withUser(user.username("pranav12").password("test123").roles("MANAGER"))
//		//auth.inMemoryAuthentication()
//		.withUser(user.username("pranav123").password("test123").roles("ADMIN"));
}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		     .antMatchers("/").hasRole("EMPLOYEE")
		     .antMatchers("/leaders/**").hasRole("MANAGER")
		     .antMatchers("/systems/**").hasRole("ADMIN")
		    // .anyRequest().authenticated() if you do like that than every request will go through login page.
		     .and()
			     .formLogin()
			      .loginPage("/showMyLoginPage")
			      .loginProcessingUrl("/authenticateTheUser")
			      .permitAll()
			  .and()
			       .logout().permitAll()
			   .and()
			   .exceptionHandling().accessDeniedPage("/access-denied")
			   .and()
			    .exceptionHandling().accessDeniedPage("/404")
			  .and()
			       .csrf().disable();
			  
			       
		    //hello
		//Hello
	}
}

class SecurityWebApplicationInitializatiolizer extends AbstractSecurityWebApplicationInitializer{
	
}

@Controller
class DemoController implements ErrorController{
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/404")
	public String showNotFound() {
		return "home";
	}
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "plain-login";
	}
	
	@GetMapping("/leaders")
	public String leaders() {
		return "managers";
	}
	
	@GetMapping("/systems")
	public String systems() {
		return "systems";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied-page";
	}
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	     
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error-500";
	        }
	    }
	    return "error";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	
	
}


@Controller
class WelComeController{
	
	  @RequestMapping(value="/welcome", method = RequestMethod.GET)
	    public String showLoginPage(ModelMap model){
	        return "welcome";
	    }
	
}
