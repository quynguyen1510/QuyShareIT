<%@page import="util.StringLibrary"%>
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
        
       	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"><script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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

                <div class="banner">
                </div>
        
                 <!-- Banner ends here -->


                 <!-- Main content starts here -->
                
                <div class="featured-blocks">
                    <div class="row-fluid">
                    <div class="featured-heading">
                        <h2>Hot News</h2>
                    </div>
                    </div>
                    <div class="row-fluid">
                       <%
                       		for(int i = 0 ; i <arHotNew.size() ; i++){
                       %>
                            
                        <div class="span4">
                            <div class="block">
                            	<img style="width:300px;height:250px;" alt="" src="<%=request.getContextPath() %>/images/<%=arHotNew.get(i).getPicture()%>">
                                <div class="block-title">
                                    <h1><%=arHotNew.get(i).getName() %></h1>
                                </div>
                                <div class="block-content">
                                    <p><%=arHotNew.get(i).getPreview() %></p>
                                    <a href="<%=request.getContextPath() %>/<%=StringLibrary.createSlug(arHotNew.get(i).getName())%>/detail/<%=arHotNew.get(i).getId() %>"  class="btn" >more</a>
                                </div>
                            </div>
                        </div>
                       <%} %>
                            
                    </div>
                </div>
                

                    <!-- Featured slider starts here -->
                <div class="main">
					<div class="main-item">
						<div class="main-content">
						<%
							ArrayList<News> arNewsByID = (ArrayList<News>) request.getAttribute("arNewsByID");
							if(arNewsByID != null){
								for(int i = 0 ; i < arNewsByID.size() ; i++){
						
						%>
							<div class="main-news">
								<a href="<%=request.getContextPath()%>/<%=StringLibrary.createSlug(arNewsByID.get(i).getName()) %>/detail/<%=arNewsByID.get(i).getId()%>"><img src="<%=request.getContextPath()%>/images/<%=arNewsByID.get(i).getPicture() %>" class="img-responsive" alt=""></a>
								<h3><a href="<%=request.getContextPath()%>/<%=StringLibrary.createSlug(arNewsByID.get(i).getName()) %>/detail/<%=arNewsByID.get(i).getId()%>"><%=arNewsByID.get(i).getName() %></a></h3>
								<p><%=arNewsByID.get(i).getPreview() %></p>	
							</div>
						<%}} %>
								<div class="clearfix"> </div>
						</div>
					</div>
					
				</div>
			</div>
                

                </div>
                   <!-- Featured slider ends here -->    
				   
                    <!-- Featured accordion starts here -->
                    

                    <!-- Featured accordion ends here -->

                <!-- Main content ends here -->

            </div>
			</div>
            <div class="container bg-blue b-radius-bottom">
               
                <div class="copy-rights">
                    Copyright (c) QuyNguyen. 
                </div>

            </div>
                
       
    
   <!--   <script>
      new AnimOnScroll( document.getElementById( 'grid' ), {
        minDuration : 0.4,
        maxDuration : 0.7,
        viewportFactor : 0.2
      } );
    </script> -->
<script>
$('#myCarousel').carousel({
    interval: 1800
});
</script>
        



   </body>
</html>
