<template>
  <div>
    <el-form ref="productForm" :inline="true" :model="product" label-position="left" label-width="160px" :rules="rules">
      <p class="product-info-title">产品信息</p>
      <div class="product-info-box">
        <div style="margin-top: 15px">
          <el-form-item prop="pTitle" label="产品名称： ">
            <el-input v-model="product.pTitle" :disabled="formEdit"></el-input>
          </el-form-item>
          <el-form-item style="margin-left: 15px" prop="pType" label="产品分类： ">
            <el-select v-model="product.pType" :disabled="typeListVisible">
              <el-option
                v-for="item in typeList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设为热门产品： " style="margin-left: 79px;">
            <el-switch v-model="product.hot"
                       active-color="#13ce66"
                       inactive-color="#cecece"
            ></el-switch>
          </el-form-item>
        </div>
        <div >
          <el-form-item label="是否可加料： ">
            <el-switch v-model="product.addable"
                       active-color="#13ce66"
                       inactive-color="#cecece"
            ></el-switch>
          </el-form-item>
          <el-form-item label="加料种类： " v-show="product.addable" style="margin-left: 60px">
            <el-checkbox-group v-model="product.addableList">
              <el-checkbox label="M-1">珍珠</el-checkbox>
              <el-checkbox label="M-2">布丁</el-checkbox>
              <el-checkbox label="M-3">西米</el-checkbox>
              <el-checkbox label="M-4">芝士</el-checkbox>
              <el-checkbox label="M-5">红豆</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </div>
        <div v-if="productIconUrl !== ''" class="product-current-icon">
          <div style="font-size: 14px; color: #606266; margin-top: 35px">当前按钮图标:</div>
          <img :src="productIconUrl" slot="trigger" class="btn-icon">
        </div>
        <div v-show="updateIconVisible">
          <el-form-item prop="btnIcon" label="按钮图标：">
            <el-upload class="avatar-uploader" :action="imageApi" :auto-upload="false" :name="'btnIcon'"
                       ref="iconUpload" :limit="1"
                       :data="iconData" :multiple="false" :file-list="fileList"
                       :before-upload="onPreUpload"
                       list-type="picture">
              <el-button v-if="productIconUrl === ''" slot="trigger" size="small" type="primary">选择按钮图片</el-button>
              <el-button v-if="productIconUrl !== ''" slot="trigger" size="small" type="primary" @click="changeImage">更新按钮图片</el-button>
              <el-button v-if="productIconUrl !== ''" @click="deleteImage" size="small" type="danger">删除</el-button>
            </el-upload>
          </el-form-item>
        </div>

        <div>
          <el-form-item prop="method" label="制作方式：">
            <el-select v-model="product.method" :disabled="formEdit">
              <el-option
                v-for="item in isTopSelectList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="price" label="价格：">
            <el-input placeholder="请输入内容"
              v-model="product.price">
            </el-input>
          </el-form-item>
          <el-form-item prop="stockNum" label="初始库存：">
            <el-input-number v-model="product.stockNum" :min="1" label="修改库存"></el-input-number>
          </el-form-item>
          <el-form-item prop="startDate" label="生效时间： ">
            <el-date-picker
              :disabled="formEdit"
              v-model="product.startDate"
              type="date"
              placeholder="选择日期时间"
              align="right"
            >
            </el-date-picker>
          </el-form-item>
        </div>
        <el-form-item prop="pDesc" label="产品描述： ">
          <el-input
            type="textarea"
            :rows="6"
            placeholder="请输入内容"
            v-model="product.pDesc">
          </el-input>
        </el-form-item>
        <div>
        </div>
      </div>
      <p class="admin-info-title">负责人</p>
      <div class="admin-info-box">
        <div style="margin-top: 15px">
          <el-form-item prop="contacts" label="联系人： ">
            <el-input :disabled="true" v-model="product.contacts" ></el-input>
          </el-form-item>
          <el-form-item prop="contactsNo" style="margin-left: 15px" label="工号： ">
            <el-input :disabled="true" v-model="product.contactsNo" ></el-input>
          </el-form-item>
        </div>
      </div>

      <div style="margin-top: 15px">
        <el-button class="op-btn" v-show="saveBtnVisible" @click="saveProduct">保存</el-button>
        <el-button class="op-btn" @click="goBack">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: 'AddProduct',
    data() {
      return {
        productId: "",
        product: {
          pTitle: "",
          startDate: "",
          pType: "dairy",
          //是否设为热门产品
          hot: false,
          addable: true,
          addableList: [],
          pDesc: "",
          price: "",
          method: 'cool',
          contacts: "陶俊宇",
          contactsNo: "001152601",
          stockNum: 10
        },


        productIconUrl: "",

        isTopSelectList: [
          {
            value: "cool",
            label: '制冰'
          },
          {
            value: "hot",
            label: '热饮'
          },
          {
            value: "brew",
            label: '精萃'
          },
        ],
        typeList: [
          {
            value: "dairy",
            label: '醇享原奶'
          },
          {
            value: "tea",
            label: '茶类'
          },
          {
            value: "coffee",
            label: '咖啡'
          }
        ],
        imageApi: this.$axios.defaults.baseURL + "/image/",
        productList: [],
        fileList: [],
        exOriginProducts: [],
        iconData: {
          imageClassify: "pd",
          unionId: "",
          imageId: ""
        },
        rules: {
          pTitle: [
            {required: true, message: '请输入产品名', trigger: 'blur'}
          ],
          pType: [
            {required: true, message: '请选择分类属性', trigger: 'change'}
          ],
          startDate: [
            {required: true, message: '请选择生效时间', trigger: 'blur'}
          ],
          pDesc: [
            {required: true, message: '请添加商品描述', trigger: 'change'}
          ]
        },

        updateIconVisible: true,
        saveBtnVisible: true,
        typeListVisible: false,
        productSelectorVisible: false,
        hyperlinksInputVisible: false,
        isHotVisible: false,
        formEdit: false,

        pubKeyAndCorpIdEditable: false,

        urlStatus: false,

        //当前页面的操作类型，insert 新增；edit 修改； read 查看；
        operationType: ""
      }
    },
    created() {
      if(this.$route.params && this.$route.params.productId) {
        this.productId = this.$route.params.productId;
        this.getProductDetail();
      }
    },
    methods: {
      changeImage() {
        let imageId = "I-" + this.$moment().format("YYYYMMDD") + "-" + Math.random().toString().substr(3, 8);
        this.iconData.unionId = this.productId;
        this.iconData.imageId = imageId;
      },
      saveProduct() {
        this.$refs["productForm"].validate((valid) => {
          if (valid) {
            let productParams = {...this.product};
            if (this.productId === "") {
              //为新增产品，创建productId
              let productId = "P-" + this.$moment().format("YYYYMMDD") + "-" + Math.random().toString().substr(3, 8);
              let imageId = "I-" + this.$moment().format("YYYYMMDD") + "-" + Math.random().toString().substr(3, 8);
              this.iconData.unionId = productId;
              this.iconData.imageId = imageId;
              productParams.productId = productId;
              productParams.startDate = this.$moment(productParams.startDate).format("YYYY-MM-DD")
              productParams.addableList = JSON.stringify(productParams.addableList)
              productParams.hot = productParams.hot ? '1' : '0'
              productParams.addable = productParams.addable ? '1' : '0'
              productParams.imageId = imageId
              console.log(productParams)
            }
            if(this.iconData.imageId) {
              this.$refs.iconUpload.submit();
              productParams.imageId = this.iconData.imageId
            }
            const loading = this.$loading({
              lock: true,
              text: '新增中...',
              spinner: 'el-icon-loading',
              background: 'rgba(0, 0, 0, 0.7)'
            });
            this.$axios.post("/drink/", productParams
            ).then(res => {
              console.log("saveProduct res => %o", res);
              this.$refs.iconUpload.submit();
              this.$message.success("保存成功")

              setTimeout(() => {
                this.$router.push({name: "ProductList"})
              }, 1000)
            }).catch(err => {
              console.error(err)
            }).then(() => {
              loading.close()
            })
          }
        })
      },
      goBack() {
        this.$router.push({name: "ProductList"})
      },
      getProductDetail() {
        let url = "/drink/product/" + this.productId
        this.$axios.get(url)
          .then(res => {
            this.product = res.data
            this.productIconUrl = this.$axios.defaults.baseURL + '/image/' +  res.data.imageId
          })
          .catch(err => {
            console.error(err)
          })
      },
      onPreUpload(file) {
      },
      deleteImage() {}
    }
  }
</script>

<style scoped>
  .product-info-box {
    text-align: left;
    padding-left: 35px;
    border: #d3d4d6 1px solid;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .admin-info-box {
    text-align: left;
    padding-left: 35px;
    border: #d3d4d6 1px solid;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .product-info-title {
    font-size: 12px;
    font-weight: bolder;
    text-align: left;
    margin: 10px;
  }

  .admin-info-title {
    font-size: 12px;
    font-weight: bolder;
    text-align: left;
    margin: 10px;
  }

  .op-btn {
    background: #66b1ff;
    color: white;
  }

  .btn-icon {
    width: 80px;
    height: 80px;
    display: block;
    margin-left: 83px;
  }

  .product-current-icon {
    display: flex;
    margin-bottom: 15px;
  }

  .btn-icon-init {
    border: #d3d4d6 1px dashed;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 150px;
    height: 150px;
    display: block;
  }
</style>
