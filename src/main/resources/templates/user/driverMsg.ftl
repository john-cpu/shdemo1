<!DOCTYPE html>
<html>
<head>
    <#assign path="${springMacroRequestContext.getContextPath()}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>驾驶员信息管理</title>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="${path}/layui/css/layui.css" rel="stylesheet" media="all" />
    <script src="${path}/layui/layui.all.js"></script>
</head>
<body class="layui-layout-body">
<div class="demoTable">
    <span style="margin-left: 100px">
        驾驶员姓名：
        <div class="layui-inline">
            <input class="layui-input" name="username" id="nameReload" autocomplete="off" style="width:150px;height:32px;border-radius:4px">
        </div>
    </span>
    <span style="margin-left: 100px">
        驾驶员电话：
        <div class="layui-inline">
            <input class="layui-input" name="tel" id="telReload" autocomplete="off" style="width:150px;height:32px;border-radius:4px">
        </div>
    </span>
    <span style="margin-left: 100px">
        学历：
        <div class="layui-inline">
            <input class="layui-input" name="study" id="studyReload" autocomplete="off" style="width:150px;height:32px;border-radius:4px">
        </div>
    </span>
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
            ,url: '/user/dmsg' //数据接口
            ,page: true //开启分页
            ,toolbar: '#barDemo'
            ,limit: 10
            ,cols: [[ //表头
                {type:'checkbox',fixed: 'id'}
                ,{field: 'username', title: '姓名', width: 80 }
                ,{field: 'sex', title: '性别', width: 80 }
                ,{field: 'company', title: '公司', width:80}
                ,{field: 'birth', title: '生日', width:120}
                ,{field: 'country', title: '国家', width:80}
                ,{field: 'imagePath', title: '路径', width: 177}
                ,{field: 'marriage', title: '婚否', width: 80}
                ,{field: 'sfz', title: '身份证号', width: 80}
                ,{field: 'study', title: '学历', width: 80}
                ,{field: 'tel', title: '电话', width: 135}
                ,{field: 'wynl', title: '外语能力', width: 100}
                ,{field: 'state', title: '状态', width: 80}
            ]]
            ,id:'demo'
        });
        table.on('toolbar(demo)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'register':
                    window.location.href = '/user/dmanage';
                    break;
                case 'edit':
                    var data = checkStatus.data;
                    $.ajax({
                        url : "/user/edit",
                        type : "POST",
                        data : JSON.stringify(data),
                        contentType : "application/json;charset=utf-8",
                        success:function(msg) {
                            if (msg != 0) {//根据返回值进行跳转
                                window.location.href="/user/getDriverMsg?id="+msg;
                            }
                        }
                    });
                    break;
                case 'getForbid':
                    var data = checkStatus.data;
                    $.ajax({
                        url : "/user/upDmStateEnd",
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
                        url : "/user/upDmStateStart",
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
                        url : "/user/delDm",
                        type : "POST",
                        data : JSON.stringify(data),
                        contentType : "application/json;charset=utf-8",
                        success : function (msg) {
                            window.location.reload();
                        }
                    });
                    break;
            };
        });
        var $ = layui.$, active = {
            reload: function(){
                var nameReload = $('#nameReload').val();
                var telReload = $('#telReload').val();
                var studyReload = $('#studyReload').val();
                table.reload('demo', {
                    page: {
                        curr: 1
                    }
                    ,where: {
                        username: nameReload
                        ,tel: telReload
                        ,study: studyReload
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