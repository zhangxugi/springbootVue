<template>
  <div  class="container" id="app">
    <el-form :model="ruleForm"  ref="ruleForm" label-width="80px" class="demo-ruleForm">
      <el-form-item label="单位名称 ">
        <el-input v-model="ruleForm.company"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="ruleForm.phone"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleForm)">立即创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
  export default {
    name: "insertmessages",
    data() {
      return {
        ruleForm: {
          company: '',
          phone: ''
        },

      }

    },
    methods: {
      submitForm: function () {
        this.$axios.post('v1/hello/insertmessages', this.ruleForm).then(
          res => {
            alert("添加成功")
            this.$router.push({path: '/messages'})
          }
        ).catch(
          this.open
        );
      },
      open() {
        this.$message('添加失败');
      },

  }
    }
</script>

<style scoped>

</style>


