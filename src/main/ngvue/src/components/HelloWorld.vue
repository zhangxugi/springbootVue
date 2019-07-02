<template>
  <div class="login">
    <el-form :model="ruleForm"  ref="ruleForm" label-width="50px" class="demo-ruleForm">

      <el-form-item label="账号" prop="ename">
        <el-input v-model="ruleForm.ename" autocomplete="on"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="epassword">
        <el-input type="password" v-model="ruleForm.epassword" autocomplete="on"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')" style="width:100%;">登录</el-button>
      </el-form-item>
    </el-form>
  </div>


</template>

<script>
   export default {
    data() {
      return {
        ruleForm: {
          ename: '',
          epassword:''
        },
        checked: false

      };
    },

    methods: {
      submitForm() {
        if (this.ruleForm.username === '' || this.ruleForm.epassword === '') {
          alert('账号密码不能为空');
        } else {
          this.$axios.post('v1/hello/login',this.ruleForm).then((response)=>{
            var status=response.data;
            if(status==='success'){
              alert("成功");
              this.$router.push({ path:'/list'})
            }else{
              alert(response.data);
            }
            console.log(response);
          }).catch((error)=>{
            console.log(response);
          });

        }

      }
    }

  };
</script>


<style scoped>
  .login{
    min-width: 350px;
    width: 25%;
    margin: auto;
  }






</style>

