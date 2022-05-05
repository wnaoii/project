<template>
  <div>
    <div class="title-h1">订单管理</div>
    <div class="query-box">
      <el-form ref="params"  :model="queryParams" label-position="left">
        <el-row style="margin-top: 20px; margin-left: 15%">
          <el-col :span="8">
            <el-form-item prop="ordersStatus" label="订单状态：">
              <el-select v-model="queryParams.ordersStatus">
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
          <el-button class="query-btn" type="primary" size="small" @click="getOrdersList">查询</el-button>
        </div>
      </el-form>
    </div>
    <div class="table">
      <el-table :data="tableData" border stripe
      >
        <el-table-column prop="orderId" label="订单号">
        </el-table-column>
        <el-table-column prop="productName" label="产品名称">
        </el-table-column>
        <el-table-column prop="mixLabel" label="加料表">
        </el-table-column>
        <el-table-column prop="num" label="数量">
        </el-table-column>
        <el-table-column prop="price" label="订单价格">
        </el-table-column>
        <el-table-column prop="userId" label="用户编号">
        </el-table-column>
        <el-table-column prop="userName" label="用户名">
        </el-table-column>
        <el-table-column prop="addressLabel" label="配送地址">
        </el-table-column>
        <el-table-column prop="phone" label="手机号">
        </el-table-column>
        <el-table-column prop="orderStatus" label="订单状态">
        </el-table-column>
        <el-table-column prop="createDate" label="下单时间">
        </el-table-column>
        <el-table-column prop="operation" label="操作" width="300px">
          <template slot-scope="scope">
            <div style="text-align: center">
              <el-button class="option-btn" size="mini" type="primary" @click="refund">退款</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

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
    name: 'OrdersList',
    data() {
      return {
        ordersStatus: '',
        attrList: [
          {
            value: "01",
            label: '支付成功'
          },
          {
            value: "02",
            label: '支付失败'
          },
          {
            value: "03",
            label: '已退款'
          },
          {
            value: "04",
            label: '退款成功'
          },
          {
            value: "05",
            label: '退款失败'
          },
          {
            value: "06",
            label: '支付中'
          },
          {
            value: "",
            label: '全部'
          }
        ],
        tableData: [],

        tableTotal: 0,

        queryParams: {
          pageSize: 10,
          pageNum: 0,
          ordersStatus: ''
        }
      }
    },
    created() {
      this.getOrdersList()
    },
    methods: {
      getOrdersList() {
        const loading = this.$loading({lock: true, text: '查询中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.get('/orders/oa/list',
          {
            params: {
              ordersStatus: this.queryParams.ordersStatus
            }
          }
        )
          .then(res => {
            this.tableData = res.data
            this.tableData.forEach(v => {
              v.createDate = this.$moment(v.createDate).format("YYYY-MM-DD HH:mm:ss")
              switch (v.orderStatus) {
                case '01':
                  v.orderStatus = '支付成功'
                  break;
                case '02':
                  v.orderStatus = '支付失败'
                  break;
                case '03':
                  v.orderStatus = '已退款'
                  break;
                case '04':
                  v.orderStatus = '退款成功'
                  break;
                case '05':
                  v.orderStatus = '退款失败'
                  break;
                case '06':
                  v.orderStatus = '支付中'
                  break;
              }
            })
          }).catch(err => {
            console.error(err)
        }).then(res => {
          loading.close()
        })
      },
      refund() {
        this.$message.warning("功能开发中...")
      }
    }
  }
</script>

<style scoped>
  .query-btn {
    /*width: 150px;*/
    /*background: #3a8ee6;*/
    /*color: white;*/
    width: 80px;
    margin-bottom: 15px;
  }
</style>
