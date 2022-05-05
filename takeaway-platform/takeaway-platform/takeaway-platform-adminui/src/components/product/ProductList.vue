<template>
  <div>
    <div class="title-h1">产品维护</div>
    <div class="query-box">
      <el-form ref="params" :rules="rules" :model="queryParams" label-position="left">
        <el-row style="margin-top: 20px; margin-left: 15%">
          <el-col :span="8">
            <el-form-item prop="attrId" label="分类属性：">
              <el-select v-model="queryParams.pType">
                <el-option
                  v-for="item in attrList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
          </el-col>
        </el-row>
        <div style="width: 100%">
          <el-button class="query-btn" type="primary" size="small" @click="getProductList">查询</el-button>
          <el-button class="query-btn" type="success" size="small" style="margin-left: 15px;" @click="toAddProduct">新增
          </el-button>
        </div>
      </el-form>
    </div>
    <div class="table">
      <el-table :data="tableData" border stripe
      >
        <el-table-column prop="pTitle" label="产品名称">
        </el-table-column>
        <el-table-column prop="pType" label="产品分类">
        </el-table-column>
        <el-table-column prop="startDate" label="上线时间">
        </el-table-column>
        <el-table-column prop="price" label="价格">
        </el-table-column>
        <el-table-column prop="soldStatus" label="销售状态">
        </el-table-column>
        <el-table-column prop="contacts" label="添加人">
        </el-table-column>
        <el-table-column prop="operation" label="操作" width="300px">
          <template slot-scope="scope">
            <div style="text-align: center">
              <el-button  type="primary" @click="revise(scope.row.productId)" size="mini">修改</el-button>
              <el-button class="option-btn" style="background-color: #f43d5e"
                         @click="deleteProduct(scope.row.productId)" size="mini">删除
              </el-button>
              <el-button v-if="scope.row.sold === '0'" class="option-btn"
                         @click="upProduct(scope.row.productId)" size="mini">上架
              </el-button>
              <el-button v-if="scope.row.sold !== '0'" class="option-btn"
                         @click="downProduct(scope.row.productId)" size="mini">下架
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="警告" :visible.sync="deleteDialogVisible" style="font-size: 12px;" width="80%">
        <span>是否确认删除【{{currentSelectionItem.productName}}】？</span>
        <div slot="footer" class="dialog-footer" style="text-align: center">
          <el-button type="danger" @click="deleteProduct">确定</el-button>
          <el-button @click="deleteDialogVisible = false">取消</el-button>
        </div>
      </el-dialog>
    </div>

    <div style="margin-top: 20px">
      <el-pagination
        :current-page="queryParams.pageNum"
        :page-sizes="[5,10,20]"
        :page-size="queryParams.pageSize"
        layout="total,sizes,prev,pager,next,jumper"
        :total="tableTotal">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ProductList',
    data() {
      return {
        queryParams: {
          productName: "",
          pType: "",
          type: "",
          all: true,
          pageNum: 1,
          pageSize: 10
        },
        currentSelectionItem: {},
        deleteDialogVisible: false,
        dict: [
          {
            value: "1",
            label: '奶制品'
          },
          {
            value: "2",
            label: '茶类'
          },
          {
            value: "3",
            label: '上新'
          }
        ],
        attrList: [
          {
            value: "dairy",
            label: '奶制品'
          },
          {
            value: "tea",
            label: '茶类'
          },
          {
            value: "coffee",
            label: '咖啡'
          },
          {
            value: "",
            label: '全部'
          }
        ],
        deletableList: [],
        tableData: [],
        tableTotal: 0,
        seqNoArray: [],
        deptDict: [],
        searchParam: {},
        rules: {
          pType: [
            {required: true, message: '请选择业务类型', trigger: 'change'}
          ]
        }
      }
    },
    created() {
      this.getProductList()
    },
    methods: {
      toAddProduct() {
        this.$router.push({
          name: "AddProduct"
        });
      },
      getProductList() {
        const loading = this.$loading({lock: true, text: '查询中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.get("/drink/product/list", {
          params: {
            pType: this.queryParams.pType
          }
        })
          .then(res => {
            this.tableData = res.data
            this.tableData.forEach(v => {
              switch (v.soldStatus) {
                case '0':
                  v.soldStatus = '上架'
                  break;
              }
              v.startDate = this.$moment(v.startDate).format('YYYY-MM-DD HH:mm:ss')
              switch (v.pType) {
                case 'dairy':
                  v.pType = '奶制品'
                  break;
                case 'coffee':
                  v.pType = '咖啡'
                  break;
                case 'tea':
                  v.pType = '茶类'
                  break;
              }
            })
          }).catch(err => {
            console.error(err)
        }).then(() => {
          loading.close()
        })
      },
      revise(productId) {
        console.log("productId => %o", productId);
        this.$router.push({name: "AddProduct", params: {productId: productId}});
      },
      deleteProduct(productId) {
        let url = '/drink/product/' + productId
        this.$axios.delete(url).then(res => {
          this.getProductList()
        }).catch(err => {
          console.error(err);
          this.$message.error("删除失败")
        })
      },
      upProduct(productId) {

      },
      downProduct() {
        this.$message.warning("功能开发中....")
      }
    }
  }
</script>

<style scoped>
  .query-box {
    border: #d3d4d6 1px solid;
    margin-top: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .query-btn {
    /*width: 150px;*/
    /*background: #3a8ee6;*/
    /*color: white;*/
    width: 80px;
    margin-bottom: 15px;
  }

  .table {
    margin-top: 15px;
    border: #d3d4d6 1px solid;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .option-btn {
    background: #3a8ee6;
    color: white;
  }

  .el-table .warning-row {
    background: #3a8ee6;
  }

  .el-table .success-row {
    background: white;
  }

  .title-h1 {
    font-size: x-large;
    display: block;
    margin-block-start: 0.2em;
    margin-block-end: 0.83em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
  }
</style>
