<!DOCTYPE html>
<html>
<head>
    <#assign path="${springMacroRequestContext.getContextPath()}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>车辆信息管理</title>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="${path}/layui/css/layui.css" rel="stylesheet" media="all" />
    <script src="${path}/layui/layui.all.js"></script>
</head>
<body class="layui-layout-body">
<div class="demoTable">
        <div class="layui-inline">
            车辆厂牌：<select name="brand" id="brandReload" style="width:150px;height:32px;border-radius:4px">
                        <option value="现代" selected>现代</option>
                        <option value="大众">大众</option>
                        <option value="奔驰">奔驰</option>
                        <option value="奥迪">奥迪</option>
                        <option value="宝马">宝马</option>
            </select>
        </div>
        <div class="layui-inline">
            车辆颜色：<select name="color" id="colorReload" style="width:150px;height:32px;border-radius:4px">
                            <option value="红色" selected>红色</option>
                            <option value="黄色">黄色</option>
                            <option value="白色">白色</option>
                            <option value="黑色">黑色</option>
                            <option value="棕色">棕色</option>
                        </select>
        </div>
        <div class="layui-inline">
            车辆类型：<select name="ctype" id="ctypeReload" style="width:150px;height:32px;border-radius:4px;">
                    <option value="轿车" selected>轿车</option>
                    <option value="SUV">SUV</option>
                    <option value="跑车">跑车</option>
                </select>
        </div>
    <span style="margin-left: 100px">
        <button class="layui-btn" data-type="reload" style="width: 60px;height: 29px;color: #000;line-height: 29px;text-align: center">搜索</button>
    </span>
</div>
<script type="text/html" id="barDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" style="width: 65px;height: 29px;color: #000;line-height: 29px;text-align: center" lay-event="register">注册</button>
        <button class="layui-btn layui-btn-sm" style="width: 65px;height: 29px;color: #000;line-height: 29px;text-align: center" lay-event="edit">编辑</button>
        <button class="layui-btn layui-btn-sm" style="width: 65px;height: 29px;color: #000;line-height: 29px;text-align: center" lay-event="getStart">启用</button>
        <button class="layui-btn layui-btn-sm" style="width: 65px;height: 29px;color: #000;line-height: 29px;text-align: center" lay-event="getForbid">禁用</button>
        <button class="layui-btn layui-btn-sm" style="width: 65px;height: 29px;color: #000;line-height: 29px;text-align: center" lay-event="del">注销</button>
        <button class="layui-btn layui-btn-sm" style="width: 65px;height: 29px;color: #000;line-height: 29px;text-align: center" lay-event="allmsg">全部信息</button>
    </div>
</script>
<table class="layui-hide" id="demo" lay-filter="demo"></table>
<script src="${path}/layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#demo'
            ,height: 520
            ,url: '/user/cmsg'
            ,page: true //开启分页
            ,toolbar: '#barDemo'
            ,limit: 10
            ,cols: [[ //表头
                {type:'checkbox',fixed: 'id'}
                ,{field: 'cph', title: '车牌号', width:130}
                ,{field: 'username', title: '车辆所有人', width:80}
                ,{field: 'color', title: '颜色', width:80}
                ,{field: 'fdjID', title: '发动机号', width: 107}
                ,{field: 'brand', title: '品牌', width: 80}
                ,{field: 'state', title: '状态', width: 80}
                ,{field: 'jjID', title: '机架号', width: 80}
                ,{field: 'cxh', title: '车型号', width: 135}
                ,{field: 'rtime', title: '注册时间', width: 135}
                ,{field: 'ctype', title: '车种类', width: 135}
                ,{field: 'firstDate', title: '初次登记日期', width: 100}
                ,{title: '最近绑定产品', width: 80}
            ]]
            ,id:'demo'
        });
        table.on('toolbar(demo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'register':
                    window.location.href = '/user/cmanage';
                    break;
                case 'edit':
                    var data = checkStatus.data;
                    $.ajax({
                        url : "/user/carEdit",
                        type : "POST",
                        data : JSON.stringify(data),
                        contentType : "application/json;charset=utf-8",
                        success:function(msg) {
                            if (msg != 0) {//根据返回值进行跳转
                                window.location.href="/user/getCarMsg?id="+msg;
                            }
                        }
                    });
                    break;
                case 'getForbid':
                    var data = checkStatus.data;
                    alert(data);
                    alert(JSON.stringify(data));
                    $.ajax({
                        url : "/user/upCmStateEnd",
                        type : "POST",
                        data : JSON.stringify(data),
                        contentType : "application/json;charset=utf-8",
                        success:function(msg) {
                            if (msg != null) {//根据返回值进行跳转
                                window.location.reload();
                            }
                        }
                    });
                    break;
                case 'getStart':
                    var data = checkStatus.data;
                    $.ajax({
                        url : "/user/upCmStateStart",
                        type : "POST",
                        data : JSON.stringify(data),
                        contentType : "application/json;charset=utf-8",
                        success : function (msg) {
                            window.location.reload();
                        }
                    });
                    break;
                case 'del':
                    var data = checkStatus.data;
                    $.ajax({
                        url : "/user/delCm",
                        type : "POST",
                        data : JSON.stringify(data),
                        contentType : "application/json;charset=utf-8",
                        success : function (msg) {
                            window.location.reload();
                        }
                    });
                    break;
                case 'allmsg':
                    window.location.href = '/user/cm';
                    break;
            };
        });
        var $ = layui.$, active = {
            reload: function(){
                var brandReload = $('#brandReload').val();
                var colorReload = $('#colorReload').val();
                var ctypeReload = $('#ctypeReload').val();
                table.reload('demo', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        brand: brandReload
                        ,color: colorReload
                        ,ctype: ctypeReload
                    }
                }, 'data');
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
