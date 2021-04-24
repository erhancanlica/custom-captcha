<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${captcha.imageWrapper}" var="image" varStatus="imageStatus">
    <div class="col-lg-6 col-md-6 col-6">
        <a id="${image.id}" class="my-image">
            <img src="data:image/jpeg;base64,${image.base}" width="50px" height="50px"/>
        </a>
    </div>
</c:forEach>
