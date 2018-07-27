
$(function () {
    friends_card_sendbtn();
    sendMessageBtn();
    system_message_div();
    change_chatting_friend();
});


//正在聊天切换
function change_chatting_friend() {
    $(".custom-chat-friend-item").off("click");
    $(".custom-chat-friend-item").on("click",function () {

        // $(this).css({"background-color":"red"});
        $(this).find(".custom-num-tip").text("");
        var remarks = $(this).find(".list-remarks").text();
        var socketaddress = $(this).attr("data-id");
        var chatHeadportrait = $(this).find(".list-headportrait").attr("src");
        change_friend_chatting_fun(socketaddress,remarks,chatHeadportrait);

    });
}

//封装正在聊天页的切换（查出聊天记录）
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

    var chattingListLength = $(".custom-chat-friend-item").length;
    for(var i=0; i<chattingListLength; i++){
        $(".custom-chat-friend-item").eq(i).css({"background-color":"#FAFAFA"});
        var dataId =  $(".custom-chat-friend-item").eq(i).attr("data-id");
        if(dataId == $("#send-to-btn").attr("socketaddress")){
            $(".custom-chat-friend-item").eq(i).css({"background-color":"#E5E7EC"});
        }
    }

    //请求历史信息
    var page = 1;
    var allPage = 1;
    //获取自己的登陆ID
    var userLoginId =  $.cookie("_userLoginId");

    //获取信息页数
    $.post(serverUrl+"/userinforevise/pagecount",{"userLoginId":userLoginId,"friendLoginId":socketaddress},function (data) {
        if(data.code == 1){
            allPage = data.data;
        }
    });

    //获取自己的头像
    var myHeadimg = $("#head-img").attr("src");
    listMessageBetweenUser(userLoginId,socketaddress,myHeadimg,page,0);
    var isFirst = true;
    function listMessageBetweenUser(userLoginId,socketaddress,myHeadimg,page,scrollPosition) {

        var formData = new FormData();
        formData.append("friendLoginId",socketaddress);
        formData.append("userLoginId",userLoginId);
        formData.append("page",page);
        $.ajax({
            url:serverUrl+"/userinforevise/listmessagebetweenusers",
            type:"POST",
            data:formData,
            async:true,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data.code == 1){
                    //清空聊天框
                    // $("#custom-messages-ul").empty();
                    if(data.data != null){
                        var dataLength = data.data.length;
                        for (var i=0; i<dataLength; i++){
                            //将消息填充入聊天框
                            switch (data.data[i].messagesToLoginid){
                                //好友发送来的（即自己为接收方）
                                case userLoginId:
                                    $("#custom-messages-ul").prepend('<dl class="row message-contain-item">\n' +
                                        '                                        <div class="message-contain-item-time">'+ data.data[i].messagesTime +'</div>\n' +
                                        '                                        <dt class="col-xs-1">\n' +
                                        '                                            <img class="img-responsive img-circle message-headportrait" src="'+chatHeadportrait+'">\n' +
                                        '                                        </dt>\n' +
                                        '                                        <dd class="col-xs-8">\n' +
                                        '                                            <div class="custom-triangle"></div>\n' +
                                        '                                            <span class="message-text-contain-friends">'+ data.data[i].messagesPostmessages +'</span>\n' +
                                        '                                        </dd>\n' +
                                        '                                    </dl>');
                                    // $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},0);
                                    break;
                                //  自己发送给好友（即接收方为对方）
                                case socketaddress:
                                    $("#custom-messages-ul").prepend('<dl class="row message-contain-item">\n' +
                                        '                                            <div class="message-contain-item-time">' + data.data[i].messagesTime + '</div>\n' +
                                        '                                            <dt class="col-xs-1 col-xs-push-11">\n' +
                                        '                                                <img class="img-responsive img-circle message-headportrait" src="'+ myHeadimg +'">\n' +
                                        '                                            </dt>\n' +
                                        '                                            <dd class="col-xs-8 col-xs-push-2">\n' +
                                        '                                                <div class="custom-triangle-right"></div>\n' +
                                        '                                                <span class="message-text-contain-me pull-right">' + data.data[i].messagesPostmessages + '</span>\n' +
                                        '                                            </dd>\n' +
                                        '                                        </dl>');
                                    // $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight},0);
                                    break;
                            }
                        }
                        $(".chat-content-div").animate({scrollTop:$(".chat-content-div")[0].scrollHeight - scrollPosition},0);

                    }
                    //获取聊天记录成功后清空redis未读信息记录
                    removeUnreadInRedis(userLoginId,socketaddress);
                    isFirst = false;
                }
            },
            error:function (err) {
                console.log("获取聊天记录连接错误："+err);
            }
        });
    }

    //移除与该好友的未读消息
    function removeUnreadInRedis(userLoginId,friendLoginId) {

        $.post(serverUrl+"/userinforevise/delunreadnum",{"friendLoginId":friendLoginId,"userLoginId":userLoginId},function (data) {
            if (data.code == 1){

            }else{
                console.log(data.tip);
            }
        })

    }
    //滚动事件（加载更多信息）
    $(".chat-content-div").off("scroll");
    $(".chat-content-div").on("scroll",function () {
        if($(this)[0].scrollTop == 0 && !isFirst){
            page++;
            if(page > allPage){
                if($("#loding_sign").length > 0){
                    return;
                }
                $("#custom-messages-ul").prepend('<h5 style="text-align: center;padding-top: 20px" id="loding_sign" class="loding_sign">没有更多了</h5>');
                return;
            }
            $("#custom-messages-ul").prepend('<h5 style="text-align: center;padding-top: 20px" class="loding_sign">正在加载...</h5>');
            var signNum = $(this)[0].scrollHeight;
            // console.log(signNum)
            setTimeout(function(){
                $("#custom-messages-ul").find(".loding_sign").remove();
                listMessageBetweenUser(userLoginId,socketaddress,myHeadimg,page,signNum);
            },2000);
        }
    });
}




//好友名片卡，发送消息按钮
function friends_card_sendbtn() {
    $("#custom-send-message-btn").off("click");
    $("#custom-send-message-btn").on("click",function () {
        var socketaddress = $(this).attr("socketaddress");
        var remarks = $("#remarks-top").text()+"("+socketaddress+")";
        var motto = $("#motto").text();
        var headportrait = $("#headportrait").attr("src");
        /*切换到正在聊天界面*/
        $("#custom-chat-btn").css("color","#FFFFFF");
        $("#custom-linkman-btn").css("color","#C3C3C3");
        $("#custom-linkman-list-id").hide(500);
        $("#custom-chat-list-id").show(500);
        // $("#custom-linkman-list-id").css("display","none");
        // $("#custom-chat-list-id").css("display","block");

        var unreadNum = "";
        /*删除已存在的该项*/
        var chatting_num = $(".custom-chat-friend-item").length;
        for (var i = 0;i < chatting_num; i++){
            var data_id = $(".custom-chat-friend-item").eq(i).attr("data-id");

            if(data_id == socketaddress){
                unreadNum = $(".custom-chat-friend-item").eq(i).find(".custom-num-tip").text();
                $(".custom-chat-friend-item").eq(i).remove();
                break;
            }
        }

        if(chatting_num == 0){
            unreadNum="";
        }
        /*将该项添加至列表，最顶*/
        $("#chatting-list").prepend(
            '<li data-id="'+ socketaddress +'" class="row custom-chat-friend-item">\n' +
            '                                <img class="col-xs-3 img-responsive img-circle list-headportrait" src="'+headportrait+'">\n' +
            '                                <dl class="col-xs-9 custom-friend-item-info">\n' +
            '                                    <dt class="list-remarks">'+ remarks +'</dt>\n' +
            '                                    <dd class="list-motto">'+ motto +'</dd>\n' +
            '                                    <span class="badge custom-num-tip">'+ unreadNum +'</span>\n' +
            '                                    <span class="badge custom-time"> </span>\n' +
            '                                    <button class="custom-del glyphicon glyphicon-remove-sign"></button>\n' +
            '                                </dl>\n' +
            '                            </li>'
        );
        del_chat_item_btn();
        change_friend_chatting_fun(socketaddress,remarks,headportrait);
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

//系统消息框
function system_message_div() {
    $("#btn_system_message").off("click");
    $("#btn_system_message").on("click",function () {
        var flag =  $("#system_message_div").css('display');
        if ( flag == "none" ) {
            $("#system_message_div").slideToggle("fast");
            $("#right-chat-friend-container-id").css({'display':"none"});
        }
        $("#system_message_top_name").text("系统消息");

    });
    $("#btn_system_friend_apply").off("click");
    $("#btn_system_friend_apply").on("click",function () {
        var flag =  $("#system_message_div").css('display');
        if ( flag == "none" ) {
            $("#system_message_div").slideToggle("fast");
            $("#right-chat-friend-container-id").css({'display':"none"});
        }
        $("#system_message_top_name").text("好友请求");
        $("#custom_system_message_div").animate({scrollTop:$(".custom_system_message_div")[0].scrollHeight},50);
    });


}

//好友申请回复模态框
function reply_friend_application() {

    //同意
    $(".agree_friend_apply").off("click");
    $(".agree_friend_apply").on("click",function () {
        //获取请求人的登陆ID
        var applyLoginId =  $(this).attr("applyId");
        $("#send_apply_btn_reply").attr("applyId",applyLoginId);
        $("#apply_reply_title").text("同意添加"+ applyLoginId +"为好友");
        //填充分组信息
        $("#apply_group_reply").empty();
        var groupLength = $(".custom-group-item").length;
        for(var i = 0; i<groupLength; i++){
            var groupName = $(".custom-group-item").eq(i).find(".group_name").text();
            var groupId = $(".custom-group-item").eq(i).find(".group_name").attr("groupId");
            $("#apply_group_reply").append("<option value='"+ groupId +"'>"+groupName+"</option>");
        }
        $(".refuse_friend_application_reply_modal").attr("display","none");
        $(".friend_application_reply_div_modal").slideToggle("fast");
        // $("#friend_application_reply_div").slideToggle("fast");

    });
    //忽略
    $(".refuse_friend_apply").off("click");
    $(".ignore_friend_apply").on("click",function () {
        alert("忽略了（正在施工）");
    });
    //拒绝
    $(".ignore_friend_apply").off("click");
    $(".refuse_friend_apply").on("click",function () {

        var applyLoginId =  $(this).attr("applyId");
        $("#refuse_btn").attr("applyId",applyLoginId);
        $("#refuse_apply_reply_title").text("拒绝"+ applyLoginId +"的好友请求");
        $(".friend_application_reply_div_modal").attr("display","none");
        $(".refuse_friend_application_reply_modal").slideToggle("fast");
    });

    //同意弹出模态框的返回按钮
    $("#back_btn_reply").off("click");
    $("#back_btn_reply").on("click",function () {
        $(".friend_application_reply_div_modal").slideToggle("fast");
    });
    //同意弹出模态框的加为好友按钮
    $("#send_apply_btn_reply").off("click");
    $("#send_apply_btn_reply").on("click",function () {

        //获取分组ID
        var applyGroupIdReply = $("#apply_group_reply").val();
        //获取对方登陆ID
        var applyLoginIdReply = $(this).attr("applyId");
        //获取备注
        var applyRemarkReply = $("#apply_remark_reply").val();
        //获取自己的登陆ID
        var myLoginIdReply =  $("#userinfo_loginId").text();
        //设置状态
        var applyStatus = "同意";
        var sendTime = custom_getdate();
        var formData = new FormData();
        formData.append("friendApplicationGroup",applyGroupIdReply);
        formData.append("friendApplicationFrom",applyLoginIdReply);
        formData.append("friendApplicationTo",myLoginIdReply);
        formData.append("friendApplicationRemark",applyRemarkReply);
        formData.append("friendApplicationStatus",applyStatus);
        $.ajax({
            url:serverUrl+"/userinforevise/dealfriendapplication",
            type:"POST",
            data:formData,
            async:true,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data.code == 1){
                    alert("操作成功");

                    //调用刷新好友申请信息框
                    friend_application_init();
                    sendMessage(applyLoginIdReply,"我们已经是好友了，快来一起聊天吧",sendTime,2);
                    //刷新分组列表和好友列表
                    // getGroupFun();
                    listFriend();
                    setTimeout(function(){
                        var myFriendsLength = $(".custom-friend-item").length;
                        var headImg = "";
                        var sendNowTime = sendTime.substring(11,16);
                        for(var j=0; j<myFriendsLength; j++){
                            if($(".custom-friend-item").eq(j).attr("socketaddress") == applyLoginIdReply){
                                headImg = $(".custom-friend-item").eq(j).find(".list-headportrait").attr("src");
                            }
                        }
                        $("#chatting-list").prepend(
                            '<li data-id="'+ applyLoginIdReply +'" class="row custom-chat-friend-item">\n' +
                            '                                <img class="col-xs-3 img-responsive img-circle list-headportrait" src="'+headImg+'">\n' +
                            '                                <dl class="col-xs-9 custom-friend-item-info">\n' +
                            '                                    <dt class="list-remarks">'+ applyRemarkReply +'</dt>\n' +
                            '                                    <dd class="list-motto">'+ "我们已经是好友了，快来一起聊天吧" +'</dd>\n' +
                            '                                    <span class="badge custom-num-tip"></span>\n' +
                            '                                    <span class="badge custom-time">'+ sendNowTime +'</span>\n' +
                            '                                    <button class="custom-del glyphicon glyphicon-remove-sign"></button>\n' +
                            '                                </dl>\n' +
                            '                            </li>'
                        );
                        del_chat_item_btn();
                        change_chatting_friend();
                    },2000);

                }else{
                    alert(data.tip);
                }
            },
            error:function (err) {
                console.log("连接失败："+err);
            }
                
        });

        $(".friend_application_reply_div_modal").slideToggle("fast");
    });


    //拒绝弹出模态框的返回按钮
    $("#refuse_back_btn").off("click");
    $("#refuse_back_btn").on("click",function () {
        $(".refuse_friend_application_reply_modal").slideToggle("fast");
    });

    //拒绝弹出模态框的拒绝按钮
    $("#refuse_btn").off("click");
    $("#refuse_btn").on("click",function () {
        //获取对方登陆ID
        var applyLoginIdReply = $(this).attr("applyId");
        var applyStatus = "拒绝";
        var sendTime = custom_getdate();
        //获取原因
        var friendApplicationRefuseMessage = $("#resufe_message").val();
        //获取自己的登陆ID
        var myLoginIdReply =  $("#userinfo_loginId").text();
        var formData = new FormData();
        formData.append("friendApplicationFrom",applyLoginIdReply);
        formData.append("friendApplicationTo",myLoginIdReply);
        formData.append("friendApplicationRefuseMessage",friendApplicationRefuseMessage);
        formData.append("friendApplicationStatus",applyStatus);
        formData.append("friendApplicationTime",sendTime);
        $.ajax({
            url:serverUrl+"/userinforevise/dealfriendapplication",
            type:"POST",
            data:formData,
            async:true,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data.code == 1){
                    alert("操作成功");
                    //调用刷新好友申请信息框
                    friend_application_init();
                }else{
                    alert(data.tip);
                }
            },
            error:function (err) {
                console.log("连接失败："+err);
            }

        });
        $(".refuse_friend_application_reply_modal").slideToggle("fast");

    });

}