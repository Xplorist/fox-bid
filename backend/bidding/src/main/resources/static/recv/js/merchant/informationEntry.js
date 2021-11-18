new Vue({
    el: '#app',
    data: function () {
        return {
            // 表單數據
            form: {
                // logo url
                logoUrl: '../../imgs/merchant/LOGO.png',
                // 廠區名稱
                name: '',
                // 廠區
                factory: '',
                // 次集團
                group: '',
                // 事業群
                bussiness: '',
                // 處
                bureau: '',
                // 單位名
                departName: '',
                // 交易法人
                corporate: '',
                // 費用代碼
                costCode: '',
                // 加工範圍
                processRange: '',
                // 業務經理
                manager: '',
                // 聯繫電話
                tel: '',
                // email
                email: '',
                // 簡介
                intro: ''
            },
            category: ''
        }
    },
    created() {

        // 獲取查詢參數值並存放在data中
        function getQuery(keyName) {
            var reg = new RegExp("(^|&)" + keyName + "=([^&]*)(&|$)"),
                r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURI(r[2])
            return null
        }

        this.category = getQuery('category')
        console.log(this.category)

    },
    methods: {
        upImg() {
            // 觸發 ref='fileInp' 的點擊事件
            this.$refs.fileInp.dispatchEvent(new MouseEvent('click'))
        },
        getFile() {
            // console.log(this.$refs.fileInp.files[0])
            // 獲取到 input 選中的文件信息
            const inputFile = this.$refs.fileInp.files[0]

            // window 对象的 URL 对象通過 createObjectURL() 将blob或者file读取成一个url。
            const windowURL = window.URL || window.webkitURL
            if (inputFile) {
                if (inputFile.type !== 'image/jpeg' && inputFile.type !== 'image/png' && inputFile.type !== 'image/gif') {
                    alert('請上傳圖片文件')
                    return
                }
                const dataURL = windowURL.createObjectURL(inputFile)
                this.form.logoUrl = dataURL
            }
        }
    }
})