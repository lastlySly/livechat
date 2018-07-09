
$(function () {
    grouplist();
    friend_card();
    del_chat_item_btn();
    change_tab();
    change_chatting_friend();
    change_meau();
    //登陆成功渲染用户信息
    getUserInfo();
    //获取分组并渲染
    getGroupFun()

    $('#edit').froalaEditor({
        toolbarButtons: [
            'insertImage','insertFile','emoticons','insertVideo','fontSize', 'html', 'specialCharacters', 'undo', 'redo'
        ],
        language: 'zh_cn',
        height: 105,
        imageUpload: true,
        imageUploadMethod :'POST',
        imageUploadURL :'/lastlySly/adminController/uploadImgEditor.action',
        imageManagerDeleteURL :'/lastlySly/adminController/deleteImg.action'
    });


})

/*获取分组并渲染*/
function getGroupFun() {
    $.ajax({
        url:"http://localhost:8080/demo/userdeal/listgroup",
        type:"POST",
        data:{},
        async:true,
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if (data.code == 1){
                console.log(data);
                $(".custom-friends-group-list").empty();
                for (var i=0;i<data.data.length;i++){
                    $(".custom-friends-group-list").append('<li class="custom-group-item">\n' +
                        '                                    <h5 class="custom-group">\n' +
                        '                                        <span class="glyphicon glyphicon-play"></span>\n' +
                        '                                        '+data.data[i].friendgroupsName+'&nbsp;0/4\n' +
                        '                                    </h5>\n' +
                        '                                    <!--该组下的好友列表-->\n' +
                        '                                    <ul class="custom-friends-list">\n' +
                        '                                    </ul>\n' +
                        '                                </li>');

                }

            }
            grouplist();
        },
        error:function (err) {
            alert("访问错误："+err);
        }

    });
}

//分组下拉
function grouplist() {
    var grouplist_num =  $(".custom-group-item").length;
    // alert(grouplist_num);
    console.log("有" + grouplist_num + "个分组");
    $(".custom-group").on("click",function () {

        $(this).parent().find(".custom-friends-list").slideToggle("fast");
        // var group_name = $(this).find(".custom-group > span").attr("class");
        // console.log(group_name)
        var group_name = $(this).find(" span ");
        if (group_name.is(".glyphicon-play")) {
           group_name.removeClass("glyphicon-play");
           group_name.addClass("glyphicon-triangle-bottom");
        }else{
            group_name.addClass("glyphicon-play");
            group_name.removeClass("glyphicon-triangle-bottom");
        }

    })
}
//好友信息卡
function friend_card() {
    $(".custom-friend-item").on("click",function () {
        var flag =  $(".right-main-container").css('display');
        if ( flag == "none" ) {
            $(".right-main-container").slideToggle("fast");
        }
        var socketaddress = $(this).attr("socketaddress");
        var remarks = $(this).find(".list-remarks").text();
        var motto = $(this).find(".list-motto").text();
        var headportrait = $(this).find(".list-headportrait").attr("src");
        $("#headportrait").attr("src",headportrait);
        $("#remarks-top").text(remarks);
        $("#remarks").text(remarks);
        $("#motto").text(motto);
        $("#custom-send-message-btn").attr("socketaddress",socketaddress);

    })
}

/*删除正在聊天列表的某项*/
function del_chat_item_btn() {
    $(".custom-del").on("click",function () {
        $(this).parent().parent().remove();
    })
}

//切换底部页面
function change_tab() {


    $("#custom-chat-btn").on("click",function () {
        //默认 color: #C3C3C3;
        $(this).css("color","#FFFFFF");
        $("#custom-linkman-btn").css("color","#C3C3C3");

        // $("#custom-linkman-list-id").css("display","none");
        // $("#custom-chat-list-id").css("display","block");
        $("#custom-linkman-list-id").hide(500);
        $("#custom-chat-list-id").show(500);
    });

    $("#custom-linkman-btn").on("click",function () {
        $(this).css("color","#FFFFFF");
        $("#custom-chat-btn").css("color","#C3C3C3");
        // $("#custom-chat-list-id").css("display","none");
        // $("#custom-linkman-list-id").css("display","block");
        $("#custom-linkman-list-id").show(500);
        $("#custom-chat-list-id").hide(500);
    });
}


//正在聊天切换
function change_chatting_friend() {
    $(".custom-chat-friend-item").on("click",function () {
        var remarks = $(this).find(".list-remarks").text();
        var socketaddress = $(this).attr("data-id");
        change_friend_chatting_fun(socketaddress,remarks);

    })
}

//封装正在聊天页的切换
function change_friend_chatting_fun(socketaddress,remarks) {
    var flag =  $("#right-chat-friend-container-id").css('display');
    if ( flag == "none" ) {
        $("#right-chat-friend-container-id").slideToggle("fast");
    }

    //清空聊天框
    $("#custom-messages-ul").empty();
    //清空输入框
    $("#edit").froalaEditor('html.set', '');

    $("#send-to-btn").attr("socketaddress",socketaddress);
    // alert($("#chatting-friend-remarks").text())
    $("#chatting-friend-remarks").text(remarks);

}

/*调出菜单*/
function change_meau() {
    /*头像菜单*/
    $("#head-nav").on("click",function () {
        $(".custom-head-meau").slideToggle("fast");
    })
    /*添加好友菜单*/
    $("#custom-add-btn").on("click",function () {
        $(".custom-add-meau").slideToggle("fast");
    })

}

/*获取用户信息*/
function getUserInfo(){
    $.ajax({
        url:"http://localhost:8080/demo/userdeal/myuserinfo",
        type:"POST",
        data:{},
        async:true,

        // xhrFields: {
        //     withCredentials: true
        // },
        success:function (data) {
            if (data.code == 1){
                console.log(data);
                $("#head-img").attr("src",data.data.userHeadportrait)
            } else{
                alert(data.tip);
            }
        },
        error:function (err) {
            alert("请求错误："+err)
        }
    });
}