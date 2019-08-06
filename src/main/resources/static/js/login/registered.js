function registered() {
    var name=$('#registeredName').val();
    var password=$('#registeredPassword').val();
    var twoPassword=$('#registeredTwePassword').val();
    var phone=$('#regPhone').val();
    if(name==null||name==""){
        alert("用户名不可以为空！");
    }else if(phone==null||phone==''){
        alert("手机号不可以为空！");
    }else if(password==null||password==""){
        alert("密码不可以为空！");
    }else if(twoPassword==null||twoPassword==""){
        alert("二次输入密码不可以为空！");
    }else if(password!=twoPassword){
        alert("两次密码输入不一样！");
    }else {
        $.ajax({
            type : "POST",
            url : "registered",
            contentType : "application/json;charset=utf-8",
            data : JSON.stringify({'name' : name , 'password' : password , 'phone' : phone}),
            dataType : "json",
            success : function (data) {
                var res=JSON.parse(JSON.stringify(data));
                if(res.status == '0000'){
                    alert(res.msg);

                }else {
                    alert(res.msg);
                }
            },
            error : function (message) {
                alert("提交失败"+JSON.stringify(message));
            }
        });
    }

}
