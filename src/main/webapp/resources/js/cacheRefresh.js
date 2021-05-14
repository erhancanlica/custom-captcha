$("#cache").click(function (){
    $.ajax({
        type: "post",
        url: "cache/refresh",
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.result == 0) {
                toastr.success(response.message)
            } else {
                toastr.error(response.message)
            }
        },

        error: function (jqXHR, textStatus, errorThrown){
            toastr.error("Bilinmeyen Bir Hata Oluştu")
        }
    });
});