var stompClient = null;
function websocket_connect(socket_address) {
    var socket = new SockJS("http://localhost:8080/demo/endpoint-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/single/' + socket_address, function (result) {
            console.log(JSON.parse(result.body));
        });
    })
}

/**
 * 发送信息
 */

$("#send-to-btn").on("click",function () {
    //对方通信ID
    var socketaddress = $(this).attr("socketaddress");
    sendMessage();
})
function sendMessage(socketaddress) {
    //自己的ID
    var mysocketaddress = $("#userinfo_revise_btn").attr("dataId");
    stompClient.send("/app/singlechat", {}, JSON.stringify({'content': "", 'to':"", 'from':""}));
}
