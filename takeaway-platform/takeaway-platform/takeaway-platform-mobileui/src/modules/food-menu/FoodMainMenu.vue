<template>
  <div>
    <el-container>
      <el-header height="calc(19vh)" style="padding: 0">
        <mu-carousel id="banner">
          <!--<mu-carousel-item v-for="(banner, index) in bannerList" :key="index">-->
          <!--<img :src="banner.imageUrl">-->
          <!--{{banner.imageUrl}}-->
          <!--</mu-carousel-item>-->
          <mu-carousel-item>
            <img src="../../assets/banner5.jpg" style="width: 100%">
          </mu-carousel-item>
          <mu-carousel-item>
            <img src="../../assets/banner6.jpg" style="width: 100%">
          </mu-carousel-item>
        </mu-carousel>
      </el-header>
      <el-container>
        <el-aside width="120px">
          <el-menu class="el-menu-vertical-demo"
                   background-color="#fff"
                   text-color="#af7c8e"
                   active-text-color="#f23ba9"
                   :router="menuRouter">
            <el-menu-item index="/menu/dairy">
              <i class="el-icon-bell"></i>
              <span slot="title">醇享原奶</span>
            </el-menu-item>
            <el-menu-item index="/menu/tea">
              <i class="el-icon-menu"></i>
              <span slot="title">芝芝茗茶</span>
            </el-menu-item>
            <el-menu-item index="/menu/new">
              <i class="el-icon-menu"></i>
              <span slot="title">茶语咖啡</span>
            </el-menu-item>
            <el-menu-item index="/menu/top">
              <i class="el-icon-menu"></i>
              <span slot="title">人气必喝</span>
            </el-menu-item>
            <el-menu-item index="/menu/other">
              <i class="el-icon-menu"></i>
              <span slot="title">茶实验室</span>
            </el-menu-item>
            <el-menu-item index="/menu/other">
              <i class="el-icon-menu"></i>
              <span slot="title">果茶家族</span>
            </el-menu-item>
            <el-menu-item index="/menu/other">
              <i class="el-icon-menu"></i>
              <span slot="title">波波家族</span>
            </el-menu-item>
            <el-menu-item index="/menu/other">
              <i class="el-icon-menu"></i>
              <span slot="title">茶品制冰</span>
            </el-menu-item>
            <el-menu-item index="other">
              <i class="el-icon-menu"></i>
              <span slot="title">纯茶</span>
            </el-menu-item>
            <el-menu-item index="other">
              <i class="el-icon-menu"></i>
              <span slot="title">纯茶</span>
            </el-menu-item>
            <el-menu-item index="other">
              <i class="el-icon-menu"></i>
              <span slot="title">纯茶</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
          <el-footer></el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import {mapState, mapMutations} from 'vuex'

  export default {
    name: 'FoodMainMenu',
    data() {
      return {
        menuRouter: true,
        bannerList: [
          {imageUrl: '/takeaway/platform/mui/static/img/banner5.jpg'},
          {imageUrl: '/takeaway/platform/mui/static/img/banner6.jpg'}
        ]
      }
    },
    beforeMount() {
      this.getProductList()
    },
    methods: {
      ...mapMutations([
        'setProductList'
      ]),
      switchTab() {

      },
      getProductList() {
        const loading = this.$loading({lock: true, text: '查询中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.get("/drink/product/list")
          .then(res => {
            let productList = res.data;
            for(let i in productList) {
              productList[i].imageUrl = 'http://localhost:9220/takeaway/api/image/' + productList[i].imageId
            }
            this.setProductList(productList)
          }).catch(err => {
          console.error(err)
        }).then(res => {
          loading.close()
        })
      }
    }
  }
</script>

<style scoped>
  #banner {
    height: calc(19vh);
    width: 100%;
  }

  #menu-header {
    height: calc(23vh);
    padding: 0;
  }
</style>
