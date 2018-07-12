
$(function () {
    pop_modal();
    userinfo_modal();
    address_deal();
})

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
                    console.log(data + "这里是查找新好友");
                    var friendSize = data.data.length;
                    $(".find-friends-result-div").empty();
                    for (var i =0; i<friendSize; i++){
                        $(".find-friends-result-div").append(' <dl class="find-friends-item">\n' +
                            '                    <dt class="find-friends-head"><img class="img-responsive img-circle" src="'+ data.data[i].userHeadportrait +'"></dt>\n' +
                            '                    <dd class="friends-nickname-and-username">'+ data.data[i].userNickname + '(' + data.data[i].userLoginId + ')</dd>\n' +
                            '                    <dd class="friends-address">'+ data.data[i].userGender +' '+ data.data[i].userProvinceId + ' ' + data.data[i].userCityId + '</dd>\n' +
                            '                    <dd class="friends-add-btn-send"><button type="button" class="btn btn-default">加好友</button></dd>\n' +
                            '                </dl>');
                    }

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

}

//地址操作（填充个人信息模态框）
function address_deal() {
    $("#province_select").empty();
    $("#province_select").append("<option value=''></option>");
    $.each(province,function (index,item) {
        $("#province_select").append("<option value='"+ item.ProID +"'>"+item.name+"</option>");
    });
    $("#province_select").change(function () {
        var shi=$("#province_select").val();
        $("#city_select").empty();
        $("#city_select").append("<option value=''></option>");
        $.each(citys,function (index,item) {
            if(item.ProID==shi){
                $("#city_select").append("<option value='"+ item.CityID +"'>"+item.name+"</option>");
            }
        });
    })

}