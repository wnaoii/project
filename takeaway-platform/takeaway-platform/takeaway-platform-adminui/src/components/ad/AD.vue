<template>
    <div>
      <div class="table">
        <el-table :data="tableData" border stripe
        >
          <el-table-column prop="adName" label="广告名称">
          </el-table-column>
          <el-table-column prop="createDate" label="上线时间">
          </el-table-column>
          <el-table-column prop="soldStatus" label="广告图片" width="200px">
            <template slot-scope="scope">
              <div>
                <img style="height: 50px" :src="'data:image/png;base64,' + scope.row.image">
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="contacts" label="添加人">
          </el-table-column>
          <el-table-column prop="operation" label="操作" width="300px">
            <template slot-scope="scope">
              <div style="text-align: center">
                <el-button type="danger"
                           @click="deleteAd(scope.row.adId)" size="mini">删除
                </el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>

        <el-button type="primary" @click="insertDialogVisible = true">新增</el-button>

        <el-dialog title="警告" :visible.sync="deleteDialogVisible" style="font-size: 12px;" width="80%">
          <div slot="footer" class="dialog-footer" style="text-align: center">
            <el-button type="danger" @click="deleteAd">确定</el-button>
            <el-button @click="deleteDialogVisible = false">取消</el-button>
          </div>
        </el-dialog>

        <el-dialog title="新增广告" :visible.sync="insertDialogVisible" style="font-size: 12px;" width="80%">
          <div>
            <el-form :model="insertAdData" ref="adForm" :rules="insertRules" :inline="true">
              <div>
                <el-form-item label="广告名称：" prop="adName">
                  <el-input placeholder="请输入广告名称" style="width: 95%" v-model="insertAdData.adName" ></el-input>
                </el-form-item>
              </div>
              <div>
                <el-form-item prop="btnIcon" label="广告图标：">
                  <el-upload class="avatar-uploader" :action="imageApi" :auto-upload="false" :name="'adIcon'"
                             ref="iconUpload" :limit="1"
                             :data="iconData" :multiple="false" :file-list="fileList"
                             :on-success="onUploadSuccess"
                             list-type="picture">
                    <el-button slot="trigger" size="small" type="primary">选择图片</el-button>
                  </el-upload>
                </el-form-item>
              </div>
            </el-form>
          </div>
          <div slot="footer" class="dialog-footer" style="text-align: center">
            <el-button @click="saveAd" type="primary">确定</el-button>
            <el-button @click="insertDialogVisible = false">取消</el-button>
          </div>
        </el-dialog>

      </div>
    </div>
</template>

<script>
  export default {
    name: 'AD',
    data() {
      return {
        tableData: [],
        fileList: [],
        insertAdData: {

        },
        imageApi: this.$axios.defaults.baseURL + "/ad/",
        insertDialogVisible: false,
        deleteDialogVisible: false,
        insertRules: {
          adName: [
            {required: true, message: '请输入广告名', trigger: 'blur'}
          ]
        },
        iconData: {
          userId: "",
          adName: ""
        },
      }
    },
    mounted() {
      this.getAdList();
    },
    methods: {
      deleteAd() {

      },
      saveAd() {
        //todo 双击保护
        this.$refs["adForm"].validate((valid) => {
          if (valid) {
            this.iconData.userId = sessionStorage.userId;
            this.iconData.adName = this.insertAdData.adName;
            this.$refs.iconUpload.submit();
          }
        })
      },
      onUploadSuccess() {
        this.insertDialogVisible = false
      },
      getAdList() {
        this.$axios.get("/ad/list")
          .then(res => {
            this.tableData = res.data
            this.tableData.forEach(o => {
              o.createDate = this.$moment(o.createDate).format("YYYY-MM-DD")
            })
          }).catch(err => {
            console.log()
        })
      }
    }
  }
</script>

<style scoped>

</style>
