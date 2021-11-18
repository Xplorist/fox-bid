<template>
    <div style="background:rgba(239, 249, 255, 1);padding:0 100px">
        <div style="color:rgba(15, 30, 41, 1);font-size:18px;border-bottom:rgba(211, 223, 231, 1) 1px solid;padding:30px 0 10px 0">评价管理</div>
        <div>
            <div style="width:100%;margin-top:10px">
              <el-radio-group v-model="tabPosition">
                <el-radio-button label="top" style="width:200px">收到的评价（59）</el-radio-button>
                <el-radio-button label="right" style="width:200px">做出的评价（59）</el-radio-button>
            </el-radio-group>
            </div>
            <div style="display: flex;flex-direction: row;align-items: center;margin-top:20px">
                <span>付款时效：</span>
                <el-rate
                    v-model="value1"
                    disabled
                    show-score
                    :colors="['rgba(0, 150, 255, 1)', 'rgba(0, 150, 255, 1)', 'rgba(0, 150, 255, 1)']"
                    text-color="rgba(0, 150, 255, 1)"
                    disabled-void-color="rgba(200, 212, 222, 1)"
                    score-template="{value}">
                </el-rate>
                <span>({{this.crowd}}个客户打分)</span>
            </div>
            <div style="display: flex;flex-direction: row;align-items: center;margin-top:10px;margin-bottom:20px">
                <span>服务态度：</span>
                <el-rate
                    v-model="value2"
                    disabled
                    show-score
                   :colors="['rgba(0, 150, 255, 1)', 'rgba(0, 150, 255, 1)', 'rgba(0, 150, 255, 1)']"
                    text-color="rgba(0, 150, 255, 1)"
                    disabled-void-color="rgba(200, 212, 222, 1)"
                    score-template="{value}">
                </el-rate>
                <span>({{this.crowda}}个客户打分)</span>
            </div> 
            <div class="userevaluation">
                <p style="color:rgba(33, 47, 58, 1);font-size:16px">{{this.evaluation}}</p>
                <p style="color:rgba(98, 111, 127, 1);font-size:14px">{{this.pj_time}}</p>
            </div>
            <div class="userevaluation">
                <p style="color:rgba(33, 47, 58, 1);font-size:16px">{{this.evaluation}}</p>
                <p style="color:rgba(98, 111, 127, 1);font-size:14px">{{this.pj_time}}</p>
            </div>
            <div class="userevaluation">
                <p style="color:rgba(33, 47, 58, 1);font-size:16px">{{this.evaluation}}</p>
                <p style="color:rgba(98, 111, 127, 1);font-size:14px">{{this.pj_time}}</p>
            </div>
            <div class="userevaluation">
                <p style="color:rgba(33, 47, 58, 1);font-size:16px">{{this.evaluation}}</p>
                <p style="color:rgba(98, 111, 127, 1);font-size:14px">{{this.pj_time}}</p>
            </div>
        </div>
        <div class="fy_paging" >
            <el-pagination background layout="prev, pager, next" :total="1000"
            prev-text="上一頁"  next-text="下一頁"
            > <!-- 分頁 -->
            </el-pagination>
        </div>
    </div>
</template>
<style>
@import url(../assets/css/subpage.css);
.el-pagination.is-background .el-pager li:not(.disabled).active{
    background: rgba(72, 84, 99, 1)
}
.el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{
    border:rgba(72, 84, 99, 1) 1px solid;
    border-radius: 0px;
    background: transparent !important
}
.el-pagination.is-background .el-pager li:not(.disabled).active{
    background: rgba(72, 84, 99, 1) !important;
    color:white !important
}
.el-pagination.is-background .el-pager li:not(.disabled):hover{
    color: white !important;
    background: rgba(72, 84, 99, 1) !important
}
.el-pagination.is-background .btn-prev:disabled{
    color:rgba(18, 34, 46, 1) !important
}
.btn-prev{
    width: 50px
}
.btn-next{
    width:50px
}
</style>
<script>
export default {
    data(){
        return{
            tabPosition: 'top',
            value1: 3.5,
            value2: 3.3,
            crowd:57,
            crowda:50,
            evaluation:'与该公司合作的非常愉快，交货时间没有延期，制作的东西非常棒，希望下次有机会再次合作。',
            pj_time:new Date()
        }
    }
}
</script>