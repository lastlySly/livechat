var stompClient = null;
$(function () {

    grouplist();
    friend_card();
    del_chat_item_btn();
    change_tab();
    // change_chatting_friend();
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
        imageUploadURL :serverUrl+'/messagedeal/uploadImgEditor',
        imageManagerDeleteURL :'/lastlySly/adminController/deleteImg.action'
    });


})

/*获取分组并渲染*/
function getGroupFun() {
    $.ajax({
        url:serverUrl+"/groupingdeal/listgroup",
        type:"POST",
        data:{},
        async:true,
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if (data.code == 1){
                $(".custom-friends-group-list").empty();
                $("#friend_info_card_group").empty();
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

                    //好友信息卡分组
                    $("#friend_info_card_group").append('<option value="'+ data.data[i].friendgroupsId +'">'+ data.data[i].friendgroupsName +'</option>');

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
                                    if (data.data[i].customFriendsGroupId == $(".group_name").eq(j).attr("groupId")){
                                        //判断好友是否在线
                                        var isOnline =$.inArray(data.data[i].customFriendsLoginId,onlineArray);
                                        switch (isOnline){
                                            case -1:
                                                $(".group_name").eq(j).parent().parent().find(".custom-friends-list").append('<li socketaddress="'+data.data[i].customFriendsLoginId+'" class="row custom-friend-item">\n' +
                                                    '                                            <img class="col-xs-3 img-responsive img-circle list-headportrait" src="'+data.data[i].customFriendsHeadportrait+'">\n' +
                                                    '\n' +
                                                    '                                            <dl class="col-xs-9 custom-friend-item-info">\n' +
                                                    '                                                <dt class="list-remarks">'+data.data[i].customFriendsRemark+'<span>('+data.data[i].customFriendsLoginId+')</span></dt>\n' +
                                                    '                                                <dd class="list-motto">'+data.data[i].customFriendsMotto+'</dd>\n' +
                                                    '                                                <!--<span class="badge custom-num-tip">5</span>-->\n' +
                                                    '                                                <span class="badge custom-online-tip">离线</span>\n' +
                                                    '                                            </dl>\n' +
                                                    '                                        </li>');
                                                break;
                                            default:
                                                $(".group_name").eq(j).parent().parent().find(".custom-friends-list").prepend('<li socketaddress="'+data.data[i].customFriendsLoginId+'" class="row custom-friend-item">\n' +
                                                    '                                            <img class="col-xs-3 img-responsive img-circle list-headportrait" src="'+data.data[i].customFriendsHeadportrait+'">\n' +
                                                    '\n' +
                                                    '                                            <dl class="col-xs-9 custom-friend-item-info">\n' +
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
    $(".custom-group").off("click");
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

    //获取好友信息
    function getfriendCardInfo(socketaddress) {

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
                    $("#headportrait").attr("src",data.data.customFriendsHeadportrait);
                    $("#remarks-top").text(data.data.customFriendsRemark);
                    $("#remarks").text(data.data.customFriendsRemark);
                    $("#motto").text(data.data.customFriendsMotto);
                    $("#custom-send-message-btn").attr("socketaddress",socketaddress);
                    $("#custom_del_friend_btn").attr("socketaddress",socketaddress);
                    $("#nickname").text(data.data.customFriendsNickName);

                    // $("#friend_info_card_group").find("option[value="+data.data.customFriendsGroupId+"]").attr("selected",true);
                    $("#friend_info_card_group").val(data.data.customFriendsGroupId);

                    $("#e-mail").text(data.data.customFriendsEmail);
                    $("#username").text(data.data.customFriendsLoginId);
                }else{
                    alert("提示：" +data.tip);
                }
            },
            error:function (err) {
                alert("提示：" +err);
            }

        });

    }

    //好友列表点击事件
    $(".custom-friend-item").off("click");
    $(".custom-friend-item").on("click",function () {

        var friendListLength = $(".custom-friend-item").length;
        for(var i=0; i<friendListLength; i++){
            $(".custom-friend-item").eq(i).css({"background-color":"#FAFAFA"});
        }
        $(this).css({"background-color":"#E5E7EC"});

        var flag =  $(".right-main-container").css('display');
        if ( flag == "none" ) {
            $(".right-main-container").slideToggle("fast");
        }
        var socketaddress = $(this).attr("socketaddress");
        getfriendCardInfo(socketaddress);

    });

    //备注修改
    nickname_revise();
    function nickname_revise() {
        $("#friend_card_icon_revise").off("click");
        $("#friend_card_icon_revise").on("click",function () {
            $("#remarks_input").val($("#remarks").text());
            $("#remarks_input").css({"display":"inline-block"});
            $("#remarks").css({"display":"none"});
            $("#friend_card_icon_revise").css({"display":"none"});

            $("#remarks_input").focus();
        });
        //输入框焦点离开事件（提交备注修改）
        $("#remarks_input").off("blur")
        $("#remarks_input").on("blur",function () {
            var friendLoginId = $("#custom-send-message-btn").attr("socketaddress");
            var newRemark = $(this).val();
            $("#remarks").css({"display":"inline-block"});
            $("#remarks_input").css({"display":"none"});
            $("#friend_card_icon_revise").css({"display":"inline-block"});
            $.post(serverUrl+"/userinforevise/revisefriendinfo",{"friendLoginId":friendLoginId,"remark":newRemark},function (data) {
                if(data.code == 1){
                    getfriendCardInfo(friendLoginId);
                    listFriend();
                }else{
                    alert("修改失败");
                }
            });
        });

    }

    $("#friend_info_card_group").off("change");
    //修改分组
    $("#friend_info_card_group").on("change",function () {
        var groupId =  $(this).val();
        var friendLoginId = $("#custom-send-message-btn").attr("socketaddress");
        $.post(serverUrl+"/userinforevise/revisefriendinfo",{"groupId":groupId,"friendLoginId":friendLoginId},function (data) {
            if(data.code == 1){
                getfriendCardInfo(friendLoginId);
                listFriend();
            }else{
                alert("修改失败");
            }
        });
    })

}

/*删除正在聊天列表的某项*/
function del_chat_item_btn() {
    $(".custom-del").off("click");
    $(".custom-del").on("click",function () {
        $(this).parent().parent().remove();
    })
}

//切换底部页面
function change_tab() {

    $("#custom-chat-btn").off("click");
    $("#custom-chat-btn").on("click",function () {
        //默认 color: #C3C3C3;
        $(this).css("color","#FFFFFF");
        $("#custom-linkman-btn").css("color","#C3C3C3");

        // $("#custom-linkman-list-id").css("display","none");
        // $("#custom-chat-list-id").css("display","block");
        $("#custom-linkman-list-id").hide(500);
        $("#custom-chat-list-id").show(500);
    });
    $("#custom-linkman-btn").off("click");
    $("#custom-linkman-btn").on("click",function () {
        $(this).css("color","#FFFFFF");
        $("#custom-chat-btn").css("color","#C3C3C3");
        // $("#custom-chat-list-id").css("display","none");
        // $("#custom-linkman-list-id").css("display","block");
        $("#custom-linkman-list-id").show(500);
        $("#custom-chat-list-id").hide(500);
    });
}

/*调出菜单*/
function change_meau() {
    /*头像菜单*/
    $("#head-nav").off("click");
    $("#head-nav").on("click",function () {
        $(".custom-head-meau").slideToggle("fast");
        // event.stopPropagation();
        stopPropagations();
    });
    //
    $("body").on("click",function () {
        $(".custom-head-meau").hide("fast");
        $(".custom-add-meau").hide("fast");
    });
    /*添加好友菜单*/
    $("#custom-add-btn").off("click");
    $("#custom-add-btn").on("click",function () {
        $(".custom-add-meau").slideToggle("fast");
        // event.stopPropagation();
        stopPropagations();
    });

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
                // $("#userinfo_head_img").prop("src",data.data.userHeadportrait);
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
                // $("#gender_select").val(data.data.userGender);
                // $("#province_select").find("option[value="+data.data.userProvinceId+"]").attr("selected",true);
                $("#province_select").val(data.data.userProvinceId);
                var shi=$("#province_select").val();
                $.each(citys,function (index,item) {
                    if(item.ProID==shi){
                        $("#city_select").append("<option value='"+ item.CityID +"'>"+item.name+"</option>");
                    }
                });
                // $("#city_select").find("option[value="+data.data.userCityId+"]").attr("selected",true);
                $("#city_select").val(data.data.userCityId);

                // websocket连接
                websocket_connect(stompClient);
            } else{
                alert("提示1：" + data.tip);
            }
        },
        error:function (err) {
            console.log("获取用户信息失败，请求错误："+err)
        }
    });



}

// /*头像加载失败触发*/
// function imgerror(){
//     $("#head-img").error(function(){
//         $(this).attr('src',"img/default_head.png");
//     });
//     $("#userinfo_head_img").error(function(){
//         $(this).attr('src',"img/default_head.png");
//     });
// }

//兼容火狐 获取event方法
function getEvent(){
    if(window.event){return window.event;}
    func = getEvent.caller;
    while(func != null){
        var arg0 = func.arguments[0];
        if(arg0){
            if((arg0.constructor == Event || arg0.constructor == MouseEvent
                    || arg0.constructor == KeyboardEvent)
                || (typeof(arg0) == "object" && arg0.preventDefault
                    && arg0.stopPropagation)){
                return arg0;
            }
        }
        func = func.caller;
    }
    return null;
}

//阻止冒泡到下一个事件
function stopPropagations(){
    var ev = getEvent();
    if (window.event) {
        ev.cancelBubble = true;
    }else if(ev.preventDefault){
        ev.stopPropagation();//阻止冒泡
    }
}
