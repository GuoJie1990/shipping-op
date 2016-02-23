<%--
  Created by IntelliJ IDEA.
  User: GuoJie
  Date: 2015/10/2
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/public/admin/img/user2-160x160.jpg" class="img-circle"
                     alt="User Image"/>
            </div>
            <div class="pull-left info">
                <p>管理员</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>
        <!--  form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" value="查询" placeholder="查询..."/>
              <span class="input-group-btn">
                <button type="submit" name="" id="-btn" class="btn btn-flat"><i class="fa fa-"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /. form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">航运管理导航菜单</li>
            <li class="treeview">
                <a href="#"><i class="fa fa-edit"></i><span>客户管理模块</span><i class="fa fa-angle-left pull-right"></i></a>
                <ul class="treeview-menu" style="display:none;">
                    <li><a href="#" id="clientList"><i class="fa fa-angle-left pull-right"></i><span>客户管理</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#"><i class="fa fa-edit"></i><span>项目管理</span><i class="fa fa-angle-left pull-right"></i></a>
                <ul class="treeview-menu" style="display:none;">
                    <li><a href="#" id="projectList"><i class="fa fa-comment-o"></i><span>项目</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#"><i class="fa fa-users"></i><span>财务管理</span><i class="fa fa-angle-left pull-right"></i></a>
                <ul class="treeview-menu" style="display:none;">
                    <li><a href="/adminUser/list" id="adminUser" ><i class="fa fa-angle-left pull-right"></i><span>财务</span></a></li>
                </ul>
            </li>
            <li><a href="/modelMaster/modelList" id="modelList"><i class="fa fa-th"></i><span>后台管理</span></a></li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>