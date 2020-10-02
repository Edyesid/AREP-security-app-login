var app = (function () {
    function loginfunction() {
        var user = $("#inputuser").val();
        var password = $("#inputpassword").val();
        var data = {"user":user, "password": password}
        console.log(data);
        apiclient.login(data,redirect);
    }

    function redirect(res) {
        if (res != null) {
            alert("error en el usuario o contraseña");
            return;
        }
        console.log("entro login");
        location.href = "/user.html";
    }

    function functionverify(bool) {
        if (JSON.parse(bool)) {
            return;
        }
        location.href = "/index.html";
    }

    function functionverifytwo(bool) {
        if (JSON.parse(bool)) {
            location.href = "/user.html";
        }
        return;
    }

    function offlogin() {
        apiclient.isLogin(functionverify);
    }
    return {
        login: function() {
            loginfunction();
        },
        verifylogin : function() {
            apiclient.isLogin(functionverify);
        },
        offlogin: function() {
            apiclient.off(offlogin);
        },
        verifylogintwo: function() {
            apiclient.isLogin(functionverifytwo);
        }
    }
})();