<template>
    <div >
      <el-dialog title="选规格" :visible.sync="addDialogVisible" style="font-size: 12px;" width="80%" :show-close="false">
        <div>
          <el-form ref="spreadItem" :inline="true">
            <div class="dialog-sub-title">加料</div>
            <div style="text-align: left; font-size: 12px">1.5元/份</div>
            <el-form-item prop="appName">
              <el-checkbox-group v-model="item.mixList">
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
              <el-radio-group v-model="item.wd">
                <el-radio :label="3">加冰</el-radio>
                <el-radio :label="6">去冰</el-radio>
                <el-radio :label="9">加热</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item prop="productId" label="数量">
              <el-input-number size="mini" :min="min" v-model="item.num"></el-input-number>
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
          <el-button class="buy-btn" @click="buy" type="primary" >购买</el-button>
          <el-button @click="closeDialog">取消</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
  import {mapState, mapMutations, mapGetters} from 'vuex'
    export default {
      name: "Selector",
      props: {
        addDialogVisible: Boolean,
        productOrders: Object
      },
      mounted() {
      },
      data() {
        return {
          min: 1,
          item: {
            num: 1,
            mixList: [],
            wd: ''
          },
          localProductOrders: this.productOrders
        }
      },
      computed: {
        sellingPrice () {
          return this.productOrders.price * this.item.num + this.item.mixList.length * 1.5
          // return 0
        }
      },
      methods: {
        ...mapMutations([
          'setProductOrder'
        ]),
        buy() {
          let params = {
            productId: this.productOrders.productId,
            num: this.item.num,
            mixList: this.item.mixList,
            price: this.sellingPrice
          }
          console.log("params %o", params)
          this.setProductOrder(params);
          this.$router.push({name: "PreOrder"})
        },
        choose(product) {
          this.selectedProduct = product;
          this.addDialogVisible = true;
        },
        closeDialog() {
          this.$emit('close-dialog');
        }
      }
    }
</script>

<style scoped>
  .dialog-sub-title {
    text-align: left;
    color: #cdad87;
    font-weight: 600;
  }
</style>
