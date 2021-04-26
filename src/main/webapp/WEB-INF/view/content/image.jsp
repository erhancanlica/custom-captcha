<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <style>
        .my-image{
            width: 128px;height: 128px;
            overflow: hidden;
            display: inline-block;}
        a{cursor: pointer}

        .container {
            position: center;
            width: 100%;
            max-width: 130px;
        }

        .image {
            display: block;
            width: 100%;
            height: auto;
        }

        .overlay {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            height: 100%;
            width: 110%;
            opacity: 0;
            transition: .3s ease;
            background-color: white;
        }

        .container:hover .overlay {
            opacity: 1;
        }

        .icon {
            color: white;
            font-size: 100px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            text-align: center;
        }

        .fa-upload:hover {
            color:#3b7cfb ;
        }

        #html_btn {
            display: none;
        }
    </style>
</head>

<body>
    <input id="html_btn" type='file' />
    <div class="row">
        <c:forEach items="${captcha.imageWrapper}" var="image" varStatus="imageStatus">
            <c:if test="${image.isValid() == true}">
                <div class="col-lg4 col-md-4 col-4 mt-1 container">
                    <a id="${image.id}" class="my-image">
                        <img src="data:image/jpeg;base64,${image.base}" width="100%" height="100%" class="image"/>
                        <div class="overlay" data-id = "${image.id}" data-valid = "${image.isValid()}">
                            <a href="#" class="icon" title="User Profile">
                                <i class="fa fa-upload"></i>
                            </a>
                        </div>
                    </a>
                </div>
            </c:if>
        </c:forEach>

        <hr>
        <c:forEach items="${captcha.imageWrapper}" var="image" varStatus="imageStatus">
            <c:if test="${image.isValid() == false}">
                <div class="col-lg4 col-md-4 col-4 mt-1 container">
                    <a id="${image.id}" class="my-image">
                        <img src="data:image/jpeg;base64,${image.base}" width="100%" height="100%" class="image"/>
                        <div class="overlay" data-id = "${image.id}" data-valid = "${image.isValid()}">
                            <a href="#" class="icon" title="User Profile">
                                <i class="fa fa-upload"></i>
                            </a>
                        </div>
                    </a>
                </div>
            </c:if>
        </c:forEach>
    </div>
</body>


<script>

    $(".overlay").click(function (){
        var id  = $(this).data('id');
        var valid = $(this).data('valid')

        $('#html_btn').data('id', id).data("valid", valid);
        $('#html_btn').click();

    });

    $("input:file#html_btn").change(function(e){
        var id = +$(this).data('id')
        var valid = $(this).data('valid')

        var image = {
            id: id,
            captchaId: "${captcha.captchaId}",
            valid: valid,
        }

        var formData = new FormData();
        formData.append("file", $('input[type=file]')[0].files[0])
        formData.append("image", JSON.stringify(image))

        $.ajax({
            type: "put",
            url: "merge",
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            data: formData,


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

            error: function (jqXHR, textStatus, errorThrown){
                toastr.error("Bilinmeyen Bir Hata Oluştu")
            }
        });
    });
</script>


