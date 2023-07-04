<template>
  <div class="app-container">

    <div class="undo item">
      <el-tag>待接诊患者</el-tag>
      <el-table
          :data="undoList"
          style="width: 100%" @row-click="selectRegister">
          <el-table-column
            prop="id"
            label="号序"
            >
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名"
           >
          </el-table-column>
          <el-table-column
            prop="age"
            label="年龄">
          </el-table-column>
        </el-table>



    </div>



    <div class="done item">
      <el-tag>已接诊患者</el-tag>
      <el-table
        :data="doneList"
        style="width: 100%" @row-click="selectRegister">
        <el-table-column
          prop="id"
          label="号序"
        >
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
        >
        </el-table-column>
        <el-table-column
          prop="age"
          label="年龄">
        </el-table-column>
      </el-table>


    </div>




  </div>
</template>

<script>
import {listRegister} from "@/api/doctor/doctor";



export default {
  name : 'RegistList',
  props:['type'],
  data(){
    return {
      tableData:[],
      registList:[]
    }
  },
  computed:{
    undoList: function () {
      let array = [];
      this.registList.forEach(item=>{
          if(item.status == 1){
            array.push(item)
          }
      })
      return array;
    },
    doneList: function () {
      let array = [];
      this.registList.forEach(item=>{
        if(item.status == 2|| item.status == 3){
          array.push(item)
        }
      })
      return array;
    }
  },

  // methods:{
  //   let type = this.type;
  //   listRegister().then(resp=>{
  //     console.log("resp",resp)
  //   })
  // },
  methods:{
    listRegister(){
        let type = this.type;
        listRegister(type).then(resp=>{
          this.registList = resp.data;
        })
    },

    //监听行选择
    selectRegister(	row, column, event){
      console.log("row",row);

      this.$emit("selectRegister",row);

    }

  },
  created() {
    this.listRegister();

  }
};
</script>
<style scoped>

  .app-container{
    height: 100%;
  }



  .item{
    height: 300px;
    /*border-bottom: solid 1px black;*/
  }


  .undo{

  }

  .done{

  }


</style>
