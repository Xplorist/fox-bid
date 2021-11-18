var app = new Vue({
    el: '#app',
    data: {
        img_src: '',
        file_save_path: '',// 'user_pic/2019/09/24',
        file_save_name: '',//'(170903684)register_bg_pic.jpg',
        file_origin_name: '' //'register_bg_pic.jpg'
    },
    methods: {
        update_file: function (event) {
            var _self = this;
            var file = event.target.files[0];
            var param = new FormData();
            param.append('file', file);
            param.append('file_type', 'user_pic');

            axios({
                method: 'post',
                url: '/api/ftp_file/upload',
                headers: {'Content-Type':'multipart/form-data'},
                data: param
            }).then(function(response) {
                var data = response.data;
                if(data.code == "1") {
                    var t = data.t;
                    // encodeURIComponent() 函數對參數進行編碼
                    // encodeURIComponent() 非常重要，不然後端服務器無法處理
                    _self.file_save_path = encodeURIComponent(t.file_save_path);
                    _self.file_save_name = encodeURIComponent(t.file_save_name);
                    _self.file_origin_name = encodeURIComponent(t.file_origin_name);
                    _self.build_img_src();
                    //alert(_self.img_src);
                    alert("上傳成功, 文件名:" +  _self.file_origin_name);
                } else {
                    alert("上傳失敗," + data.msg + data.t);
                }
            });
        },
        delete_file: function() {
            var _self = this;

            axios({
                method: 'post',
                url: '/api/ftp_file/delete',
                data: {
                    file_save_path: _self.file_save_path,
                    file_save_name: _self.file_save_name,
                    file_origin_name: _self.file_origin_name
                }
            }).then(function(response) {
                var data = response.data;
                if(data.code == "1") {
                    alert("刪除成功" + data.msg + data.t);
                    _self.img_src = "";
                    _self.file_save_path = "";
                    _self.file_save_name = "";
                    _self.file_origin_name = "";
                } else {
                    alert("刪除失敗" + data.msg + data.t);
                }
            });
        },
        build_img_src: function() {
            var _self = this;
            _self.img_src = '/api/pic_show/load?file_save_path=' + _self.file_save_path
                +'&file_save_name=' + _self.file_save_name + '&file_origin_name=' + _self.file_origin_name;
        }
    },
    created: function() {
        var _self = this;
        //_self.build_img_src();
    }
});