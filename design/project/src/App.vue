<template>
  <div id="app">
    <home></home>
  </div>
</template>

<script>
import home from "./pages/home"
export default {
  components:{home}
}
</script>

<style>
html,body{
  padding: 0;
  margin: 0;
  height: 100%;
}
body{
  background: rgb(211, 223, 231)
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
