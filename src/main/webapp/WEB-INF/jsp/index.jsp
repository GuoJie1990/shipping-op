<%--
  Created by IntelliJ IDEA.
  User: GuoJie
  Date: 2015/10/2
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>航运后台管理系统V1.0</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <jsp:include page="common/commonHeaderStyle.jsp"/>
    <style>
        table th, table tr, table td{ white-space: nowrap;}
    </style>
</head>
<body class="skin-blue sidebar-mini fixed">
<div class="wrapper">
    <jsp:include page="common/nav.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="common/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                航运后台管理系统
                <small>控制面板</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">欢迎页面</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>

        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="common/footer.jsp"/>
    <!-- Control Sidebar -->
    <jsp:include page="common/control-sidebar.jsp"/>
    <div class="control-sidebar-bg"></div>
</div>
<jsp:include page="common/commonFooterScript.jsp"/>
<script type="text/javascript">
</script>
</body>
</html>
