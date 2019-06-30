# spring-security-using-DB
1. Tecnology used

=========================================================================

       a.SpringBoot
       
       b.SpringSecurity
       
       c.JSP
   
========================================================================

I will try to make it simple to implement Spring-Security and How 

to used Best prectice in Spring-Security.

========================================================================

Resources(URI)  ----

=========================================================================
 https://localhost:8080
 
                       / (Home Page) 
                       
                       /showMyLoginPage(Custom Login Page) 
                       
                       /authenticateTheUser(this is authenticate Url you will see this in JSP file)
                       



 What is CSRF(Cross-site request forgery)?
 
 ===============================================================================
 
  A security attack where an evil website tricks you into executing an action on a wed application .
  that you are currently logged in.
  
  CSRF protection
  
  ===========================================================================
  
  To protect against CSRF attacks.
  
  Embed  additional authentication data/token into all  HTML forms.(important point)
  
  On sunbsequent request ,web app will verify token before processing.
  
  create table
  
  ===========================================================================
 ```
 CREATE TABLE users( username varchar(50) NOT NULL,
 password varchar(50) NOT NULL, 
enabled tinyint(1) NOT NULL, 
PRIMARY KEY(username) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into users values ('pranav','{noop}test123',1);
 insert into users values ('raghu','{noop}test123',1); 
insert into users values ('aditya','{noop}test123',1);


CREATE TABLE authorities ( username varchar(50) NOT NULL, authority varchar(50) NOT NULL,

UNIQUE KEY authoritiesidx1 (username,authority),

CONSTRAINT authoritiesibfk1 FOREIGN KEY (username) REFERENCES users (username) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into authorities values ('pranav','ROLE_EMPLOYEE'), ('raghu','ROLE_EMPLOYEE'), ('raghu','ROLE_ADMIN'), ('aditya','ROLE_EMPLOYEE'), ('aditya','ROLE_ADMIN'), ('aditya','ROLE_MANAGER');

```



  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
