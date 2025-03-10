<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String mid = (String)request.getAttribute("mid");
String mnum = (String)request.getAttribute("mnum");
String mad = (String)request.getAttribute("mad");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>쿠폰 등록 페이지</p>
아이디 : <%=mid%><br>
쿠폰번호 : <%=mnum%><br>
<%=mad%><br>
</body>
</html>