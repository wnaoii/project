<!--首页栏目维护-->
<template>
  <div>
    <div class="title-h1">首页栏目维护</div>
    <div>
      <el-table :data="plateData" border class="table-border">
        <el-table-column prod="seqNo" label="显示顺序">
          <template slot-scope="{$index, row}">
            <span>{{row.seqNo}}</span>
          </template>
        </el-table-column>
        <el-table-column prod="appName" label="应用显示名称">
          <template slot-scope="{$index, row}">
            <span v-if="!showEditPlate[$index]">{{row.appName}}</span>
            <el-input v-else v-model="row.appName"></el-input>
          </template>
        </el-table-column>
        <el-table-column prod="prodCName" label="对应产品分类">
          <template slot-scope="{$index, row}">
            <span v-if="!showEditPlate[$index]">{{row.prodCName}}</span>
            <el-select v-else v-model="row.prodClassId" @change="onEditProductAttrFocus(row.prodClassId), row.productId=''">
              <el-option
                v-for="item in prodCList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prod="productName" label="对应产品">
          <template slot-scope="{$index, row}">
            <span v-if="!showEditPlate[$index]">{{row.productName}}</span>
            <el-select v-else v-model="row.productId">
              <el-option
                v-for="item in productList"
                :key="item.value"
                :label="item.label"
                :value="item.value"></el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column prod="operation" label="操作">
          <template slot-scope="{$index, row}">
            <div v-show="!showEditPlate[$index]">
              <el-button size="mini" class="opt-btn"
                         @click.native="editPlate(row, $index)">修改
              </el-button>
              <el-button size="mini" class="opt-btn" style="background-color: #f43d5e"
                         @click.native="toDelete(row)">删除
              </el-button>
              <el-button size="mini" class="opt-btn"
                         @click.native="downTr($index)" v-show="row.seqNo < plateData.length">下移
              </el-button>
            </div>
            <div v-show="showEditPlate[$index]">
              <el-button size="mini" class="opt-btn"
                         @click.native="saveEdit(row, $index)">保存
              </el-button>
              <el-button size="mini" class="opt-btn"
                         @click.native="cancelEdit($index), row.appName=plate_T.appName, row.prodCName=plate_T.prodCName, row.productName=plate_T.productName">取消
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <el-button v-show="!confirmAdd" type="success" size="small" style="margin-top: 2em; width: 80px"
                 @click="toAdd">新增
      </el-button>
      <div v-show="confirmAdd" style="color: #ff000073; font-weight: 600; font-size: 15px; margin-top: 25px; margin-bottom: 15px">首页栏目最多11条</div>


      <el-dialog :visible.sync="showAddDialog" width="480px">
        <el-form :model="addPlateData" ref="addPlateForm" :rules="rules" :inline="true">
          <span class="add-dialog-title">首页栏目新增</span>
          <el-row style="margin-top: 3em">
            <el-form-item label="应用显示名称：" prop="appName">
              <el-input placeholder="请输入应用显示名称" style="width: 95%" v-model="addPlateData.appName" ></el-input>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="对应产品分类：" prop="prodClassId" style="padding-left: 2%">
              <el-select v-model="addPlateData.prodClassId" style="width: 91%" @change="addPlateData.productId = ''">
                <el-option
                  v-for="item in prodCList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="对应产品：" prop="productId" style="margin-left: 8%">
              <el-select v-model="addPlateData.productId" style="width: 91%" @focus="onEditProductAttrFocus(addPlateData.prodClassId)">
                <el-option
                  v-for="item in productList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-row>
          <el-button type="success" size="small" @click="saveAdd" :disabled="addDisabled">新增</el-button>
          <el-button size="small" @click="showAddDialog = !showAddDialog" style="margin-bottom: 15px; margin-left: 20px; margin-top: 15px" >取消</el-button>
        </el-form>
      </el-dialog>

      <el-dialog :visible.sync="confirmEdit" width="280px">
        <h2 align="center">当前修改尚未保存</h2>
        <el-button @click="confirmEdit =! confirmEdit" type="primary"
                   style="margin-block-start: 2.5em; margin-block-end: 2em">确定</el-button>
      </el-dialog>

      <el-dialog :visible.sync="confirmDel" width="280px">
        <h2 align="center">确定删除？</h2>
        <el-button @click="saveDel" type="primary"
                   style="margin-block-start: 2.5em; margin-block-end: 2em">确定
        </el-button>
        <el-button @click="confirmDel =! confirmDel" type="primary">取消</el-button>
      </el-dialog>

      <el-dialog :visible.sync="confirmDel2" width="280px">
        <h2 align="center">首页栏目至少5条</h2>
        <el-button @click="confirmDel2 =! confirmDel2" type="primary"
                   style="margin-block-start: 2.5em; margin-block-end: 2em">确定</el-button>
      </el-dialog>

    </div>
  </div>
</template>

<script>
  import axios from "axios"
  import moment from "moment"

  export default {
    name: "MixList",
    data() {
      return {
        plateData: [],
        prodCList: [],         // 产品类型列表
        productList: [],       // 产品列表
        showEditPlate:[],      // 修改状态
        plate_T: {
          appName: "",
          prodCName: "",
          productName: ""
        },
        prodClassId: "",
        confirmEdit: false,   // 修改未保存提示
        confirmDel: false,    // 删除确认
        confirmDel2: false,    // 删除条数下限提示
        plateToDel: "",
        confirmAdd: false,     // 新增条数上限提示
        addPlateData: {
          appName: "",
          prodClassId: "",
          productId: ""
        },
        showAddDialog: false,
        rules: {
          appName: [{
            required: true, message: '请输入应用显示名称', trigger: 'blur'
          }],
          prodClassId: [{
            required: true, message: '请选择对应产品分类', trigger: 'change'
          }],
          productId: [{
            required: true, message: '请选择对应产品', trigger: 'change'
          }]
        },
        addDisabled: false
      }
    },

    created() {

      this.getPlate();
      this.getProdCList();
      this.getProductList();
    },

    methods: {
      editPlate(row, index) {
        let edit = true;
        for(let i=0; i<this.showEditPlate.length; i++) {
          if(this.showEditPlate[i]===true && i!==index) {
            edit= false;
            this.confirmEdit = true;
          }
        }
        if(edit === true) {
          this.$set(this.showEditPlate, index, true);
          this.plate_T.appName = row.appName;
          this.plate_T.prodCName = row.prodCName;
          this.plate_T.productName = row.productName;
          this.prodClassId = row.prodClassId;
          this.getProductList();
        }
      },
      cancelEdit(index) {
        this.$set(this.showEditPlate, index, false);
        this.getPlate();
      },
      saveEdit(row, index) {
        this.$delete(row, 'prodCName');
        this.$delete(row, 'productName');
        console.log("row:  "+JSON.stringify(row));
        if(row.productId === "") {
          this.$message.error("请选择对应产品");
        } else {
          axios.put("/plate/updateop", {
            plate: row
          })
            .then(res =>{
              console.log("plateUpdateRtn: "+res.data);
              this.$set(this.showEditPlate, index, false);
              this.getPlate();
            })
            .catch(err =>{
              console.log(err);
              this.$message.error("修改失败");
            });
        }
      },
      toDelete(row) {
        if(this.plateData.length<=5) {
          this.confirmDel2 = !this.confirmDel2;
        } else {
          this.confirmDel = !this.confirmDel;
          this.plateToDel = row.plateId;
        }

      },
      saveDel() {
        axios.put("/plate/deleteop", {}, {
          params: {plateId: this.plateToDel}
        })
          .then(res =>{
            console.log("plateDeleteRtn: "+res.data)
            this.confirmDel = false;
            this.getPlate();
          })
          .catch(err =>{
            console.log(err);
            this.$message.error("删除失败");
          })
      },
      downTr(index) {
        axios.put("/plate/downtrop", {} ,{
          params: {
            seqNo: index + 1,
            seqNo1: index + 2
          }
        })
          .then(res =>{
            console.log("plateDownTrRtn: "+res.data)
            this.getPlate();
          })
          .catch(err =>{
            console.log(err);
            this.$message.error("下移失败");
          })
      },
      toAdd() {
        this.addDisabled = false;
        this.showAddDialog = !this.showAddDialog;
      },
      saveAdd() {
        let plateId = "PL-" + moment().format("YYYYMMDD") + "-" + Math.random().toString().substr(3, 8);
        this.addPlateData.plateId = plateId;
        this.addPlateData.seqNo = this.plateData.length + 1;
        this.$refs["addPlateForm"].validate((valid) =>{
          if(valid) {
            // alert("addPlateData: "+JSON.stringify(this.addPlateData));
            axios.post("/plate/addop", {
              plate: this.addPlateData
            })
              .then(res =>{
                this.addDisabled = true;
                console.log("plateAddRtn: "+res.data);
                this.showAddDialog = false;
                this.getPlate();
              })
              .catch(err =>{
                this.addDisabled = true;
                console.log(err);
                this.$message.error("新增失败");
              })
          }
        })
      },
      getPlate() {
        axios.get("/plate/list")
          .then(res =>{
            console.log(res.data);
            this.plateData = res.data;
            if(this.plateData.length >= 11) {
              this.confirmAdd = true;
            } else {
              this.confirmAdd = false;
            }
            // alert("plate:  "+JSON.stringify(this.plateData));
          })
          .catch(err =>{
            console.log(err);
            this.$message.error("获取首页栏目列表失败");
          })
      },
      getProdCList() {
        axios.get("/sys/settings/getprodcid")
          .then(res => {
            let prodCList_T = [];
            for(let i in res.data){
              let item = {};
              item.value = res.data[i].classId;
              item.label = res.data[i].prodCName;
              prodCList_T.push(item)
            }
            this.prodCList = prodCList_T;
            // alert("prodCList: "+JSON.stringify(this.prodCList))

          }).catch(err => {
          console.log(err);
          this.$message.error("获取产品分类失败");
        })
      },
      getProductList() {
        axios.get("/product/list/", {
          params: {
            productName: "",
            type: "",
            attrId: this.prodClassId
          }
        })
          .then(res => {
            if (res.data && res.data.productList.length > 0) {
              let resData = res.data.productList;
              let productList_T = [];
              for (let i in resData) {
                let item = {};
                item.label = resData[i].productName;
                item.value = resData[i].productId;
                productList_T.push(item)
              }
              this.productList = productList_T;
              // alert("productList: "+JSON.stringify(this.productList));
            }
          })
          .catch(err => {
            console.error(err);
            this.$message.error("获取产品列表失败");
          })
      },
      onEditProductAttrFocus(prodClassId) {
        console.log("prodClassId => %o", prodClassId);
        console.log("addPlateData.productId => %o", this.addPlateData.productId);
        console.log("productList => %o", this.productList);
        this.prodClassId = prodClassId;
        this.getProductList();
      }
    }
  }
</script>

<style scoped>

  h2 {
    font-size: large;
    font-weight: bold;
  }

  .title-h1 {
    font-size: x-large;
    display: block;
    margin-block-start: 0.2em;
    margin-block-end: 0.83em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
  }

  .table-border {
    margin-top: 20px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, .12);
  }

  .add-dialog-title {
    font-size: 19px;
    font-weight: bolder;
    text-align: left;
    margin-block-start: 0.2em;
    margin-block-end: 0.83em;
  }

  .opt-btn {
    background-color: #3A8EE6;
    color: white;
  }
</style>
