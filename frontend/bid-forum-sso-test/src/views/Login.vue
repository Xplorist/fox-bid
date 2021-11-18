<template>
    <div class="login">
        用户：<input type="text" v-model="username"><br>
        密码：<input type="password" v-model="password"><br>
        <button v-on:click="login()">登录</button>
    </div>
</template>

<script>
import Vue from 'vue'
import jsonp from 'vue-jsonp'

Vue.use(jsonp)

export default {
    name: 'login',
    props: {

    },
    data: function () {
        return {
            username: '',
            password: '',
        };
    },
    methods: {
        login () {
           Vue.jsonp(
               'http://10.244.231.103:14000/bidsso/' + 'login', 
                {
                 username: this.username, 
                 password: this.md5(this.password), 
                 callbackName: 'jsonpFunc'
                }
           ).then((response) => {
               if (response.code === '1') {
                // 登录成功，将返回的用户id和token更新至store
                let t = response.t;
                /*
                this.$store.commit('updateLoginInfo', {
                    userId: t.userId,
                    token: t.token
                });
                */
                sessionStorage.setItem('userId', t.userId);
                sessionStorage.setItem('token', t.token);
                // 跳转至论坛首页
                this.$router.push('forum');
               } else {
                alert(response.msg);
               }
           }); 
        },
    },
    created: function () {

    }
}
</script>