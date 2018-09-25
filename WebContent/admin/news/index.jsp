<%@page import="model.bean.News"%>
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
					
					<%
						String msg = request.getParameter("msg");
						if("6".equals(msg)){
							out.print("<span style='color:white ; font-weight: bold ; background-color:green;'>Thêm thành công<span>");
						}
						if("7".equals(msg)){
							out.print("<span style='color:white ; font-weight: bold ; background-color:green;'>Sửa thành công<span>");
						}
						if("9".equals(msg)){
							out.print("<span style='color:white ; font-weight: bold ; background-color:green;'> Xóa thành công<span>");
						}
					%>
						<div class="header">
							<h4 class="title">Danh sách bài viết</h4>
							<a href="<%=request.getContextPath()%>/admin/news/add" class="addtop"><img
								src="<%=request.getContextPath()%>/templates/admin/assets/img/add.png"
								alt="" /> Thêm</a>
						</div>
						<div class="content table-responsive table-full-width">
							<table class="table table-striped">
								<thead>
									<th>ID</th>
									<th>Tên bài viết</th>
									<th>Hình ảnh</th>
									<th>Ngày tạo</th>
									<th>Danh mục</th>
									<th>Chức năng</th>
								</thead>
								<tbody>
								<%
									ArrayList<News> arNews = (ArrayList<News>) request.getAttribute("arNews");
									if(arNews != null){
										for(int i = 0 ; i< arNews.size() ; i++){
								%>
									<tr>
										<td><a href="<%=request.getContextPath()%>/admin/news/edit?id=<%=arNews.get(i).getId()%>"><%=arNews.get(i).getId() %></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/news/edit?id=<%=arNews.get(i).getId()%>"><%=arNews.get(i).getName() %></a></td>
										<td>
											<img style='width: 150px ; height: 100px' src="<%=request.getContextPath()%>/images/<%=arNews.get(i).getPicture() %>" class="picture" />
										</td>
										<td><a href="<%=request.getContextPath()%>/admin/news/edit?id=<%=arNews.get(i).getId()%>"><%=arNews.get(i).getDate_create() %></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/news/edit?id=<%=arNews.get(i).getId()%>"><%=arNews.get(i).getCategory().getName() %></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/news/edit?id=<%=arNews.get(i).getId()%>">
										<img src="<%=request.getContextPath()%>/templates/admin/assets/img/edit.gif" alt="" /> Sửa</a> &nbsp;||&nbsp; 
										<a href="<%=request.getContextPath()%>/admin/news/delete?id=<%=arNews.get(i).getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">
										<img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" /> Xóa</a></td>
									</tr>
									<%}} %>
								</tbody>
							</table>
							<div class="text-center">
								<ul class="pagination">
								<%
									String active = "";
									int currentPage = (Integer) request.getAttribute("currentPage");
									int sumPage = (Integer) request.getAttribute("sumPage");
									if(currentPage == 1 ) {
								%>
									<li><a href="<%=request.getContextPath()%>/admin/news?page=<%=sumPage %>" title="">Privious</a></li>
									<%}else{ %>
									<li><a href="<%=request.getContextPath()%>/admin/news?page=<%=currentPage-1 %>" title="">Privious</a></li>
									<%} %>
								<%
									for(int i = 1 ; i <= sumPage ; i++){
										if(currentPage == i){
											active = "style = 'background-color:#2bbbdf ; color:white;' ";
										}else{
											active = "";
										}
								%>
									
									<li><a <%=active %> href="<%=request.getContextPath()%>/admin/news?page=<%=i %>" title=""><%=i %></a></li>
									<%} %>
								<%
									if(currentPage == sumPage ) {
								%>
									<li><a href="<%=request.getContextPath()%>/admin/news?page=<%=1 %>" title="">Next</a></li>
								<%}else{%>
									<li><a href="<%=request.getContextPath()%>/admin/news?page=<%=currentPage+1 %>" title="">Next</a></li>
								<%} %>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="/templates/admin/inc/footer.jsp"%>
