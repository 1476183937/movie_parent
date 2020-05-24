$(function () {


    $("body").on("click","#btn2",function () {
        var value = $("#testVal").attr("value");
        var url = "/test1?val="+value;
        $.ajax({
            url: url,
            type: "GET",
            data: {},
            success: function (data) {
                alert("成功了22")
                console.log(data)
                // $("#outer").html(data);
                // $("#outer").text = ""
                $("#outer").html("")
                $("#outer").html(data)

            }
        })
    })

    $("#btn1").click(function () {
        // alert("111")
        var value = $("#testVal").attr("value");
        console.log("1: "+value)
        console.log("2: "+$("#testVal").val())
        var url = "/test1?val="+value;
        $.ajax({
            url: url,
            type: "GET",
            data: {},
            success: function (data) {
                alert("成功了")
                console.log(data)
                // $("#outer").html(data);
                // $("#outer").text = ""
                $("#outer").html("")
                $("#outer").html("<div>"+data+"</div>")
                // $.getScript("/js/jquery.js")

            }
        })
    })

})