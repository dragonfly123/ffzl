<%--
  Created by IntelliJ IDEA.
  User: longfei
  Date: 16-3-6
  Time: 下午8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:url value="/j_spring_security_logout"  var="logout"/>

    <li><a href="${logout}">log out</a></li>
    <sec:authorize url="/home/changepassword.do">
    <c:url value="/home/changepassword.do" var="chagepass"/>
    <li><a href="${chagepass}">changepassword</a></li>
    </sec:authorize>
    <p>Please log in  to your Account</p>
    <form action="/login" method="post">
        <label for="j_username">Login</label>:
        <input id="j_username" name="username" size="20" maxlength="50" type="text"/>
        <br/>
        <input  id="_spring_security_remember_me" name="re" type="checkbox" value="true"/>
        <label for="_spring_security_remember_me">remember me</label><br/>
        <label for="j_password">Password</label>:
        <input id="j_password" name="password" size="20" maxlength="50" type="text"/>
        <br/>
        <input type="submit" value="login">
    </form>
</body>

</html>
