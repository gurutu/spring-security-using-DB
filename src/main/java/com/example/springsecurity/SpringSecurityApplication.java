package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
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
			     .withUser("pranav").password("{noop}test123").roles("ADMIN");
			auth.inMemoryAuthentication()
			     .withUser("pranav12").password("{noop}test1234").roles("EMPLOYEE");

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
		     .anyRequest().authenticated()
		     .and()
			     .formLogin()
			      .loginPage("/showMyLoginPage")
			      .loginProcessingUrl("/authenticateTheUser")
			      .permitAll()
			  .and()
			       .logout().permitAll()
			  .and()
			       .csrf().disable();
		    //hello
		
	}
}

class SecurityWebApplicationInitializatiolizer extends AbstractSecurityWebApplicationInitializer{
	
}

@Controller
class DemoController{
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "plain-login";
	}
	
}


@Controller
class WelComeController{
	
	  @RequestMapping(value="/welcome", method = RequestMethod.GET)
	    public String showLoginPage(ModelMap model){
	        return "welcome";
	    }
	
}
