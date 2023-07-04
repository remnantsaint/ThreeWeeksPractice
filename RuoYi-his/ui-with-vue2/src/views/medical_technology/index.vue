<template>

  <div class="container">

    <el-tabs v-model="tabName">
      <el-tab-pane label="处理检查" name="check">

        <CheckApply></CheckApply>

      </el-tab-pane>
      <el-tab-pane label="处理检验" name="inspect">

        <InspectApply></InspectApply>

      </el-tab-pane>
    </el-tabs>


  </div>

</template>

<script>

import CheckApply from './check_apply'
import InspectApply from './inspect_apply'

export default {
  name: 'MedicalTechnology',
  components: {
    CheckApply,
    InspectApply
  },
  data() {
    return {
      tabName:'check'
    }
  },
  methods: {},
  created() {
  }

}

</script>

<style scoped>
.container {

  padding: 10px;


}


</style>
