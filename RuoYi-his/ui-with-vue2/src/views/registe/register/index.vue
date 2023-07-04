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
      <el-form-item label="出生日期" prop="birthday">
        <el-date-picker clearable
                        v-model="queryParams.birthday"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input
          v-model="queryParams.age"
          placeholder="请输入年龄"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="家庭住址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入家庭住址"
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
      <el-form-item label="是否要病历本" prop="book">
        <el-input
          v-model="queryParams.book"
          placeholder="请输入是否要病历本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="看诊时间" prop="visittime">
        <el-date-picker clearable
                        v-model="queryParams.visittime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择看诊时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="挂号费用" prop="fee">
        <el-input
          v-model="queryParams.fee"
          placeholder="请输入挂号费用"
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
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag type="info"  v-if="scope.row.status== 1">待诊</el-tag>
          <el-tag type="warning" v-else-if="scope.row.status== 2">暂存</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 3">初诊</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 4">已退号</el-tag>
        </template>

      </el-table-column>
      <el-table-column label="看诊时间" align="center" prop="visittime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visittime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="挂号费用" align="center" prop="fee"/>
      <!--      <el-table-column label="主诉" align="center" prop="readme"/>-->
      <!--      <el-table-column label="现病史" align="center" prop="present"/>-->
      <!--      <el-table-column label="现病史治疗情况" align="center" prop="presentTreat"/>-->
      <!--      <el-table-column label="既往史" align="center" prop="history"/>-->
      <!--      <el-table-column label="过敏史" align="center" prop="allergy"/>-->
      <!--      <el-table-column label="确诊疾病" align="center" prop="disease"/>-->
      <!--      <el-table-column label="处置方案" align="center" prop="suit"/>-->
      <!--      <el-table-column label="药品清单" align="center" prop="drug"/>-->
      <!--      <el-table-column label="状态" align="center" prop="status"/>-->
      <!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-button-->
      <!--            size="mini"-->
      <!--            type="text"-->
      <!--            icon="el-icon-edit"-->
      <!--            @click="handleUpdate(scope.row)"-->
      <!--            v-hasPermi="['registe:register:edit']"-->
      <!--          >修改-->
      <!--          </el-button>-->
      <!--          <el-button-->
      <!--            size="mini"-->
      <!--            type="text"-->
      <!--            icon="el-icon-delete"-->
      <!--            @click="handleDelete(scope.row)"-->
      <!--            v-hasPermi="['registe:register:remove']"-->
      <!--          >删除-->
      <!--          </el-button>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改诊疗信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">


        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入姓名"/>
            </el-form-item>

          </el-col>
          <el-col :span="12">

            <el-form-item label="性别" prop="gender">
              <el-select v-model="form.gender" placeholder="请选择性别">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>

          </el-col>
        </el-row>




        <el-row>
          <el-col :span="12">

            <el-form-item label="身份证号" prop="idno">
              <el-input v-model="form.idno" placeholder="请输入身份证号"/>
            </el-form-item>


          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker clearable
                              v-model="form.birthday"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择出生日期">
              </el-date-picker>
            </el-form-item>

          </el-col>
        </el-row>










        <el-row>
          <el-col :span="12">
            <el-form-item label="年龄" prop="age">
              <el-input v-model="form.age" placeholder="请输入年龄"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="家庭住址" prop="address">
              <el-input v-model="form.address" placeholder="请输入家庭住址"/>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-form-item label="挂号级别" prop="regsitLevelId">
              <!--                          <el-input v-model="form.regsitLevelId" placeholder="请输入挂号级别" />-->

              <el-select v-model="form.regsitLevelId" placeholder="请选择挂号级别" @change="changeRegistLevel()">
                <el-option
                  v-for="level in registLevels"
                  :key="level.id"
                  :label="level.registname"
                  :value="level.id"
                ></el-option>
              </el-select>
            </el-form-item>

          </el-col>
          <el-col :span="12">

            <el-form-item label="挂号科室" prop="deptId">
              <el-select v-model="form.deptId" placeholder="请选择挂号科室" @change="listDoctor()">
                <el-option
                  v-for="dept in depts"
                  :key="dept.deptId"
                  :label="dept.deptName"
                  :value="dept.deptId"
                ></el-option>
              </el-select>
              <!--                          <el-input v-model="form.deptId" placeholder="请输入挂号科室" />-->
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">

            <el-form-item label="看诊医生" prop="doctorId">
              <!--          <el-input v-model="form.doctorId" placeholder="请输入看诊医生"/>-->
              <el-select v-model="form.doctorId" placeholder="请选择看诊医生">
                <el-option
                  v-for="doctor in doctors"
                  :key="doctor.userId"
                  :label="doctor.nickName"
                  :value="doctor.userId"
                ></el-option>
              </el-select>


            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="看诊时间" prop="visittime">
              <el-date-picker clearable
                              v-model="form.visittime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择看诊时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>




        <el-row>
          <el-col :span="12">
            <el-form-item label="是否需要病历本" prop="book">
            <el-select v-model="form.book" placeholder="是否需要病历本" @change="changeRegistLevel">
              <el-option
                v-for="dict in dict.type.sys_yes_no"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
            </el-form-item>


          </el-col>
          <el-col :span="12">

            <el-form-item label="挂号费用" prop="fee">
              <el-input v-model="form.fee" readonly/>
            </el-form-item>


          </el-col>
        </el-row>



      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
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
  listDoctor
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRegister(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改诊疗信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRegister(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegister(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除诊疗信息编号为"' + ids + '"的数据项？').then(function () {
        return delRegister(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('registe/register/export', {
        ...this.queryParams
      }, `register_${new Date().getTime()}.xlsx`)
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
    //挂号级别有变化， listDoctor ， 显示一下，挂号的费用
    changeRegistLevel() {
      this.listDoctor();

      this.registLevels.forEach(item => {

        if (item.id == this.form.regsitLevelId) {
          this.form.fee = item.registfee  + (this.form.book == 'Y'?1:0);
          return false;
        }
      })
    }
  }
};
</script>
