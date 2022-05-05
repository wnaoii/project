<template xmlns="http://www.w3.org/1999/html">
  <div id="page" class="pages">
    <div class="contentFixed">

<!--      <div v-show="user.addressId.length <= 0">-->
<!--        <el-button type="primary" size="mini">添加地址</el-button>-->
<!--      </div>-->

      <el-row style="padding-top: 10px;">
        <el-col :span="8" class="goods-pic">
<!--          <el-image fit="fill" :src="product.imageUrl"></el-image>-->
          <img style="width: 100%" :src="product.imageUrl">
        </el-col>


        <el-col :span="15" style="padding-top:0px;padding-left:20px;">
          <div class="goods-title">{{product.pTitle}}</div>
          <div class="goods-description" ></div>

          <el-row class="goods-price">
            <el-col :span="2.5"
                    class="goods-price-text">单价：
            </el-col>
            <el-col :span="10"
                    class="goods-price-num">￥ {{product.price}}
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <mu-divider></mu-divider>
      <el-row class="goods-price">
        <el-col :span="12" style="line-height: 42px;font-weight: 300;width: 40%" class="goods-price-text">购买数量</el-col>

        <el-col :span="5"
                class="goods-number">
          {{productOrder.num}}
        </el-col>
      </el-row>
      <mu-divider></mu-divider>
      <div class="price-style">
        <div class="price-text">加料</div>
        <div class="price-detail">{{mixLabel}}</div>
      </div>
      <div class="price-style">
        <div class="price-text">运费</div>
        <div class="price-detail">￥ {{carriage}} </div>
      </div>
      <div class="price-style">
        <div class="price-text">商品总价</div>
        <div class="price-detail">￥{{totalPrice}}</div>
      </div>
      <div class="price-style" @click="showDialog">
        <div class="price-text">地址</div>
        <div v-show="address.length > 0"  class="price-detail">{{addressLabel}}</div>
        <div v-show="address.length <= 0"  class="price-detail">
<!--          <el-button type="primary" size="mini" @click="showDialog">添加地址</el-button>-->
          <div>请选择地址</div>
        </div>
      </div>
    </div>
    <div style="    text-align: left;
    margin: 15px;
    font-size: 12px;
    color: #9fa1a2;">
      说明：浦东地区运费5元，此外所有运费10元
    </div>
    <div>
      <div id="order-btn">
        <el-button type="primary" @click="pay">确认下单</el-button>
      </div>
    </div>

    <el-dialog title="添加地址" :visible.sync="addressDialogVisible" style="font-size: 12px;" width="80%">
      <div>
        <el-form ref="spreadItem" :inline="true">
          <el-cascader
            v-model="address"
            :options="addressOptions"
            @change="cascaderSelector"></el-cascader>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button class="buy-btn" @click="ok" >确定</el-button>
        <el-button @click="addDialogVisible = false">取消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import axios from "axios"
  import _ from 'lodash'
  import {mapState} from 'vuex'
  export default {
    name: 'PreOrder',
    data () {
      return {
        imageUrl: "",
        dealPrice: "",
        goodsInfo: {},
        goodsTitle: "",
        addressDialogVisible: false,
        address: "",

        product: {},

        addressOptions: [],

        num: "1",
        productName: ""
      }
    },
    filters: {
      mixFilter(v) {
        let mixLabel = ''
        let mixDict = JSON.parse(sessionStorage.mixDict)
        if(v.length <= 0) {
          return '无'
        }
        for (let i =0; i< v.length; i++) {
          for (let j = 0; j < mixDict.length; j++) {
            if(v[i] === mixDict[j].value) {
              mixLabel += '  ' + mixDict[j].label
            }
          }
        }
        return mixLabel
      },
      addressFilter(v) {
        let addressLabel = ''
        let addressDict = JSON.parse(sessionStorage.addressDict)
        for (let i = 0; i < v.length; i++) {
          for(let j = 0; j < addressDict.length; j++) {
            if(v[i] === addressDict[j].value) {
              addressLabel += '/' + addressDict[j].label
            }
          }
        }
        return addressLabel
      }
    },

    beforeCreate() {

    },
    created: function () {
      this.addressId = this.$route.query.addressId;
      this.getAddressDict()
    },
    computed: {
      ...mapState({
        user: state => state.user.user,
        productOrder: state => state.product.productOrder,
        productList: state => state.product.productList
      }),
      carriage() {
        if(this.address[1] === '0011') {
          return 5
        } else if (this.address.length <= 0) {
          return 0
        } else {
          return 10
        }
      },
      totalPrice() {
        return this.productOrder.price + this.carriage
      },
      addressLabel() {
        let addressLabel = ''
        let addressDict = JSON.parse(sessionStorage.addressDict)
        for (let i = 0; i < this.address.length; i++) {
          for(let j = 0; j < addressDict.length; j++) {
            if(this.address[i] === addressDict[j].value) {
              addressLabel += ' ' + addressDict[j].label
            }
          }
        }
        return addressLabel
      },
      mixLabel() {
        let mixLabel = ''
        let mixDict = JSON.parse(sessionStorage.mixDict)
        if(this.productOrder.mixList.length <= 0) {
          return '无'
        }
        for (let i =0; i< this.productOrder.mixList.length; i++) {
          for (let j = 0; j < mixDict.length; j++) {
            if(this.productOrder.mixList[i] === mixDict[j].value) {
              mixLabel += ' ' + mixDict[j].label
            }
          }
        }
        return mixLabel
      }
    },
    mounted(){
      this.getProduct(this.productOrder.productId)
    },
    methods: {
      //付款
      pay() {
        if(this.address.length <= 0) {
          this.$message.warning("请选择配送地址")
          return;
        }
        const loading = this.$loading({lock: true, text: '下单中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.post("/orders/", {
          mixList: this.productOrder.mixList,
          productId: this.productOrder.productId,
          userId: "123",
          address: this.address,
          num: this.productOrder.num,
          addressLabel: this.addressLabel,
          mixLabel: this.mixLabel
        })
          .then(res => {
            console.log(res.data)
            this.$router.push({name: 'OrdersList'})
          })
          .catch(err => {
            this.$hanlder.errorHandler(err)
          })
          .then(res => {
            loading.close()
          })
      },

      cascaderSelector(v) {
        console.log("sss %o",v)

      },
      showDialog() {
        this.addressDialogVisible = true
      },
      ok() {
        this.addressDialogVisible = false
      },
      getProduct(productId) {
        this.product = this.$_.find(this.productList, o => {
          return o.productId === productId
        })
      },
      getAddressDict() {
        let addressOptions = [{
          value: '001',
          label: '上海市',
          children: [
            {
              value: '0011',
              label: '浦东区',
              children: [{
                value: '00111',
                label: '金桥'
              }, {
                value: '00112',
                label: '云桥'
              }, {
                value: '00113',
                label: '张江'
              }]
            },
            {
              value: '0012',
              label: '黄埔区',
              children: [{
                value: '00121',
                label: '区一',
              },{
                value: '00122',
                label: '区二',
              }]
            },
            {
              value: '0013',
              label: '静安区',
            },
            {
              value: '0014',
              label: '松江区',
            },
            {
              value: '0015',
              label: '青浦区',
            },
            {
              value: '0016',
              label: '普陀区',
            },
            {
              value: '0017',
              label: '杨浦区',
            },
            {
              value: '0018',
              label: '长宁区',
            }
          ],
        }]
        let addressDict = [
          {
            value: '001',
            label: '上海市'
          },
          {
            value: '0011',
            label: '浦东区'
          },
          {
            value: '00111',
            label: '金桥'
          },
          {
            value: '00112',
            label: '云桥'
          },
          {
            value: '00113',
            label: '张江'
          },
          {
            value: '0012',
            label: '黄埔区'
          },
          {
            value: '00121',
            label: '区一'
          },
          {
            value: '00122',
            label: '区二'
          },
          {
            value: '0013',
            label: '静安区'
          },
          {
            value: '0014',
            label: '松江区'
          },
          {
            value: '0015',
            label: '青浦区'
          },
          {
            value: '0016',
            label: '普陀区'
          },
          {
            value: '0017',
            label: '杨浦区'
          },
          {
            value: '0018',
            label: '长宁区'
          }
        ]
        sessionStorage.addressDict = JSON.stringify(addressDict)
        this.addressOptions = addressOptions;
      }
    }
  }
</script>

<style scoped>
  body{
    user-select:none;
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    -o-user-select:none;
  }
  #order-btn {
    margin-top: calc(20vh);
  }
  #card-desc {
    /*text-align: left;*/
    /*padding-right: 5%;*/
    /*padding-left: 5%*/
  }

  #page {
    height: 100%;
    /*background: linear-gradient(rgb(255, 233, 233),rgb(0, 53, 101));*/
  }

  /* .container {
    display: flex;
    flex-direction: column;
    width: 95%;
  } */
  input[type="button"], input[type="submit"], input[type="reset"] {
    -webkit-appearance: none;
  }

  textarea {
    -webkit-appearance: none;
  }

  .button {
    border-radius: 0;
  }

  .address-container {
    display: flex;
    flex-direction: row;
  }

  .address-title {
    font-size: 15px;
    font-weight: 700;
    margin-left: 10px;
  }

  .address-text {
    font-size: 13px;
  }

  .address-border {
    display: flex;
    flex-direction: row;
    margin: 5px;
    border: 2px solid #ededed;
    min-height: 40px;
  }

  .address-border-img {
    width: 31px;
    height: 45px;
    margin:11px 10px;
  }

  .address-border-text {
    font-size: 13px;
    margin: 15px 15px 15px 5px;
  }
  .address-border-text > div{
    line-height: 20px;
    text-align: left;
  }
  .address-border-jt {
    margin-top: 22px;
    margin-left: auto;
  }
  .address-no{
    padding: 5px;
    box-sizing: border-box;
    clear: both;
  }
  .address-no p{
    font-size: 12px;
    color: #999;
    line-height: 24px;
    margin-top: 5px;
  }
  .goods-pic {
    margin: 5px;
    width: 86px;
    height: 86px;
    border: 1px solid #ededed;
    border-radius: 4px;
    overflow: hidden;
  }

  .goods-title {
    font-size: 14px;
    font-weight: 700;
    margin-left: 5px;
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp:2;
    -webkit-box-orient: vertical;
    line-height: 20px;
    margin-top: 2px;
  }

  .goods-description {
    font-size: 12px;
    /*margin-left: 5px;*/
    text-align: left;
    padding: 5px 5px 0;
    color: #999;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp:1;
    -webkit-box-orient: vertical;
  }

  .goods-price {
    margin-left: 5px;
    margin-top: 5px;
    line-height: 32px;
  }

  .goods-price-text {
    font-size: 15px;
    font-weight: 700;
    text-align: left;
  }

  .goods-price-num {
    font-size: 15px;
    text-align: left;
    color: #f00;
  }

  .goods_number {
    width: 150px;
    height: 30px;
  }

  .pack-description {
    font-size: 15px;
    font-weight:300;
    margin-left: 5px;
    text-align: left;
    padding: 20px 0 10px;
  }

  .invoice-container {
    margin: 20px 5px 0px 5px;
    border-bottom: 1px solid #ededed;
    padding-bottom: 20px;
  }

  .invoice-style {
    display: flex;
    flex-direction: row;
    margin: 5px 0 5px 0;
  }

  .invoice-text {
    font-size: 15px;
    font-weight: 300;
    text-align: left;
    line-height: 36px;
  }

  .invoice-option {
    margin-left: auto;
    font-size: 13px;
  }

  .price-style {
    display: flex;
    flex-direction: row;
    margin: 10px 5px;
  }

  .price-text {
    font-size: 15px;
    font-weight: 500;
    text-align: left;
    line-height: 40px;
  }

  .price-detail {
    margin-left: auto;
    font-size: 15px;
    color: #cdad87;
    float: left;
    line-height: 40px;
  }

  .price-pay-style {
    margin: 30px 5px 0 5px;
    width: 100%;
    text-align: right;
  }

  .price-pay-text1 {
    font-size: 12px;
    font-weight: 500;
    margin-left: 3px;
    float: left;
    line-height: 40px;
  }

  .price-pay-text2 {
    font-size: 15px;
    font-weight: 900;
    text-align: left;
    margin-left: 10px;
    float: left;
    line-height: 40px;
  }

  .must-known {
    margin-top: 25px;
    width: 100%;
    margin-bottom: 80px;
  }
  .userNotic{
    font-size: 12px;
    margin: 10px 5px;
    /*height: 100px;*/
    box-sizing: border-box;
    line-height: 18px;
    text-align: left;
  }
  .textareaBox{
    margin: 5px 0;
  }
  .invoice-container >>> .el-radio__input.is-checked .el-radio__inner,
  .must-known >>> .el-checkbox__input.is-checked .el-checkbox__inner{
    border-color: #aaa;
    background: #aaa;
  }
  .invoice-container >>> .el-radio__input.is-checked+.el-radio__label,.must-known >>> .el-checkbox__input.is-checked+.el-checkbox__label{
    color: #606266;
  }
  /*radio*/
  .radio-box{
    position: relative;
    height: 36px;
    line-height: 36px;
    margin-right: 5px;
    font-size: 14px;
    width: 100%;
    color: #aaa;
  }
  .radio{
    display: inline-block;
    width: 12px;
    height: 12px;
    vertical-align: text-bottom;
    cursor: pointer;
    border: 1px solid #aaa;
    border-radius: 50%;
  }
  .input-radio{
    display: inline-block;
    position: absolute;
    opacity: 0;
    width: 20px;
    height: 36px;
    cursor: pointer;
    left: 0;
    outline: none;
    -webkit-appearance: none;
  }

  .active{
    background-repeat: no-repeat;
    background-size: 100% 100%;
    border: none;
    width: 14px;
    height: 14px;
    vertical-align: text-top;
    margin-top: 1px;
  }

  .pay-btn {
    background: -webkit-linear-gradient(left, #ffc502, #ff5200);
    border: none;
    width: 115px;
    border-radius: 50px;
    font-size: 14px;
    position: relative;
    top: 1px;
    left: -5px;
    height: 36PX;
    line-height: 36PX;
    color: white;
  }

  .address_btn{
    margin: 8px auto 0 auto;
    color: #fff;
    padding: 8px 50px;
    background: -webkit-gradient(linear, left top, right top, from(#ffc502), to(#ff5200));
    border-radius: 50px;
    border: none;
    font-size: 13px;
    width: 38%;


  }

  .contentFixed{
    padding: 0 10px;
    box-sizing: border-box;
  }
  .goods-number{
    width: 60%;

  }
  .goods-number >>> .el-input-number {
    float: right;
    margin: 1px 0;
  }
  .goods-number >>> .el-input-number__decrease,.goods-number >>> .el-input-number__increase{
    width: 36px;
  }
  .goods-number >>>  .el-input-number{
    line-height: 38px;
  }
  .goods-number >>> .el-input__inner{
    line-height: 38px;
    height:40px;
    padding-left:36px;
    padding-right: 36px;
    font-size: 14px;
    color: #aaa;
  }
  .must-known >>> .el-checkbox__inner:hover{
    border-color: #dcdfe6;
  }
  .goods-number >>> .el-input-number__decrease:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled),.goods-number >>>  .el-input-number__increase:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled) {
    border-color: #dcdfe6 !important;
  }
</style>

<style>
  .el-message{
    top:0px;
  }

  .el-button--danger {
    color: #fff;
    background: -webkit-gradient(linear, left top, right top, from(#ffc502), to(#ff5200));
  }


  .el-input__inner:hover {
    border-color: #c0c4cc !important;
  }

  .el-textarea__inner:hover {
    border-color: #c0c4cc !important;
  }

  .el-checkbox__inner:hover {
    border-color: #c0c4cc !important;
  }

  .el-checkbox__inner {
    border-color: #c0c4cc !important;
  }

  .el-input__inner {
    border-color: #c0c4cc !important;
  }

  .el-textarea__inner {
    border-color: #c0c4cc !important;
  }


  .el-input-number__increase:hover {
    color: #606266 !important;
  }

  .num {
    border-bottom: solid 1px #e3e4e7;
    border-top: solid 1px #e3e4e7;
  }



</style>

