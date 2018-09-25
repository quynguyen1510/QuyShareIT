<%@page import="model.bean.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<%
		Comment objCmt = (Comment) request.getAttribute("objCmt");
		if(objCmt != null){
	%>
	<div style='border:1px solid gray;width:60%;background-color: #f7f7f7;margin-bottom: 10px;'>
		<img alt="" src="<%=request.getContextPath()%>/templates/public/img/avar.png" style='padding-left: 5px;'>
		<p style='margin-bottom: -30px;padding-left: 5px'><%=objCmt.getName()%></p>
		<p style='padding-left: 5px;'><%=objCmt.getDate_create() %></p>
		<p style="padding-left: 5px;"><%=objCmt.getContent() %></p>
		<a href="javascript:void(0)" onlick="reply(<%=objCmt.getId()%>)" title="">Reply</a>
	</div>
	<%}%>
</div>
<script>
function reply(idnew) {
	$.ajax({
		url : '<%=request.getContextPath()%>/public/replycmt',
		type : 'POST',
		cache : false,
		data : {
		//Dữ liệu gửi đi
			id : idnews,
		},
		success : function(data) {
			// Nhận dữ liệu trả về
			$('.showreply').html(data)//Hiển thị ở trang web
		},
		error : function() {
			alert('Có lỗi');
		}
	});
}
</script>