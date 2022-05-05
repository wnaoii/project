// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'
import MuseUI from 'muse-ui';
import 'muse-ui/dist/muse-ui.css';
import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
import '../static/theme/index.css'
import 'material-design-icons'
import axios from 'axios'
import _ from 'lodash'
import handler from '@/utils/error_handler'
/*
* 按需引
* */
// import {
//   Menu,
//   Submenu,
//   MenuItem,
// } from 'element-ui';

Vue.config.productionTip = false

if(process.env.NODE_ENV === 'development') {
  axios.defaults.baseURL = "/api/takeaway/api"
} else {
  axios.defaults.baseURL = "/takeaway/api"
}


Vue.prototype.$axios = axios;
Vue.prototype.$_ = _;
Vue.prototype.$hanlder = handler

Vue.use(MuseUI)
Vue.use(ElementUI)
// Vue.use(Menu)
// Vue.use(Submenu)
// Vue.use(MenuItem)


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
