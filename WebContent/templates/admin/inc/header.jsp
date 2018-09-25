<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/templates/admin/assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/templates/admin/assets/img/favicon.png">
	<script src="<%=request.getContextPath()%>/library/js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/library/js/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/library/ckeditor/ckeditor.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/library/ckfinder/ckfinder.js" type="text/javascript"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>AdminCP - VinaEnter</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/demo.css" rel="stylesheet" />


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/themify-icons.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">
    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="http://vinaenter.edu.vn" class="simple-text">AdminCP</a>
            </div>

            <ul class="nav">
            	<li>
                    <a href="<%=request.getContextPath()%>/admin/cats">
                        <i class="ti-map"></i>
                        <p>Danh mục tin tức</p>
                    </a>
                </li>
            	 <li>
                    <a href="<%=request.getContextPath()%>/admin/news">
                        <i class="ti-view-list-alt"></i>
                        <p>Danh sách tin tức</p>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/comment">
                        <i class="ti-panel"></i>
                        <p>Bình luận</p>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/users">
                        <i class="ti-user"></i>
                        <p>Quản trị viên</p>
                    </a>
                </li>
            </ul>
    	</div>
    </div>
	
    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                    <a class="navbar-brand" href="/admin">Trang quản lý</a>
                </div>
                <div class="collapse navbar-collapse">
                <%
                	Users objUser = (Users) session.getAttribute("login");
                	if(objUser != null){
                %>
                    <ul class="nav navbar-nav navbar-right">
                    	<li><a href="<%=request.getContextPath()%>/admin/users">Chào, <%=objUser.getUsername() %></a></li>
						<li>
                            <a href="<%=request.getContextPath()%>/logout">
								<i class="fa fa-power-off"></i>
								<p>Đăng Xuất</p>
                            </a>
                        </li>
       
                    </ul>
				<%} %>
                </div>
            </div>
        </nav>
        <script type="text/javascript">
        $(document).ready(function () {
        	$('a[href="' + this.location.pathname + '"]').parent().addClass('active');
        });
        </script>
       
        
        