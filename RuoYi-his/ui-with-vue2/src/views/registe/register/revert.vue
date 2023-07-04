<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idno">
        <el-input
          v-model="queryParams.idno"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="挂号级别" prop="regsitLevelId">
        <el-input
          v-model="queryParams.regsitLevelId"
          placeholder="请输入挂号级别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="挂号科室" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入挂号科室"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="看诊医生" prop="doctorId">
        <el-input
          v-model="queryParams.doctorId"
          placeholder="请输入看诊医生"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>



      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['registe:register:add']"
        >挂号
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['registe:register:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id病历号" align="center" prop="id"/>
      <el-table-column label="姓名" align="center" prop="name"/>
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="挂号级别" align="center" prop="regsitLevelId"/>
      <el-table-column label="挂号科室" align="center" prop="deptId"/>
      <el-table-column label="看诊医生" align="center" prop="doctorId"/>
      <el-table-column label="看诊时间" align="center" prop="visittime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visittime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag type="info"  v-if="scope.row.status== 1">待诊</el-tag>
          <el-tag type="warning" v-else-if="scope.row.status== 2">暂存</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 3">初诊</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 4">已退号</el-tag>
        </template>

      </el-table-column>
      <el-table-column label="挂号费用" align="center" prop="fee"/>
      <el-table-column label="操作" align="center" prop="id" width="180">
        <template slot-scope="scope">
          <el-button  size="mini" @click="revert(scope.row.id)">退号</el-button>

        </template>
      </el-table-column>

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


  </div>
</template>

<script>
import {
  listRegister,
  getRegister,
  delRegister,
  addRegister,
  updateRegister,
  getInfo,
  listDoctor,
  revert
} from "@/api/registe/register";

export default {
  name: "Register",
  dicts: ['sys_user_sex','sys_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 诊疗信息表格数据
      registerList: [],
      depts: [],
      registLevels: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        gender: null,
        idno: null,
        birthday: null,
        age: null,
        address: null,
        regsitLevelId: null,
        deptId: null,
        doctorId: null,
        book: null,
        visittime: null,
        fee: null,
        readme: null,
        present: null,
        presentTreat: null,
        history: null,
        allergy: null,
        disease: null,
        suit: null,
        drug: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      doctors: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询诊疗信息列表 */
    getList() {
      this.loading = true;
      listRegister(this.queryParams).then(response => {
        this.registerList = response.records;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        gender: null,
        idno: null,
        birthday: null,
        age: null,
        address: null,
        regsitLevelId: null,
        deptId: null,
        doctorId: null,
        book: null,
        visittime: null,
        fee: null,
        readme: null,
        present: null,
        presentTreat: null,
        history: null,
        allergy: null,
        disease: null,
        suit: null,
        drug: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        isDelete: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      getInfo().then((response) => {

        this.depts = response.depts
        this.registLevels = response.registLevels
        this.reset();
        this.open = true;
        this.title = "添加诊疗信息";
      })

    },

    /**
     * 根据科室、医生级别 获取对应的医生
     */
    listDoctor() {
      if (!this.form.deptId || !this.form.regsitLevelId) {
        return;
      }
      listDoctor(this.form.deptId, this.form.regsitLevelId).then(resp => {
        this.doctors = resp.data
      })
    },

    revert(id){
      // return request()  service -->axios
      revert(id).then(resp=>{
        // resp ==> Ret<Boolean>
        if( resp.data ){
          this.$modal.msgSuccess("退号成功");
          this.getList();
        }else{
          this.$modal.msgError("错误反馈");
        }
      })
    }
  }
};
</script>
