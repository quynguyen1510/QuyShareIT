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
                                <h4 class="title">Thêm danh mục</h4>
                            </div>
                            <%
                            	String err = request.getParameter("error");
                            	if("0".equals(err)){
                            		out.print("<span style='color:red ; font-weight: bold ;'>Thêm thất bại</span>");
                            	}
                            %>
                            <div class="content">
                                <form action="" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Tên danh mục</label>
                                                <input type="text" name="name" class="form-control border-input" placeholder="Nhập tên danh mục" value="">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Danh mục cha</label>
                                                <select name="parent_id" class="form-control border-input">
                                           		     <option value="<%=0%>">Không chọn</option>
                                                <%
                                                	ArrayList<Category> arCatParent = (ArrayList<Category>) request.getAttribute("arCatParent");
                                                	if(arCatParent != null){
                                                		for(int i = 0 ; i< arCatParent.size() ; i++){
                                                %>	
                                                	
                                                	<option value="<%=arCatParent.get(i).getId()%>"><%=arCatParent.get(i).getName()%></option>
                                                <%}} %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                
                                    <div class="text-center">
                                        <input type="submit" class="btn btn-info btn-fill btn-wd" value="Thực hiện" />
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
