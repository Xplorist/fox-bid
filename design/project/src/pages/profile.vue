<template>
    <div style="background:rgba(239,249,255,1);padding:0 100px">
        <div style="color：rgba(15, 30, 41, 1);font-size:18px;border-bottom:1px solid rgba(211, 223, 231, 1);padding:30px 0 10px 0;">个人资料</div>
        <div style="display:flex;flex-direction: row;margin-top:50px">
            <div style="display:flex;flex-direction: column;width:90px">
                <span>账号属性：</span>
                <span style="margin-top:50px">LOGO头像:</span>
                <span style="margin-top:60px">账号名称:</span>
                <span v-for="(item,index) in list" :key="index" style="margin-top:30px">{{item.name}}</span>
            </div>
            <div style="width:700px">
                <span style="color:rgba(0, 146, 255, 1);font-size:16px">集团内部账号</span>
                <div style="display:flex;flex-direction: row;align-items:center;margin-top:25px">
                    <img src="../assets/imgs/touxiang.png">
                    <span  style="color:rgba(0, 146, 255, 1);font-size:16px;margin-left:15px;cursor: pointer">更换</span>
                </div>
                <input type="text" placeholder="SHZBG" class="purpose" style="height:34px;margin-top:25px">
                <div style="display:flex;flex-direction: row;align-items:center;margin-top:14px">
                    <div class="city_choice" style="width:80px;">
                        <span>成都</span> 
                        <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
                    </div>
                    <div class="city_choice" style="width:100px;">
                        <span>次集体</span> 
                        <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
                    </div>
                    <div class="city_choice" style="width:100px;">
                        <span>事业群</span> 
                        <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
                    </div>
                     <div class="city_choice" style="width:150px;">
                        <span>处</span> 
                        <img src="../assets/imgs/xl_px.png" style="position: absolute;top:15px;right:10px" />
                    </div>
                </div>
                <input type="text" placeholder="SHZBG" class="purpose" style="height:34px">
                <div class="purpose" style="border:none">
                    <span>鸿富锦成都</span>
                    <span style="color:rgba(0, 146, 255, 1);font-size:16px;margin-left:24px;cursor: pointer;">变更</span>
                </div>
                <div class="purpose" style="border:none">
                    <span>Q358DS5A5278A</span>
                    <span style="color:rgba(0, 146, 255, 1);font-size:16px;margin-left:24px;cursor: pointer">变更</span>
                </div>
                <div class="purpose" style="border:none;width:300px">
                    <span>中国工商银行   6222***********6789</span>
                    <span style="color:rgba(0, 146, 255, 1);font-size:16px;margin-left:24px;cursor: pointer">变更</span>
                </div>
                <input type="text" placeholder="SHZBG" class="purpose" style="height:34px">
                <div style="display:flex;flex-direction: row;align-items:center;height：36px;margin-top:10px">
                     <input type="text" placeholder="022525-25555" class="purpose" style="height:34px;margin:0">
                     <div style="display:flex;flex-direction: row;align-items:center;margin-left:20px" @click="change_png">
                         <img src="../assets/imgs/visible.png" v-if="show_png === true"/>
                         <img src="../assets/imgs/invisible.png" v-if="show_png === false">
                         <span style="margin-left:10px">在主页显示</span>
                     </div>
                </div>
                <input type="text" placeholder="hzcd-mis-sys1@mail.foxconn.com" class="purpose" style="height:34px">
                <el-input  style="margin-top:15px"
                    type="textarea"
                    :rows="4"
                    placeholder="请输入您对商家的评价"
                    v-model="textarea">
                </el-input>
            </div>
        </div>
        <div style="padding:50px 0;">
            <div style="width:100px;height:40px;background:rgba(0,146,255,1);border-radius:4px;color:white;font-size:16px;text-align:center;line-height:40px">保存</div>
        </div>
    </div>
</template>
<style>
@import url(../assets/css/subpage.css);
</style>
<script>
export default {
    data(){
        return{
            list:[
                {name:'所属单位:'},
                {name:'单位名称:'},
                {name:'交易法人:'},
                {name:'费用代码:'},
                {name:'银行账号:'},
                {name:'业务经理:'},
                {name:'联系电话:'},
                {name:'Email:'},
                {name:'简介:'}
            ],
            show_png:true,
            textarea: '',
        }
    },
    methods:{
        change_png:function(){
            this.show_png = !this.show_png
        }
    }
}
</script>