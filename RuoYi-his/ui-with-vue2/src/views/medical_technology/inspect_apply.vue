<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病历号" prop="registerId">
        <el-input
          v-model="queryParams.registerId"
          placeholder="请输入病历号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>



    <el-table v-loading="loading" :data="check_applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="病历号" align="center" prop="registerId" />
      <el-table-column label="患者姓名" align="center" prop="name" />
      <el-table-column label="项目id" align="center" prop="itemId" />
      <el-table-column label="项目名称" align="center" prop="itemName" />
      <el-table-column label="检查费用" align="center" prop="fee" />
      <el-table-column label="状态" align="center" prop="status">

        <template  slot-scope="scope">
          <el-tag type="info"  v-if="scope.row.status== 1">待缴费</el-tag>
          <el-tag type="warning" v-else-if="scope.row.status== 2">已缴费</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 3">已检查</el-tag>
          <el-tag type="danger" v-else-if="scope.row.status== 4">已退费</el-tag>
        </template>
        <!--      <el-table-column label="状态" align="center" prop="status" />-->
      </el-table-column>

      <el-table-column label="申请时间" align="center" prop="createtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}-{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-if="scope.row.status == 2"
          >处理</el-button>


          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDetail(scope.row)"
            v-if="scope.row.status == 3"
          >查看详情</el-button>
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

    <!-- 添加或修改检查申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>


      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="病历号" prop="registerId">
          <el-input v-model="form.registerId" readonly placeholder="请输入病历号" />
        </el-form-item>
        <el-form-item label="项目id" prop="itemId">
          <el-input v-model="form.itemId" readonly placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="项目名称" prop="itemName">
          <el-input v-model="form.itemName" readonly placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="检查费用" prop="fee">
          <el-input v-model="form.fee"  readonly placeholder="请输入检查费用" />
        </el-form-item>
        <el-form-item label="检查结果" prop="result">
          <el-input v-model="form.result" type="textarea" placeholder="请输入检查结果" />
        </el-form-item>

        <el-form-item label="影像资料" prop="fee">

          <el-upload
            class="avatar-uploader"
            :action="upload.url"
            :headers="upload.headers"
            :show-file-list="false"
            :on-success="handleFileSuccess"
          >
            <img v-if="url!='' " :src="url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>





        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCheck_apply, getCheck_apply, result  } from "@/api/medical_technology";
import {getToken} from '@/utils/auth'

export default {
  name: "Check_apply",
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
      // 检查申请表格数据
      check_applyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        registerId: null,
        itemId: null,
        itemName: null,
        fee: null,
        status: null,
        active: null,
        createtime: null
      },
      // 表单参数
      form: {

      },
      // 表单校验
      rules: {
      },
      // 上传参数
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/common/upload",
        // 上传的文件列表
        fileList: []
      },
      url:''

    };
  },
  created() {
    this.getList();

  },
  methods: {
    /** 查询检查申请列表 */
    getList() {
      this.loading = true;
      listCheck_apply(this.queryParams).then(response => {
        this.check_applyList = response.records;
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
        registerId: null,
        itemId: null,
        itemName: null,
        fee: null,
        status: null,
        active: null,
        createtime: null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "检查结果处理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCheck_apply(id).then(response => {
        this.form = response.data;
        this.form.url = ''
        this.open = true;
        this.title = "检查结果处理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {

          result(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });

        }
      });
    },

    // 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },
// 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
// 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.isUploading = false;
      this.form.fileName = response.newFileName;
      this.form.filePath = response.fileName;
      this.form.orgName = response.originalFilename;


      this.url = process.env.VUE_APP_BASE_API + response.fileName;

      this.$modal.msgSuccess(response.msg);
    },
    handleDetail(row){

      查看已处理的检查申请
      alert("")
    }



  }
};
</script>
<style scoped>

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
