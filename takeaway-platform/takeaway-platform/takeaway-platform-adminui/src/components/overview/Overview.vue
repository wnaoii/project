<template>
    <div>
      <el-row>
        <el-col :span="12">
          <!--<div>总金额：{{totalPrice}}</div>-->

        </el-col>
        <el-col :span="12">

        </el-col>
      </el-row>
      <div id="sold-pie" style="width: 100%;height:600px; "></div>
      <div style="height: 35px"></div>
      <div id="stock-line" style="width: 100%;height:600px;"></div>
    </div>
</template>

<script>
  export default {
    name: 'Overview',
    data() {
      return {
        totalPrice: 0
      }
    },
    mounted() {
      this.initSoldPie()
      this.initStockLine()
    },
    methods: {
      initSoldPie() {
        let soldPieOption = {
          title: {
            text: '产品销售分析',
            subtext: '各产品销售金额占比情况',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            left: 'center',
            top: 'bottom',
            data: []
          },
          toolbox: {
            show: true,
            feature: {
              mark: {show: true},
              dataView: {show: true, readOnly: false},
              magicType: {
                show: true,
                type: ['pie', 'funnel']
              },
              restore: {show: true},
              saveAsImage: {show: true}
            }
          },
          series: [
            {
              name: '销售额占比',
              type: 'pie',
              radius: [20, 110],
              center: ['50%', '50%'],
              roseType: 'radius',
              label: {
                show: true
              },
              emphasis: {
                label: {
                  show: true
                }
              },
              data: [
              ]
            }
          ]
        };
        const loading = this.$loading({lock: true, text: '查询中...', spinner: 'el-icon-loading', background: 'rgba(0, 0, 0, 0.7)'});
        this.$axios('/charts/pie/sold')
          .then(res => {
            soldPieOption.series[0].data = res.data
            let pieChart = this.$echarts.init(document.getElementById('sold-pie'))
            res.data.forEach(v => {
              this.totalPrice += v.value
              soldPieOption.legend.data.push(v.name)
            })
            pieChart.setOption(soldPieOption)
          })
          .catch(err => {
            console.error(err)
            this.$message.error("数据获取失败")
          })
          .then(res => {
            loading.close()
          })
      },
      initStockLine() {
        let option = {
          title: {
            text: '各产品当前库存',
            subtext: '各产品当前库存情况',
            left: 'center'
          },
          xAxis: {
            type: 'category',
            data: []
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            type: 'bar',
            itemStyle: {
              color: '#d48265'
            },
            emphasis: {
              itemStyle: {
                color: '#d78f76'
              }
            },
            data: []
          }]
        };
        this.$axios.get("/stock/product")
          .then(res => {
            res.data.forEach(v => {
              option.series[0].data.push(v.stockNum)
              option.xAxis.data.push(v.productName)
            })
            let chart = this.$echarts.init(document.getElementById('stock-line'))
            chart.setOption(option)
          }).catch(err => {
          console.error(err)
        })
      }
    }
  }
</script>

<style scoped>

</style>
