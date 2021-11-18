<template>
    <div style="background:rgba(239, 249, 255, 1);padding:0 100px">
        <div style="display: flex; flex-direction: row;justify-content: space-between;padding:30px 0;border-bottom:rgba(175, 194, 207, 1) solid 1px">
            <span style="font-size:18px;color:rgba(33, 47, 58, 1)">评价</span>
            <div>
                <p style="margin:0px;font-size:18px;color:rgba(33, 47, 58, 1)">鴻富錦精密（成都）有限公司</p>
                <p style="margin:5px 0;font-size:18px;color:rgba(0, 150, 255, 1)">【模具】需求單號{{this.oddnumber}}</p>
            </div>
            <span></span> <!-- 用于布局的伪标签 -->
        </div>
        <div style="height:60px;line-height:60px">
            <span>综合评价：</span>
            <span v-for="item in star_listc" :key="item.id">
                  <img v-if="item.flag" :src="item.img_man" style="margin-left:5px">   
                  <img v-if="!item.flag" :src="item.img_no" style="margin-left:5px">
            </span>
            <span>（非常好）</span>
        </div>
        <el-input 
            type="textarea"
            :rows="9"
            placeholder="请输入您对商家的评价"
            v-model="textarea">
        </el-input>
        <div style=" display: flex; flex-direction: row;justify-content: space-between;align-items: center;margin-top:10px">
            <div @click="evaluatea" style="display: flex; flex-direction: row;align-items: center" >
                <img src="../assets/imgs/visible.png" v-if="eval_pj === true"/>
               <img src="../assets/imgs/invisible.png" v-if="eval_pj === false">
               <span style="margin-left:10px;color:rgba(18, 34, 46, 1);font-size:16px">匿名評價</span>
            </div>
             <span style="color:rgba(98, 111, 127, 1);font-size:16px">匿名評價不會再個人主頁中展示昵称</span>
        </div>
        <div style="margin-top:20px">
            <div v-for="lista in list" :key="lista.name" style="padding-top:30px">
                <span>{{lista.name}}</span>
                <span v-for="item in lista.children" :key="item.id" @click="stateb(lista, item)">
                <img v-if="item.flag" :src="item.img_man" style="margin-left:5px">   
                    <img v-if="!item.flag" :src="item.img_no" style="margin-left:5px">
                </span>
                <span style="margin-left:5px">{{lista.status}}</span>
             </div>
        </div>
        <div style="display:flex;justify-content:center;width:100%;padding-bottom:30px;margin-top:50px;cursor: pointer;">
             <div style="position: relative;margin-left:20px">
                <img src="../assets/imgs/fabu.png">
                <span style="position: absolute;top:20px;left:80px;font-size;20px;color:white">提交</span>
            </div>
        </div>
    </div>
</template>
<style>
@import url(../assets/css/subpage.css);
.el-textarea__inner{
    background:transparent !important;
    border:1px solid rgba(175,194,207,1) !important;
    border-radius: 0 !important;
    padding:15px
}
</style>
<script>
const getModel = () => {
    return [
                {id: 0, flag: true, img_man: require('../assets/imgs/Stars_man.png'), img_no:  require('../assets/imgs/Stars_no.png')},
                {id: 1, flag: true, img_man: require('../assets/imgs/Stars_man.png'), img_no:  require('../assets/imgs/Stars_no.png')},
                {id: 2, flag: true, img_man: require('../assets/imgs/Stars_man.png'), img_no:  require('../assets/imgs/Stars_no.png')},
                {id: 3, flag: true, img_man: require('../assets/imgs/Stars_man.png'), img_no:  require('../assets/imgs/Stars_no.png')},
                {id: 4, flag: true, img_man: require('../assets/imgs/Stars_man.png'), img_no:  require('../assets/imgs/Stars_no.png')}
            ];
};
export default {
    data(){
        return{
            textarea: '',
            eval_pj:true,
            list:[
                {
                    name:"出貨時效",
                    status:'(非常好)',
                    children: getModel() 
                },
                {
                    name:"出貨質量",
                    status:'(非常好)',
                    children: getModel() 
                },
                {
                    name:"服務態度",
                    status:'(非常好)',
                    children: getModel() 
                }
            ],
            
            oddnumber:'A3000028'
        }
    },
    mounted() {
       let arr = ['a', 'b', 'c'];
       let forEach = function  (arr, callback) {
           for(let i = 0; i < arr.length; i++) {
               callback(arr[i]);
           }
       }
       forEach(arr, function(value) { //回调函数
           window.console.log(value);
       });

    },
    computed:{
        star_listc: function(){
            let multiple = getModel();
            let count = 0;
            let list = JSON.parse(JSON.stringify(this.list));
            list.forEach(item => { count = count + item.children.filter(item => item.flag).length });
            multiple.forEach(item => item.flag = false); 
            for(let i = 0; i < Math.floor(count / 3); i++)  multiple[i].flag = true;
            return multiple;
        },
    },
    methods:{
        evaluatea:function(){
            this.eval_pj = !this.eval_pj
        },
        stateb:function(list, obj){
            let x = list.children;
            let dica =["(非常差)","(差)","(一般)","(好)","(非常好)"];
            let dicb = ["","(非常差)","(差)","(一般)","(好)"];
            if(obj.flag){
                for(let i = 0; i<x.length; i ++){
                    let y = x[i];
                    if(i > obj.id){
                        y.flag = false
                    }else{
                        y.flag = true
                    }
                }
            }else{
                for(let i = 0; i<x.length; i ++){
                    let y = x[i];
                    if(i <= obj.id){
                        y.flag = true
                    }else{
                        y.flag = false
                    }
                }
            }

            if(obj.flag){
                list.status = dica[obj.id];
            }else{
                list.status = dicb[obj.id];
            }
        }

    }
}
</script>