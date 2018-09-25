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
                                <h4 class="title">Thêm quản trị viên</h4>
                            </div>
                            <%
                            	String err = request.getParameter("error");
                            	String username = request.getParameter("username");
                            	String fullname = request.getParameter("fullname");
                            	if(username == null){
                            		username = "";
                            	}
                            	if(fullname == null){
                            		fullname = "";
                            	}
                            	if("3".equals(err)){
                            		out.print("<span style='color:red ; font-weight:bold ; background:yellow;'>User đã tồn tại</span>");
                            	}
                            	if("3.1".equals(err)){
                            		out.print("<span style='color:red ; font-weight:bold ; background:yellow;'>Password không giống nhau</span>");
                            	}
                            %>
                            <div class="content">
                                <form action="" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Username</label>
                                                <input type="text" name="username" class="form-control border-input" placeholder="abc" value="<%=username%>">
                                                <label>Password</label>
                                                <input type="password" name="password" class="form-control border-input" placeholder="*****" value="">
                                                <label>Nhập lại password</label>
                                                <input type="password" name="repassword" class="form-control border-input" placeholder="*****" value="">
                                                <label>Fullname</label>
                                                <input type="text" name="fullname" class="form-control border-input" placeholder="Nguyễn Văn A" value="<%=fullname%>">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Quyền hạn</label>
                                                <select name="quyenhan" class="form-control border-input">
                                           		     <option value="<%=1%>">Active</option>
                                                	
                                                	<option value="<%=0%>">Block</option>
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
