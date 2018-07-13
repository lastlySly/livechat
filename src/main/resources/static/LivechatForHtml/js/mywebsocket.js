

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
    stompClient.send("/app/singlechat", {}, JSON.stringify({'messagesFromUserid': mysocketaddress,
        'messagesToUserid':socketaddress, 'messagesPostmessages':myMessage,'messagesTime':sendTime,'messagesTypeid':3}));
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
    $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},50)
    
}