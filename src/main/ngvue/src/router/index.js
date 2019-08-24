import Vue from 'vue'
import Router from 'vue-router'
import testwebsocket from '@/components/testwebsocket'
import list from '@/components/list'
import insert from '@/components/insert'
import yanzhengma from '@/components/yanzhengma'
import messages from '@/components/messages'
import insertmessages from '@/components/insertmessages'
import Distpicker from 'v-distpicker'
Vue.use(Router,Distpicker)

export default new Router({
  routes: [
       {path: '/', name: 'testwebsocket', component: testwebsocket},
   // {path: '/', name: 'HelloWorld', component: HelloWorld},
    {path: '/list', name: 'list', component: list},
    {path:'/insert', name:'insert', component: insert},
    {path:'/yanzhengma', name:'yanzhengma', component: yanzhengma},
    {path:'/messages', name:'messages', component: messages},
    {path:'/insertmessages', name:'insertmessages', component: insertmessages}
  ]
})
