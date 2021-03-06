<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;" charset="utf-8">
    <title>车辆注册</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.editable-select.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body style="width:1000px;height:343px;margin-top:100px;margin-left:250px;border:1px solid green">
<div style="width:1000px;height:30px;line-height:30px;text-align:left;background: cornflowerblue">车辆管理-注册 <#if error??><span style="margin-left:200px;color:red;text-align: center">${error}</span></#if></div>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">基本信息</a>
    </li>
    <li><a href="#ios" data-toggle="tab">运输证</a></li>
    <li><a href="#ios" data-toggle="tab">其它信息</a></li>

    </ul>
    <div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="home" style="width:1000px">
        <#if cm??>
            <form action="/user/upCarMsg" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="id" value="${cm.id}">
                <input type="hidden" name="state" value="${cm.state}">
                <table class="table table-bordered" style="width:700px;float:left">
                    <tbody>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>车牌号</td>
                        <td><input type="text"  name="cph" value="${cm.cph}"> </td>
                        <td style="text-align:right"><span style="color:red">* </span>车辆所有人</td>
                        <td><input type="text"  name="username" value="${cm.username}"> </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>车辆颜色</td>
                        <td>
                            <select  name="color" style="width:175px;height:28px;border-radius:4px">
                                <option <#if cm.color=='红色'>selected</#if> value="红色">红色</option>
                                <option <#if cm.color=='黄色'>selected</#if> value="黄色">黄色</option>
                                <option <#if cm.color=='白色'>selected</#if> value="白色">白色</option>
                                <option <#if cm.color=='黑色'>selected</#if> value="黑色">黑色</option>
                                <option <#if cm.color=='棕色'>selected</#if> value="棕色">棕色</option>
                            </select>
                        </td>
                        <td style="text-align:right"><span style="color:red">* </span>发动机号</td>
                        <td><input type="text" name="fdjID" value="${cm.fdjID}"> </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>车辆厂牌</td>
                        <td>
                            <select name="brand" style="width:175px;height:28px;border-radius:4px">
                                <option <#if cm.brand=='现代'>selected</#if> value="现代">现代</option>
                                <option <#if cm.brand=='大众'>selected</#if> value="大众">大众</option>
                                <option <#if cm.brand=='奔驰'>selected</#if> value="奔驰">奔驰</option>
                                <option <#if cm.brand=='奥迪'>selected</#if> value="奥迪">奥迪</option>
                                <option <#if cm.brand=='宝马'>selected</#if> value="宝马">宝马</option>
                            </select>
                        </td>
                        <td style="text-align:right"><span style="color:red">* </span>车辆机架号</td>
                        <td><input type="text" name="jjID" value="${cm.jjID}"> </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>车辆型号</td>
                        <td><input type="text"  name="cxh" value="${cm.cxh}"> </td>
                        <td style="text-align:right"><span style="color:red">* </span>注册日期</td>
                        <td><input type="text"  name="rtime" value="${cm.rtime}"> </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>车辆类型</td>
                        <td>
                            <select name="ctype" style="width:175px;height:28px;border-radius:4px" id="editable-select">
                                <option <#if cm.ctype=='轿车'>selected</#if> value="轿车">轿车</option>
                                <option <#if cm.ctype=='SUV'>selected</#if> value="SUV">SUV</option>
                                <option <#if cm.ctype=='跑车'>selected</#if> value="跑车">跑车</option>
                            </select>
                        </td>
                        <td style="text-align:right"><span style="color:red">* </span>初次登记日期</td>
                        <td><input type="text" name="firstDate" value="${cm.firstDate}"> </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="border:none;text-align:center"><input type="submit"class="btn btn-primary" value="提交"/></td>
                        <td colspan="2" style="border:none;text-align:center"><input type="reset" class="btn btn-primary" onclick="javascript:history.back(-1);" value="取消"/></td>
                    </tr>
                    </tbody>
                </table>
                <div style="border:1px solid;width:200px;height:200px;float:left;margin-left:20px">
                    <input type="file" accept="image/gif, image/jpeg" name="imgfile" value="选择图片">
                </div>

            </form>
            <#else >
                <form action="/user/carRegister" method="post" enctype="multipart/form-data" >
                    <table class="table table-bordered" style="width:700px;float:left">
                        <tbody>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>车牌号</td>
                            <td><input type="text"  name="cph"> </td>
                            <td style="text-align:right"><span style="color:red">* </span>车辆所有人</td>
                            <td><input type="text"  name="username"> </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>车辆颜色</td>
                            <td>
                                <select name="color" style="width:175px;height:28px;border-radius:4px">
                                    <option value="红色">红色</option>
                                    <option value="黄色">黄色</option>
                                    <option value="白色">白色</option>
                                    <option value="黑色">黑色</option>
                                    <option value="棕色">棕色</option>
                                </select>
                            </td>
                            <td style="text-align:right"><span style="color:red">* </span>发动机号</td>
                            <td><input type="text" name="fdjID"> </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>车辆厂牌</td>
                            <td>
                                <select name="brand" style="width:175px;height:28px;border-radius:4px">
                                    <option value="现代">现代</option>
                                    <option value="大众">大众</option>
                                    <option value="奔驰">奔驰</option>
                                    <option value="奥迪">奥迪</option>
                                    <option value="宝马">宝马</option>
                                </select>
                            </td>
                            <td style="text-align:right"><span style="color:red">* </span>车辆机架号</td>
                            <td><input type="text" name="jjID"> </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>车辆型号</td>
                            <td><input type="text"  name="cxh"> </td>
                            <td style="text-align:right"><span style="color:red">* </span>注册日期</td>
                            <td><input type="text"  name="rtime"> </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>车辆类型</td>
                            <td>
                                <select name="ctype" style="width:175px;height:28px;border-radius:4px" id="editable-select">
                                    <option value="轿车">轿车</option>
                                    <option value="SUV">SUV</option>
                                    <option value="跑车">跑车</option>
                                </select>
                            </td>
                            <td style="text-align:right"><span style="color:red">* </span>初次登记日期</td>
                            <td><input type="text" name="firstDate"> </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="border:none;text-align:center"><input type="submit"class="btn btn-primary" value="提交"/></td>
                            <td colspan="2" style="border:none;text-align:center"><input type="reset" class="btn btn-primary" onclick="javascript:history.back(-1);" value="取消"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <div style="border:1px solid;width:200px;height:200px;float:left;margin-left:20px">
                        <input type="file" accept="image/gif, image/jpeg" name="imgfile" value="选择图片">
                    </div>
                </form>
        </#if>
    </div>
    <div class="tab-pane fade" id="ios">
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="tab-pane fade" id="jmeter">
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="tab-pane fade" id="ejb">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>
</div>
<!--</script>-->
</body>
</html>