'use strict'
import _ from 'lodash'

const state = {
  productList: [
    {
      imageUrl: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590913934442&di=364ae0cfa081e35aa86a3d8c6a57d13f&imgtype=0&src=http%3A%2F%2Fwww.yuwengc.com%2Fuploads%2F190923%2F1-1Z923120049421.jpg",
      pTitle: "多肉芒芒",
      pDesc: "它，真的是那种很特别的那种，让人看了一眼就忘不了",
      price: "30",
      pType: "dairy",
      productId: "123"
    },
    {
      imageUrl: "/takeaway/platform/mui/static/img/logo.png",
      pTitle: "多肉芒芒",
      pDesc: "它，真的是那种很特别的那种，让人看了一眼就忘不了",
      price: "30",
      pType: "dairy",
      productId: "124"
    }],
  productOrder: {
    productId: "",
    price: "",
    mixList: "",
    num: ""
  }
}

const getters = {
  dairyList: state => {
    return _.filter(state.productList.filter(product => product.pType === 'dairy'))
    // return [{aa: "456"}]
  }
}

const mutations = {
  setProductList(state, list) {
    state.productList = list
  },
  setProductOrder(state, product) {
    state.productOrder = product
  }
}

const actions = {
}

export default {
  state,
  getters,
  mutations,
  actions
}
