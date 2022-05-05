// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
import '../static/theme/index.css'
// import 'element-ui/lib/theme-chalk/display.css'
import _ from 'lodash'
import moment from 'moment'
import axios from 'axios'
import echarts from 'echarts'

Vue.use(ElementUI)
Vue.config.productionTip = false
// axios.defaults.baseURL = "/api/takeaway/api"
if(process.env.NODE_ENV === 'development') {
  axios.defaults.baseURL = "/api/takeaway/api"
} else {
  axios.defaults.baseURL = "/takeaway/api"
}

axios.interceptors.request.use(
  config => {
    config.headers['IBANK_TOKEN'] = '123'
    return config;
  },
  error => {
    console.error(error)
    return Promise.reject(error)
  }
)

axios.interceptors.response.use(
  res => {
    return res
  },
  error => {
    console.error("inter %o", error)
    switch (error.status) {
      case 400:
        this.$message.error("缺少查询要素，请重新核对或稍后重试")
        break;
      case 403:
        this.$message.error(res.data.message)
        break;
      case 500:
        this.$message.error("系统异常，请稍后重试")
        break;
      case 404:
        this.$message.error("找不到资源，请稍后重试")
        break;
    }
    return Promise.reject(error.response)
  }
)


sessionStorage.userId = "001152601"
sessionStorage.userName = "陶俊宇"


Vue.prototype.$_ = _
Vue.prototype.$moment = moment
Vue.prototype.$axios = axios
Vue.prototype.$echarts = echarts

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
