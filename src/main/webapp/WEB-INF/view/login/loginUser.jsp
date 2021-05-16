<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <title>Login</title>
    <!-- Custom CSS -->
    <link href="/resources/css/style.min.css" rel="stylesheet">
    <link href="../../../resources/css/toastr.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .my-image{
            width: 128px;height: 128px;
            overflow: hidden;
            display: inline-block;}
        .selected-image{border-style: solid;border-color: purple; border-width: 5px}
        a{cursor: pointer}

        .hidden{
            display: none!important;
        }


    </style>
</head>

<body>
<div class="main-wrapper">
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->

    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Login box.scss -->
    <!-- ============================================================== -->
    <div class="auth-wrapper d-flex no-block justify-content-center align-items-center">
        <div class="auth-box">
            <div id="loginform">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        <button class="close" data-close="alert"></button>
                        <span>${error}</span>
                    </div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="alert alert-success">
                        <button class="close" data-close="alert"></button>
                        <span>${msg}</span>
                    </div>
                </c:if>
                <c:if test="${not empty captchaErr}">
                    <div class="alert alert-danger">
                        <button class="close" data-close="alert"></button>
                        <span>${captchaErr}</span>
                    </div>
                </c:if>
                <!-- Form -->
                <div class="row">
                    <div class="col-12">
                        <form class="form-horizontal m-t-20" action="/userLogin" method='POST'>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1"><i class="ti-user"></i></span>
                                </div>
                                <input type="text" class="form-control form-control-lg" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" name='username' value=''>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon2"><i class="ti-pencil"></i></span>
                                </div>
                                <input type="password" class="form-control form-control-lg" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" name='password'>
                            </div>

                            <div id="openCaptchaDiv" class="form-group text-center">
                                <div class="col-xs-12 p-b-20">
                                    <button id="openCaptcha" type="button" class="btn btn-primary float-left mb-3">
                                        Doğrula
                                    </button>
                                </div>
                            </div>
                            <div id="validDiv" class="form-group text-center hidden">
                                <div class="col-xs-12 p-b-20">
                                    <p class="alert alert-success">Giriş Yapabilirsiniz</p>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <div class="col-xs-12 p-b-20">
                                    <input id="submit" class="btn btn-block btn-lg btn-info" type="submit" value="Giriş"/>
                                </div>
                                <br>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="captchaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width: 420px;padding-right: 10px">
                <div class="modal-header d-block " >
                    <button type="button" class="close " data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <div style="width: 360px ;padding-left: 5px;padding-top: 5px; " >
                        <h2 class="modal-title text-capitalize text-primary" id="exampleModalLongTitle">${captcha.captchaCategory}</h2>
                        <div><h4>içeren fotoğrafları seçiniz.</h4></div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row">
                    <c:forEach items="${captcha.imageWrapper}" var="image" varStatus="imageStatus">
                        <div class="col-lg-4 col-md-4 col-4 mt-1">
                            <a id="${image.id}" class="my-image">
                                <img src="data:image/jpeg;base64,${image.base}" width="100%" height="100%"/>
                            </a>
                        </div>
                    </c:forEach>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Kapat</button>
                    <button id="validate" type="button" class="btn btn-primary">Doğrula</button>
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- Login box.scss -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper scss in scafholding.scss -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper scss in scafholding.scss -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Right Sidebar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Right Sidebar -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- All Required js -->
<!-- ============================================================== -->
<script src="/resources/js/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/toastr.js"></script>

<script>
    var selectedImages= [];
    // $(function () {
    //     var token = $("meta[name='_csrf']").attr("content");
    //     var header = $("meta[name='_csrf_header']").attr("content");
    //     $(document).ajaxSend(function(e, xhr, options) {
    //         xhr.setRequestHeader(header, token);
    //     });
    // });
    $(document).ready(function() {
        $("#openCaptcha").click(function () {
            selectedImages = [];
            $('#captchaModal').modal('show');
        })

        $(".my-image").click(function () {
            var captcha = {}
            var index = getIndex(+this.id);
            if (index > -1) {
                selectedImages.splice(index, 1);
                $(this).removeClass("selected-image");
            } else {
                captcha["id"] = +this.id;
                captcha["captchaId"] = +"${captcha.captchaId}";
                selectedImages.push(captcha);
                $(this).addClass("selected-image");
            }
            console.log(selectedImages);
        })

        function getIndex(id){
            var index = selectedImages.findIndex(function(element){
                return element.id === id;
            });
            return index;
        }

        $("#validate").click(function () {
            console.log(selectedImages);
            $.ajax({
                type: "post",
                traditional : true,
                dataType: "json",
                url: "validate",
                data: JSON.stringify(selectedImages),
                contentType: "application/json;charset=UTF-8",

                success: function(response) {
                    if(response.result == 0){
                        toastr.success(response.message)
                        $('#captchaModal').modal('toggle');
                        $('#openCaptchaDiv').html("");
                        $('#validDiv').removeClass("hidden")
                    }
                    else {
                        toastr.error(response.message)
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    toastr.error("Bilinmeyen Bir Hata oluştu")
                }
            })
        })
    })

</script>
</body>

</html>
