<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;" charset="utf-8">
    <title>Bootstrap 实例 - 响应</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.editable-select.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="width:1000px;height:343px;margin-top:100px;margin-left:250px;border:1px solid green">
<div style="width:1000px;height:30px;line-height:30px;text-align:left;background: cornflowerblue">驾驶员管理-注册</div>
<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#home" data-toggle="tab">基本信息</a>
    </li>
    <li><a href="#ios" data-toggle="tab">证件信息</a></li>
    <li><a href="#ios" data-toggle="tab">其它信息</a></li>

</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="home" style="width:1000px">
        <#if dm??>
            <form action="/user/upDriverMsg" method="post" enctype="multipart/form-data" >
                <input type="hidden" name="id" value="${dm.id}">
                <input type="hidden" name="state" value="${dm.state}">
                <table class="table table-bordered" style="width:700px;float:left">
                    <tbody>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>驾驶员姓名</td>
                        <td><input type="text"  name="username" value="${dm.username}"> </td>
                        <td style="text-align:right"><span style="color:red">* </span>国籍</td>
                        <td><input type="text"  name="country" value="${dm.country}"> </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>驾驶员手机号</td>
                        <td><input type="text" name="tel" value="${dm.tel}"> </td>
                        <td style="text-align:right"><span style="color:red">* </span>婚姻状况</td>
                        <td>
                            <select name="marriage" style="width:175px;height:28px;border-radius:4px" >
                                <option value="未婚">未婚</option>
                                <option value="已婚">已婚</option>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>身份证号</td>
                        <td><input type="text" name="sfz" value="${dm.sfz}"> </td>
                        <td style="text-align:right"><span style="color:red">* </span>所属公司</td>
                        <td>
                            <select name="company" style="width:175px;height:28px;border-radius:4px">
                                <option value="四川省成兴运业有限公司">四川省成兴运业有限公司</option>
                                <option value="四川省当代运业有限公司">四川省当代运业有限公司</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>性别</td>
                        <td>
                            <select name="sex" style="width:175px;height:28px;border-radius:4px" id="editable-select">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </td>
                        <td style="text-align:right"><span style="color:red">* </span>外语能力</td>
                        <td><input type="text" name="wynl" value="${dm.wynl}"> </td>
                    </tr>
                    <tr>
                        <td style="text-align:right"><span style="color:red">* </span>出生日期</td>
                        <td><input type="text"  name="birth" value="${dm.birth}"> </td>
                        <td style="text-align:right"><span style="color:red">* </span>学历</td>
                        <td><input type="text"  name="study" value="${dm.study}"> </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="border:none;text-align:center"><input type="submit"class="btn btn-primary" value="提交"/></td>
                        <td colspan="2" style="border:none;text-align:center"><input type="reset" class="btn btn-primary" value="取消"/></td>
                    </tr>
                    </tbody>
                </table>
                <div style="border:1px solid;width:200px;height:200px;float:left;margin-left:20px">
                    <input type="file" accept="image/gif, image/jpeg" name="imgfile">
                </div>
            </form>
            <#else >
                <form action="/user/driverRegister" method="post" enctype="multipart/form-data" >
                    <table class="table table-bordered" style="width:700px;float:left">
                        <tbody>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>驾驶员姓名</td>
                            <td><input type="text"  name="username"> </td>
                            <td style="text-align:right"><span style="color:red">* </span>国籍</td>
                            <td><input type="text"  name="country"> </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>驾驶员手机号</td>
                            <td><input type="text" name="tel"> </td>
                            <td style="text-align:right"><span style="color:red">* </span>婚姻状况</td>
                            <td>
                                <select name="marriage" style="width:175px;height:28px;border-radius:4px">
                                    <option value="未婚">未婚</option>
                                    <option value="已婚">已婚</option>
                                </select>
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>身份证号</td>
                            <td><input type="text" name="sfz"> </td>
                            <td style="text-align:right"><span style="color:red">* </span>所属公司</td>
                            <td>
                                <select name="company" style="width:175px;height:28px;border-radius:4px">
                                    <option value="四川省成兴运业有限公司">四川省成兴运业有限公司</option>
                                    <option value="四川省当代运业有限公司">四川省当代运业有限公司</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>性别</td>
                            <td>
                                <select name="sex" style="width:175px;height:28px;border-radius:4px" id="editable-select">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </td>
                            <td style="text-align:right"><span style="color:red">* </span>外语能力</td>
                            <td><input type="text" name="wynl"> </td>
                        </tr>
                        <tr>
                            <td style="text-align:right"><span style="color:red">* </span>出生日期</td>
                            <td><input type="text"  name="birth"> </td>
                            <td style="text-align:right"><span style="color:red">* </span>学历</td>
                            <td><input type="text"  name="study"> </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="border:none;text-align:center"><input type="submit"class="btn btn-primary" value="提交"/></td>
                            <td colspan="2" style="border:none;text-align:center"><input type="reset" class="btn btn-primary" value="取消"/></td>
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