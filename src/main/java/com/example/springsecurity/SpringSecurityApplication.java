package com.example.springsecurity;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}

@Configuration
@EnableWebSecurity
class DemoConfigration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);

		auth.inMemoryAuthentication()
		     .withUser("pranav").password("{noop}test123").roles("EMPLOYEE","ADMIN");
		auth.inMemoryAuthentication()
		     .withUser("pranav12").password("{noop}test123").roles("EMPLOYEE");
		auth.inMemoryAuthentication()
	         .withUser("raghu").password("{noop}test123").roles("MANAGER");

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
