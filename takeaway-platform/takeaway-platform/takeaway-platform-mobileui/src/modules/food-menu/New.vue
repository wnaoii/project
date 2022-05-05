<template>
  <div>
    <mu-list textline="three-line" >
      <mu-sub-header class="modules-title">茶语咖啡</mu-sub-header>
      <mu-list-item class="p-list" avatar :ripple="false" button v-for="product in newList" :key="product.productId">
        <mu-list-item-action>
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

    <selector v-show="selectorv" :addDialogVisible="addDialogVisible" :product-orders="selectedProduct"></selector>
  </div>
</template>

<script>
  import Selector from "../../components/Selector";
  export default {
    name: 'New',
    components: {Selector},
    data() {
      return {
        addDialogVisible: false,
        selectedProduct: {},
        selectorv: false
      }
    },
    computed: {
      newList() {
        return this.$store.state.product.productList.filter((product => product.pType === 'coffee'))
      }
    },
    methods: {
      showDialog(product) {
        console.log("p %o", product)
        this.selectorv = true
        this.addDialogVisible = true;
        this.selectedProduct = product
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
