
$(function () {
    pop_modal();
})

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