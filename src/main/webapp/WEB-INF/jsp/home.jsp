<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Dispaly name -->
<p>
User :- <security:authentication property="principal.username"/>
<br><br>
Role :- <security:authentication property="principal.authorities"/>
</p>
<p>
<a href="${pageContext.request.contextPath}/leaders">Leader Meeting(Only)</a>
</p>
<p>
<a href="${pageContext.request.contextPath}/systems">Leader Meeting(Managers)</a>
</p>


Hello You are login successfully !.
<br>
<form:form action="${pageContext.request.contextPath}/logout" method="post">
<input type="submit" value="Logout">
</form:form>
</body>
</html>