
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
                // console.log(data);
                $(".custom-friends-group-list").empty();
                for (var i=0;i<data.data.length;i++){
                    $(".custom-friends-group-list").append('<li class="custom-group-item">\n' +
                        '                                    <h5 class="custom-group">\n' +
                        '                                        <span class="glyphicon glyphicon-play"></span>\n' +
                        '                                        <span class="group_name">'+data.data[i].friendgroupsName+'</span>&nbsp;0/4\n' +
                        '                                    </h5>\n' +
                        '                                    <!--该组下的好友列表-->\n' +
                        '                                    <ul class="custom-friends-list">\n' +
                        '                                    </ul>\n' +
                        '                                </li>');

                }

            }
            // 调用分组列表
            grouplist();
            //调用好友
            listFriend();

        },
        error:function (err) {
            alert("访问错误："+err);
        }

    });
}

//获取好友列表
function listFriend() {
    $.ajax({
        url:"http://localhost:8080/demo/userdeal/listfriends",
        type:"POST",
        data:{},
        async:true,
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if(data.code == 1){
                var grouplist_count =  $(".group_name").length;
                // console.log("好友数量:"+grouplist_count);
                if (data.data == null){
                    return;
                }
                for(var i=0;i<dataLength;i++){
                    for (var j=0;j<grouplist_count;j++){
                        if (data.data[i].customFriendsGroupName == $(".group_name").eq(j).text()){
                            $(".group_name").eq(j).parent().parent().find(".custom-friends-list").append('<li socketaddress="'+data.data[i].customFriendsFriendsId+'" class="row custom-friend-item">\n' +
                                '                                            <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+data.data[i].customFriendsHeadportrait+'">\n' +
                                '\n' +
                                '                                            <dl class="col-md-9 custom-friend-item-info">\n' +
                                '                                                <dt class="list-remarks">'+data.data[i].customFriendsRemark+'</dt>\n' +
                                '                                                <dd class="list-motto">'+data.data[i].customFriendsMotto+'</dd>\n' +
                                '                                                <!--<span class="badge custom-num-tip">5</span>-->\n' +
                                '                                                <span class="badge custom-online-tip">在线</span>\n' +
                                '                                            </dl>\n' +
                                '                                        </li>');
                        }
                    }

                }
            }
            //调用好友名片卡
            friend_card();
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
        var group_name = $(this).find(" span ").eq(0);
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


        var formData = new FormData();
        formData.append("friendUserId",socketaddress);
        $.ajax({
            url:"http://localhost:8080/demo/userdeal/infofriend",
            type:"POST",
            data:formData,
            async:true,
            // cache: false,缓存，get请求有效，true缓存
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.code == 1){
                    console.log(data);
                    $("#headportrait").attr("src",data.data.customFriendsHeadportrait);
                    $("#remarks-top").text(data.data.customFriendsRemark);
                    $("#remarks").text(data.data.customFriendsRemark);
                    $("#motto").text(data.data.customFriendsMotto);
                    $("#custom-send-message-btn").attr("socketaddress",socketaddress);
                    $("#nickname").text(data.data.customFriendsNickName);
                    $("#group").text(data.data.customFriendsGroupName);
                    $("#e-mail").text(data.data.customFriendsEmail);
                    $("#username").text(data.data.customFriendsLoginId);
                }else{
                    alert(data.tip);
                }
            },
            error:function (err) {
                alert("连接错误："+err);
            }

        });


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
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if (data.code == 1){
                // console.log(data);
                $("#head-img").attr("src",data.data.userHeadportrait);

                //将个人信息填充个人模态框
                $("#userinfo_head_img").attr("src",data.data.userHeadportrait);
                $("#userinfo_nickname").val(data.data.userNickname);
                $("#userinfo_motto").text(data.data.userMotto);
                $("#userinfo_loginId").text(data.data.userLoginId);
                $("#userinfo_age").val(data.data.userAge);
                $("#userinfo_birthday").val(data.data.userBirthday);
                $("#userinfo_tel").val(data.data.userTelephone);
                $("#userinfo_email").val(data.data.userEmail);
                $("#userinfo_vocation").val(data.data.userVocation);
                $("#userinfo_synopsis").text(data.data.userSynopsis);
                $("#userinfo_revise_btn").attr("dataId",data.data.userId);
                $("#gender_select").find("option[value="+data.data.userGender+"]").attr("selected",true);
                $("#province_select").find("option[value="+data.data.userProvinceId+"]").attr("selected",true);
                $("#city_select").find("option[value="+data.data.userCityId+"]").attr("selected",true);

            } else{
                alert(data.tip);
            }
        },
        error:function (err) {
            alert("请求错误："+err)
        }
    });
}