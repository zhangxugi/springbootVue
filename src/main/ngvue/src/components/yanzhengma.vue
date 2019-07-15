<template>
<el-form ref="loginForm" :model="loginForm" class="login-form">
  <el-form-item>
    <el-input  v-model="loginForm.phone" placeholder="手机号"></el-input>
  </el-form-item>
  <el-form-item label="" prop="code" class="pr">

    <el-input prop="code" v-model="loginForm.code" placeholder="验证码"></el-input>
    <button @click="getCode('loginForm')" class="code-btn" :disabled="!show">
      <span v-show="show">发送验证码</span>
      <span v-show="!show" class="count">{{count}} s</span>
    </button>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="add('loginForm')" style="width:50%;">登录</el-button>
  </el-form-item>
</el-form>
</template>
<script>
  const TIME_COUNT = 60 // 倒计时的时间
  export default {
    data () {
      return {
        loginForm: {
          phone: '',
          code:''
        },
        show: true,
        count: '',
        timer: null
      }
    },
    methods: {
      // 验证码倒计时
      getCode () {
        var reg=11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/;
        if (!reg.test(this.loginForm.phone)) {
          alert('手机格式不正确');
         }else {
          if (!this.timer) {
            this.count = TIME_COUNT
            this.show = false
            this.timer = setInterval(() => {
              if (this.count > 0 && this.count <= TIME_COUNT) {
                this.count--
              } else {
                this.show = true
                clearInterval(this.timer)
                this.timer = null
              }
            }, 1000);
            this.$axios.get('v1/hello/yanzhengma/', {params: {phone: this.loginForm.phone}}).then((response) => {
              var status = response.data;
              if (status === 'success') {
                alert("成功");
              } else {
                alert(response.data);
              }
              console.log(response);
            }).catch((error) => {
              console.log(response);
            });

          }
        }
        },
      //手机号登录
      add:function () {
        this.$axios.get('v1/hello/phonelogin/', {params: {phone: this.loginForm.phone,code: this.loginForm.code}}).then(
          response=>{
            var status = response.data;
            if (status === 'success') {
              alert(response.data);
              this.$router.push({ path:'/list'})
            } else {
              alert(response.data);
            } console.log(response);
          }).catch((error) => {
          console.log(response);
        });
      },
      }


  }

</script>
<style scoped>
  .pr {
    position: relative;
  }
  .code-btn {
    width: 100px;
    height: 24px;
    position: absolute;
    top: 10px;
    right: 5px;
    z-index: 222;
    color: #F5A623;
    font-size: 14px;
    border: none;
    border-left: 1px solid #bababa;
    padding-left: 10px;
    background-color: #fff;
  }

</style>
