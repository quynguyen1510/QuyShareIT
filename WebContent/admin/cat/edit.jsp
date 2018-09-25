<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp"%>
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12">
				<div class="card">
					<div class="header">
						<h4 class="title">Sửa danh mục</h4>
					</div>
					<%
						String err = request.getParameter("error");
						if("1".equals(err)){
							out.print("<span style='color:red ; font-weight: bold ;'>Sửa thất bại</span>");
						}
					%>
					<div class="content">
						<form action="" method="post">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<%
											Category objCat = (Category) request.getAttribute("objCat");
											String name = request.getParameter("name");
											if (objCat != null) {
												name = objCat.getName();
											} else {
												name = "";
											}
										%>
										<label>Tên danh mục</label> <input type="text" name="name"
											class="form-control border-input"
											placeholder="Nhập tên danh mục" value="<%=name%>">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>Danh mục cha</label> 
										<select name="parent_id" class="form-control border-input">
											<%
												if(objCat.getParent_id() == 0){
											%>
											<option selected = 'selected' value="<%=0%>">Không chọn</option>
											<%}else{ %>
											<option value="<%=0%>">Không chọn</option>
											<%} %>
											<%
												ArrayList<Category> parentCat = (ArrayList<Category>) request.getAttribute("parentCat");
												String selected = "";
												if(parentCat != null){
													for (int i = 0; i < parentCat.size(); i++) {
														if (objCat.getParent_id() == parentCat.get(i).getId()) {
															selected = "selected = 'selected'";
														} else {
															selected = "";
	
														}
											%>
													<option <%=selected%> value="<%=parentCat.get(i).getId()%>"><%=parentCat.get(i).getName() %></option>
											<%}} %>
											
										</select>
									</div>
								</div>
							</div>

							<div class="text-center">
								<input type="submit" class="btn btn-info btn-fill btn-wd"
									value="Thực hiện" />
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>


		</div>
	</div>
</div>


<%@include file="/templates/admin/inc/footer.jsp"%>


</div>
</div>


</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="assets/js/paper-dashboard.js"></script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>


</html>
