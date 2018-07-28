friend_application_init();
logoutBtn();
//获取好友申请信息
function friend_application_init(){

    //请求历史信息
    var page = 1;
    var allPage = 1;
    //获取自己的登陆ID
    var userLoginId =  $.cookie("_userLoginId");

    //获取信息页数
    $.post(serverUrl+"/userinforevise/friendapplicationpagecount",{"userLoginId":userLoginId},function (data) {
        if(data.code == 1){
            allPage = data.data;
        }
    });
    var isFirst = true;
    $("#custom_system_message_div").empty();
    listApplication(page,0);
    function listApplication(page,scrollPosition) {

        var formData = new FormData();
        formData.append("page",page);
        $.ajax({
            url:serverUrl+"/userinforevise/listfriendapplication",
            type:"POST",
            data:formData,
            async:true,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data.code == 1){
                    if(data.data != null){
                        var myLoginId = $.cookie('_userLoginId');
                        var dataLength = data.data.length;
                        var unDealFriendApplicationMessage = 0;
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
                                    unDealFriendApplicationMessage++;
                                    // //添加 同意 拒绝 忽略 按钮的点击事件
                                    // reply_friend_application();
                                }
                            }

                        }
                        isFirst = false;
                        $("#custom_system_message_div").animate({scrollTop:$(".custom_system_message_div")[0].scrollHeight - scrollPosition},0);
                        //设置未处理的好友请求条数
                        $("#friend_application_num").text(unDealFriendApplicationMessage);
                        //添加 同意 拒绝 忽略 按钮的点击事件
                        reply_friend_application();

                    }
                }
            },
            error:function (err) {
                console.log("连接错误friend_application_init：" + err);
            }
        });

    }


    //滚动事件（加载更多信息）
    $("#custom_system_message_div").off("scroll");
    $("#custom_system_message_div").on("scroll",function () {
        if($(this)[0].scrollTop == 0 && !isFirst){
            page++;
            if(page > allPage){
                if($("#loding_sign_application").length > 0){
                    return;
                }
                $("#custom_system_message_div").prepend('<h5 style="text-align: center;padding-top: 20px" id="loding_sign_application" class="loding_sign">没有更多了</h5>');
                return;
            }
            $("#custom_system_message_div").prepend('<h5 style="text-align: center;padding-top: 20px" class="loding_sign">正在加载...</h5>');
            var signNum = $(this)[0].scrollHeight;
            // console.log(signNum)
            setTimeout(function(){
                $("#custom_system_message_div").find(".loding_sign").remove();
                listApplication(page,signNum);
            },1000);
        }
    });



}

//登出
function logoutBtn() {
    $("#logout_btn").off("click");
    $("#logout_btn").on("click",function () {
        $.post(serverUrl+"/userdeal/logout",{},function (data) {
        });
        location.reload();
    });
    // $(window).on("unload",function(){
    //     //响应事件
    //     alert("获取到了页面要关闭的事件了！");
    // });


}



/*=================================头像裁剪上传start============================*/

//弹出框水平垂直居中
(window.onresize = function () {
    // var win_height = $(window).height();
    // var win_width = $(window).width();
    var win_height = $(".head_portrait_modal_div").height();
    var win_width = $(".head_portrait_modal_div").width();
    if (win_width <= 768){
        $(".tailoring-content").css({
            "top": (win_height - $(".tailoring-content").outerHeight())/2,
            "left": 0
        });
    }else{
        $(".tailoring-content").css({
            "top": (win_height - $(".tailoring-content").outerHeight())/2,
            "left": (win_width - $(".tailoring-content").outerWidth())/2
        });
    }
})();

//弹出图片裁剪框
$("#userinfo_head_img").on("click",function () {
    // $(".tailoring-container").slideToggle();
    $(".head_portrait_modal_div").slideToggle();
});
//图像上传
function selectImg(file) {
    if (!file.files || !file.files[0]){
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var replaceSrc = evt.target.result;
        //更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc,false);//默认false，适应高度，不失真
    }
    reader.readAsDataURL(file.files[0]);
}
//cropper图片裁剪
$('#tailoringImg').cropper({
    aspectRatio: 1/1,//默认比例
    preview: '.previewImg',//预览视图
    guides: false,  //裁剪框的虚线(九宫格)
    autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
    movable: false, //是否允许移动图片
    dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
    movable: true,  //是否允许移动剪裁框
    resizable: true,  //是否允许改变裁剪框的大小
    zoomable: true,  //是否允许缩放图片大小
    mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
    touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
    rotatable: true,  //是否允许旋转图片
    crop: function(e) {
        // 输出结果数据裁剪图像。
    }
});
//旋转
$(".cropper-rotate-btn").on("click",function () {
    $('#tailoringImg').cropper("rotate", 45);
});
//复位
$(".cropper-reset-btn").on("click",function () {
    $('#tailoringImg').cropper("reset");
});
//换向
var flagX = true;
$(".cropper-scaleX-btn").on("click",function () {
    if(flagX){
        $('#tailoringImg').cropper("scaleX", -1);
        flagX = false;
    }else{
        $('#tailoringImg').cropper("scaleX", 1);
        flagX = true;
    }
    flagX != flagX;
});

//裁剪后的处理
$("#sureCut").on("click",function () {
    if ($("#tailoringImg").attr("src") == null ){
        return false;
    }else{
        var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
        // var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
        var base64url = cas.toDataURL('image/jpeg'); //转换为base64地址形式,png太大
        // $("#userinfo_head_img").prop("src",base64url);//显示为图片的形式
        // //关闭裁剪框
        // closeTailor();
        //调用ajax上传
        uploadFile(encodeURIComponent(base64url),base64url);

    }

});
//关闭裁剪框
function closeTailor() {
    // $(".tailoring-container").toggle();
    $(".head_portrait_modal_div").slideToggle();
}
//ajax请求上传
function uploadFile(file,base64url) {
    var userId = $("#userinfo_loginId").attr("userId");
    // var formData = new FormData();
    // formData.append("userId",userId);
    // formData.append("file",file);
    //"file=" + file
    // console.log(file);
    $.ajax({
        url :serverUrl+ '/userinforevise/upload',
        type : 'POST',
        data : "file=" + file,
        async : true,
        success : function(data) {
            // console.log(data)
            // getUserInfo();
            if(data.code == 1){
                $("#head-img").attr("src",data.data);
                $("#userinfo_head_img").attr("src",data.data);
                $("#userinfo_head_img").prop("src",base64url);//显示为图片的形式
                //关闭裁剪框
                closeTailor();
            }else{
                alert(data.tip);
            }

        }
    });
}

/*=================================头像裁剪上传end============================*/