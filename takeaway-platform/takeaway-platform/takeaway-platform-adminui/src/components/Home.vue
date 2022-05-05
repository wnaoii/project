<template>
  <div>
    <el-container>
      <el-header class="menu-header">
        <div class="namebox">欢迎登录，{{}}
          <span class="loginout"  @click="loginout" style="margin-left:30px; ">
          [&nbsp;<span style="text-decoration: underline;cursor: pointer">退出</span>&nbsp;]
        </span>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
                   @select="menuSelect"
                   :router="menuRouter">
            <el-menu-item-group>
              <el-menu-item index="/home/overview">
                <el-icon class="el-icon-s-finance"></el-icon>
                总览
              </el-menu-item>
              <el-menu-item index="/home/product/list">
                <el-icon class="el-icon-goods"></el-icon>
                产品维护
              </el-menu-item>
              <el-menu-item index="/home/orders">
                <el-icon class="el-icon-s-order"></el-icon>
                订单管理
              </el-menu-item>
              <el-menu-item index="/home/add/stock">
                <el-icon class="el-icon-s-grid"></el-icon>
                库存管理
              </el-menu-item>
              <el-menu-item index="/home/user" @click="tips">
                <el-icon class="el-icon-s-finance"></el-icon>
                用户管理
              </el-menu-item>
              <el-menu-item index="/home/ad">
                <el-icon class="el-icon-s-finance"></el-icon>
                广告推送
              </el-menu-item>
            </el-menu-item-group>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        menuRouter: true,
        isActive: true
      }
    },
    methods: {
      loginout (key, keyPath) {
        localStorage.clear();
      },
      menuSelect (key, keyPath) {
        this.isActive = key;
      },
      tips() {
        this.$message.warning("功能尚在开发中....")
      }
    }
  }
</script>

<style scoped>
  .menu-header {
    background-color: burlywood;
  }
  .namebox{
    color: #fff;
    font-size: 14px;
    line-height: 30px;
    width: 30%;
    float: right;
    text-align: right;
    margin: 15px 20px;
  }
</style>
