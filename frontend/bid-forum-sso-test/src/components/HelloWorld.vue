<template>
  <div class="hello">
    <!--
    <h1>{{ msg }}</h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3>Installed CLI Plugins</h3>
    <ul>
      <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel" target="_blank" rel="noopener">babel</a></li>
      <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint" target="_blank" rel="noopener">eslint</a></li>
    </ul>
    <h3>Essential Links</h3>
    <ul>
      <li><a href="https://vuejs.org" target="_blank" rel="noopener">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank" rel="noopener">Forum</a></li>
      <li><a href="https://chat.vuejs.org" target="_blank" rel="noopener">Community Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank" rel="noopener">Twitter</a></li>
      <li><a href="https://news.vuejs.org" target="_blank" rel="noopener">News</a></li>
    </ul>
    <h3>Ecosystem</h3>
    <ul>
      <li><a href="https://router.vuejs.org" target="_blank" rel="noopener">vue-router</a></li>
      <li><a href="https://vuex.vuejs.org" target="_blank" rel="noopener">vuex</a></li>
      <li><a href="https://github.com/vuejs/vue-devtools#vue-devtools" target="_blank" rel="noopener">vue-devtools</a></li>
      <li><a href="https://vue-loader.vuejs.org" target="_blank" rel="noopener">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank" rel="noopener">awesome-vue</a></li>
    </ul>
    -->
  </div>
</template>

<script>
import Vue from 'vue'
import VueJsonp from 'vue-jsonp'

Vue.use(VueJsonp)

export default {
  name: 'HelloWorld',
  props: {
    msg: String
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
    // 验证是否登录
    loginValidate () {
      Vue.jsonp('http://10.244.231.103:14000/bidsso/' + 'loginValidate',
      {callbackName: 'jsonpFunc'}).then((response) => {
        if (response.code === '1') {
          let t = response.t;
          // 登录中心已登录，将返回的用户id存放至store中
          /*
          this.$store.commit('updateLoginInfo', {
            userId: t.userId,
            token: t.token
          });
          */
          sessionStorage.setItem('userId', t.userId);
          sessionStorage.setItem('token', t.token);
          this.getLoginInfo();
          // 进入论坛首页
          this.$router.push('forum');
        } else if (response.code === '0') {
          alert(response.msg);
          // 未登录或登录失效，跳转到登录界面
          this.$router.push('login');
        } else if (response.code === '-1') {
          alert(response.msg);
        }
        window.console.log(response);
      }).catch((err) => {
            alert(err);
            window.console.log(err);
      });
    },
    getLoginInfo () {
      this.userId = this.$store.state.userId;
      this.token = this.$store.state.token;
    },
  },
  created: function () {
    this.loginValidate();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
