// 操作评论的js，包括发表评论、获取评论、刷新评论等
$(function () {

    $("#commentBtn").click(function () {

        var content = $("#commentContent").val()
        var uid = "22960758"
        var mid = $("#hiden_movieId").attr("value")

        if (content === '') {
            layer.msg('请输入要发表的内容!', function () {
                //关闭后的操作
                return
            });
            return

        }

        //询问框

        layer.confirm('确认发表这条评论吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            // layer.msg('发表成功！', {icon: 1});

            $.ajax({
                type: 'POST',
                url: "/comment/publishComment?returnUrl="+window.location.href,
                data: JSON.stringify({
                    "mid": $("#hiden_movieId").attr("value"),
                    "content": $("#commentContent").val(),
                }),
                async: false,
                contentType: 'application/json; charset=utf-8',
                success: function (data) {

                    if (data.result === 'SUCCESS') {
                        layer.msg('发表成功！', {icon: 1});

                        //修改电影图片中显示的评论数量
                        // $("#countInPic").text(data.data[0].count)
                        $("#countInPic").text(data.data.count)

                        //修改显示在底部的评论数量
                        $("#countAtBottom").text('共 ' + data.data.count + ' 条数据')

                        //将评论区的内容清空
                        $("#commentContent").val("")

                        var strHtml = "<p class=\"smt_wrap fl clearfix\">\n" +
                            "\t\t\t\t\t\t\t\t<span class=\"total fl\">共<em id=\"item_count\">" + data.data.count + "</em>条评论</span>\n" +
                            "\t\t\t\t\t\t\t</p>"
                        for (var i = 0; i < data.data.commentVOList.length; i++) {
                            strHtml = strHtml + "<div  class=\"cmt_item clearfix\">\n" +
                                "<a class=\"face_wrap fl\" href=\"javascript:;\">\n" +
                                "\t\t<img class=\"face\" src=\"" + data.data.commentVOList[i].face + "\"></a>\n" +
                                "\t<div class=\"item_con fl\">\n" +
                                "\t\t<p class=\"top\">\n" +
                                "\t\t\t<span class=\"fr\" >" + data.data.commentVOList[i].date + "</span>\n" +
                                "\t\t\t<a class=\"name\" href=\"javascript:;\" >" + data.data.commentVOList[i].username + "</a>\n" +
                                "\t\t\t(<a target=\"_blank\">(117.136.124.19)</a>)\n" +
                                "\t\t</p>\n" +
                                "\t\t<p class=\"con\"  >" + data.data.commentVOList[i].content + "</p>\n" +
                                "\t\t<div class=\"gw-action\">\n" +
                                "                <span class=\"click-ding-gw\">\n" +
                                "                    <a class=\"digg_link\" data-id=\"8\" data-mid=\"6\" data-type=\"up\"\n" +
                                "\t\t\t\t\t   href=\"javascript:;\">\n" +
                                "                        <i class=\"icon-ding\"></i>\n" +
                                "                        <em class=\"digg_num icon-num\" >" + data.data.commentVOList[i].likeNum + "</em>\n" +
                                "                    </a>\n" +
                                "                    <a class=\"digg_link\" data-id=\"8\" data-mid=\"6\" data-type=\"down\"\n" +
                                "\t\t\t\t\t   href=\"javascript:;\">\n" +
                                "                        <i class=\"icon-dw\"></i>\n" +
                                "                        <em class=\"digg_num icon-num\" >" + data.data.commentVOList[i].dislikeNum + "</em>\n" +
                                "                    </a>\n" +
                                "                </span>\n" +
                                "\t\t\t<a class=\"comment_reply\" data-id=\"8\" href=\"javascript:;\">回复</a>\n" +
                                "\t\t\t<a class=\"comment_report\" data-id=\"8\" href=\"javascript:;\">举报</a>\n" +
                                "\t\t</div>\n" +
                                "\n" +
                                "\t</div>\n" +
                                "\n" +
                                "</div>"
                        }


                        $("#commentsList").html("")
                        $("#commentsList").html(strHtml)

                        $("#nextCommentPage").attr({"value":data.data.nextPage+""})
                        $("#preCommentPage").attr({"value":data.data.prePage+""})
                        // $("#currentPage").attr({"text":data.data.currentPage+""})
                        $("#currentPage").text(data.data.currentPage+"")
                        $("#totalPage").attr({"value":data.data.totalPage+""})
                        $("#countAtBottom").text("共 "+data.data.count + " 条数据")
                        $("#current_total").text("当前 "+data.data.currentPage+"/"+data.data.totalPage+" 页")

                        window.history.replaceState("", "", "/index/detail/" + mid + ".html?startPage=" + 1)


                    } else if (data.message === 'REDIRECT_LOGIN') {
                        //表示重定向到登录页面
                        window.location.href = "/login.html?returnUrl="+window.location.href
                    }else{
                        layer.msg('发表失败！请稍后重试', {icon: 2});

                    }

                },
                error: function () {
                    layer.msg('发表失败！请稍后重试', {icon: 2});
                },
                dataType: "json",
            })

        }, function () {
            layer.msg('已取消！', {icon: 2});
        });

    })

    $.showComment = function(page){
        var mid = $("#hiden_movieId").attr("value")

        $.ajax({
            type: 'GET',
            url: "/comment/get?mid="+mid + "&startPage="+page,
            data: {},
            async: false,
            contentType: 'application/json; charset=utf-8',
            success: function (data) {

                if (data.result === 'SUCCESS') {
                    // layer.msg('发表成功！', {icon: 1});

                    //修改显示在底部的评论数量
                    $("#countAtBottom").text('共 ' + data.data.count + ' 条数据')


                    var strHtml = "<p class=\"smt_wrap fl clearfix\">\n" +
                        "\t\t\t\t\t\t\t\t<span class=\"total fl\">共<em id=\"item_count\">" + data.data.count + "</em>条评论</span>\n" +
                        "\t\t\t\t\t\t\t</p>"
                    for (var i = 0; i < data.data.commentVOList.length; i++) {
                        strHtml = strHtml + "<div  class=\"cmt_item clearfix\">\n" +
                            "<a class=\"face_wrap fl\" href=\"javascript:;\">\n" +
                            "\t\t<img class=\"face\" src=\"" + data.data.commentVOList[i].face + "\"></a>\n" +
                            "\t<div class=\"item_con fl\">\n" +
                            "\t\t<p class=\"top\">\n" +
                            "\t\t\t<span class=\"fr\" >" + data.data.commentVOList[i].date + "</span>\n" +
                            "\t\t\t<a class=\"name\" href=\"javascript:;\" >" + data.data.commentVOList[i].username + "</a>\n" +
                            "\t\t\t(<a target=\"_blank\">(117.136.124.19)</a>)\n" +
                            "\t\t</p>\n" +
                            "\t\t<p class=\"con\"  >" + data.data.commentVOList[i].content + "</p>\n" +
                            "\t\t<div class=\"gw-action\">\n" +
                            "                <span class=\"click-ding-gw\">\n" +
                            "                    <a class=\"digg_link\" data-id=\"8\" data-mid=\"6\" data-type=\"up\"\n" +
                            "\t\t\t\t\t   href=\"javascript:;\">\n" +
                            "                        <i class=\"icon-ding\"></i>\n" +
                            "                        <em class=\"digg_num icon-num\" >" + data.data.commentVOList[i].likeNum + "</em>\n" +
                            "                    </a>\n" +
                            "                    <a class=\"digg_link\" data-id=\"8\" data-mid=\"6\" data-type=\"down\"\n" +
                            "\t\t\t\t\t   href=\"javascript:;\">\n" +
                            "                        <i class=\"icon-dw\"></i>\n" +
                            "                        <em class=\"digg_num icon-num\" >" + data.data.commentVOList[i].dislikeNum + "</em>\n" +
                            "                    </a>\n" +
                            "                </span>\n" +
                            "\t\t\t<a class=\"comment_reply\" data-id=\"8\" href=\"javascript:;\">回复</a>\n" +
                            "\t\t\t<a class=\"comment_report\" data-id=\"8\" href=\"javascript:;\">举报</a>\n" +
                            "\t\t</div>\n" +
                            "\n" +
                            "\t</div>\n" +
                            "\n" +
                            "</div>"
                    }


                    $("#commentsList").html("")
                    $("#commentsList").html(strHtml)


                    $("#nextCommentPage").attr({"value":data.data.nextPage+""})
                    $("#preCommentPage").attr({"value":data.data.prePage+""})
                    // $("#currentPage").attr({"text":data.data.currentPage+""})
                    $("#currentPage").text(data.data.currentPage+"")
                    $("#totalPage").attr({"value":data.data.totalPage+""})
                    $("#countAtBottom").text("共 "+data.data.count + " 条数据")
                    $("#current_total").text("当前 "+data.data.currentPage+"/"+data.data.totalPage+" 页")

                    window.history.replaceState("", "", "/index/detail/" + mid + ".html?startPage=" + page)

                } else {
                    layer.msg('获取失败！请稍后重试', {icon: 2});
                }

            },
            error: function () {
                layer.msg('获取失败！请稍后重试', {icon: 2});
            },
            dataType: "json",
        })

    }

    $.showComment2 = function (el) {

        var page = el
        var mid = $("#movieUrl").attr("value")

        $.ajax({
            type: 'GET',
            url: "/index/detail/" + mid + ".html?startPage=" + page,
            data: {},
            async: false,
            // contentType:'application/json',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {

                window.history.pushState("", "", "/index/detail/" + mid + ".html?startPage=" + page)
                $(document.body).html(data);

            },
            error: function () {
                alert("获取失败")
            },
            dataType: "html",//返回整合HTML
        })

    }

    $("#gotoPage").click(function () {
        var page = $("#page").val()
        console.log("gotopage:"+page)

        $.showComment(page)
    })

    $("#firstCommentPage").click(function () {
        var page = $("#firstCommentPage").attr("value")
        $.showComment(page)
    })
    $("#nextCommentPage").click(function () {
        var page = $("#nextCommentPage").attr("value")
        $.showComment(page)
    })

    /*$("body").on("click","#nextCommentPage",function () {
        var page = $("#nextCommentPage").attr("value")
        $.showComment(page)
    })*/

    $("#preCommentPage").click(function () {
        var page = $("#preCommentPage").attr("value")
        $.showComment(page)
    })
    $("#lastCommentPage").click(function () {
        var page = $("#lastCommentPage").attr("value")
        $.showComment(page)
    })

});

