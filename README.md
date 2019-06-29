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
  
  
  
  
