var app = new Vue({
    el: '#app',
    data: {
        username: '',
        password: '',
        confirmPSW: '',
        encodedPSW: '',
        email: ''
    },
    methods: {
        register: function() {
            var _self = this;

            if(_self.username == "" || _self.password == "" || _self.email == "") {
                alert("用戶信息不能為空");
                return;
            }

            if(_self.confirmPSW != _self.password) {
                alert("兩次密碼輸入不一致");
                return;
            }
            _self.encodedPSW = md5(_self.password);

            axios({
                method: 'post',
                url: '/user/register',
                data: {
                    username: _self.username,
                    password: _self.encodedPSW,
                    email: _self.email
                }
            }).then(function(response) {
                var result = response.data;
                if(result.code == "1") {
                    alert("註冊成功" + result.msg);
                } else {
                    alert("註冊失敗" + result.msg);
                }
            });
        }
    },
    created: function() {

    }
});