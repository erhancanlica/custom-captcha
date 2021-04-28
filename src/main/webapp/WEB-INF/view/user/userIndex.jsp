<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Hoş geldin ${user.username}</h1>
<br>
<a  href="<c:url value="/user/logout"/>">
    <i class="fa fa-power-off m-r-5 m-l-5"></i> Cikis
</a>
</body>
</html>
