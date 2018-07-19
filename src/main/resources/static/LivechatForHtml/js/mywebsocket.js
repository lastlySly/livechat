

function websocket_connect() {

    var mysocket_address = $("#userinfo_revise_btn").attr("dataId");
    var socket = new SockJS(serverUrl+'/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        //订阅单聊地址
        stompClient.subscribe('/chat/single/' + mysocket_address, function (result) {
            showMessage(JSON.parse(result.body));
        });
        //订阅好友申请地址
        stompClient.subscribe('/mysystem/applyfriend/' + mysocket_address, function (result) {
            showFriendApplication(JSON.parse(result.body));
        });
        //订阅系统发来的个人消息地址(好友上下线通知等)
        stompClient.subscribe('/mysystem/adminpushto/' + mysocket_address, function (result) {
            showFriendOnlineOrOffline(JSON.parse(result.body));
            // console.log(JSON.parse(result.body));
        });
        //订阅未读消息
        stompClient.subscribe('/mysystem/unread/' + mysocket_address, function (result) {
            showUnreadMessage(JSON.parse(result.body));
            // console.log(JSON.parse(result.body));
        });



    });

}

/**
 * 发送信息
 */

function sendMessage(socketaddress,myMessage,sendTime,typeId) {
    //自己的ID
    var mysocketaddress = $("#userinfo_revise_btn").attr("dataId");
    stompClient.send("/app/singlechat", {}, JSON.stringify({'messagesFromLoginid': mysocketaddress,
        'messagesToLoginid':socketaddress, 'messagesPostmessages':myMessage,'messagesTime':sendTime,'messagesTypeid':typeId}));
}


//发送按钮
function sendMessageBtn() {
    $("#send-to-btn").on("click",function () {
        //对方socket地址
        var socketaddress = $(this).attr("socketaddress");

        var head_img = $("#head-img").attr("src");
        var myMessage = $("#edit").froalaEditor('html.get');
        var sendTime = custom_getdate();
        sendMessage(socketaddress,myMessage,sendTime,3);
        $("#custom-messages-ul").append('<dl class="row message-contain-item">\n' +
            '                                        <div class="message-contain-item-time">' + sendTime + '</div>\n' +
            '                                        <dt class="col-md-1 col-md-push-11">\n' +
            '                                            <img class="img-responsive img-circle message-headportrait" src="' + head_img + '">\n' +
            '                                        </dt>\n' +
            '                                        <dd class="col-md-8 col-md-push-2">\n' +
            '                                            <div class="custom-triangle-right"></div>\n' +
            '                                            <span class="message-text-contain-me pull-right">' + myMessage + '</span>\n' +
            '                                        </dd>\n' +
            '                                    </dl>');

        //将发送的信息与时间简单修改入正在聊天列表项
        var chatting_num = $(".custom-chat-friend-item").length;
        for (var i = 0;i < chatting_num; i++){
            var data_id = $(".custom-chat-friend-item").eq(i).attr("data-id");
            if(data_id == socketaddress){
                $(".custom-chat-friend-item").eq(i).find(".list-motto").text(myMessage);
                var sendNowTime = sendTime.substring(11,16);
                $(".custom-chat-friend-item").eq(i).find(".custom-time").text(sendNowTime);
                break;
            }
        }

        //清空输入框
        $("#edit").froalaEditor('html.set', '');


        //滚动条置于聊天框最底部
        $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},50)
        // console.log("===" + $(".chat-content-div")[0].scrollHeight)

    });
}

//显示接收的消息
function showMessage(result) {
    
    // function refreshFun() {
    //     const p = new Promise();
    //
    //     return p
    // }
    
    //如果消息为刚申请成为好友的好友发来的消息（消息状态为2）则先刷新分组列表和好友列表
    if(result.messagesTypeid == 2){

        //刷新分组列表和好友列表
        getGroupFun();
        //刷新好友申请信息
        friend_application_init();
    }

    if(result.messagesFromLoginid ==$("#send-to-btn").attr("socketaddress") ){
        var friendsHeadImg =  $("#chatting-friend-remarks").attr("data-img");
        $("#custom-messages-ul").append('<dl class="row message-contain-item">\n' +
            '                                        <div class="message-contain-item-time">'+ result.messagesTime +'</div>\n' +
            '                                        <dt class="col-md-1">\n' +
            '                                            <img class="img-responsive img-circle message-headportrait" src="'+friendsHeadImg+'">\n' +
            '                                        </dt>\n' +
            '                                        <dd class="col-md-8">\n' +
            '                                            <div class="custom-triangle"></div>\n' +
            '                                            <span class="message-text-contain-friends">'+ result.messagesPostmessages +'</span>\n' +
            '                                        </dd>\n' +
            '                                    </dl>');
        $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},50);

    }else{
        var formData = new FormData();
        formData.append("loginIdOrNickname",result.messagesFromLoginid);
        $.ajax({
            url:serverUrl+"/userdeal/selectnewfriends",
            type:"POST",
            data:formData,
            async:true,
            // cache: false,缓存，get请求有效，true缓存
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.code == 1){

                    var remark = null;
                    var friendsNum = $(".custom-friend-item").length;
                    for(var i=0; i< friendsNum ;i++){
                        var loginId = $(".custom-friend-item").eq(i).attr("socketaddress");
                        if(loginId == result.messagesFromLoginid){
                            remark = $(".custom-friend-item").eq(i).find(".list-remarks").text();
                            break;
                        }
                    }
                    if(remark == null){
                        remark = data.data[0].userNickname + "(陌生人)";
                    }

                    /*删除已存在的该项,获取未读消息条数并加上一条*/
                    var unreadMessageNum = 0;
                    var chatting_num = $(".custom-chat-friend-item").length;
                    for (var i = 0;i < chatting_num; i++){
                        var data_id = $(".custom-chat-friend-item").eq(i).attr("data-id");

                        if(data_id == data.data[0].userLoginId){
                            unreadMessageNum = $(".custom-chat-friend-item").eq(i).find(".custom-num-tip").text();
                            $(".custom-chat-friend-item").eq(i).remove();
                            break;
                        }
                    }
                    unreadMessageNum = parseInt(unreadMessageNum) + 1;
                    var messageTime = result.messagesTime.substring(11,16);

                    var messageDate = new Date(result.messagesTime.substring(0,10));
                    var nowDate = new Date(custom_getdate().substring(0,10));
                    var dateDiffer = (nowDate - messageDate) / (1000 * 60 * 60 * 24);
                    if(dateDiffer > 0){
                        messageTime = result.messagesTime.substring(0,10);
                    }
                    /*将该项添加至列表，最顶*/
                    $("#chatting-list").prepend(
                        '<li data-id="'+ data.data[0].userLoginId +'" class="row custom-chat-friend-item">\n' +
                        '                                <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+data.data[0].userHeadportrait+'">\n' +
                        '                                <dl class="col-md-9 custom-friend-item-info">\n' +
                        '                                    <dt class="list-remarks">'+ remark +'</dt>\n' +
                        '                                    <dd class="list-motto">'+ result.messagesPostmessages +'</dd>\n' +
                        '                                    <span class="badge custom-num-tip">'+ unreadMessageNum +'</span>\n' +
                        '                                    <span class="badge custom-time">'+ messageTime +'</span>\n' +
                        '                                    <button class="custom-del glyphicon glyphicon-remove-sign"></button>\n' +
                        '                                </dl>\n' +
                        '                            </li>'
                    );
                    del_chat_item_btn();

                    change_chatting_friend();

                }else{
                    alert(data.tip);
                }
            },
            error:function (err) {
                console.log("连接错误："+err);
            }

        });

    }

}

//显示接收到的好友申请或申请回复
function showFriendApplication(result) {

    var num = $("#friend_application_num").text();
    $("#friend_application_num").text(parseInt(num)+1);
    // $("#custom_system_message_div").empty();

    if(result.friendApplicationStatus == "" || result.friendApplicationStatus == null){

        $("#custom_system_message_div").append('<div class="system_message_item">\n' +
            '                                    <div class="system_message_ite_time">'+ result.friendApplicationTime +'</div>\n' +
            '                                    <span class="system_message_text">\n' +
            '                                        账号'+ result.friendApplicationFrom +'向你发起了好友请求，消息为：\n' +
            '                                        <span class="message_friend_apply">'+ result.friendApplicationMessage +'</span><br>\n' +
            '                                        <a applyId="'+ result.friendApplicationFrom +'" class="agree_friend_apply" href="javascript:void(0);">同意</a>\n' +
            '                                        <a applyId="'+ result.friendApplicationFrom +'" class="refuse_friend_apply" href="javascript:void(0);">拒绝</a>\n' +
            '                                        <a applyId="'+ result.friendApplicationFrom +'" class="ignore_friend_apply" href="javascript:void(0);">忽略</a>\n' +
            '                                    </span>\n' +
            '                                </div>');

        //添加 同意 拒绝 忽略 按钮的点击事件
        reply_friend_application();
    }else{

        var status = result.friendApplicationStatus;
        var refuse = result.friendApplicationRefuseMessage;
        switch (status){
            case "同意":
                $("#custom_system_message_div").append('<div class="system_message_item">\n' +
                    '                                    <div class="system_message_ite_time">' +result.friendApplicationTime+ '</div>\n' +
                    '                                    <span class="system_message_text">\n' +
                    '                                        '+result.friendApplicationTo+'同意了你的好友请求<br>\n' +
                    '                                    </span>\n' +
                    '                                </div>');
                break;
            case "拒绝":
                $("#custom_system_message_div").append('<div class="system_message_item">\n' +
                    '                                    <div class="system_message_ite_time">' +result.friendApplicationTime+ '</div>\n' +
                    '                                    <span class="system_message_text">\n' +
                    '                                        '+result.friendApplicationTo+'拒绝了你的好友请求，原因：<br>\n' + result.friendApplicationRefuseMessage +
                    '                                    </span>\n' +
                    '                                </div>');
                break;
        }
    }
    $("#custom_system_message_div").animate({scrollTop:$("#custom_system_message_div")[0].scrollHeight},50);


}

//显示未读消息
function showUnreadMessage(result){

    var formData = new FormData();
    formData.append("loginIdOrNickname",result.messagesSheet.messagesFromLoginid);
    $.ajax({
        url:serverUrl+"/userdeal/selectnewfriends",
        type:"POST",
        data:formData,
        async:true,
        // cache: false,缓存，get请求有效，true缓存
        contentType: false,
        processData: false,
        success:function (data) {
            if (data.code == 1){

                var remark = null;
                var friendsNum = $(".custom-friend-item").length;

                for(var i=0; i< friendsNum ;i++){
                    var loginId = $(".custom-friend-item").eq(i).attr("socketaddress");
                    if(loginId == result.messagesSheet.messagesFromLoginid){
                        remark = $(".custom-friend-item").eq(i).find(".list-remarks").text();

                        break;
                    }

                }
                if(remark == null){
                    remark = data.data[0].userNickname + "(陌生人)";
                }

                /*删除已存在的该项*/
                var chatting_num = $(".custom-chat-friend-item").length;
                for (var i = 0;i < chatting_num; i++){
                    var data_id = $(".custom-chat-friend-item").eq(i).attr("data-id");

                    if(data_id == data.data[0].userLoginId){
                        $(".custom-chat-friend-item").eq(i).remove();
                        break;
                    }
                }
                var messageTime = result.messagesSheet.messagesTime.substring(11,16);
                /*将该项添加至列表，最顶*/
                $("#chatting-list").prepend(
                    '<li data-id="'+ data.data[0].userLoginId +'" class="row custom-chat-friend-item">\n' +
                    '                                <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+data.data[0].userHeadportrait+'">\n' +
                    '                                <dl class="col-md-9 custom-friend-item-info">\n' +
                    '                                    <dt class="list-remarks">'+ remark +'</dt>\n' +
                    '                                    <dd class="list-motto">'+ result.messagesSheet.messagesPostmessages +'</dd>\n' +
                    '                                    <span class="badge custom-num-tip">'+ result.unreadNum +'</span>\n' +
                    '                                    <span class="badge custom-time">'+ messageTime +'</span>\n' +
                    '                                    <button class="custom-del glyphicon glyphicon-remove-sign"></button>\n' +
                    '                                </dl>\n' +
                    '                            </li>'
                );
                del_chat_item_btn();

                change_chatting_friend();

            }else{
                alert(data.tip);
            }
        },
        error:function (err) {
            console.log("显示未读消息，连接错误："+err);
        }

    });

}

//显示用户上线下线
function showFriendOnlineOrOffline(result) {
    setTimeout(function () {
        $("#friend_online_offline_advice").text(result.messagesPostmessages);
        $("#friend_online_offline_advice").hide(1000);
    },1000);
    $("#friend_online_offline_advice").show(3000);
}
