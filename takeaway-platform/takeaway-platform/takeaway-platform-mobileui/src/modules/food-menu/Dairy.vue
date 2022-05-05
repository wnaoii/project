<template>
  <div>
    <mu-list textline="three-line" >
      <mu-sub-header class="modules-title">醇香原奶</mu-sub-header>
      <mu-list-item class="p-list" avatar :ripple="false" button v-for="product in dairyList" :key="product.productId">
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
            <span style="color: #2c3e50">￥{{product.price}}</span><span><span class="c-btn" @click="choose(product)">选规格</span></span>
          </mu-list-item-sub-title>
        </mu-list-item-content>
      </mu-list-item>
    </mu-list>

<!-- todo 组件化-->
    <el-dialog title="选规格" :visible.sync="addDialogVisible" style="font-size: 12px;" width="80%">
      <div>
        <el-form ref="spreadItem" :inline="true" :model="item">
          <div class="dialog-sub-title">加料</div>
          <el-form-item prop="appName">
            <el-checkbox-group v-model="mixList">
              <el-checkbox label="M-1">小米</el-checkbox>
              <el-checkbox label="M-2">布丁</el-checkbox>
              <el-checkbox label="M-3">红米</el-checkbox>
              <el-checkbox label="M-4">珍珠</el-checkbox>
              <el-checkbox label="M-5">燕麦</el-checkbox>
              <el-checkbox label="M-6">咖啡冻</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <div class="dialog-sub-title">温度</div>
          <el-form-item prop="typeId" >
            <el-radio-group v-model="wd">
              <el-radio :label="3">加冰</el-radio>
              <el-radio :label="6">去冰</el-radio>
              <el-radio :label="9">加热</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="productId" label="数量">
            <el-input-number size="mini" :min="numMin" v-model="num"></el-input-number>
          </el-form-item>
          <div>
            <el-row>
              <el-col :span="4" style="text-align: center; font-weight: 600">
                价格
              </el-col>
              <el-col :span="20" style="text-align: center">
                {{sellingPrice}}
              </el-col>
            </el-row>
          </div>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button class="buy-btn" @click="buy" >购买</el-button>
        <el-button @click="addDialogVisible = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState, mapMutations, mapGetters} from 'vuex'
  import Selector from "../../components/Selector";
  export default {
    name: 'Dairy',
    components: {Selector},
    data () {
      return {
        addDialogVisible: false,
        item: {
        },
        mixList: [],
        wd: "",
        num: "1",
        selectedProduct: {},

        numMin: 0
      }
    },
    computed: {
      // ...mapState({
      //   productList: state => state.product.productList
      // }),
      /*...mapGetters({
        dairyList: state => state.product.dairyList
      })*/
      dairyList() {
        /*console.log(this.$store.state.product.productList)
        let a = _.filter(this.$store.state.product.productList, (product) => {
          return product.pType === 'dairy'
        })
        console.log(a)*/
        return this.$store.state.product.productList.filter((product => product.pType === 'dairy'))
      },
      sellingPrice () {
        return this.selectedProduct.price * this.num + this.mixList.length * 1.5
      }
    },
    methods: {
      ...mapMutations([
        'setProductOrder'
      ]),
      buy() {
        let params = {
          productId: this.selectedProduct.productId,
          num: this.num,
          mixList: this.mixList,
          price: this.sellingPrice
        }
        this.setProductOrder(params);
        this.$router.push({name: "PreOrder"})
      },
      choose(product) {
        this.selectedProduct = product;
        this.addDialogVisible = true;
      }
    }
  }
</script>

<style scoped>
  .item-list {
    width: 50px;
  }
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
  .buy-btn{
    background-color: #cdad87;
    color: white;
  }
  .dialog-sub-title {
    text-align: left;
    color: #cdad87;
    font-weight: 600;
  }
</style>
