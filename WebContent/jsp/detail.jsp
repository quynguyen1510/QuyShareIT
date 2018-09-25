<%@page import="util.StringLibrary"%>
<%@page import="model.bean.Comment"%>
<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <script src="<%=request.getContextPath()%>/library/ckeditor/ckeditor.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/library/ckfinder/ckfinder.js" type="text/javascript"></script>
       <!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"><script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
		<link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/custom-styles.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/font-awesome.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/component.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/font-awesome-ie7.css">
        <script src="<%=request.getContextPath()%>/templates/public/js/jquery-1.9.1.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/jquery-1.9.1.js"></script> 
		<script src="<%=request.getContextPath()%>/templates/public/js/bootstrap.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/masonry.pkgd.min.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/imagesloaded.js"></script>
	    <script src="<%=request.getContextPath()%>/templates/public/js/classie.js"></script>
	    <script src="<%=request.getContextPath()%>/templates/public/js/AnimOnScroll.js"></script>
        <script src="<%=request.getContextPath()%>/templates/public/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <!-- Site header starts here -->

            


             <!-- main content starts here -->

            <div class="container b-radius-top">
                <div class="top-bar b-radius">
                </div>
                <div class="login">
						<a href="<%=request.getContextPath()%>/login">Login</a>
				</div>
                <div class="site-header">


                     <!-- Site Name starts here -->

                    <div class="site-name">
                        <h1>Share IT</h1>
                        <h5>Đã học là làm được</h5>
                    </div>
					
                    <!-- Site Name ends -->

                     <!-- Menu starts here -->
                    <div class="menu">
                                <ul class="nav">
                                    <li class="active"><a href="<%=request.getContextPath()%>/trang-chu">Home</a></li>
                                    <%
                                    	ArrayList<Category> arParentCat = (ArrayList<Category>) request.getAttribute("arParentCat");
                                    	ArrayList<Category> arChildCat = (ArrayList<Category>)  request.getAttribute("arChildCat");
                                    	ArrayList<News> arHotNew = (ArrayList<News>) request.getAttribute("arHotNew");
                                    	for(int i = 0 ; i< arParentCat.size() ; i++){
                                    %>
                                    <li>
										<a href="<%=request.getContextPath()%>/<%=StringLibrary.createSlug(arParentCat.get(i).getName()) %>-<%=arParentCat.get(i).getId()%>"><%=arParentCat.get(i).getName() %></a>
										<ul class="son-cat">
										<%for(int j = 0 ; j <arChildCat.size() ; j++){ 
											if(arChildCat.get(j).getParent_id() == arParentCat.get(i).getId()){
										%>
											<li><a href="<%=request.getContextPath()%>/<%=StringLibrary.createSlug(arChildCat.get(i).getName()) %>-<%=arChildCat.get(j).getId()%>"><%=arChildCat.get(j).getName() %></a></li>
										<%}} %>
										</ul>
									</li>
									<%} %>
                                </ul>
                                 <form class="search-form" method="POST" action="<%=request.getContextPath()%>/tim-kiem">
                                    <div class="input-append ">
                                        <input type="text" name="searchnews" placeholder="site search">
                                        <button type="submit" class="btn"><i class="icon-search"></i></button>
                                    </div>
                                </form>
                            
                        </div>
                    </div>

                    <!-- Menu ends -->
            

                <!-- Banner starts here -->

                
        
                 <!-- Banner ends here -->


                 <!-- Main content starts here -->
                <div class="detail-content">
                <div class="featured-blocks">
                <%
                	News objNews = (News) request.getAttribute("objNews");
                	if(objNews != null){
                %>
                    <h2 class="title"><%=objNews.getName() %></h2>
                	<p class="detail"><%=objNews.getDetail() %></p>
                <%} %>
                </div>
                </div>
                
				 <div>
					<%
						ArrayList<Comment> arComments = (ArrayList<Comment>) request.getAttribute("arComments");
						if(arComments != null){
							for(int i = 0 ; i < arComments.size() ; i++){
					%>
					<div style='border:1px solid gray;width:60%;background-color: #f7f7f7;margin-bottom: 10px; margin-top:10px;'>
						<img alt="" src="<%=request.getContextPath()%>/templates/public/img/avar.png" style='padding-left: 5px;'>
						<p style='margin-bottom: -30px;padding-left: 5px'><%=arComments.get(i).getName()%></p>
						<p style='padding-left: 5px;'><%=arComments.get(i).getDate_create() %></p>
						<p style="padding-left: 5px;"><%=arComments.get(i).getContent() %></p>
						<a href="javascript:void(0)" onclick="reply(<%=arComments.get(i).getId() %>)" title="">Reply</a>
						<div id="rep-<%=arComments.get(i).getId()%>">
						</div> 
					</div>
					
					<%}}%>
				</div>
				  
				<div class="showcmt">
				</div>
				             
                <!-- Form comment -->
               
                <div class="comment">
					<h2>Viết bình luận</h2>
					<form method="post" action="<%=request.getContextPath()%>/admin/comment/add?idnews=<%=objNews.getId()%>">
						<input id="name"  type="text" class="textbox" name="Name" placeholder="Nhập tên của bạn">
						<input id="email"type="text" class="textbox" name="Email" placeholder="Nhập địa chỉ mail của bạn"></br>
						<input id="website"type="text" class="textbox" name="Website" placeholder="Nhập tên website(không bắt buộc)"></br>
						<textarea id="message" name="Message" placeholder="Nội dung của bạn"></textarea>
						<div class="smt1">
							<a href="javascript:void(0)" title="" onclick="show(<%=objNews.getId()%>)">Gửi</a>
						</div>
					</form>
				</div>
				
				
                
                <!-- Form comment end -->
                    <!-- Featured slider starts here -->
                <div class="news">
					<div class="news-limit">
						<h2>Tin liên quan</h2>
						<div class="news-content">
						<%
							int check = 0;
							ArrayList<News> arNewsLimit = (ArrayList<News>) request.getAttribute("arNewsLimit");
							for(int i = 0 ; i < arNewsLimit.size() ; i++){
								check = check + 1;
						%>
							<div class="news-detail">
								<a href="<%=request.getContextPath()%>/<%=StringLibrary.createSlug(arNewsLimit.get(i).getName())%>/detail/<%=arNewsLimit.get(i).getId()%>"><img style="width:500px ; height:300px;" alt="" src="<%=request.getContextPath() %>/images/<%=arNewsLimit.get(i).getPicture()%>"></a>
								<h3><a href="<%=request.getContextPath()%>/<%=StringLibrary.createSlug(arNewsLimit.get(i).getName()) %>/detail/<%=arNewsLimit.get(i).getId()%>"><%=arNewsLimit.get(i).getName() %></a></h3>
								<p><%=arNewsLimit.get(i).getPreview() %></p>
							</div>
							<%if(check == 2){ %>	
							<div class="clearfix"> </div>
							<%check = 0;}  %>
						<%}%>
						</div>
					</div>
					
				</div>
			<script>
				function show(idnews) {
					var name = $('#name').val(); //lấy dữ liệu từ ô input
					var email = $('#email').val();
					var website = $('#website').val();
					var message = $('#message').val();
					$.ajax({
						url : '<%=request.getContextPath()%>/admin/comment/add',
						type : 'POST',
						cache : false,
						data : {
						//Dữ liệu gửi đi
							name: name,
							email : email,
							website : website,
							message : message,
							id : idnews,
						},
						success : function(data) {
							// Nhận dữ liệu trả về
							$('.showcmt').html(data)//Hiển thị ở trang web
						},
						error : function() {
							alert('Có lỗi');
						}
					});
				}
				
				function reply(idnews) {
					$.ajax({
						url : '<%=request.getContextPath()%>/public/replycmt',
						type : 'GET',
						cache : false,
						data : {
						//Dữ liệu gửi đi
							id : idnews,
						},
						success : function(data) {
							// Nhận dữ liệu trả về
							$('#rep-'+idnews).html(data)//Hiển thị ở trang web
						},
						error : function() {
							alert('Có lỗi');
						}
					});
				}
			</script>
			</div>
                

                </div>
                   <!-- Featured slider ends here -->    
				   
                    <!-- Featured accordion starts here -->
                    

                    <!-- Featured accordion ends here -->

                <!-- Main content ends here -->

            </div>
			</div>
            <div class="container bg-blue b-radius-bottom">
               
                <div class="">
                    Copyright (c) QuyNguyen. 
                </div>

            </div>
                
       
    
    <!--<script>
      new AnimOnScroll( document.getElementById( 'grid' ), {
        minDuration : 0.4,
        maxDuration : 0.7,
        viewportFactor : 0.2
      } );
    </script>-->
<script>
$('#myCarousel').carousel({
    interval: 1800
});
</script>
        



   </body>
</html>
