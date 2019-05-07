
$(function(){
    getPic();
    $('#loginButton').click(function(){
        var username = $('#username').val();
        var password = $('#password').val();
        var captchar = $('#captchar').val();

        var username = username?username:'admin';
        var password =  password?password:'admin';
        var captchar = 'AK47';
        // username?password?captchar?1:alert('请输入验证码'):alert('请输入密码'):alert('请输入用户名');

        // console.log('hello world');
        // console.log('glng',username,password,captchar);
        var data = {username:username,password:password,captcha:captchar};
        $.post(HOST + '/xtgn-yhlb/login',data,function(result){
            getPic();
            if(result.status == FAILED)alert(result.message);
            if(result.status == SUCCESS){
                result = result.result;
                window.localStorage.ACCTOUN_USER = JSON.stringify(result);
                // console.log('>>>>>>>>>',result);
                DEPTS_REF_FUNC();
                window.location.href = './index.html';
            }
            // console.log(result);
        });
    });

    
});