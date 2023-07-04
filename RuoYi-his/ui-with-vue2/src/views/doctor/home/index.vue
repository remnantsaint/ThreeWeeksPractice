<template>
  <div class="app-container">


    <el-row style="height: 100%;">
      <el-col :span="6" class="left">


        <el-tabs v-model="RegisterListActiveName">
          <el-tab-pane label="本人" name="person">

            <Register type="person" @selectRegister="selectRegister" :key="personKey"></Register>


          </el-tab-pane>
          <el-tab-pane label="科室" name="dept">

            <Register type="dept" @selectRegister="selectRegister" :key="deptKey"></Register>

          </el-tab-pane>
        </el-tabs>


      </el-col>
      <el-col :span="18" class="right">

        <div v-if="selected" class="personInfo">

          <span>就诊号码:{{ register.id }}</span>
          <span>患者姓名:{{ register.name }}</span>
          <span>年龄:{{ register.age }}</span>
        </div>


        <el-tabs v-model="handlerActiveName" :before-leave="handlerTabs">
          <el-tab-pane label="门诊首页" name="paper">

            <div style="margin-bottom: 10px">

              <el-row>
                <el-col :span="5" :offset="10">
                  <el-button type="primary" size="mini" @click="saveRegistger(1)">暂存</el-button>
                </el-col>
                <el-col :span="5">
                  <el-button type="primary" size="mini" @click="saveRegistger(2)">提交</el-button>
                </el-col>
              </el-row>
            </div>


            <el-form ref="form" :model="form" label-width="150px">

              <el-form-item label="主诉" prop="readme">
                <el-input v-model="form.readme" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="现病史" prop="present">
                <el-input v-model="form.present" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="现病史治疗情况" prop="presentTreat">
                <el-input v-model="form.presentTreat" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="既往史" prop="history">
                <el-input v-model="form.history" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="过敏史" prop="allergy">
                <el-input v-model="form.allergy" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="确诊疾病" prop="disease">
                <el-input v-model="form.disease" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="处置方案" prop="suit">
                <el-input v-model="form.suit" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
              <el-form-item label="药品清单" prop="drug">
                <el-input v-model="form.drug" type="textarea" placeholder="请输入内容"/>
              </el-form-item>
            </el-form>


          </el-tab-pane>
          <el-tab-pane label="检查申请" name="check">
            <el-button size="mini" type="primary" @click="CheckListVisible=true">选择检查</el-button>
            <el-button size="mini" type="primary" @click="saveCheckList">申请检查</el-button>


            <el-table :data="checkList"  @selection-change="handleCheckListSelectionChange">
              <el-table-column type="selection" width="55" align="center" :selectable="selectableCheckList"/>
<!--              <el-table-column label="id" align="center" prop="id"/>-->
              <el-table-column label="检查名称" align="center" prop="itemName"/>
              <el-table-column label="检查费用" align="center" prop="fee"/>
              <el-table-column label="状态" align="center" prop="status">

                <template  slot-scope="scope">
                  <el-tag type="info"  v-if="scope.row.status== 1">待缴费</el-tag>
                  <el-tag type="warning" v-else-if="scope.row.status== 2">已缴费</el-tag>
                  <el-tag type="danger" v-else-if="scope.row.status== 3">已检查</el-tag>
                  <el-tag type="danger" v-else-if="scope.row.status== 4">已退费</el-tag>
                  <el-tag v-else>新增</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template  slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    v-if="scope.row.status== 1 || scope.row.status == undefined"
                    @click="handlerCheckListRemove(scope.row,scope.$index)"
                  >删除   </el-button>



                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    v-if="scope.row.status== 3 "
                    @click="handlerCheckApplyDetail(scope.row)"
                  >详情   </el-button>


                </template>
              </el-table-column>
            </el-table>


            <!--    检查项目列表       -->
            <el-dialog :visible.sync="CheckListVisible" title="选择检查项目">
              <!--              -->
              <CheckList @selectCheck="selectCheck" :CheckListVisible.sync="CheckListVisible"></CheckList>
            </el-dialog>

          </el-tab-pane>
          <el-tab-pane label="检验申请" name="inspect">
            <el-button size="mini" type="primary" @click="InspectListVisible=true">选择检验</el-button>
            <el-button size="mini" type="primary" @click="saveInspectList">申请检验</el-button>

            <el-dialog :visible.sync="InspectListVisible" title="选择检验项目">
<!--              @selectCheck="selectCheck"-->
              <InspectList  @selectInspect="selectInspect"  ></InspectList>
            </el-dialog>


<!--            @selection-change="handleCheckListSelectionChange"-->
            <el-table :data="inspectList"   @selection-change="handleInspectListSelectionChange">
<!--              :selectable="selectableCheckList" -->
              <el-table-column type="selection" width="55" align="center" :selectable="selectableInspectList" />
              <!--              <el-table-column label="id" align="center" prop="id"/>-->
              <el-table-column label="检验名称" align="center" prop="itemName"/>
              <el-table-column label="检验费用" align="center" prop="fee"/>
              <el-table-column label="id" align="center" prop="id"/>
              <el-table-column label="itemId" align="center" prop="itemId"/>
              <el-table-column label="状态" align="center" prop="status">

                <template  slot-scope="scope">
                  <el-tag type="info"  v-if="scope.row.status== 1">待缴费</el-tag>
                  <el-tag type="warning" v-else-if="scope.row.status== 2">已缴费</el-tag>
                  <el-tag type="danger" v-else-if="scope.row.status== 3">已检查</el-tag>
                  <el-tag type="danger" v-else-if="scope.row.status== 4">已退费</el-tag>
                  <el-tag v-else>新增</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template  slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    v-if="scope.row.status== 1 || scope.row.status == undefined"
                    @click="handlerCheckListRemove(scope.row,scope.$index)"
                  >删除   </el-button>
                </template>
              </el-table-column>
            </el-table>



          </el-tab-pane>
        </el-tabs>


      </el-col>
    </el-row>





   <el-dialog title="查看检查结果" :visible.sync="checkApplyDetailFormVisible">

     <el-form ref="checkApplyDetailForm" :model="checkApplyDetailForm" label-width="150px">

       <el-form-item label="检查项目名称" prop="itemName">
         <el-input v-model="checkApplyDetailForm.itemName" type="textarea" disabled/>
       </el-form-item>
       <el-form-item label="检查结果" prop="result">
         <el-input v-model="checkApplyDetailForm.result" type="textarea" disabled />
       </el-form-item>
       <el-form-item label="影像资料" prop="url">
         <img  :src="checkApplyDetailForm.url" width="100%" class="avatar">
       </el-form-item>

     </el-form>



   </el-dialog>









  </div>
</template>

<script>
import Register from './Register'
import CheckList from './CheckList'
import InspectList from './InspectList'
import {saveRegister, saveCheckList,saveInspectList, getInfo} from "@/api/doctor/doctor";

import {getFile_info} from "@/api/system/file_info";

export default {
  name: 'DoctorHome',
  components: {
    Register,
    CheckList,
    InspectList
  },
  data() {
    return {
      CheckListVisible: false,
      InspectListVisible: false,

      RegisterListActiveName: 'person',
      handlerActiveName: 'paper',
      register: {},
      selected: false,
      form: {},
      personKey: 'personKey',
      deptKey: 'deptKey',
      checkList: [],
      inspectList:[],
      //选中的检查表格数据
      chooiceCheckList:[],
      //选中的检验表格数据
      chooiceInspectList:[],


      checkApplyDetailForm:{
        itemName:'',
        result:'',
        url:''
      },
      //是否显示详情页面
      checkApplyDetailFormVisible:false
    }
  },
  methods: {
    getInfo(){
      getInfo(this.register.id).then(resp => {
        this.checkList = resp.data.checkApplyList?resp.data.checkApplyList:[];
        this.inspectList = resp.data.inspectApplyList?resp.data.inspectApplyList:[];
      })
    },
    //选择患者
    selectRegister(register) {

      this.register = register;
      this.selected = true;

      //病例文本
      this.form.readme = register.readme
      this.form.present = register.present
      this.form.presentTreat = register.presentTreat
      this.form.history = register.history
      this.form.disease = register.disease
      this.form.suit = register.suit
      this.form.drug = register.drug


      // this.checkList = ?
      this.getInfo();



    },

    saveRegistger(type) {

      if (!this.register.id) {
        this.$modal.msgError("请选择患者");
        return;
      }


      this.form.id = this.register.id;
      this.form.status = type;

      saveRegister(this.form).then(resp => {
        let success = resp.data;
        if (success) {

          this.personKey = 'personKey' + Math.random();
          this.deptKey = 'deptKey' + Math.random();

          this.$modal.msgSuccess("成功反馈");
        } else {
          this.$modal.msgError("错误反馈");
        }
      })


    },

    handlerTabs(activeName, oldActiveName) {
      if (this.register.status == 3) {
        return true;
      }
      this.$modal.msgError("请选择已提交的患者!!!");
      return false;
    },
    //选择检查项目
    selectCheck(selectCheckList) {
      this.CheckListVisible = false;
      // this.checkList = selectCheckList;

      selectCheckList.forEach(item => {
        let exists = false;
        this.checkList.forEach(check => {
          if (item.id == check.itemId) {
            exists = true;
          }
        })
        //判断原来是否已经申请过
        if(!exists){
          this.checkList.push({
            itemId:item.id,
            itemName:item.name,
            fee:item.fee,
            registerId: this.register.id
          });
        }
      })

      //将check_item（id\name） ---> checkApply(item_name\item_id)

    },


    //选择检查项目
    selectInspect(selectInspectList) {
      this.InspectListVisible = false;

      selectInspectList.forEach(item => {
        let exists = false;
        this.inspectList.forEach(inspect => {
          console.log(item.id+"\t"+inspect.itemId)
          if (item.id == inspect.itemId) {
            exists = true;
          }
        })

        //判断原来是 否已经申请过
        if(!exists){
          this.inspectList.push({
            itemId:item.id,
            itemName:item.name,
            fee:item.fee,
            registerId: this.register.id
          });
        }
      })
    },
    handleCheckListSelectionChange(chooiceCheckList){
      this.chooiceCheckList = chooiceCheckList;
    },

    //接收选中的检验项目
    handleInspectListSelectionChange(chooiceInspectList){
      this.chooiceInspectList = chooiceInspectList;
    },

    saveCheckList() {

      //设置下项目对应的患者ID

      //发起网络请求
      //this.checkList 页面上所有的表格行
      //this.chooiceCheckList  //页面上被勾选的行
      saveCheckList(this.chooiceCheckList).then(resp => {

        if (resp.data) {
          this.$modal.msgSuccess("保存成功...");

         this.getInfo();


        } else {
          this.$modal.msgError("保存失败....");
        }


      })
    },

    //保存患者申请的检验项目
    saveInspectList(){
      saveInspectList(this.chooiceInspectList).then(resp => {

        if (resp.data) {
          this.$modal.msgSuccess("保存成功...");
          //重新拉取信息,
          this.getInfo();


        } else {
          this.$modal.msgError("保存失败....");
        }


      })
    },
    //每行渲染的时候 设置 复选框是否能被选中(检查)
    selectableCheckList(row, index){
      return !row.status
    },
    //每行渲染的时候 设置 复选框是否能被选中(检验)
    selectableInspectList(row, index){
      return !row.status
    },

    handlerCheckListRemove(row,index){
      // console.log("row",row)
      // console.log("scope",scope)
      this.$modal.confirm('确认删除检查项目:【'+row.itemName+'】').then(()=> {
        this.checkList.splice(index,1)
      })

    },

    //查看检查申请的详情
    handlerCheckApplyDetail(row){

      let {itemName , result} = row;


      this.checkApplyDetailForm.itemName = itemName;
      this.checkApplyDetailForm.result = result;
      // this.checkApplyDetailForm.url = row.fileId-->url;

      //process.env 获取的是nodejs的环境   '/dev-api/profile/upload/2023/04/11/教育账号_20230411134658A002.bmp"
      //        开发的服务器    index.html       `http://localhost:9090//dev-api/profile/upload/2023/04/11/教育账号_20230411134658A002.bmp"
      //        开发的服务器    index.html       http://localhost:9090/profile/upload/2023/04/11/教育账号_20230411134658A002.bmp"

      //根据文件ID获取 路径
      getFile_info( row.fileId).then(resp=>{
        let path = resp.data.filePath
        this.checkApplyDetailForm.url = process.env.VUE_APP_BASE_API +path;
        this.checkApplyDetailFormVisible = true; //显示dialog
      })






    }



  }

};
</script>

<style scoped>


.app-container {
  height: 100%;
}

.left {
  height: 100%;
}

.right {
  padding-left: 10px;
}

.personInfo > span {
  display: inline-block;
  margin: 0 10px;
}


</style>
