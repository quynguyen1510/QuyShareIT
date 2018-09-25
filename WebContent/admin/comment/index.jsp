<%@page import="model.bean.Comment"%>
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
					<%
						String msg = request.getParameter("msg");
						if("11".equals(msg)){
							out.print("<span style='background-color:green ; font-size:25px; color:white;'>Xóa thành công</span>");
						}
					%>
					<div class="card">
						<div class="header">
							<h4 class="title">Bình luận</h4>
						</div>
						<div class="content table-responsive table-full-width">
							<table class="table table-striped">
								<thead>
									<th>ID</th>
									<th>Tên bài viết</th>
									<th> Họ và tên</th>
									<th>Bình luận</th>
									<th>Trạng thái</th>
									<th>Chức năng</th>
								</thead>
								<tbody>
									<tr>
									<%
										ArrayList<Comment> arCmt = (ArrayList<Comment>) request.getAttribute("arCmt");
										if(arCmt != null){
											for(int i = 0 ; i<arCmt.size() ; i++){
									%>
										<td><a href="<%=request.getContextPath()%>/admin/users/edit"><%=arCmt.get(i).getId() %></a></td>
										<td><a href="<%=request.getContextPath()%>/admin/users/edit"><%=arCmt.get(i).getNews().getName() %></a></td>
										<td><%=arCmt.get(i).getName()%></td>
										<td><%=arCmt.get(i).getContent() %></td>
										<td id="change-<%=arCmt.get(i).getId()%>">
											<%if(arCmt.get(i).getActive() == 0){ %>
											<a onclick="change(<%=arCmt.get(i).getId()%>,<%= arCmt.get(i).getActive()%>)" id="changeActive" href="javascript:void(0)">
											<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/disactive.png" alt="" /></a>
											<%}else{ %>
											<a onclick="change(<%=arCmt.get(i).getId()%>,<%= arCmt.get(i).getActive()%>)" id="changeActive" href="javascript:void(0)">
											<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/active.png" alt="" /></a>
											<%} %>										
										</td>
										<td> 
											<a href="<%=request.getContextPath()%>/admin/comment/delete?id=<%=arCmt.get(i).getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa')">
											<img src="<%=request.getContextPath()%>/templates/admin/assets/img/del.gif" alt="" />Xóa</a>
										</td>
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
									<li><a href="<%=request.getContextPath()%>/admin/comment?page=<%=sumPage %>" title="">Privious</a></li>
									<%}else{ %>
									<li><a href="<%=request.getContextPath()%>/admin/comment?page=<%=currentPage-1 %>" title="">Privious</a></li>
									<%} %>
								<%
									for(int i = 1 ; i <= sumPage ; i++){
										if(currentPage == i){
											active = "style = 'background-color:#2bbbdf ; color:white;' ";
										}else{
											active = "";
										}
								%>
									
									<li><a <%=active %> href="<%=request.getContextPath()%>/admin/comment?page=<%=i %>" title=""><%=i %></a></li>
									<%} %>
								<%
									if(currentPage == sumPage ) {
								%>
									<li><a href="<%=request.getContextPath()%>/admin/comment?page=<%=1 %>" title="">Next</a></li>
								<%}else{%>
									<li><a href="<%=request.getContextPath()%>/admin/comment?page=<%=currentPage+1 %>" title="">Next</a></li>
								<%} %>
								</ul>
							</div>
						</div>
					</div>
				</div>
			<script>
				function change(newsID,active) {
					 //lấy dữ liệu từ ô input;
					$.ajax({
						url : '<%=request.getContextPath()%>/admin/comment',
						type : 'POST',
						cache : false,
						data : {
						//Dữ liệu gửi đi
							activeidc: active,
							id : newsID,
						},
						success : function(data) {
							// Nhận dữ liệu trả về
							$('#change-'+newsID).html(data)//Hiển thị ở trang web
						},
						error : function() {
							alert('Có lỗi');
						}
					});
				}
			</script>
			</div>
		</div>
	</div>
<%@include file="/templates/admin/inc/footer.jsp"%>
