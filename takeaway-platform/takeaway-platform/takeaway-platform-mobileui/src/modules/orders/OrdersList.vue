<template>
    <div>
      <mu-list textline="two-line">
        <div v-for="orders in ordersList" :key="orders.orderId">
          <mu-list-item avatar :ripple="false" button >
            <mu-list-item-content>
              <mu-list-item-title>商品：{{orders.productName}}</mu-list-item-title>
              <mu-list-item-sub-title style="color: rgba(0, 0, 0, .87)">  数量：{{orders.num}} , 加料：{{orders.mixLabel}}</mu-list-item-sub-title>
              <mu-list-item-sub-title>
                订单号： {{orders.orderId}}
              </mu-list-item-sub-title>
            </mu-list-item-content>
            <mu-list-item-action >
              <mu-list-item-after-text>{{orders.createDate | dateFilter}}</mu-list-item-after-text>
              <mu-badge :content="orders.orderStatus" color="#76d051"></mu-badge>
            </mu-list-item-action>
          </mu-list-item>
          <mu-divider></mu-divider>
        </div>
      </mu-list>
    </div>
</template>

<script>
  import moment from 'moment'
  export default {
    name: 'OthersList',
    data() {
      return {
        ordersList: []
      }
    },
    computed: {

    },
    filters: {
      dateFilter(v) {
        return moment(v).format("YYYY-MM-DD HH:mm:ss")
      }
    },
    created() {
      this.getOrdersList()
    },
    methods: {
      getOrdersList() {
        const loading = this.$loading({lock: true, text: '下单中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios.get('/orders/list', {
          params: {
            userId: sessionStorage.userId
          }
        }).then(res => {
          this.ordersList = res.data;
          // todo 按后端字典映射
          for(let i in this.ordersList) {
            switch (this.ordersList[i].orderStatus) {
              case '01':
                this.ordersList[i].orderStatus = '已接单'
                    break;
              case '02':
                this.ordersList[i].orderStatus = '已送达'
                break;
              case '03':
                this.ordersList[i].orderStatus = '已退款'
                break;
            }
          }
        }).catch(err => {
          this.$hanlder.errorHandler(err)
        }).then(res => {
          loading.close()
        })
      }
    }
  }
</script>

<style scoped>

</style>
