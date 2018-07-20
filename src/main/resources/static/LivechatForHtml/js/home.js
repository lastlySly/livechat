var stompClient = null;
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
    getGroupFun();


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
        url:serverUrl+"/userdeal/listgroup",
        type:"POST",
        data:{},
        async:true,
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if (data.code == 1){
                $(".custom-friends-group-list").empty();
                for (var i=0;i<data.data.length;i++){
                    $(".custom-friends-group-list").append('<li class="custom-group-item">\n' +
                        '                                    <h5 class="custom-group">\n' +
                        '                                        <span class="glyphicon glyphicon-play"></span>\n' +
                        '                                        <span groupId="'+data.data[i].friendgroupsId+'" class="group_name">'+
                        data.data[i].friendgroupsName+'</span>&nbsp;<span class="online_num">0</span>/<span class="group_all_num">0</span>\n' +
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
            console.log("访问错误："+err);
        }

    });
}

//获取好友列表，在线离线分类，该方法必须在获取完分组后调用
function listFriend() {
    //查询在线好友
    $.post(serverUrl+"/userdeal/listonlinefriend",{},function (data) {
        if(data.code == 1){
            var onlineArray = new Array();
            if(data.data != null){
                var onlineLength = data.data.length;
                for (var i = 0; i<onlineLength; i++){
                    // console.log("在线好友==" + data.data[i].friendsFriendLoginid);
                    onlineArray.push(data.data[i].friendsFriendLoginid);
                }
            }
            //查询全部好友
            $.ajax({
                url:serverUrl+"/userdeal/listfriends",
                type:"POST",
                data:{},
                async:true,
                // cache: false,缓存，get请求有效，true缓存
                contentType: false,
                processData: false,
                success:function (data) {

                    if(data.code == 1){
                        $(".custom-friends-list").empty();
                        $(".custom-group-item").find(".group_all_num").text("0");
                        $(".custom-group-item").find(".online_num").text("0");
                        var grouplist_count =  $(".group_name").length;
                        if (data.data == null){
                            return;
                        }
                        var dataLength = data.data.length;
                            for (var j=0;j<grouplist_count;j++){
                                for(var i=0;i<dataLength;i++){
                                    if (data.data[i].customFriendsGroupName == $(".group_name").eq(j).text()){
                                        //判断好友是否在线
                                        var isOnline =$.inArray(data.data[i].customFriendsLoginId,onlineArray);
                                        switch (isOnline){
                                            case -1:
                                                $(".group_name").eq(j).parent().parent().find(".custom-friends-list").append('<li socketaddress="'+data.data[i].customFriendsLoginId+'" class="row custom-friend-item">\n' +
                                                    '                                            <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+data.data[i].customFriendsHeadportrait+'">\n' +
                                                    '\n' +
                                                    '                                            <dl class="col-md-9 custom-friend-item-info">\n' +
                                                    '                                                <dt class="list-remarks">'+data.data[i].customFriendsRemark+'<span>('+data.data[i].customFriendsLoginId+')</span></dt>\n' +
                                                    '                                                <dd class="list-motto">'+data.data[i].customFriendsMotto+'</dd>\n' +
                                                    '                                                <!--<span class="badge custom-num-tip">5</span>-->\n' +
                                                    '                                                <span class="badge custom-online-tip">离线</span>\n' +
                                                    '                                            </dl>\n' +
                                                    '                                        </li>');
                                                break;
                                            default:
                                                $(".group_name").eq(j).parent().parent().find(".custom-friends-list").prepend('<li socketaddress="'+data.data[i].customFriendsLoginId+'" class="row custom-friend-item">\n' +
                                                    '                                            <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+data.data[i].customFriendsHeadportrait+'">\n' +
                                                    '\n' +
                                                    '                                            <dl class="col-md-9 custom-friend-item-info">\n' +
                                                    '                                                <dt class="list-remarks">'+data.data[i].customFriendsRemark+'<span>('+data.data[i].customFriendsLoginId+')</span></dt>\n' +
                                                    '                                                <dd class="list-motto">'+data.data[i].customFriendsMotto+'</dd>\n' +
                                                    '                                                <!--<span class="badge custom-num-tip">5</span>-->\n' +
                                                    '                                                <span class="badge custom-online-tip">在线</span>\n' +
                                                    '                                            </dl>\n' +
                                                    '                                        </li>');
                                                    var group_online_num =  $(".group_name").eq(j).parent().find(".online_num").text();
                                                    $(".group_name").eq(j).parent().find(".online_num").text(parseInt(group_online_num)+1);
                                                break;
                                        }

                                        var group_all_num =  $(".group_name").eq(j).parent().find(".group_all_num").text();
                                        $(".group_name").eq(j).parent().find(".group_all_num").text(parseInt(group_all_num)+1);

                                }
                            }
                        }
                    }
                    //调用好友名片卡
                    friend_card();

                },
                error:function (err) {
                    console.log("访问错误："+err);
                }
            });





        }
    });
}


//分组下拉
function grouplist() {
    var grouplist_num =  $(".custom-group-item").length;
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
            url:serverUrl+"/userdeal/infofriend",
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

        // $(this).css({"background-color":"red"});
        $(this).find(".custom-num-tip").text("");
        var remarks = $(this).find(".list-remarks").text();
        var socketaddress = $(this).attr("data-id");
        var chatHeadportrait = $(this).find(".list-headportrait").attr("src");
        change_friend_chatting_fun(socketaddress,remarks,chatHeadportrait);

    })
}

//封装正在聊天页的切换
function change_friend_chatting_fun(socketaddress,remarks,chatHeadportrait) {

    var flag =  $("#right-chat-friend-container-id").css('display');
    if ( flag == "none" ) {
        $("#right-chat-friend-container-id").slideToggle("fast");
        $("#system_message_div").css({'display':"none"});
    }

    //清空聊天框
    $("#custom-messages-ul").empty();
    //清空输入框
    $("#edit").froalaEditor('html.set', '');

    $("#send-to-btn").attr("socketaddress",socketaddress);
    $("#chatting-friend-remarks").text(remarks);
    $("#chatting-friend-remarks").attr("data-img",chatHeadportrait);

    //请求历史信息
    //获取自己的登陆ID
    var userLoginId =  $.cookie("_userLoginId");
    //获取自己的头像
    var myHeadimg = $("#head-img").attr("src")
    var formData = new FormData();
    formData.append("friendLoginId",socketaddress);
    formData.append("userLoginId",userLoginId);
    formData.append("page",1);
    $.ajax({
        url:serverUrl+"/userinforevise/listmessagebetweenusers",
        type:"POST",
        data:formData,
        async:true,
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if(data.code == 1){
                //清空聊天框
                $("#custom-messages-ul").empty();
                if(data.data != null){
                    var dataLength = data.data.length;
                    for (var i=0; i<dataLength; i++){
                        //将消息填充入聊天框
                        switch (data.data[i].messagesToLoginid){
                            //好友发送来的（即自己为接收方）
                            case userLoginId:
                                $("#custom-messages-ul").prepend('<dl class="row message-contain-item">\n' +
                                    '                                        <div class="message-contain-item-time">'+ data.data[i].messagesTime +'</div>\n' +
                                    '                                        <dt class="col-md-1">\n' +
                                    '                                            <img class="img-responsive img-circle message-headportrait" src="'+chatHeadportrait+'">\n' +
                                    '                                        </dt>\n' +
                                    '                                        <dd class="col-md-8">\n' +
                                    '                                            <div class="custom-triangle"></div>\n' +
                                    '                                            <span class="message-text-contain-friends">'+ data.data[i].messagesPostmessages +'</span>\n' +
                                    '                                        </dd>\n' +
                                    '                                    </dl>');
                                $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},0);
                                break;
                            //  自己发送给好友（即接收方为对方）
                            case socketaddress:
                                $("#custom-messages-ul").prepend('<dl class="row message-contain-item">\n' +
                                    '                                            <div class="message-contain-item-time">' + data.data[i].messagesTime + '</div>\n' +
                                    '                                            <dt class="col-md-1 col-md-push-11">\n' +
                                    '                                                <img class="img-responsive img-circle message-headportrait" src="'+ myHeadimg +'">\n' +
                                    '                                            </dt>\n' +
                                    '                                            <dd class="col-md-8 col-md-push-2">\n' +
                                    '                                                <div class="custom-triangle-right"></div>\n' +
                                    '                                                <span class="message-text-contain-me pull-right">' + data.data[i].messagesPostmessages + '</span>\n' +
                                    '                                            </dd>\n' +
                                    '                                        </dl>');
                                $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},0);
                                break;
                        }
                    }

                }
                //获取聊天记录成功后清空redis未读信息记录
                removeUnreadInRedis(userLoginId,socketaddress);

            }
        },
        error:function (err) {
            console.log("获取聊天记录连接错误："+err);
        }
    });


    //移除与该好友的未读消息
    function removeUnreadInRedis(userLoginId,friendLoginId) {

        $.post(serverUrl+"/userinforevise/delunreadnum",{"friendLoginId":friendLoginId,"userLoginId":userLoginId},function (data) {
            if (data.code == 1){

            }else{
                console.log(data.tip);
            }
        })
        
        }

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
        url:serverUrl+"/userdeal/myuserinfo",
        type:"POST",
        data:{},
        async:true,
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
                $("#userinfo_loginId").attr("userId",data.data.userId);
                $("#userinfo_age").val(data.data.userAge);
                $("#userinfo_birthday").val(data.data.userBirthday);
                $("#userinfo_tel").val(data.data.userTelephone);
                $("#userinfo_email").val(data.data.userEmail);
                $("#userinfo_vocation").val(data.data.userVocation);
                $("#userinfo_synopsis").text(data.data.userSynopsis);
                $("#userinfo_revise_btn").attr("dataId",data.data.userLoginId);//通信地址为登陆名
                $("#gender_select").find("option[value="+data.data.userGender+"]").attr("selected",true);
                $("#province_select").find("option[value="+data.data.userProvinceId+"]").attr("selected",true);
                var shi=$("#province_select").val();
                $.each(citys,function (index,item) {
                    if(item.ProID==shi){
                        $("#city_select").append("<option value='"+ item.CityID +"'>"+item.name+"</option>");
                    }
                });
                $("#city_select").find("option[value="+data.data.userCityId+"]").attr("selected",true);

                // websocket连接
                websocket_connect(stompClient);
            } else{
                alert(data.tip);
            }
        },
        error:function (err) {
            console.log("获取用户信息失败，请求错误："+err)
        }
    });



}
