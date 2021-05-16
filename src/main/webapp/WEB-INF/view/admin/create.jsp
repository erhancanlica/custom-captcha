<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="command" class="tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <title>Nice admin Template - The Ultimate Multipurpose admin template</title>
    <!-- Custom CSS -->
    <!-- Custom CSS -->
    <link href="/resources/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .error {
            color:red;
        }
    </style>
</head>

<body>
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->

<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <jsp:include page="../topHeader.jsp"></jsp:include>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <jsp:include page="../leftSideBar.jsp"></jsp:include>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">CAPTCHA EKLE</h4>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex align-items-center justify-content-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="#">Anasayfa</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">Captcha Ekle</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->

            <!-- ============================================================== -->
            <!-- ============================================================== -->

            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Table -->
            <!-- ============================================================== -->
            <div class="row">
                <div class="col-12">
                    <div class="card">

                        <form:form action="create"  method="post"  enctype="multipart/form-data">
                            <div class="card-body">
                                <c:if test="${not empty lengthErr}">
                                    <div class="alert alert-danger">
                                        <span>${lengthErr}</span>
                                    </div>
                                </c:if>
                                <div class="row m-auto">
                                    <div class="col-12">
                                        <div class="form-group">
                                            <form:label  class="control-label col-form-label" path="captchaName">Captcha Ismi</form:label>
                                            <form:input type="text" class="form-control" name="captchaName" placeholder="Captcha ismi giriniz" path="captchaName" required="required"/>
                                            <form:errors path="captchaName" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-group">
                                            <form:label class="control-label col-form-label" path="captchaCategory">Kategori</form:label>
                                            <form:input type="text" class="form-control" name="captchaCategory" placeholder="Kategori giriniz" path="captchaCategory" required="required" />
                                            <form:errors path="captchaCategory" cssClass="error" />
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="form-group">
                                            <label class="control-label col-form-label">Resmi Seç</label>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Doğru Resimler</span>
                                                </div>
                                                <div class="custom-file">
                                                    <label for="trueImages" class="custom-file-label" data-navbarbg="images" >Buraya Tıkla!</label>
                                                    <input id="trueImages" type="file" class="custom-file-input" name="trueImages" multiple="multiple">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-12">
                                        <div class="form-group">
                                            <label class="control-label col-form-label">Resmi Seç</label>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Yanlış Resimler</span>
                                                </div>
                                                <div class="custom-file">
                                                    <label for="falseImages" class="custom-file-label" data-navbarbg="images" >Buraya Tıkla!</label>
                                                    <input id="falseImages" type="file" class="custom-file-input" name="falseImages" multiple="multiple">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            <hr>
                            <div class="card-body">
                                <div class="d-flex no-block align-items-center">
                                    <a class="action-form">
                                        <a class="form-group m-b-0 text-center">
                                            <button type="submit" class="btn btn-info waves-effect waves-light">Kaydet</button>
                                            <button type="reset" class="btn btn-dark waves-effect waves-light">İptal</button>
                                        </a>
                                    </a>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- Table -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- footer -->
        <!-- ============================================================== -->
        <jsp:include page="../footer.jsp"></jsp:include>
        <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Wrapper -->

<div class="chat-windows"></div>
<!-- ============================================================== -->
</body>

</html>