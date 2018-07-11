
//初始化
function initVerification() {
    var handlerEmbed = function (captchaObj) {

        login(captchaObj)

        // // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
        captchaObj.appendTo("#nowlogin");
        captchaObj.onReady(function () {
            $("#nowlogin").html("");
        });

    }

    //验证码--------------------------------------------------
    $.ajax({
        // 获取id，challenge，success（是否启用failback）
        url: "/demo/gt/register1?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            // 使用initGeetest接口
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                new_captcha: data.new_captcha,
                product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handlerEmbed);
        }
    });
//yzm--------------------------------------------------

}


function verificationLogin(validate) {
    //将返回结果存入validate
    if(!validate){
        code=-1;
    }else{
        code=1;
    }
}

/*判断验证是否成功*/
function submitVerification() {

}
