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
						<%
							String msg = request.getParameter("msg");
							String err = request.getParameter("error");
							if("2".equals(err)){
								out.print("<span style='color:red ; font-weight: bold ; background-color:yellow;'>Xóa thất bại<span>");
							}
							if("0".equals(msg)){
								out.print("<span style='color:white ; font-weight: bold ; background-color:green;'>Thêm thành công<span>");
							}
							if("1".equals(msg)){
								out.print("<span style='color:white ; font-weight: bold ; background-color:green;'>Sửa thành công<span>");
							}
							if("2".equals(msg)){
								out.print("<span style='color:white ; font-weight: bold ; background-color:green;'>Xóa thành công<span>");
							}
						%>
						<div class="header">
							<h4 class="title">Danh mục tin tức</h4>
							<a href="<%=request.getContextPath()%>/admin/cats/add" class="addtop"><img
								src="<%=request.getContextPath()%>/templates/admin/assets/img/add.png"
								alt="" /> Thêm</a>
						</div>
						<div class="content table-responsive table-full-width">
							<table class="table table-striped">
								<thead>
									<th>Tên danh mục</th>
									<th>Chức năng</th>
								</thead>
								<tbody>
								<%
									ArrayList<Category> arParentCat = (ArrayList<Category>) request.getAttribute("arParentCat");
									ArrayList<Category> arChildCat = (ArrayList<Category>) request.getAttribute("arChildCat");
									for(int i = 0 ; i < arParentCat.size() ; i++){	
								%>
									<tr>
										<td><a href="<%=request.getContextPath()%>/admin/cats/edit?id=<%=arParentCat.get(i).getId()%>"><%=arParentCat.get(i).getName()%></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/cats/edit?id=<%=arParentCat.get(i).getId()%>">
										<img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp; 
										<a href="<%=request.getContextPath()%>/admin/cats/delete?id=<%=arParentCat.get(i).getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">
										<img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a></td>
									</tr>
										<%	for(int j = 0 ; j < arChildCat.size() ; j++){
												if(arChildCat.get(j).getParent_id() == arParentCat.get(i).getId()){%>
									<tr>
										<td><a href="<%=request.getContextPath()%>/admin/cats/edit?id=<%=arChildCat.get(j).getId()%>">|--<%=arChildCat.get(j).getName()%></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/cats/edit?id=<%=arChildCat.get(j).getId()%>">
										<img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp; 
										<a href="<%=request.getContextPath()%>/admin/cats/delete?id=<%=arChildCat.get(j).getId()%>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">
										<img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a></td>
									</tr>		
										<%}}}%>
									
								</tbody>
							</table>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="/templates/admin/inc/footer.jsp"%>
