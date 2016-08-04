/**
 * Created by leacher on 16-8-2.
 */
function resh(){
    $.ajax({
        type:"post",
        url:"/admin/",
        dataType:"text",
        success: function (data) {
            $("#time").text(data);
        }
    });
}

$(document).ready(function () {
    setInterval("resh()",1000);
});

