<div class="top_wrap">
    <div class="top">
        <p class="fl phone">欢迎致电：021-67690939</p>
    </div>
</div>
<div class="header">
    <div class="contain">
        <a href="/index?0?0" class="logo">
        </a>
        <div class="header_nav" id="indexNav">
            <a href="/index?0?0">首页</a>
            <a href="/item/investmentList?0?1">我要投资</a>
            <a href="/security?0?2">安全保障</a>
            <a href="/user/assets?0?3">我的账户</a>
            <a href="/introduce?0?4">关于我们</a>
        </div>
        <div class="header_button">
            <#if user??>
                <div id="hasUserId">

                    <div class='btn login' style="margin:36px auto 0; width: 164px;">
                         <p>${basUser.mobile}<img style="margin:15px 0 0 5px;" src="/img/xl-icon.png" alt=""></p>
                    </div>
                    <div id="option" class="option display">
                        <div class="option-message">
                            <a class="selected" href="/user/site?-1?3" style="cursor:pointer;">消息中心</a>
                        </div>
                        <div class="option-two">
                            <a class="selected2" href="/user/log?3?3" style="cursor:pointer;">资金记录</a>
                        </div>
                        <div class="option-two">
                            <a class="selected3" href="/user/inviteCode?5?3" style="cursor:pointer;">邀请好友</a>
                        </div>
                        <div class="option-two">
                            <a class="selected4" href="/loginOut" id="exit" style="cursor:pointer;">我要退出</a>
                        </div>
                    </div>
                </div>
            <#else>
                <div id="noUserId" style="width:142px;float: right">
                    <a href="${ctx}/user/toLoginPage"><input class='btn register' id='loginPage' type="button" value="登录"></a>
                    <a href="${ctx}/user/toRegisterPage"><input class='btn register' id='registerPage' type="button" value="注册"></a>
                </div>
            </#if>
        </div>
    </div>
</div>
