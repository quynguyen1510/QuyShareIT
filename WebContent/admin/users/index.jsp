<%@page import="model.bean.Users"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp"%>
	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="header">
						<%
							String msg = request.getParameter("msg");
							String error = request.getParameter("error");
							if("3".equals(msg)){
								out.print("<span style='color:white ; font-weight:bold ; background:green;'>Thêm thành công</span>");
							}
							if("4".equals(msg)){
								out.print("<span style='color:white ; font-weight:bold ; background:green;'>Sửa thành công</span>");
							}
							if("5".equals(msg)){
								out.print("<span style='color:white ; font-weight:bold ; background:green;'>Xóa thành công</span>");
							}
							if("5".equals(error)){
								out.print("<span style='color:white ; font-weight:bold ; background:green;'>Xóa không thành công</span>");
							}
						%>
							<h4 class="title">Danh sách Quản trị viên</h4>
							<a href="<%=request.getContextPath()%>/admin/users/add" class="addtop"><img
								src="<%=request.getContextPath()%>/templates/admin/assets/img/add.png"
								alt="" /> Thêm</a>
						</div>
						<div class="content table-responsive table-full-width">
							<table class="table table-striped">
								<thead>
									<th>ID</th>
									<th>Username</th>
									<th>Fullname</th>
									<th>Trạng thái</th>
									<th>Chức năng</th>
								</thead>
								<tbody>
								<%
										Users info = (Users) session.getAttribute("login");
										ArrayList<Users> arrUsers = (ArrayList<Users>) request.getAttribute("arrUsers");
										if(arrUsers != null){
											for(int i = 0 ; i < arrUsers.size() ; i++){
								%>
									<tr>
										<td><a href="<%=request.getContextPath()%>/admin/users/edit?id=<%=arrUsers.get(i).getId()%>"><%=arrUsers.get(i).getId()%></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/users/edit?id=<%=arrUsers.get(i).getId()%>"><%=arrUsers.get(i).getUsername() %></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/users/edit?id=<%=arrUsers.get(i).getId()%>"><%=arrUsers.get(i).getFullname() %></a></td>
										<%if("admin".equals(objUser.getUsername())){ %>
											<%if(arrUsers.get(i).getActive() == 1){ %>
										<td id="change">
											<a id="changeActive" value="1"href="javascript:void(0)" >
											<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/active.png" alt="" /></a>
										</td>
										<%}else{ %>
										<td id="change">
											<a id="changeActive" value="0"href="javascript:void(0)" >
											<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/disactive.png" alt="" /></a>
										</td>
										<%} %>
										<td>
											<a href="<%=request.getContextPath()%>/admin/users/edit?id=<%=arrUsers.get(i).getId()%>">
											<img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp; 
											<a href="<%=request.getContextPath()%>/admin/users/delete?id=<%=arrUsers.get(i).getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">
											<img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a>
										</td>
										<%}else if(arrUsers.get(i).getId() == info.getId()){ %>
											<%if(arrUsers.get(i).getActive() == 1){ %>
										<td id="change">
											<a id="changeActive" value="1"href="javascript:void(0)" >
											<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/active.png" alt="" /></a>
										</td>
										<%}else{ %>
										<td id="change">
											<a id="changeActive" value="0"href="javascript:void(0)" >
											<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/disactive.png" alt="" /></a>
										</td>
										<%} %>
										<td>
											<a href="<%=request.getContextPath()%>/admin/users/edit?id=<%=arrUsers.get(i).getId()%>">
											<img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a>  
										</td>
										<%} %>
									</tr>
									<%}} %>
								</tbody>
							</table>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="/templates/admin/inc/footer.jsp"%>
