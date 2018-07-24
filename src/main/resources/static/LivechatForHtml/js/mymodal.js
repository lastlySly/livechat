
$(function () {
    pop_modal();
    userinfo_modal();
    address_deal();
    group_manage();
    del_friend();
});



//添加好友，添加修改分组模态
function pop_modal() {
    $("#add-friends-btn").on("click",function () {
        /*关闭添加按钮组*/
        $(".custom-add-meau").slideToggle("fast");
        /*打开模态框*/
        $(".add-friends-modal-div").slideToggle("fast");

    });

    $(".remove-add-friends-modal-btn").on("click",function () {
        /*关闭添加好友模态框*/
        $(".add-friends-modal-div").slideToggle("fast");

        if($(".apply_div").css("display") != "none"){
            $(".apply_div").slideToggle("fast");
        }
    })

    //寻找新好友
    $("#findNewFriendBtn").on("click",function () {

        //先查询自己的好友列表，如果存在，则不渲染添加按钮，防止重复添加

        var myFriendsArray = new Array();
        var myFriendsLength = $(".custom-friend-item").length;
        //将自己添加为第一项，方便判断是否为自己
        var myLoginId = $.cookie("_userLoginId");
        myFriendsArray.push(myLoginId);
        for(var j=0; j<myFriendsLength; j++){
            myFriendsArray.push($(".custom-friend-item").eq(j).attr("socketaddress"));
        }

        //根据账号获取昵称查询
        var loginIdOrNickname = $("#loginIdOrNickname").val();
        var formData = new FormData();
        formData.append("loginIdOrNickname",loginIdOrNickname);
        $.ajax({
            url:serverUrl+"/userdeal/selectnewfriends",
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

                        var isFriend = $.inArray(data.data[i].userLoginId,myFriendsArray);
                        switch (isFriend){
                            case -1:
                                $(".find-friends-result-div").append(' <dl class="find-friends-item">\n' +
                                    '                    <dt class="find-friends-head"><img class="img-responsive img-circle" src="'+ data.data[i].userHeadportrait +'"></dt>\n' +
                                    '                    <dd class="friends-nickname-and-username">'+ data.data[i].userNickname + '(' + data.data[i].userLoginId + ')</dd>\n' +
                                    '                    <dd class="friends-address">'+ userGender +' '+ userProvince + ' ' + userCity + '</dd>\n' +
                                    '                    <dd class="friends-add-btn-send"><button type="button" dataId="'+ data.data[i].userLoginId +'"  class="apply_btn_custom btn btn-default">加好友</button></dd>\n' +
                                    '                </dl>');

                                break;
                            case 0:
                                $(".find-friends-result-div").append(' <dl class="find-friends-item">\n' +
                                    '                    <dt class="find-friends-head"><img class="img-responsive img-circle" src="'+ data.data[i].userHeadportrait +'"></dt>\n' +
                                    '                    <dd class="friends-nickname-and-username">'+ data.data[i].userNickname + '(' + data.data[i].userLoginId + ')</dd>\n' +
                                    '                    <dd class="friends-address">'+ userGender +' '+ userProvince + ' ' + userCity + '</dd>\n' +
                                    '                    <dd class="friends-add-btn-send"><button type="button" dataId="'+ data.data[i].userLoginId +'" disabled="disabled" class="apply_btn_custom btn btn-default">我的账号</button></dd>\n' +
                                    '                </dl>');
                                break;
                            default:
                                $(".find-friends-result-div").append(' <dl class="find-friends-item">\n' +
                                    '                    <dt class="find-friends-head"><img class="img-responsive img-circle" src="'+ data.data[i].userHeadportrait +'"></dt>\n' +
                                    '                    <dd class="friends-nickname-and-username">'+ data.data[i].userNickname + '(' + data.data[i].userLoginId + ')</dd>\n' +
                                    '                    <dd class="friends-address">'+ userGender +' '+ userProvince + ' ' + userCity + '</dd>\n' +
                                    '                    <dd class="friends-add-btn-send"><button type="button" dataId="'+ data.data[i].userLoginId +'" disabled="disabled" class="apply_btn_custom btn btn-default">已是好友</button></dd>\n' +
                                    '                </dl>');

                                break;
                        }


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
    $(".userinfo_div_input").on("click",function () {
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
            url:serverUrl+"/userinforevise/updateuserinfo",
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

        var someoneNickname = $("#apply_title").text();

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
            url:serverUrl+"/userinforevise/sendfriendapplication",
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
        $("#findNewFriendBtn").removeAttr("disabled");
    })

}

//分组管理
function group_manage() {
      // // 无连接服务器开启
      //   listGroup();
      //   group_effect();
      //   add_group_btn();

    //打开分组管理模态框
    $("#group_manage_btn").on("click",function () {
        /*关闭添加按钮组*/
        $(".custom-add-meau").slideToggle("fast");
        /*打开模态框*/
        $(".groups_modal_div").slideToggle("fast");
        listGroup();

    });
    //关闭分组管理模态框
    $(".remove_groups_modal_div_btn").on("click",function () {
        /*关闭分组管理模态框*/
        $(".groups_modal_div").slideToggle("fast");
    });

    //效果，事件
    function group_effect() {
        //单击可修改
        $(".group_input_rename").off("click");
        $(".group_input_rename").off("blur");
        $(".group_rename_div").off("click");
        $(".groups_modal_div").off("click");
        $(".ok_group_icon").off("click");
        $(".del_group_icon").off("click");
        //输入框样式
        $(".group_input_rename").on("click",function () {
            $(this).css({"background":"rgba(255,255,255,1)","border":"1px solid rgba(0,0,0,0)","text-align":"center"});
            $(this).removeAttr("readonly");
            $(this).parent().find(".ok_group_icon").css({"display":"inline-block"});
        });
        $(".group_input_rename").on("blur",function () {
            $(this).css({"background":"rgba(255,255,255,0)","border":"1px solid rgba(0,0,0,0)","text-align":"left"});
            $(this).attr("readonly","readonly");
            // $(this).parent().find(".ok_group_icon").css({"display":"none"});
        });

        $(".group_rename_div").on("click",function () {
            // var renameDivLength = $(".group_rename_div").length;
            $(".group_rename_div").find(".ok_group_icon").css({"display":"none"});
            $(this).find(".ok_group_icon").css({"display":"inline-block"});
            //阻止事件起泡（冒泡问题，点击子布局事件不触发父布局事件）
            event.stopPropagation();

        })
        $(".groups_modal_div").on("click",function () {
            $(".group_rename_div").find(".ok_group_icon").css({"display":"none"});
        })
        //提交修改
        $(".ok_group_icon").on("click",function () {
            var group_input_rename = $(this).parent().find(".group_input_rename");
            var groupId = group_input_rename.attr("groupId");
            var groupName = group_input_rename.val();
            var loginId = $.cookie("_userLoginId");
            $.post(serverUrl+"/groupingdeal/renamegroup",{"groupId":groupId,"groupName":groupName,"loginId":loginId},function (data) {
                if(data.code == 1){
                    listGroup();
                    var groupLength = $(".group_name").length;
                    for (var i = 0; i<groupLength; i++){
                        var mainDivGroupId = $(".group_name").eq(i).attr("groupId");
                        if(mainDivGroupId == groupId){
                            $(".group_name").eq(i).text(groupName);
                        }
                    }
                    alert(data.tip);
                }else{
                    alert(data.tip);
                }
            })
        });

        //删除分组
        $(".del_group_icon").on("click",function () {
            var groupId = $(this).attr("groupId");
            $("#del_group_tip").slideToggle("fast");
            $("#confirm_del_group_btn").attr("groupId",groupId);
            $("#un_del_group_btn").off("click");
            $("#confirm_del_group_btn").off("click");
            //取消删除
            $("#un_del_group_btn").on("click",function () {
                $("#del_group_tip").slideToggle("fast");
            });
            //确认删除
            $("#confirm_del_group_btn").on("click",function () {
                var groupId = $(this).attr("groupId");
                var loginId = $.cookie("_userLoginId");
                $.post(serverUrl+"/groupingdeal/delgroup",{"loginId":loginId,"groupId":groupId},function (data) {
                    if(data.code == 1){
                        listGroup();
                        getGroupFun();
                        $("#del_group_tip").slideToggle("fast");
                    }else{
                        alert(data.tip);
                    }
                });
            })

        })
    }

    //新建分组
    function add_group_btn() {
        $(".add_group_btn").off("click");
        $(".group_back_icon_btn").off("click");
        //添加分组
        $(".add_group_btn").on("click",function () {
            if($("#add_new_group_div").length>0){
                alert("请先完成以下新建操作");
                return;
            }
            $(this).after(' <li>\n' +
                '                    <div id="add_new_group_div" class="group_rename_div">\n' +
                '                        <input class="new_group_input" type="text" placeholder="请输入分组名称" value="" />\n' +
                '                        <img class="my_icon group_add_submit_btn" src="img/icon_ok_02.png">\n' +
                '                        <img class="my_icon group_back_icon_btn" src="img/icon_back_02.png">\n' +
                '                    </div>\n' +
                '                </li>');
            //重新调用绑定事件
            group_effect();

            $(".group_back_icon_btn").off("click");
            $(".group_add_submit_btn").off("click");
            //撤销新建操作
            $(".group_back_icon_btn").on("click",function () {
                $("#add_new_group_div").parent().remove();
            });

            //提交新建分组
            $(".group_add_submit_btn").on("click",function () {
                var loginId = $.cookie("_userLoginId");
                var groupName = $(".new_group_input").val();
                $.post(serverUrl+"/groupingdeal/addnewgroup",{"loginId":loginId,"groupName":groupName},function (data) {
                    if(data.code == 1){
                        listGroup();
                        getGroupFun();

                    }else{
                        alert(data.tip);
                    }
                })
            })



        });


    }


    //获取分组
    function listGroup() {
        var loginId = $.cookie("_userLoginId");
        $.post(serverUrl+"/groupingdeal/listgroup",{"loginId":loginId},function (data) {
            if(data.code == 1){
                $(".groups_modal_groups_list").empty();
                $(".groups_modal_groups_list").append('<h5 class="add_group_btn">' +
                    '<img class="my_icon add_group_icon" src="img/add_icon.png">添加分组</h5>');
                if(data.data == null){
                    return;
                }
                var dataLength = data.data.length;
                for(var i= 0; i<dataLength; i++){
                    var friendgroupsGrade = data.data[i].friendgroupsGrade;
                    switch (friendgroupsGrade){
                        case 1:
                            $(".groups_modal_groups_list").append('<li><img groupId="'+ data.data[i].friendgroupsId +'" class="my_icon not_group_icon" src="img/icon_not_1.png">\n' +
                                '                    <div class="group_rename_div">\n' +
                                '                        <input class="group_input_rename" groupId ="'+ data.data[i].friendgroupsId +'" ' +
                                ' type="text" value="'+ data.data[i].friendgroupsName +'" readonly="readonly" />\n' +
                                '                        <img class="my_icon ok_group_icon" src="img/ok_1.png">\n' +
                                '                    </div>\n' +
                                '                </li>');

                            break;
                        default:
                            $(".groups_modal_groups_list").append('<li><img groupId="'+ data.data[i].friendgroupsId +'" class="my_icon del_group_icon" src="img/del_2.png">\n' +
                                '                    <div class="group_rename_div">\n' +
                                '                        <input class="group_input_rename" groupId ="'+ data.data[i].friendgroupsId +'"' +
                                '  type="text" value="'+ data.data[i].friendgroupsName +'" readonly="readonly" />\n' +
                                '                        <img class="my_icon ok_group_icon" src="img/ok_1.png">\n' +
                                '                    </div>\n' +
                                '                </li>');
                            break;
                    }
                    //添加事件
                    group_effect();
                    add_group_btn();
                }

            }
        })
    }


}

//删除好友操作
function del_friend() {
    $("#custom_del_friend_btn").off("click");
    $("#custom_del_friend_btn").on("click",function () {
        /*打开模态框*/
        $(".is_del_friend_modal_div").slideToggle("fast");
        var friendId = $(this).attr("socketaddress");
        $("#confirm_del_friend_btn").attr("friendId",friendId);
    });
    //取消删除
    $("#un_del_friend_btn").off("click");
    $("#un_del_friend_btn").on("click",function () {
        $(".is_del_friend_modal_div").slideToggle("fast");
    });
    //确认删除
    $("#confirm_del_friend_btn").off("click");
    $("#confirm_del_friend_btn").on("click",function () {
        var friendId = $(this).attr("friendId");
        var userLoginId = $.cookie("_userLoginId");
        $.post(serverUrl+"/userinforevise/delfriend",{"friendLoginId":friendId,"userLoginId":userLoginId},function (data) {
            if(data.code == 1){
                listFriend();
                $(".is_del_friend_modal_div").slideToggle("fast");
                //关闭信息卡
                $(".right-main-container").slideToggle("fast");
            }else{
                alert(data.tip);
            }
        })
    });

}

