<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h5>True Images</h5>
<c:forEach items="${captcha.imageWrapper}" var="image" varStatus="imageStatus">
    <c:if test="${image.isValid() == true}" >
        <div class="col-lg-6 col-md-6 col-6">
            <a id="${image.id}" class="my-image">
                <img src="data:image/jpeg;base64,${image.base}" width="50px" height="50px"/>
            </a>
        </div>
    </c:if>
</c:forEach>

<hr>

<h5>False Images</h5>
<c:forEach items="${captcha.imageWrapper}" var="image" varStatus="imageStatus">
    <c:if test="${image.isValid() == false}" >
        <div class="col-lg-6 col-md-6 col-6">
            <a id="${image.id}" class="my-image">
                <img src="data:image/jpeg;base64,${image.base}" width="50px" height="50px"/>
            </a>
        </div>
    </c:if>
</c:forEach>


