

function websocket_connect(tompClient) {

    var mysocket_address = $("#userinfo_revise_btn").attr("dataId");
    var socket = new SockJS('http://localhost:8080/demo/endpoint-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/single/' + mysocket_address, function (result) {
            // console.log(JSON.parse(result.body));
            showMessage(JSON.parse(result.body));
        });
    });

}

/**
 * 发送信息
 */

function sendMessage(socketaddress,myMessage,sendTime) {
    //自己的ID
    var mysocketaddress = $("#userinfo_revise_btn").attr("dataId");
    stompClient.send("/app/singlechat", {}, JSON.stringify({'messagesFromLoginid': mysocketaddress,
        'messagesToLoginid':socketaddress, 'messagesPostmessages':myMessage,'messagesTime':sendTime,'messagesTypeid':3}));
}


//发送按钮
function sendMessageBtn() {
    $("#send-to-btn").on("click",function () {
        //对方socket地址
        var socketaddress = $(this).attr("socketaddress");

        var head_img = $("#head-img").attr("src");
        var myMessage = $("#edit").froalaEditor('html.get');
        var sendTime = custom_getdate();
        sendMessage(socketaddress,myMessage,sendTime);
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

        //清空输入框
        $("#edit").froalaEditor('html.set', '');


        //滚动条置于聊天框最底部
        $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},50)
        // console.log("===" + $(".chat-content-div")[0].scrollHeight)

    })
}

//显示接收的消息
function showMessage(result) {

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
            url:"http://localhost:8080/demo/userdeal/selectnewfriends",
            type:"POST",
            data:formData,
            async:true,
            // cache: false,缓存，get请求有效，true缓存
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.code == 1){

                    var remark = null;
                    var friendsNum = $("#custom-friend-item").length;
                    for(var i=0; i< friendsNum ;i++){
                        var loginId = $("#custom-friend-item").eq(i).attr("socketaddress");
                        if(loginId = result.messagesFromLoginid){
                            remark = $("#custom-friend-item").eq(i).find(".list-remarks").text();
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
                            console.log("存在，删除")
                            $(".custom-chat-friend-item").eq(i).remove();
                            break;
                        }
                    }
                    /*将该项添加至列表，最顶*/
                    $("#chatting-list").prepend(
                        '<li data-id="'+ data.data[0].userLoginId +'" class="row custom-chat-friend-item">\n' +
                        '                                <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+data.data[0].userHeadportrait+'">\n' +
                        '                                <dl class="col-md-9 custom-friend-item-info">\n' +
                        '                                    <dt class="list-remarks">'+ remark +'</dt>\n' +
                        '                                    <dd class="list-motto">'+ data.data[0].userMotto +'</dd>\n' +
                        '                                    <span class="badge custom-num-tip">1</span>\n' +
                        '                                    <span class="badge custom-time">10:45</span>\n' +
                        '                                    <button class="custom-del glyphicon glyphicon-remove-sign"></button>\n' +
                        '                                </dl>\n' +
                        '                            </li>'
                    );
                    del_chat_item_btn();
                    var flag =  $("#right-chat-friend-container-id").css('display');
                    if ( flag == "none" ) {
                        $("#right-chat-friend-container-id").slideToggle("fast");
                    }
                    $("#send-to-btn").attr("socketaddress",data.data.customFriendsLoginId);
                    $("#chatting-friend-remarks").text(data.data.customFriendsRemark);
                    $("#chatting-friend-remarks").attr("data-img",data.data.customFriendsHeadportrait);

                    change_chatting_friend();

                }else{
                    alert(data.tip);
                }
            },
            error:function (err) {
                alert("连接错误："+err);
            }

        });

    }

    
}