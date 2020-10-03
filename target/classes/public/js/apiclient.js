var apiclient = (function () {

    return {
        isLogin: function(callback) {
            var promise = $.get({
                url: "/islogin"
            });
            promise.then((bool) => callback(bool));
        },
        login: function(data,callback) {
            var promise = $.post({
                url: "/login",
                data: JSON.stringify(data)
            });
            promise.then(() => callback(null),
                        (error) => callback(error));
        },
        off: function(callback) {
            var promise = $.get({
                url: "/off",
            });
            promise.then(callback);
        }
    }
})();