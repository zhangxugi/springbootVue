<template>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column prop="eid" label="ID" width="180"></el-table-column>
    <el-table-column prop="ename" label="姓名" width="180"></el-table-column>
    <el-table-column prop="esex" label="性别" width="180"></el-table-column>
    <el-table-column prop="ecreaton" label="日期" width="180"></el-table-column>
    <el-table-column prop="eaddress" label="地址" width="180"></el-table-column>
    <el-table-column label="操作"><template slot-scope="scope">
        <el-button size="mini"  type="danger" @click="empSelectbyId(scope.$index, scope.row,tableData)">编辑</el-button>
      <el-button size="mini" type="danger"><router-link to="/insert">添加</router-link></el-button>

      <el-dialog title="修改数据" :visible.sync="dialogFormVisible">
        <el-form :model="ruleForm">
          <el-form-item label="名称" >
            <el-input v-model="ruleForm.ename" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" >
            <el-input v-model="ruleForm.epassword" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" >
            <el-select v-model="ruleForm.esex" placeholder="请选择性别">
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
          <el-form-item label="地址" prop="eaddress" >
            <VDistpicker :province="getListData.province" :city="getListData.city" :area="getListData.district" @province="onChangeProvince" @city="onChangeCity" @area="onChangeArea" ></VDistpicker>
          </el-form-item>
          <el-form-item label="详细地址" prop="detailedaddress">
            <el-input v-model="ruleForm.detailedaddress" style="width: 300px"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false,handleEdit(ruleForm)">确 定</el-button>
        </div>
      </el-dialog>
        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row,tableData)">删除</el-button>
      <el-button size="mini" type="danger" @click="Shortmessages(scope.$index, scope.row,tableData)">短信发送</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
<!---->
<script>
  import VDistpicker from 'v-distpicker'
  export default {
    components: { VDistpicker },
    data() {
      return {
        tableData: [],
        ruleForm: {
          eid: '',
          ename: '',
          esex: '',
          epassword: '',
          eaddress: '',
          detailedaddress:'',
          ecreaton:''
        },
        getListData:{
          province:'',
          city:'',
          district:''
        },
        dialogTableVisible: false,
        dialogFormVisible: false,
      }
    },
  //查找
  mounted:function(){
      this.findAll();
  },
  
    methods: {
      findAll:function () {
        this.$axios.post('v1/hello/select',this.tableData).then((response)=>{
          this.tableData=response.data;
        });
      },
      //删除
      handleDelete : function(index,row,tableData) {
        this.$axios.get('v1/hello/delete/',{params :{id:tableData[index].eid}}).then(
          (response)=>{
            this.findAll()
          })
          .catch(function (res) {
          console.log(res.data);
        })
      },
      //根据id查找
      empSelectbyId:function(index,row,tableData){
        alert(tableData[index].eid)
        this.$axios.get('v1/hello/selectbyid/', {params :{ids:tableData[index].eid}}
        ).then(
          response=>{
           this.dialogTableVisible=true;
            this.dialogFormVisible=true;
            this.ruleForm = response.data;
          }

        );
      },
      //修改
      handleEdit:function() {
      this.$axios.post('v1/hello/update',this.ruleForm).then(res => {
       var status= res.data;
       if(status==='success'){
         alert("成功");
         this.findAll();
       }
        }
       );
},

      //短信发送
      Shortmessages:function (index,row,tableData) {
        if(tableData[index].eaddress!==''&&tableData[index].eaddress!==null){
          this.$router.push({path: '/messages'});
        }else {
          alert("地址为空");
        }

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
<style>
  a {
    text-decoration: none;
  }
</style>
