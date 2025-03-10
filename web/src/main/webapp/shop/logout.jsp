<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession hs = request.getSession();
	hs.invalidate(); //세션 모두 날리기
	out.print("<script>" 
			+ "alert('로그아웃 되셨습니다.');"
			+ "location.href='login.jsp';"
			+ "</script>");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>