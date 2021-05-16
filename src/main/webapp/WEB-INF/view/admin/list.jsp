<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/images/favicon.png">
    <title>Nice admin Template - The Ultimate Multipurpose admin template</title>
    <!-- Custom CSS -->
    <!-- Custom CSS -->
    <link href="../../../resources/css/style.min.css" rel="stylesheet">
    <link href="../../../resources/css/toastr.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.24/af-2.3.5/b-1.7.0/b-colvis-1.7.0/b-html5-1.7.0/b-print-1.7.0/cr-1.5.3/date-1.0.3/fc-3.3.2/fh-3.1.8/kt-2.6.1/r-2.2.7/rg-1.1.2/rr-1.2.7/sc-2.0.3/sb-1.0.1/sp-1.2.2/sl-1.3.3/datatables.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">

    <![endif]-->



</head>

<body>
    <div id="main-wrapper">

    <jsp:include page="../topHeader.jsp"></jsp:include>

    <jsp:include page="../leftSideBar.jsp"></jsp:include>

        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Captcha Listele</h4>
                    </div>
                    <div class="col-7 align-self-center">
                        <div class="d-flex align-items-center justify-content-end">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item">
                                        <a href="#">Anasayfa</a>
                                    </li>
                                    <li class="breadcrumb-item active" aria-current="page">Captcha Listele</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <!-- File export -->
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title"></h4>
                            <h6 class="card-subtitle"></h6>
                            <div class="table-responsive">
                                <table id="file_export" class="table table-striped table-bordered display">
                                    <thead>
                                    <tr>
                                        <th>Captcha Adı</th>
                                        <th>Kategori</th>
                                        <th>Statü</th>
                                        <th>Resimler</th>
                                        <th>Sil</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="capt" items="${captchas}">
                                            <tr>
                                                <td><span class="label label-custom">${capt.captchaName}</span></td>
                                                <td><span class="label label-custom">${capt.captchaCategory}</span></td>
                                                <td>
                                                <c:choose>
                                                    <c:when test="${capt.status}">
                                                        <span>Aktif</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span>(Pasif) 9 fotoğraf bulunmalı</span>
                                                    </c:otherwise>
                                                </c:choose>
                                                </td>
                                                <td>
                                                    <a class="update" data-id="${capt.captchaId}">
                                                        <i class="fas fa-search" data-toggle="tooltip"
                                                           data-placement="bottom" title="Göster"></i>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a class="delete" data-id="${capt.captchaId}">
                                                        <i class="fas fa-trash" data-toggle="tooltip"
                                                           data-placement="bottom" title="Sil"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                         </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="../footer.jsp"></jsp:include>
        </div>

            <div class="modal fade" id="delete" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">

                            <h5 class="modal-title">Kaydı silmek üzeresiniz!</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="modal-body">
                            Silmek istediğinize emin misiniz ?
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn waves-effect waves-light btn-danger" id="btnDelete">Evet</button>
                            <button type="reset" class="btn waves-effect waves-light btn-inf" data-dismiss="modal">Hayır</button>
                        </div>

                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="captchaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" style="width: 420px; padding-right: 10px">
                        <div  class="modal-header">
                            <div style="width: 360px ;padding-left: 5px;padding-top: 5px; " >
                                <div><h4>Değiştirmek istediğiniz fotoğrafa tıklayın.</h4></div>
                            </div>
                        </div>
                        <div id="captchaDiv" class="modal-body">
                                <jsp:include page="../content/image.jsp" />
                        </div>
                    </div>
               </div>
           </div>
        </div>

        <script>
            $(document).ready(function() {
                $(".update").click(function (e) {

                    e.preventDefault();
                    $("#captchaDiv").html("");
                    var id = $(this).data('id')

                    $.ajax({
                        type: "post",
                        url: "/admin/findById/"+id,

                        success: function (response){
                           console.log(response)
                            $("#captchaDiv").html(response);
                            $('#captchaModal').modal('show');
                        },

                        error: function (jqXHR, textStatus, errorThrown){
                            toastr.error("Bilinmeyen Bir Hata Oluştu")
                        }
                        });
                })

                $(".delete").click(function () {
                    $('#delete').data('id', $(this).data('id')).modal('show');
                })

                $("#btnDelete").click(function (e) {

                    var id = $('#delete').data('id')
                    console.log(id);

                    $.ajax({
                        type: "delete",
                        url: "/admin/delete/"+id,

                        success: function (response) {
                            if (response.result == 0) {
                                toastr.success(response.message)
                                setTimeout(function () {
                                    location.reload()
                                }, 500)
                            } else {
                                toastr.error(response.message)
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            toastr.error("Bilinmeyen Bir Hata Oluştu")
                        }
                    });
                })

            });
        </script>
</body>
</html>

