<template>
  <div ref="user">
    <img src="../../assets/user-banner.png" style="width: 100%" @click="generatePdf"/>
    <div id="user-info">
      <mu-row>
        <mu-col span="6">
          <img id="avatar" src="../../../static/images/naicha3.jpg">
        </mu-col>
        <mu-col span="6" class="username">
          <div id="username">
            {{user.userName}}
          </div>
        </mu-col>
      </mu-row>
    </div>
    <div id="op-list">
      <mu-list>
        <mu-list-item button :ripple="false" @click="tips">
          <mu-list-item-action>
            <mu-icon value="inbox"></mu-icon>
          </mu-list-item-action>
          <mu-list-item-title>地址: </mu-list-item-title>
        </mu-list-item>
        <mu-list-item button :ripple="false">
          <mu-list-item-action>
            <mu-icon value="inbox"></mu-icon>
          </mu-list-item-action>
          <mu-list-item-title>余额</mu-list-item-title>
        </mu-list-item>
        <mu-list-item button :ripple="false">
          <mu-list-item-action>
            <mu-icon value="inbox"></mu-icon>
          </mu-list-item-action>
          <mu-list-item-title>支付卡号：{{cardNo}}</mu-list-item-title>
        </mu-list-item>
      </mu-list>
    </div>

    <el-dialog title="添加地址" :visible.sync="addressDialogVisible" style="font-size: 12px;" width="80%">
      <div>
        <el-form ref="spreadItem" :inline="true">
          <el-cascader
            v-model="address"
            :options="addressOptions"
            @change="cascaderSelector"></el-cascader>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="addressDetail">
          </el-input>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer" style="text-align: center">
        <el-button class="buy-btn" @click="addAddress" >确定</el-button>
        <el-button @click="addDialogVisible = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  // import html2Canvas from 'html2canvas'
  // import JsPDF from 'jspdf'
  export default {
    name: 'User',
    data () {
      return {
        addressDetail: '',
        addressDialogVisible: false,
        addressOptions: [],
        address: "",
        cardNo: "1234"
      }
    },
    computed: {
      ...mapState({
        user: state => state.user.user
      })
    },
    methods: {
      getUserInfo() {
        this.$axios.get()
      },
      addAddress() {
        this.$axios.post('/user/address', {

        }).then(res => {

        }).catch(err => {
          console.error(err)
        })
      },
      tips() {
        this.$message.warning("功能尚在开发中...")
      },
      cascaderSelector() {},
      generatePdf() {
        var title = 'pdf名字'
        html2Canvas(this.$refs.user, {
          allowTaint: true
        }).then(function (canvas) {
          console.log("canvas %o", canvas)
          let contentWidth = canvas.width
          let contentHeight = canvas.height
          let pageHeight = contentWidth / 592.28 * 841.89
          let leftHeight = contentHeight
          let position = 0
          let imgWidth = 595.28
          let imgHeight = 592.28 / contentWidth * contentHeight
          let pageData = canvas.toDataURL('image/jpeg', 1.0)
          let PDF = new JsPDF('', 'pt', 'a4')
          if (leftHeight < pageHeight) {
            PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
          } else {
            while (leftHeight > 0) {
              PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
              leftHeight -= pageHeight
              position -= 841.89
              if (leftHeight > 0) {
                PDF.addPage()
              }
            }
          }
          PDF.save(title + '.pdf')
        })
      }
    }
  }
</script>

<style scoped>
  #username {
    margin: auto;
    position: relative;
    top: 17px;
    font-size: 19px;
    font-weight: 600;
  }
  #avatar {
    width: 64px;
    border-radius: 45px;
    top: 15px;
    position: relative;
  }
  #user-info {
    position: relative;
    top: -46px;
    height: calc(15vh);
    /*border-top-right-radius: 8px;*/
    /*border-top-left-radius: 8px;*/
    border-radius: 8px;
    margin: 0px 8px;
    background-color: white;
  }

  #op-list {
    background-color: white;
    border-radius: 8px;
    margin: 0px 8px;
  }
</style>
