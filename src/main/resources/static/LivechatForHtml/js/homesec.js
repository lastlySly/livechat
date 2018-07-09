
$(function () {
    friends_card_sendbtn();
    sendMessageBtn();
});


//好友名片卡，发送消息按钮
function friends_card_sendbtn() {
    $("#custom-send-message-btn").on("click",function () {
        var socketaddress = $(this).attr("socketaddress");
        var remarks = $("#remarks-top").text();
        var motto = $("#motto").text();
        var headportrait = $("#headportrait").attr("src");
        /*切换到正在聊天界面*/
        $("#custom-chat-btn").css("color","#FFFFFF");
        $("#custom-linkman-btn").css("color","#C3C3C3");
        $("#custom-linkman-list-id").hide(500);
        $("#custom-chat-list-id").show(500);
        // $("#custom-linkman-list-id").css("display","none");
        // $("#custom-chat-list-id").css("display","block");

        /*删除已存在的该项*/
        var chatting_num = $(".custom-chat-friend-item").length;
        for (var i = 0;i < chatting_num; i++){
            var data_id = $(".custom-chat-friend-item").eq(i).attr("data-id");
            console.log(data_id + "===" + socketaddress);

            if(data_id == socketaddress){
                console.log("存在，删除")
                $(".custom-chat-friend-item").eq(i).remove();
                break;
            }
        }
        /*将该项添加至列表，最顶*/
        $("#chatting-list").prepend(
            '<li data-id="'+ socketaddress +'" class="row custom-chat-friend-item">\n' +
            '                                <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+headportrait+'">\n' +
            '                                <dl class="col-md-9 custom-friend-item-info">\n' +
            '                                    <dt class="list-remarks">'+ remarks +'</dt>\n' +
            '                                    <dd class="list-motto">'+ motto +'</dd>\n' +
            '                                    <span class="badge custom-num-tip">4</span>\n' +
            '                                    <span class="badge custom-time">3:15</span>\n' +
            '                                    <button class="custom-del glyphicon glyphicon-remove-sign"></button>\n' +
            '                                </dl>\n' +
            '                            </li>'
        );
        del_chat_item_btn();
        change_friend_chatting_fun(socketaddress,remarks);
        change_chatting_friend();

    });
}

/*获取系统当前时间*/
function custom_getdate() {
    var date1 = new Date();
    var y = date1.getFullYear();
    var m = date1.getMonth()+1;
    var d = date1.getDate();
    var h = date1.getHours();
    var f = date1.getMinutes();
    var s = date1.getSeconds();

    if (m<10) {
        m = ""+"0"+m;
    }
    if (d<10) {
        d = ""+"0"+d;
    }
    if (h<10) {
        h = ""+"0"+h;
    }
    if (f<10) {
        f = ""+"0"+f;
    }
    if (s<10) {
        s = ""+"0"+s;
    }
    var datetime = y + "-" + m + "-" + d + " "+ h + ":" + f + ":" + s;
    return datetime;

}
/*获取系统当前时间End*/


//发送按钮
function sendMessageBtn() {
    $("#send-to-btn").on("click",function () {
        var head_img = $("#head-img").attr("src");
        var myMessage = $("#edit").froalaEditor('html.get');
        var sendTime = custom_getdate()
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

        $("#custom-messages-ul").append('<dl class="row message-contain-item">\n' +
            '                                        <div class="message-contain-item-time">2018-06-24 14:01:08</div>\n' +
            '                                        <dt class="col-md-1">\n' +
            '                                            <img class="img-responsive img-circle message-headportrait" src="img/headportrait.jpg">\n' +
            '                                        </dt>\n' +
            '                                        <dd class="col-md-8">\n' +
            '                                            <div class="custom-triangle"></div>\n' +
            '                                            <span class="message-text-contain-friends">ssssssssssssssss\n' +
            '                                            ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss</span>\n' +
            '                                        </dd>\n' +
            '                                    </dl>');

        //滚动条置于聊天框最底部
        $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},50)
        // console.log("===" + $(".chat-content-div")[0].scrollHeight)

    })
}
