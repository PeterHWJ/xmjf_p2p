$(function () {
    
    
    $(".validImg").click(function () {
        $(this).attr("src",ctx+"/kaptcha/getKaptchaImage?time="+new Date());
    });

    $("#clickMes").click(function () {
        var phone = $("#phone").val();
        var code = $("#code").val();

        if(isEmpty(phone)){
            layer.tips('手机号不能为空！', '#phone');
            return;
        }
        if(isEmpty(code)){
            layer.tips('图形验证码不能为空！', '#code');
            return;
        }

        var _this=$(this);

        $.ajax({
            type:"post",
            url:ctx+"sms/sendMobileVerifyCode",
            data:{
                mobile:phone,
                kaptchaCode:code
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    time(_this);
                }else {
                    layer.tips(data.msg, '#clickMes');
                }

            }
        });

    });

    $("#register").click(function () {
        var phone = $("#phone").val();
        var code = $("#code").val();
        var verification=$("#verification").val();
        var password=$("#password").val();

        if(isEmpty(phone)){
            layer.tips('手机号不能为空！', '#phone');
            return;
        }
        if(isEmpty(code)){
            layer.tips('图形验证码不能为空！', '#code');
            return;
        }
        if(isEmpty(verification)){
            layer.tips('手机验证码不能为空！', '#verification');
            return;
        }
        if(isEmpty(password)){
            layer.tips('密码不能为空！', '#password');
            return;
        }

        $.ajax({
            type:"post",
            url:ctx+"user/register",
            data:{
                phone:phone,
                picVerifyCode:code,
                phoneVerifyCode:verification,
                password:password
            },
            dataType:"json",
            success:function (data) {

            }
        });
    });
})

var wait=6;
function time(o) {
    if (wait == 0) {
        o.removeAttr("disabled");
        o.val('获取验证码');
        o.css("color", '#ffffff');
        o.css("background","#fcb22f");

        wait = 6;
    } else {
        o.attr("disabled", true);
        o.css("color", '#fff');
        o.css("background", '#ddd');
        o.val("重新发送(" + wait + "s)");
        wait--;
        setTimeout(function() {
            time(o)
        }, 1000)
    }
}
