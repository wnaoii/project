'use strict'
import _ from 'lodash'

const state = {
  mix: []
}

const getters = {

}

const mutations = {
  setMixDict(state, dict) {
    this.state.mix = dict
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
