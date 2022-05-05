<template>
  <div id="app">
    <router-view/>

    <mu-bottom-nav id="nav" @change="navChange">
      <mu-bottom-nav-item title="点单" icon="restore"></mu-bottom-nav-item>
      <mu-bottom-nav-item title="订单" icon="reorder"></mu-bottom-nav-item>
      <mu-bottom-nav-item title="我的" icon="face"></mu-bottom-nav-item>
    </mu-bottom-nav>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex'
export default {
  name: 'App',
  created() {
    let userId = '123'
    this.getUserInfo(userId);
    this.getDict()

    sessionStorage.userId = '123'
  },
  methods: {
    ...mapMutations([
      'setUser',
      'setMixDict'
    ]),
    navChange(v) {
      console.log(typeof v)
      switch (v) {
        case 0 :
          this.$router.push({path: '/'})
          break;
        case 1:
          this.$router.push({name: 'OrdersList'})
          break;
        case 2:
          this.$router.push({name: 'User'})
          break;
      }
    },
    getUserInfo(userId){
      let url = '/user/' + userId;
      this.$axios.get(url)
        .then(res => {
          this.setUser(res.data)
        }).catch(err => {
      })
    },
    getDict() {
      let mixDict = [
        {
          label: "小米",
          value: "M-1"
        },
        {
          label: "布丁",
          value: "M-2"
        },
        {
          label: "红米",
          value: "M-3"
        },
        {
          label: "珍珠",
          value: "M-4"
        },
        {
          label: "燕麦",
          value: "M-5"
        },
        {
          label: "咖啡冻",
          value: "M-6"
        },
      ]
      this.setMixDict(mixDict)
      sessionStorage.mixDict = JSON.stringify(mixDict)
    }
  }
}
</script>

<style>

  header {
    padding: 0;
    height: 0;
  }
#app {
  max-width: 600px;
  max-height: 600px;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
  #nav {
    position: absolute;
    bottom: 0;
    width: 100%;
  }
  .el-main {
    padding: 0;
  }
</style>
