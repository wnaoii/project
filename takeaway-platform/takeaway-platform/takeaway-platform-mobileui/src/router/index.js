import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import FoodMainMenu from '@/modules/food-menu/FoodMainMenu'
import Dairy from '@/modules/food-menu/Dairy'
import New from '@/modules/food-menu/New'
import Tea from '@/modules/food-menu/Tea'
import Top from '@/modules/food-menu/Top'
import Other from '@/modules/food-menu/Other'
import OrdersList from '@/modules/orders/OrdersList'
import PreOrder from '@/modules/orders/PreOrder'
import User from '@/modules/user-center/User'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/menu/dairy'
    },
    {
      path: '/menu',
      name: 'FoodMainMenu',
      component: FoodMainMenu,
      children: [
        {
          path: 'dairy',
          name: 'Dairy',
          component: Dairy,
        },
        {
          path: 'new',
          name: 'New',
          component: New,
        },
        {
          path: 'tea',
          name: 'Tea',
          component: Tea,
        },
        {
          path: 'top',
          name: 'Top',
          component: Top,
        },
        {
          path: 'other',
          name: 'Other',
          component: Other,
        }
      ]
    },
    {
      path: '/orders/list',
      name: 'OrdersList',
      component: OrdersList
    },
    {
      path: '/pre/order',
      name: 'PreOrder',
      component: PreOrder
    },
    {
      path: '/user',
      name: 'User',
      component: User
    }
  ]
})
