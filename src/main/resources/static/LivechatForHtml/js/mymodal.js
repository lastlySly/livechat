
$(function () {
    pop_modal();
    userinfo_modal();
    address_deal();

});

//添加好友，添加修改分组模态
function pop_modal() {
    $("#add-friends-btn").on("click",function () {
        /*关闭添加按钮组*/
        $(".custom-add-meau").slideToggle("fast");
        /*打开模态框*/
        $(".add-friends-modal-div").slideToggle("fast");

    })

    $(".remove-add-friends-modal-btn").on("click",function () {
        /*关闭添加好友模态框*/
        $(".add-friends-modal-div").slideToggle("fast");
    })


    //寻找新好友
    $("#findNewFriendBtn").on("click",function () {
        var loginIdOrNickname = $("#loginIdOrNickname").val();
        var formData = new FormData();
        formData.append("loginIdOrNickname",loginIdOrNickname);
        $.ajax({
            url:"http://localhost:8080/demo/userdeal/selectnewfriends",
            type:"POST",
            data:formData,
            async:true,
            contentType:false,
            processData:false,
            success:function (data) {
                if (data.code == 1){
                    // console.log(data + "这里是查找新好友");
                    if(data.data == null){
                        return;
                    }
                    var friendSize = data.data.length;
                    $(".find-friends-result-div").empty();
                    for (var i =0; i<friendSize; i++){
                        var userGender = "保密";
                        var userProvince = "保密";
                        var userCity = "保密";
                        if(data.data[i].userGender){
                            userGender = "男";
                        }else{
                            userGender = "女";
                        }
                        $.each(province,function (index,item) {
                            if (item.ProID == data.data[i].userProvinceId){
                                userProvince = item.name;
                                return;
                            }
                        });
                        $.each(citys,function (index,item) {
                            if(item.CityID==data.data[i].userCityId){
                                userCity = item.name;
                            }
                        });


                        $(".find-friends-result-div").append(' <dl class="find-friends-item">\n' +
                            '                    <dt class="find-friends-head"><img class="img-responsive img-circle" src="'+ data.data[i].userHeadportrait +'"></dt>\n' +
                            '                    <dd class="friends-nickname-and-username">'+ data.data[i].userNickname + '(' + data.data[i].userLoginId + ')</dd>\n' +
                            '                    <dd class="friends-address">'+ userGender +' '+ userProvince + ' ' + userCity + '</dd>\n' +
                            '                    <dd class="friends-add-btn-send"><button type="button" dataId="'+ data.data[i].userLoginId +'"  class="apply_btn_custom btn btn-default">加好友</button></dd>\n' +
                            '                </dl>');
                    }

                    apply_friend();
                }else{
                    alert(data.tip)
                }



            },
            error:function (err) {
                alert("请求错误" + err);
            }
        });

    })


}

//个人信息模态
function userinfo_modal() {

    $("#userinfo_btn").on("click",function () {
        /*关闭头像按钮组*/
        $(".custom-head-meau").slideToggle("fast");
        /*打开模态框*/
        $(".userinfo-modal-div").slideToggle("fast");

    });

    $(".remove-userinfo-modal-btn").on("click",function () {
        /*关闭个人信息模态框*/
        $(".userinfo-modal-div").slideToggle("fast");
    })

    //双击可修改
    $(".userinfo_div_input").on("dblclick",function () {
        $(this).css({"background":"rgba(255,255,255,1)","border":"1px solid rgba(0,0,0,1)"});
        $(this).removeAttr("readonly");
    });
    $(".userinfo_div_input").on("blur",function () {
        $(this).css({"background":"rgba(255,255,255,0)","border":"1px solid rgba(0,0,0,0)"});
        $(this).attr("readonly","readonly");
    })


    //提交修改
    $("#userinfo_revise_btn").on("click",function () {
        var userinfo_nickname = $("#userinfo_nickname").val();
        var userinfo_motto = $("#userinfo_motto").val();
        // var userinfo_loginId = $("#userinfo_loginId").text();
        var userinfo_age = $("#userinfo_age").val();
        var userinfo_birthday = $("#userinfo_birthday").val();
        var userinfo_tel = $("#userinfo_tel").val();
        var userinfo_email = $("#userinfo_email").val();
        var userinfo_vocation = $("#userinfo_vocation").val();
        var userinfo_synopsis = $("#userinfo_synopsis").val();
        var gender_select = $("#gender_select").val();
        var province_select = $("#province_select").val();
        var city_select = $("#city_select").val();
        // var userinfo_userLoginId = $("#userinfo_revise_btn").attr("dataId");
        var userId = $("#userinfo_loginId").attr("userId");
        var formData = new FormData();
        formData.append("userNickname",userinfo_nickname);
        // formData.append("userLoginId",userinfo_userLoginId);
        formData.append("userId",userId);
        formData.append("userMotto",userinfo_motto);
        formData.append("userAge",userinfo_age);
        formData.append("userBirthday",userinfo_birthday);
        formData.append("userTelephone",userinfo_tel);
        formData.append("userEmail",userinfo_email);
        formData.append("userVocation",userinfo_vocation);
        formData.append("userSynopsis",userinfo_synopsis);
        formData.append("userGender",gender_select);
        if (province_select != null && province_select != ""){
            formData.append("userProvinceId",province_select);
        }
        if (city_select !=null && city_select !=""){
            formData.append("userCityId",city_select);
        }
        $.ajax({
            url:"http://localhost:8080/demo/userinforevise/updateuserinfo",
            type:"POST",
            data:formData,
            async:true,
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.code == 1){
                    alert("修改成功");
                }else{
                    alert("修改失败");
                }
            },
            error:function (err) {
                console.log(err);
                alert("连接错误："+err);
            }
        });

    });

}

//地址操作（填充个人信息模态框）
function address_deal() {

    $("#province_select").empty();
    // $("#province_select").append("<option value=''></option>");
    $.each(province,function (index,item) {
        $("#province_select").append("<option value='"+ item.ProID +"'>"+item.name+"</option>");
    });
    $("#province_select").change(function () {
        initCity();
    });
    function initCity() {
        var shi=$("#province_select").val();
        $("#city_select").empty();
        // $("#city_select").append("<option value=''></option>");
        $.each(citys,function (index,item) {
            if(item.ProID==shi){
                $("#city_select").append("<option value='"+ item.CityID +"'>"+item.name+"</option>");
            }
        });
    }

}

//申请好友操作
function apply_friend() {
    $(".apply_btn_custom").off("click");
    $("#back_btn").off("click");
    $("#send_apply_btn").off("click");
    $(".apply_btn_custom").on("click",function () {
        var someoneloginId = $(this).attr("dataId");
        var someoneNickname = ($(this).parent().parent().find(".friends-nickname-and-username")).text();
        $(".apply_div").slideToggle("fast");
        $("#apply_title").text("申请 【"+ someoneNickname +"】为好友");
        $("#send_apply_btn").attr("fromData",someoneloginId);
        $("#findNewFriendBtn").attr("disabled","true");
        //填充分组信息
        $("#apply_group").empty();
        var groupLength = $(".custom-group-item").length;
        for(var i = 0; i<groupLength; i++){
            var groupName = $(".custom-group-item").eq(i).find(".group_name").text();
            var groupId = $(".custom-group-item").eq(i).find(".group_name").attr("groupId");
            $("#apply_group").append("<option value='"+ groupId +"'>"+groupName+"</option>");
        }

    });

    $("#back_btn").on("click",function () {
        $("#findNewFriendBtn").removeAttr("disabled");
        $(".apply_div").slideToggle("fast");
    });

    $("#send_apply_btn").on("click",function () {
        //获取对方的登陆ID
        var tologinId =  $(this).attr("fromData");
        //获取申请备注
        var applyRemark = $("#apply_remark").val();

        //获取要发送的申请信息
        var applyMessage = $("#apply_message").val();
        //获取分组ID
        var applyGroupId = $("#apply_group").val();
        //获取时间
        var sendTime = custom_getdate();
        //获取自己的登陆ID
        var fromLoginId = $("#userinfo_loginId").text();

        var someoneNickname = ($(".apply_btn_custom").parent().parent().find(".friends-nickname-and-username")).text();

        // stompClient.send("/app/applyfriend", {}, JSON.stringify({'friendApplicationTo': tologinId,
        //     'friendApplicationRemark':applyRemark, 'friendApplicationGroup':applyGroupId,
        //     'friendApplicationMessage':applyMessage,'friendApplicationTime':sendTime,'friendApplicationFrom':fromLoginId}));
        // alert("向【" + someoneNickname + "】发送好友申请成功");
        var formData = new FormData();
        formData.append("friendApplicationTo",tologinId);
        formData.append("friendApplicationRemark",applyRemark);
        formData.append("friendApplicationGroup",applyGroupId);
        formData.append("friendApplicationMessage",applyMessage);
        formData.append("friendApplicationTime",sendTime);

        $.ajax({
            url:"http://localhost:8080/demo/userinforevise/sendfriendapplication",
            type:"POST",
            data:formData,
            async:true,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data.code == 1){
                    $("#custom_system_message_div").append('<div class="system_message_item">\n' +
                        '                                    <div class="system_message_ite_time">' +sendTime+ '</div>\n' +
                        '                                    <span class="system_message_text">\n' +
                        '<a class="" href="javascript:void(0);">等待验证</a>' +
                        '                                        向'+someoneNickname+'发起了好友请求<br>\n' +
                        '                                    </span>\n' +
                        '                                </div>');
                    alert("向【" + someoneNickname + "】发送好友申请成功");
                }else{
                    alert(data.tip);
                }
            },
            error:function (err) {
                alert("连接失败："+err);
            }
        });

        $(".apply_div").slideToggle("fast");
    })

}