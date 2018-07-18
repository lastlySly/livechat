
var animateTime = 1000;
var loginId = "";
// console.log("1:"+validate);
$(function () {
    specialEffect();
    commit_register();
    // login();
    initVerification();
    // alert(2222);
});


/*输入框触发特效*/
function specialEffect() {

    $(".custom_input").on("focus",function () {
        $(this).parent().find(".icon").animate({"opacity":"1"},200);
    });
    $(".custom_input").on("blur",function () {
        $(this).parent().find(".icon").animate({"opacity":"0.5"},200);
    });


    /*前往注册*/
    $(".to_register_btn").on("click",function () {
        $(".login-container").hide(animateTime);
        $(".find_password-container").hide(animateTime);
        $(".register-container").show(animateTime);
    });
    /*前往登陆*/
    $(".to_login_btn").on("click",function () {
        $(".find_password-container").hide(animateTime);
        $(".register-container").hide(animateTime);
        $(".login-container").show(animateTime);
    });
    /*前往找回密码*/
    $(".find_password_btn").on("click",function () {
        $(".login-container").hide(animateTime);
        $(".register-container").hide(animateTime);
        $(".find_password-container").show(animateTime);
    })

}

/*提示*/
function tip_change(flag,parent_div,tip) {
    if(flag){
        parent_div.find(".validation").css("opacity","1");
        parent_div.find(".tip_div").hide(500);
    }else{
        parent_div.find(".validation").css("opacity","0");
        parent_div.find(".tip_div > h5").text(tip);
        parent_div.find(".tip_div").show(500);
    }

}



/*注册*/
function commit_register() {

    /*校验电话码格式正则 */
    // var tel_number= /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
    /*校验邮件地址是否合法正则 */
    // var reg=/^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;

    //账号正则
    var loginid_regexp = /^[a-zA-z0-9]{5,15}$/;
    //密码正则
    var pwd_regexp = /^[a-z0-9A-Z]{6,16}/;
    //昵称正则
    var nickname_regexp = /['"]/;

    /*验证账号可用性*/
    $("#reg_loginid_input").on("blur",function () {
        var reg_loginid = $(this).val();
        var parent_div = $(this).parent();
        if (reg_loginid != "" && loginid_regexp.test(reg_loginid)){
            $.post(serverUrl+"/userdeal/loginidisuse",{"userLoginId":reg_loginid},function (data) {
                if(data.code == "1"){
                    tip_change(true,parent_div,null);
                }else{
                    tip_change(false,parent_div,"该账号已被注册");
                }
            });

        }else{
            tip_change(false,parent_div,"请输入5~15位数字或字母的账号");
        }
    });

    /*验证密码正则*/
    $("#reg_password_input").on("blur",function () {
        var reg_password = $(this).val();
        var reg_peat_password = $("#reg_repeat_password_input").val();
        if(reg_password != "" && pwd_regexp.test(reg_password)){
            tip_change(true,$(this).parent(),null);
        }else{
            tip_change(false,$(this).parent(),"请输入6~15位数字或字母的密码");
        }
        /*两次密码是否相同验证*/
        if (reg_peat_password == reg_password){
            tip_change(true,$("#reg_repeat_password_input").parent(),null);
        } else{
            tip_change(false,$("#reg_repeat_password_input").parent(),"两次密码不相同");
        }

    });

    /*确认密码*/
    $("#reg_repeat_password_input").on("blur",function () {
        var reg_password = $("#reg_password_input").val();
        var reg_peat_password = $(this).val();
        if (reg_peat_password == reg_password){
            tip_change(true,$(this).parent(),null);
        } else{
            tip_change(false,$(this).parent(),"两次密码不相同");
        }
    });

    /*昵称*/
    $("#reg_nickname").on("blur",function () {
        var reg_nickname = $(this).val();
        if (reg_nickname != "" && !nickname_regexp.test(reg_nickname)){
            tip_change(true,$(this).parent(),null);
        } else{
            tip_change(false,$(this).parent(),"昵称不能为空或存在引号等特殊符号");
        }
    });

    /*点击注册按钮*/
    $("#register_btn").on("click",function () {

        var isOk = $(".register-container").find(".validation");

        for(var i = 0;i<isOk.length;i++){
            if (isOk.eq(i).css("opacity") != "1"){
                alert("您的信息填写有误，请检查")
                return;
            }
        }
        var reg_loginid_input = $("#reg_loginid_input").val();
        var reg_password_input = $("#reg_password_input").val();
        var reg_repeat_password_input = $("#reg_repeat_password_input").val();
        var reg_nickname = $("#reg_nickname").val();

        var formData = new FormData();
        formData.append("userLoginId",reg_loginid_input);
        formData.append("userPassword",reg_password_input);
        formData.append("userNickname",reg_nickname);

        $.ajax({
            url:serverUrl+"/userdeal/userregister",
            type: 'POST',
            data:formData,
            async:true,//异步请求
            // cache: false,缓存，get请求有效，true缓存
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.code == "1"){
                    alert(data.tip);
                    $(".find_password-container").hide(animateTime);
                    $(".register-container").hide(animateTime);
                    $(".login-container").show(animateTime);
                    $("#login_id_input").val(reg_loginid_input);

                }else{
                    alert(data.tip);
                }
            },
            error: function (err) {
                alert(err);
            }

        });
        
    });

}

/*登陆*/
function login(captchaObj) {
    $("#login_btn").on("click",function () {
        //验证码判断
        var validate = captchaObj.getValidate();
        if (!validate){
            alert("请先点击完成验证")
        }else{
            var login_login_id = $("#login_id_input").val();
            var login_password = $("#password_input").val();
            var formData = new FormData();
            formData.append("userLoginId",login_login_id);
            formData.append("userPassword",login_password);

            $.ajax({
                url:serverUrl+"/userdeal/login",
                type:"POST",
                data:formData,
                async:true,
                // cache: false,缓存，get请求有效，true缓存
                contentType: false,
                processData: false,
                success:function (data) {
                    if (data.code == 1){
                        alert(data.tip);
                        // console.log(data);
                        $.cookie('_userLoginId', login_login_id, {path: '/' });
                        window.location.href = "../home.html";
                    }else{
                        alert(data.tip)
                    }
                },
                error: function (err) {
                    alert(err);
                }

            });
        }

    });
}