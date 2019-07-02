import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import list from '@/components/list'
import insert from '@/components/insert'
Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', name: 'HelloWorld', component: HelloWorld},
    {path: '/list', name: 'list', component: list},
    {path:'/insert', name:'insert', component: insert}
  ]
})
