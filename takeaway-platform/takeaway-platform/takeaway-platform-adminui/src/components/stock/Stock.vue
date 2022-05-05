<template>
  <div>
    <div class="table">
      <div class="s-title">产品库存</div>
      <el-table :data="pTableData" border stripe
      >
        <el-table-column prop="productId" label="产品Id">
        </el-table-column>
        <el-table-column prop="productName" label="产品名称">
        </el-table-column>
        <el-table-column prop="ptype" label="产品分类">
        </el-table-column>
        <el-table-column prop="startDate" label="上线时间">
        </el-table-column>
        <el-table-column prop="stockNum" label="当前库存量">
        </el-table-column>
        <el-table-column prop="operation" label="操作" width="300px">
          <template slot-scope="scope">
            <div style="text-align: center">
              <el-button class="option-btn" @click="showProductDialog(scope.row)" size="mini" type="primary">修改</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="修改库存" :visible.sync="addProductStockDialogVisible" style="font-size: 12px;" width="80%">
        <div>
          <div style="margin: 25px 0px; font-weight: 600">产品名：{{selectedProduct.productName}}</div>
          <el-input-number v-model="selectedProduct.stockNum" :min="1" label="修改库存"></el-input-number>
        </div>
        <div slot="footer" class="dialog-footer" style="text-align: center">
          <el-button type="danger" @click="reviseProduct(selectedProduct.productId)">确定</el-button>
          <el-button @click="deleteDialogVisible = false">取消</el-button>
        </div>
      </el-dialog>

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

    <div class="table" style="margin-top: 25px">
      <div class="s-title">配料库存</div>
      <el-table :data="mTableData" border stripe
      >
        <el-table-column prop="mixId" label="配料Id">
        </el-table-column>
        <el-table-column prop="mixTitle" label="配料名称">
        </el-table-column>
        <el-table-column prop="stock" label="当前库存量">
        </el-table-column>
        <el-table-column prop="operation" label="操作" width="300px">
          <template slot-scope="scope">
            <div style="text-align: center">
              <el-button class="option-btn" @click="showMixDialog(scope.row)" size="mini" type="primary">修改</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="警告" :visible.sync="addMixStockDialogVisible" style="font-size: 12px;" width="80%">
        <div>
          <div style="margin: 25px 0px; font-weight: 600">配料名：{{selectedMix.mixTitle}}</div>
          <el-input-number v-model="selectedMix.stock" :min="1" label="修改库存"></el-input-number>
        </div>
        <div slot="footer" class="dialog-footer" style="text-align: center">
          <el-button type="danger" @click="reviseMix(selectedMix.mixId)">确定</el-button>
          <el-button @click="deleteDialogVisible = false">取消</el-button>
        </div>
      </el-dialog>

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

  </div>
</template>

<script>
  export default {
    name: 'Stock',
    data() {
      return {
        queryParams: {
          productName: "",
          attrId: "",
          type: "",
          all: true,
          pageNum: 1,
          pageSize: 10
        },
        currentSelectionItem: {},
        addProductStockDialogVisible: false,
        addMixStockDialogVisible: false,
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
        pTableData: [],
        mTableData: [],
        tableTotal: 0,

        selectedProduct: {},
        selectedMix: {},
        rules: {
          attrId: [
            {required: true, message: '请选择业务类型', trigger: 'change'}
          ]
        }
      }
    },
    created() {
      this.getStockProduct();
      this.getStockMix()
    },
    methods: {
      showProductDialog(product) {
        this.addProductStockDialogVisible = true
        console.log("product %o", product)
        this.selectedProduct = product
      },
      showMixDialog(mix) {
        this.addMixStockDialogVisible = true
        this.selectedMix = mix
      },
      reviseProduct(productId) {
        const loading = this.$loading({lock: true, text: '修改中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.put("/stock/product", {}, {
          params: {
            productId: productId,
            stockNum: this.selectedProduct.stockNum
          }
        })
          .then(res => {
            this.$message.success("修改成功")
          }).catch(err => {
          this.$message.error("修改失败")
            console.error(err)
        }).then(res => {
          loading.close()
          this.addProductStockDialogVisible = false
        })
      },
      getStockProduct() {
        this.$axios.get("/stock/product")
          .then(res => {
            this.pTableData = res.data;
            this.pTableData.forEach(v => {
              v.startDate = this.$moment(v.startDate).format("YYYY-MM-DD")
            })
          }).catch(err => {
          console.error(err)
        })
      },
      getStockMix() {
        this.$axios.get("/stock/mix")
          .then(res => {
            this.mTableData = res.data;
          }).catch(err => {
          console.error(err)
        })
      },
      reviseMix(mixId) {
        const loading = this.$loading({lock: true, text: '修改中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.put("/stock/mix", {}, {
          params: {
            mixId: mixId,
            stockNum: this.selectedMix.stock
          }
        })
          .then(res => {
            this.$message.success("修改成功")
          }).catch(err => {
          this.$message.error("修改失败")
          console.error(err)
        }).then(res => {
          loading.close()
          this.addMixStockDialogVisible = false
        })
      }
    }
  }
</script>

<style scoped>
 .s-title {
   margin: 8px;
   text-align: left;
   font-size: 19px;
   font-weight: 600;
 }
</style>
