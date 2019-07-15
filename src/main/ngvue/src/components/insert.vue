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
      <el-form-item label="日期">
        <el-date-picker
          v-model="ruleForm.ecreaton"
          type="datetime"
          placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="地址" prop="eaddress">
        <VDistpicker :province="getListData.province" :city="getListData.city" :area="getListData.district" @province="onChangeProvince" @city="onChangeCity" @area="onChangeArea" ></VDistpicker>
      </el-form-item>
      <el-form-item label="详细地址" prop="detailedaddress">
      <el-input v-model="ruleForm.detailedaddress" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleForm)">立即创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
  import VDistpicker from 'v-distpicker'
  export default {
    components: { VDistpicker },
    name: "insert",
    data () {
      return{
        ruleForm:{
          ename :'',
          epassword :'',
          esex:'',
          eaddress :'',
         detailedaddress:'',
          ecreaton:''
        },
        getListData:{
          province:'',
          city:'',
          district:''
        },

      }

    },
    methods:{
      submitForm :function() {
        this.ruleForm.eaddress=this.getListData.province+this.getListData.city+this.getListData.district+this.ruleForm.detailedaddress;
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
      },
      onChangeProvince(data) {
        this.getListData.province = data.value;
      },
      onChangeCity(data) {
        this.getListData.city = data.value;
      },
      onChangeArea(data) {
        this.getListData.district = data.value;
      },

    }
  }
</script>

<style scoped>

</style>
