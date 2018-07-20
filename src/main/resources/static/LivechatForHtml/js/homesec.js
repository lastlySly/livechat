
$(function () {
    friends_card_sendbtn();
    sendMessageBtn();
    system_message_div();
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

        var unreadNum = 0;
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
            '                                <img class="col-md-3 img-responsive img-circle list-headportrait" src="'+headportrait+'">\n' +
            '                                <dl class="col-md-9 custom-friend-item-info">\n' +
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
    $("#btn_system_message").on("click",function () {
        var flag =  $("#system_message_div").css('display');
        if ( flag == "none" ) {
            $("#system_message_div").slideToggle("fast");
            $("#right-chat-friend-container-id").css({'display':"none"});
        }
        $("#system_message_top_name").text("系统消息");

    });

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
    $(".agree_friend_apply").off("click");
    $(".refuse_friend_apply").off("click");
    $(".ignore_friend_apply").off("click");
    $("#back_btn_reply").off("click");
    $("#send_apply_btn_reply").off("click");

    //同意
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
    $(".ignore_friend_apply").on("click",function () {
        alert("忽略了（正在施工）");
    });
    //拒绝
    $(".refuse_friend_apply").on("click",function () {

        var applyLoginId =  $(this).attr("applyId");
        $("#refuse_btn").attr("applyId",applyLoginId);
        $("#refuse_apply_reply_title").text("拒绝"+ applyLoginId +"的好友请求");
        $(".friend_application_reply_div_modal").attr("display","none");
        $(".refuse_friend_application_reply_modal").slideToggle("fast");
    });

    //同意弹出模态框的返回按钮
    $("#back_btn_reply").on("click",function () {
        $(".friend_application_reply_div_modal").slideToggle("fast");
    });
    //同意弹出模态框的加为好友按钮
    $("#send_apply_btn_reply").on("click",function () {

        //获取分组ID
        var applyGroupIdReply = $("#apply_group_reply").val();
        //获取对方登陆ID
        var applyLoginIdReply = $(this).attr("applyId");
        //获取备注
        var applyNickNameReply = $("#apply_remark_reply").val();
        //获取自己的登陆ID
        var myLoginIdReply =  $("#userinfo_loginId").text();
        //设置状态
        var applyStatus = "同意";
        var sendTime = custom_getdate();
        var formData = new FormData();
        formData.append("friendApplicationGroup",applyGroupIdReply);
        formData.append("friendApplicationFrom",applyLoginIdReply);
        formData.append("friendApplicationTo",myLoginIdReply);
        formData.append("friendApplicationRemark",applyNickNameReply);
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
                    getGroupFun();
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
    $("#refuse_back_btn").on("click",function () {
        $(".refuse_friend_application_reply_modal").slideToggle("fast");
    });

    //拒绝弹出模态框的加为好友按钮
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