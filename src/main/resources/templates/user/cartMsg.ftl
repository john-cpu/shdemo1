<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;" charset="utf-8">
    <title>车辆信息</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script LANGUAGE="JavaScript">
        showInput = function() {
            var boxes = document.getElementsByName("test");
            var gr = [];
            var str = "";
            for (var i = 0; i < boxes.length; i++)
            {
                if (boxes[i].checked)
                {
                    str = boxes[i].getAttribute('value');
                    gr.push(str);
                }
            }
            if(gr.length == 0){
                return false;
            }
            var str2 = "";
            for(var i = 0;i<gr.length;i++){
                    str2 += gr[i]+"#";
            }
            $.ajax({
                type: 'post',
                url: '/user/upStateStart'+'?needId='+str2,
                data: {},
                contentType:"application/json,charset=utf-8",
                async: false,
                success: function(data){
                    alert(data);
                }
            });
            /*document.getElementById("needId").value=str2;
            document.form2.action="/user/upStateStart";
            document.form2.submit();*/
        }
        showInput2 = function() {
            var boxes = document.getElementsByName("test");
            var gr = [];
            var str = "";
            for (var i = 0; i < boxes.length; i++)
            {
                if (boxes[i].checked)
                {
                    str = boxes[i].getAttribute('value');
                    gr.push(str);
                }
            }
            if(gr.length == 0){
                return false;
            }
            var str2 = "";
            for(var i = 0;i<gr.length;i++){
                str2 += gr[i]+"#";
            }
            document.getElementById("needId").value=str2;
            document.form2.action="/user/upStateEnd";
            document.form2.submit();
        }
        showInput3 = function() {
            var boxes = document.getElementsByName("test");
            var gr = [];
            var str = "";
            for (var i = 0; i < boxes.length; i++)
            {
                if (boxes[i].checked)
                {
                    str = boxes[i].getAttribute('value');
                    gr.push(str);
                }
            }
            if(gr.length == 0){
                return false;
            }
            var str2 = "";
            for(var i = 0;i<gr.length;i++){
                str2 += gr[i]+"#";
            }
            document.getElementById("needId").value=str2;
            document.form2.action="/user/delById";
            document.form2.submit();
        }

    </script>
    <script type="text/javascript">

    </script>
</head>
<body style="width:1200px;height:605px;margin-top:0px;margin-left:100px;">
<form method="post" name="form2">
    <input id="needId" name="needId" type="hidden" >
</form>
<form action="/user/select" name="form1" method="post">
    <div style="width:1200px;height:36px;line-height:34px;text-align:left;border: 1px solid">
          <span style="margin-left: 100px">车辆厂牌<select name="brand" style="width:150px;height:32px;border-radius:4px">
                        <option value="现代" selected>现代</option>
                        <option value="大众">大众</option>
                        <option value="奔驰">奔驰</option>
                        <option value="奥迪">奥迪</option>
                        <option value="宝马">宝马</option>
                    </select>
          </span>
        <span style="margin-left: 100px">车牌颜色<select name="color" style="width:150px;height:32px;border-radius:4px">
                            <option value="红色" selected>红色</option>
                            <option value="黄色">黄色</option>
                            <option value="白色">白色</option>
                            <option value="黑色">黑色</option>
                            <option value="棕色">棕色</option>
                        </select>
        </span>
        <span style="margin-left: 100px">车辆类型<select name="ctype" style="width:150px;height:32px;border-radius:4px;">
                    <option value="轿车" selected>轿车</option>
                    <option value="SUV">SUV</option>
                    <option value="跑车">跑车</option>
                </select>
        </span>
    </div>
    <div style="width:1200px;height:36px;margin-top: 15px;line-height:36px;text-align:left;border: 1px solid">
        <div style="margin-top: 2px;width:550px;height:34px;line-height:34px;text-align:left;float:left">
            <a href="/user/cm"><button type="button" style="width: 55px;height: 29px;color: #000;padding: 0px;border: none;">注册</button></a>
            <a  href="/user/cm"><button type="button" style="width: 55px;height: 29px;color: #000;padding: 0px;border: none;">编辑</button></a>
            <button type="button" form="form2" style="width: 55px;height: 29px;color: #000;padding: 0px;border: none;" onclick="showInput()">启用</button>
            <button type="button" form="form2" style="width: 55px;height: 29px;color: #000;padding: 0px;border: none;" onclick="showInput2()">禁用</button>
            <button type="button" form="form2" style="width: 55px;height: 29px;color: #000;padding: 0px;border: none;" onclick="showInput3()">注销</button>
           <a href="/user/cm"><button type="button" style="width: 100px;height: 29px;color: #000;padding: 0px;border: none;">导入模板下载</button></a>
            <a href="/user/cm"><button type="button" style="width: 80px;height: 29px;color: #000;padding: 0px;border: none;">批量导入</button></a>
        </div>
        <div style="margin-top: 2px;width:100px;height:30px;line-height:30px;text-align:left;float:right">
            <input type="submit" style="width: 60px;height: 29px;color: #000;padding: 0px;border: none;" value="查询">
        </div>
    </div>
</form>
<div style="width:1200px;height:auto;line-height:30px;text-align:left;">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th></th>
            <th>车牌号</th>
            <th>车辆所有人</th>
            <th>车辆颜色</th>
            <th>发动机号</th>
            <th>车辆厂牌</th>
            <th>状态</th>
            <th>车辆机架号</th>
            <th>车辆型号</th>
            <th>注册日期</th>
            <th>车辆类型</th>
            <th>初次登记日期</th>
            <th>最近绑定产品</th>
        </tr>
        </thead>
        <tbody>
        <#if rlist??>
            <#list rlist as item>
                <tr>
                    <td>
                        <input type="checkbox" name="test" value="${item.id}">
                    </td>
                    <td>${item.cph}</td>
                    <td>${item.username}</td>
                    <td>${item.color}</td>
                    <td>${item.fdjID}</td>
                    <td>${item.brand}</td>
                    <td>${item.state}</td>
                    <td>${item.jjID}</td>
                    <td>${item.cxh}</td>
                    <td>${item.rtime}</td>
                    <td>${item.ctype}</td>
                    <td>${item.firstDate}</td>
                    <td></td>
                </tr>
            </#list>
            <#else>
                <tr>
                    <td colspan="13">无相关结果</td>
                </tr>
        </#if>
        </tbody>
    </table>
</div>
<#--<#if (cur?? && countPage??)>
    <div class="page" style="height:40px;width:300px;text-align: center;margin: 0 auto">
        <div style="height:40px;width:180px;text-align: center;margin: 0 auto;float:left;">
            <a href="/user/free?page=${cur-1}"><button type="button" id="bt1" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:40px;height:17px;"><<</button></a>
            <input type="text" id="curPage" form="form3" name="curPape" value="${cur}" style="width:30px;height:17px">共${countPage}页
            <a href="/user/free?page=${cur+1}"><button type="button" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:40px;height:17px;">>></button></a>
        </div>
        <div style="height:40px;width:90px;text-align: center;margin: 0 auto;float:left;">
            <form method="get" name="form3" action="/user/free">
                <select id="page" name="page" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:30px;height:17px">
                    <#if countPage??>
                        <#list 1..countPage as x>
                            <option value="${x}">${x}</option>
                        </#list>
                    </#if>
                </select>
                <input type="submit" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:50px;height:17px;" value="跳转">
            </form>
        </div>
    </div>
</#if>-->
<#if (cur?? && countPage??)>
<div class="page" style="height:40px;width:300px;text-align: center;margin: 0 auto">
    <div style="height:40px;width:180px;text-align: center;margin: 0 auto;float:left;">
        <a onclick="toPage(#{cur-1})"><button type="button" id="bt1" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:40px;height:17px;"><<</button></a>
        <input type="text" id="curPage" form="form3" name="curPape" value="${cur}" style="width:30px;height:17px">共${countPage}页
        <a href="/user/free?page=${cur+1}"><button type="button" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:40px;height:17px;">>></button></a>
    </div>
    <div style="height:40px;width:90px;text-align: center;margin: 0 auto;float:left;">
        <form method="get" name="form3" action="/user/free">
            <select id="page" name="page" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:30px;height:17px">
                <#if countPage??>
                    <#list 1..countPage as x>
                        <option value="${x}">${x}</option>
                    </#list>
                </#if>
            </select>
            <input type="submit" style="vertical-align:bottom;line-height:10px;margin-bottom:2px;width:50px;height:17px;" value="跳转">
        </form>
    </div>
</div>
</#if>
</body>
</html>