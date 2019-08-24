<!--<template>-->

  <!--       <div>-->
  <!--          <Button type="primary" @click="threadPoxi()" icon="plus">发送消息</Button>-->
  <!--       </div>-->

<!--</template>-->
<!--<script>-->
  <!--export default{-->
    <!--data () {-->
      <!--return {-->
        <!--websocket: null-->
      <!--}-->
    <!--},-->
    <!--methods: {-->
      <!--threadPoxi(){  // 实际调用的方法 如果ws已经连接则直接发送消息；如正在开启状态，则等待300毫秒，再发送消息；若未开启 ，则等待500毫秒，再发送消息。-->
        <!--//参数-->
        <!--const agentData = "从前台发出的信息";-->
        <!--//若是ws开启状态-->
        <!--if (this.websocket.readyState === this.websocket.OPEN) {-->
          <!--this.websocketsend(agentData)-->
        <!--}-->
        <!--// 若是 正在开启状态，则等待300毫秒-->
        <!--else if (this.websocket.readyState === this.websocket.CONNECTING) {-->
          <!--let that = this;//保存当前对象this-->
          <!--setTimeout(function () {-->
            <!--that.websocketsend(agentData)-->
          <!--}, 300);-->
        <!--}-->
        <!--// 若未开启 ，则等待500毫秒-->
        <!--else {-->
          <!--this.initWs();-->
          <!--let that = this;//保存当前对象this-->
          <!--setTimeout(function () {-->
            <!--that.websocketsend(agentData)-->
          <!--}, 500);-->
        <!--}-->
      <!--},-->
      <!--initWs () {-->
        <!--if ('WebSocket' in window) {-->
          <!--this.websocket = new WebSocket("ws://localhost:8081/Jqcase");-->
        <!--}-->
        <!--else {-->
          <!--alert('Sorry, websocket not supported by your browser.')-->
        <!--}-->
        <!--//数据接收-->
        <!--this.websocket.onmessage = this.websocketonmessage;-->
        <!--/*-->
       <!--            this.websocket.onclose = this.websocketclose; */-->
        <!--//this.websocket.close();-->
      <!--},-->
      <!--websocketonmessage: function (e) {//数据接收-->
        <!--var da = JSON.parse(e.data);-->
        <!--console.log(da);-->
        <!--this.msg_data.unshift(da);-->
      <!--},-->
      <!--websocketsend: function(agentData){//数据发送-->
        <!--this.websocket.send(agentData);-->
      <!--},-->
      <!--websocketclose:function(e){  //关闭-->
        <!--this.websocket.close();-->
        <!--console.log("connection closed (" + e.code + ")");-->
      <!--}-->

    <!--},-->
    <!--created(){-->
      <!--this.initWs();-->
    <!--}-->
  <!--}-->
<!--</script>-->
<template>
  <div id="vue">
    <div>{{ message }}</div>
    <el-input type="text" name="Message" v-model="Message" value=""/>{{resultData}}
    <el-button @click="webSocketSend();">发送消息</el-button><el-button @click="webSocketClose();">断开连接</el-button><el-button @click="initWebSocket();">重新连接</el-button>
  </div>
</template>
<script type="text/javascript">
  export default {
//将模块引出在其他地方使用
    name: "vueday01",
    data() {
//data即使不需要传数据，亦必须return,否则报错
      return {
        message: "vue实现webSocket",
        webSocket: null,
        Message:"",
        resultData:""
      };
    },
    created() {
//页面刚进入时开启长连接
      this.initWebSocket()
    },
    destroyed: function () {
//页面销毁时关闭长连接
      this.webSocketClose();
    },
    methods: {
      initWebSocket() {
// WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
        const wsUri = "ws://localhost:8081/websocket/1";
        this.webSocket = new WebSocket(wsUri);
        this.webSocket.onopen = this.webSocketOpen;
        this.webSocket.onmessage = this.webSocketMessage;
        this.webSocket.onerror = this.webSocketError;
      },
      webSocketOpen() {
        alert("WebSocket连接成功");
        console.log("WebSocket连接成功");
      },
      webSocketError() {
        console.log("WebSocket连接发生错误");
      },
      webSocketMessage:function(result) {
//数据接收
        try {
//具体业务具体解决
          console.log("服务器端返回："+result.data);
          const json = JSON.parse(result.data);
          this.resultData=json;
        } catch (e) {
          console.info(result)
        }
      },
      webSocketSend() {
//数据发送
        debugger;
        var message=this.Message;
        this.webSocket.send(message);
      },
      webSocketClose() {
//关闭
        this.webSocket.close();
        console.log("connection closed");
      },
    }
  };
</script>
<style>
  #vue {
    width: 50%;
    font-size: 25px;
    margin: auto;
    text-align: left;
  }
  div {
    border: 1px solid #000;
  }
</style>
