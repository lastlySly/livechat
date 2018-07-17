friend_application_init();

function friend_application_init(){
    $("#custom_system_message_div").empty();
    $.ajax({
        url:"http://localhost:8080/demo/userinforevise/listfriendapplication",
        type:"POST",
        data:'{"page":1}',
        async:true,
        contentType: false,
        processData: false,
        success:function (data) {
            if(data.code == 1){
                if(data.data != null){
                    var myLoginId = $.cookie('_userLoginId');
                    console.log("测试cookie:"+myLoginId);
                    var dataLength = data.data.length;
                    for(var i=0; i<dataLength; i++){

                        //如果发送方是自己
                        if(data.data[i].friendApplicationFrom == myLoginId){
                            if(data.data[i].friendApplicationStatus == "拒绝"){
                                $("#custom_system_message_div").prepend('<div class="system_message_item">\n' +
                                    '                                    <div class="system_message_ite_time">' +data.data[i].friendApplicationTime+ '</div>\n' +
                                    '                                    <span class="system_message_text">\n' +
                                    '                                        向'+data.data[i].friendApplicationTo+'发起了好友请求<br>\n' +
                                    '<a class="" href="javascript:void(0);">已被拒绝：'+data.data[i].friendApplicationRefuseMessage+'</a>' +
                                    '                                    </span>\n' +
                                    '                                </div>');
                            } else if(data.data[i].friendApplicationStatus == "同意"){
                                $("#custom_system_message_div").prepend('<div class="system_message_item">\n' +
                                    '                                    <div class="system_message_ite_time">' +data.data[i].friendApplicationTime+ '</div>\n' +
                                    '                                    <span class="system_message_text">\n' +
                                    '                                        向'+data.data[i].friendApplicationTo+'发起了好友请求<br>\n' +
                                    '<a class="" href="javascript:void(0);">已通过</a>' +
                                    '                                    </span>\n' +
                                    '                                </div>');
                            } else{
                                $("#custom_system_message_div").prepend('<div class="system_message_item">\n' +
                                    '                                    <div class="system_message_ite_time">' +data.data[i].friendApplicationTime+ '</div>\n' +
                                    '                                    <span class="system_message_text">\n' +
                                    '                                        向'+data.data[i].friendApplicationTo+'发起了好友请求<br>\n' +
                                    '<a class="" href="javascript:void(0);">等待验证</a>' +
                                    '                                    </span>\n' +
                                    '                                </div>');
                            }
                        }
                        //如果发送方是他人
                        if(data.data[i].friendApplicationTo == myLoginId){

                            if(data.data[i].friendApplicationStatus == "同意"){
                                $("#custom_system_message_div").prepend('<div class="system_message_item">\n' +
                                    '                                    <div class="system_message_ite_time">' +data.data[i].friendApplicationTime+ '</div>\n' +
                                    '                                    <span class="system_message_text">\n' +
                                    '                                        账号'+ data.data[i].friendApplicationFrom +'向你发起了好友请求，消息为：\n' +
                                    '                                        <span class="message_friend_apply">'+ data.data[i].friendApplicationMessage +'</span><br>\n' +
                                    '<a class="" href="javascript:void(0);">已同意</a>' +
                                    '                                    </span>\n' +
                                    '                                </div>');

                            }else if(data.data[i].friendApplicationStatus == "拒绝"){
                                $("#custom_system_message_div").prepend('<div class="system_message_item">\n' +
                                    '                                    <div class="system_message_ite_time">' +data.data[i].friendApplicationTime+ '</div>\n' +
                                    '                                    <span class="system_message_text">\n' +

                                    '                                        账号'+ data.data[i].friendApplicationFrom +'向你发起了好友请求，消息为：\n' +
                                    '                                        <span class="message_friend_apply">'+ data.data[i].friendApplicationMessage +'</span><br>\n' +
                                    '<a class="" href="javascript:void(0);">已拒绝</a>' +
                                    '                                    </span>\n' +
                                    '                                </div>');
                            }else{
                                $("#custom_system_message_div").prepend('<div class="system_message_item">\n' +
                                    '                                    <div class="system_message_ite_time">'+ data.data[i].friendApplicationTime +'</div>\n' +
                                    '                                    <span class="system_message_text">\n' +
                                    '                                        账号'+ data.data[i].friendApplicationFrom +'向你发起了好友请求，消息为：\n' +
                                    '                                        <span class="message_friend_apply">'+ data.data[i].friendApplicationMessage +'</span><br>\n' +
                                    '                                        <a applyId="'+ data.data[i].friendApplicationFrom +'" class="agree_friend_apply" href="javascript:void(0);">同意</a>\n' +
                                    '                                        <a applyId="'+ data.data[i].friendApplicationFrom +'" class="refuse_friend_apply" href="javascript:void(0);">拒绝</a>\n' +
                                    '                                        <a applyId="'+ data.data[i].friendApplicationFrom +'" class="ignore_friend_apply" href="javascript:void(0);">忽略</a>\n' +
                                    '                                    </span>\n' +
                                    '                                </div>');
                                //添加 同意 拒绝 忽略 按钮的点击事件
                                reply_friend_application();
                            }
                        }

                    }


                }
            }
        },
        error:function (err) {
            alert("连接错误friend_application_init：" + err);
        }
    });
}