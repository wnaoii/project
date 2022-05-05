<template>
  <div>
    <mu-list textline="three-line" >
      <mu-sub-header class="modules-title">芝芝茗茶</mu-sub-header>
      <mu-list-item class="p-list" avatar :ripple="false" button v-for="product in teaList" :key="product.productId">
        <mu-list-item-action>
          <!--<img src="../../assets/naicha3.jpg" style="width: 50px;">-->
          <el-image :src="product.imageUrl" style="width: 50px;" fit="fill"></el-image>
        </mu-list-item-action>
        <mu-list-item-content>
          <mu-list-item-title style="font-weight: 600">{{product.pTitle}}</mu-list-item-title>
          <mu-list-item-sub-title>
            <span style="color: rgba(0, 0, 0, .87)">推荐 -</span> {{product.pDesc}}
          </mu-list-item-sub-title>
          <mu-list-item-sub-title>
            <span style="color: #2c3e50">￥{{product.price}}</span><span><span class="c-btn" @click="showDialog(product)">选规格</span></span>
          </mu-list-item-sub-title>
        </mu-list-item-content>
      </mu-list-item>
    </mu-list>

    <selector v-show="selectorv" :addDialogVisible="addDialogVisible" :product-orders="selectedProduct" @close-dialog="closeDialog"></selector>
  </div>
</template>

<script>
  import Selector from "../../components/Selector";
  export default {
    name: 'Tea',
    data() {
      return {
        addDialogVisible: false,
        selectedProduct: {},
        selectorv: false
      }
    },
    components: {Selector},
    computed: {
      teaList() {
        return this.$store.state.product.productList.filter((product => product.pType === 'tea'))
      }
    },
    methods: {
      showDialog(product) {
        console.log("p %o", product)
        this.selectorv = true
        this.addDialogVisible = true;
        this.selectedProduct = product
      },
      closeDialog() {
        this.addDialogVisible = false;
        this.selectorv = false
      }
    }
  }
</script>

<style scoped>
  .c-btn {
    border-radius: 8px;
    background-color: #cdad87;
    padding: 3px;
    font-size: 12px;
    position: absolute;
    right: 16%;
    color: white;
  }
  .p-list {
    margin: 20px 5px;
  }
  .modules-title {
    text-align: left;
    position: relative;
    top: 8px;
  }
</style>
