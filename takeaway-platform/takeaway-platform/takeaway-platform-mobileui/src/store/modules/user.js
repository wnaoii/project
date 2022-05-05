'use strict'
import _ from 'lodash'

const state = {
  user: {}
}

const getters = {
  dairyList: state => {
    return _.filter(state.productList.filter(product => product.pType === 'dairy'))
  }
}

const mutations = {
  setUser(state, user) {
    state.user = user
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
