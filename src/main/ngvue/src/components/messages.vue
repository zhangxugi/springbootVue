<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="mid" label="ID" width="180"></el-table-column>
    <el-table-column prop="company" label="单位名称" width="180"></el-table-column>
    <el-table-column prop="phone" label="手机号" width="180"></el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button size="mini"   type="danger" @click="messagesSelectbyId(scope.$index, scope.row,tableData)">编辑</el-button>
        <el-button size="mini"  type="danger"> <router-link to="/insertmessages">添加</router-link></el-button>

        <el-dialog title="修改数据" :visible.sync="dialogFormVisible">
          <el-form :model="ruleForm">
            <el-form-item label="单位名称">
              <el-input v-model="ruleForm.company" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="手机号" >
              <el-input v-model="ruleForm.phone" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false,handleEdit(ruleForm)">确 定</el-button>
          </div>
        </el-dialog>
        <el-button size="mini" type="danger" @click="confirmationsending(scope.$index, scope.row,tableData)">确定发送</el-button>

      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        ruleForm: {
          company: '',
          phone: ''
        },
        dialogTableVisible: false,
        dialogFormVisible: false,
      }
    },
    //查找
    mounted: function () {
      this.findAll();
    },

    methods: {
      findAll: function () {
        this.$axios.post('v1/hello/selectmessages', this.tableData).then((response) => {
          this.tableData = response.data;
        });
      },
//确认发送
      confirmationsending: function (index, row, tableData) {
        this.$axios.get('v1/hello/yanzhengma', {params: {phone: tableData[index].phone}}).then((response) => {
         // this.tableData = response.data;
          var status= response.data;
          if(status==='success') {
          alert("发送成功");
            this.$router.push({path: '/list'});
          }

        });
      },
      //根据id查找
      messagesSelectbyId: function (index, row, tableData) {
        alert(tableData[index].mid)
        this.$axios.get('v1/hello/selectmessagesbyid/', {params: {id: tableData[index].mid}}
        ).then(
          response => {
            this.dialogTableVisible = true;
            this.dialogFormVisible = true;
            this.ruleForm = response.data;
          }
        );
      },
      //修改
      handleEdit: function () {
        this.$axios.post('v1/hello/updatemessages', this.ruleForm).then(res => {
            var status = res.data;
            if (status === 'success') {
              alert("成功");
              this.findAll();
            }
          }
        );
      },
    }
    }
</script>
<style scoped>
  a {
    text-decoration: none;
  }
</style>
