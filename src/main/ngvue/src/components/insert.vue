<template>
  <div  class="container" id="app">
    <el-form :model="ruleForm"  ref="ruleForm" label-width="80px" class="demo-ruleForm">
      <el-form-item label="名字">
        <el-input v-model="ruleForm.ename"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="ruleForm.epassword"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="esex">
        <el-select v-model="ruleForm.esex" placeholder="选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="地址" prop="eaddress">
        <el-input v-model="ruleForm.eaddress"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleForm)">立即创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
  export default {
    name: "insert",
    data () {
      return{
        ruleForm:{
          ename :'',
          epassword :'',
          esex:'',
          eaddress :''


        },
      }

    },
    methods:{
      submitForm :function() {
        this.$axios.post('v1/hello/insert', this.ruleForm).then(
          res=>{
          alert("添加成功")
        this.$router.push({ path:'/list'})}
        ).catch(
          this.open
        ) ;
      },
      open() {
        this.$message('添加失败');
      }
    }
  }
</script>

<style scoped>

</style>
