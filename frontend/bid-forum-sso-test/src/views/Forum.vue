<template>
    <div class="forum">
        <button v-on:click="logout()">注销</button><br>
        <span>{{ userId }}</span><br>
        <span>{{ token }}</span>
        
    </div>
</template>

<script>
import Vue from 'vue'
import jsonp from 'vue-jsonp'

Vue.use(jsonp)

export default {
    name: 'forum',
    props: {

    },
    data: function () {
        return {
            userId: '',
            token: '',
        };
    },
    computed: {
       
    },
    methods: {
         getLoginInfo () {
            /*
            this.userId = this.$store.state.userId;
            this.token = this.$store.state.token;
            window.console.log('userId = ' + this.$store.state.userId);
            window.console.log('token = ' + this.$store.state.token);
            */
            this.userId = sessionStorage.getItem('userId');
            this.token = sessionStorage.getItem('token');
        },
        logout () {
            let flag = confirm('确定登出？');
            if (!flag) {
                return;
            }
            Vue.jsonp(
                'http://10.244.231.103:14000/bidsso/' + 'logout',
                {
                    callbackName: 'jsonpFunc'
                }
            ).then((response) => {
                alert(response.msg);
                if (response.code === '1') {
                    sessionStorage.clear();
                    this.$router.push('login');
                }
            });
        },
    },
    created: function () {
        this.getLoginInfo();
    }
}
</script>