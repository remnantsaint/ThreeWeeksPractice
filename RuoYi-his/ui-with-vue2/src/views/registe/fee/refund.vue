<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病历号" prop="name">
        <el-input
          v-model="queryParams.registerId"
          placeholder="请输入病历号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>


    <div>
      应退金额： ￥ {{allFee}}             <el-button type="primary" icon="el-icon-monitor" size="mini" @click="refund()">退费</el-button>
    </div>
    <el-table v-loading="loading" :data="appliItemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" :selectable="selectAble" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="病历号" align="center" prop="registerId" />
      <el-table-column label="患者姓名" align="center" prop="registerName" />
      <el-table-column label="项目名称" align="center" prop="itemName" />
      <el-table-column label="检查费用" align="center" prop="fee" />
      <el-table-column label="状态" align="center" prop="status" >

        <template  slot-scope="scope">
          <el-tag type="primary"  v-if="scope.row.status== 1">待缴费</el-tag>
          <el-tag type="warning" v-else-if="scope.row.status== 2">已缴费</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 3">已检查</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 4">已退费</el-tag>
        </template>
      </el-table-column>

    </el-table>


  </div>
</template>

<script>
import { getRegisterApplyItems ,refund } from "@/api/registe/fee";

export default {
  name: "RegisterFee",
  data() {
    return {
      // 遮罩层
      loading: false,
      //勾选了那些代收费的项目
      selection:[],

      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 已申请的项目集合
      appliItemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        registerId:null,

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    // this.getRegisterApplyItems();
  },
  computed:{
    allFee(){
      let fee = 0;
      this.selection.forEach(item=>{
        fee += parseFloat(item.fee);
      })
      return fee;
    }
  },

  methods: {
    /** 查询检查项目管理列表 */
    getRegisterApplyItems() {
      this.loading = true;
      // let registId = 3;
      getRegisterApplyItems(this.queryParams.registerId).then(response => {
        this.appliItemList = response.data;
        this.loading = false;

        if(!this.appliItemList ||  this.appliItemList.length==0){
          this.$modal.msgWarning("未查询患者");

        }
      });
    },


    /** 搜索按钮操作 */
    handleQuery() {
      this.getRegisterApplyItems();
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selection = selection;
    },


    //判断是否允许收费（是否可选择）
    selectAble(row){
      return row.status == 2;
    },
    refund(){

      //获取到当前选中的项目 (id,type)
      let arr = [];
      this.selection.forEach(item=>{
        arr.push(
            {
                id:item.id,
                type:item.type
            })
      });

      refund(arr).then(resp=>{
        if(resp.data){
          this.$modal.msgSuccess("退费成功");
          this.handleQuery()
        }else{
          this.$modal.msgError("退费失败");
        }
      })

    }


  }
};
</script>
