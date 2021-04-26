<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionInfo.user.userType eq 'ADMIN'}">
<aside class="left-sidebar">
    <!-- Sidebar scroll-->
    <div class="scroll-sidebar">
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav">
            <ul id="sidebarnav">
                <li class="nav-small-cap">
                    <i class="mdi mdi-dots-horizontal"></i>
                    <span class="hide-menu">Captcha</span>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false">
                        <i class="mdi mdi-tune"></i>
                        <span class="hide-menu">Admin Paneli</span>
                    </a>
                    <ul aria-expanded="false" class="collapse  first-level">
                        <li class="sidebar-item">
                            <a href="<c:url value="/admin/create"/>" class="sidebar-link">
                                <i class="mdi mdi-view-quilt"></i>
                                <span class="hide-menu">Captcha Ekle </span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a href="<c:url value="/admin/list"/>" class="sidebar-link">
                                <i class="mdi mdi-view-quilt"></i>
                                <span class="hide-menu">Captcha Liste</span>
                            </a>
                        </li>
                    </ul>


            </ul>
        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</aside>
</c:if>