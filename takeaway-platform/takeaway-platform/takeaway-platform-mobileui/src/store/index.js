'use strict'
import Vue from 'vue'
import Vuex from 'vuex'
import product from './modules/product'
import user from './modules/user'
import dict from './modules/dict'

Vue.use(Vuex)
// vuex严格模式可以在调试环境中确保状态都被调试工具跟踪，发布环境需要关闭避免性能损失
const debug = process.env.NODE_ENV !== 'production'


const state = {
}

const getter = {

}

const mutations = {

}

const actions = {
}



export default new Vuex.Store({
  modules: {
    product,
    user,
    dict
  },
  strict: debug
})
