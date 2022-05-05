import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/Home'
import ProductList from '@/components/product/ProductList'
import AddProduct from '@/components/product/AddProduct'
import Stock from '@/components/stock/Stock'
import OrdersList from '@/components/orders/OrdersList'
import Overview from '@/components/overview/Overview'
import AD from '@/components/ad/AD'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/home/:userId?/:defaultRoleId?/:userBranchId?/:defaultFuncId?/:dse_sessionId?/:netType?',
      name: 'Home',
      component: Home,
      children: [
        {
          path: 'product/list',
          name: 'ProductList',
          component: ProductList,
        },
        {
          path: 'add/product',
          name: 'AddProduct',
          component: AddProduct,
        },
        {
          path: 'add/stock',
          name: 'Stock',
          component: Stock,
        },
        {
          path: 'orders',
          name: 'OrdersList',
          component: OrdersList,
        },
        {
          path: 'overview',
          name: 'Overview',
          component: Overview,
        },
        {
          path: 'ad',
          name: 'AD',
          component: AD,
        }
      ]
    }
  ]
})
