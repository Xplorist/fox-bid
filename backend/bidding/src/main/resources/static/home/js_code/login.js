var app = new Vue({
    el: '#app',
    data: {
        username: '',
        password: '',
        encodedPSW: '',
    },
    methods: {
        goto_register: function () {
            window.location.href = "register.html";
        },
        login: function () {
            var _self = this;
            _self.encodedPSW = md5(_self.password);

            axios({
                method: 'post',
                url: '/user/login',
                data: {
                    username: _self.username,
                    password: _self.encodedPSW
                }
            }).then(function(response) {
                var result = response.data;
                if(result.code == "1") {
                    alert("登錄成功:" + result.msg);
                    document.cookie = result.t;
                    //window.location.href = "recv/index.html";
                } else {
                    alert("登錄失敗:" + result.msg);
                }
            });
        },
        test_token: function () {
            var _self = this;
            var token = document.cookie.split(";")[0];

            if(_self.username == '') {
                alert("用戶名不能為空");
                return;
            }

            axios({
                method: 'get',
                url: '/user/getUser?username=' + _self.username,
                headers: {
                    'token': token
                },
                data: ''
            }).then(function(response) {
                var result = response.data;
                if(result.code == "1") {
                    alert("驗證token成功:" + result.msg);
                    //window.location.href = "recv/index.html";
                } else {
                    alert("驗證token失敗:" + result.msg);
                }
            });
        }
    },
    created: function() {

    }
});