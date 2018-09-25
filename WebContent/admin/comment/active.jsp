<%@page import="model.bean.Comment"%>
<%@page import="model.bean.Users"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int active = (Integer) request.getAttribute("active");
	int id = (Integer) request.getAttribute("id");
	if(active == 1){
%>
		<a onclick="change(<%=id%>,<%=active%>)" id="changeActive" href="javascript:void(0)">
		<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/active.png" alt="" /></a>
<%}else{%>
		<a onclick="change(<%=id%>,<%=active%>)" id="changeActive" href="javascript:void(0)">
		<img id="img" src="<%=request.getContextPath()%>/templates/admin/assets/img/disactive.png" alt="" /></a>
<%}%>