<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="/templates/admin/inc/header.jsp"%>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Thêm bài viết</h4>
                            </div>
                            <%
                            
    						String err = request.getParameter("error");
    						if("6".equals(err)){
    							out.print("<span style='color:red ; font-weight: bold ; background-color:yellow;'>Thêm không thành công<span>");
    						}
    					
                            %>
                            <div class="content">
                                <form action="" method="post" enctype="multipart/form-data" >
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Tên bài viết</label>
                                                <input type="text" name="name" class="form-control border-input" placeholder="Tên bài viết" value="">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Danh mục tin</label>
                                                <select name="category" class="form-control border-input">
                                                <%
                                                	ArrayList<Category> arCat = (ArrayList<Category>) request.getAttribute("arCat");
                                                	if(arCat!= null){
                                                		for(int i = 0 ; i< arCat.size() ; i++){
                                                %>
                                                	<option value="<%=arCat.get(i).getId()%>"><%=arCat.get(i).getName() %></option>
                                                <%}} %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Hình ảnh</label>
                                                <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Slide</label>
                                                <input type="checkbox" name="isslide" value="1" />
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Mô tả</label>
                                                <textarea id="editor" name="preview" rows="4" class="form-control border-input" placeholder="Mô tả tin"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Chi tiết</label>
                                                <textarea id="finder" name="detail" rows="6" class="form-control border-input" placeholder="Mô tả chi tiết"></textarea>
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

					<script type="text/javascript">
						var editor = CKEDITOR.replace('editor');
						var editor2 = CKEDITOR.replace('finder');
						CKFinder.setupCKEditor(editor , '<%=request.getContextPath()%>/library/ckfinder/');
						CKFinder.setupCKEditor(editor2 , '<%=request.getContextPath()%>/library/ckfinder/');
					</script>

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
