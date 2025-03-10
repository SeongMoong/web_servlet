<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession hs = request.getSession();
	String mid = (String)hs.getAttribute("mid");
	//out.print(mid); : 적용했을 때 null 출력이 될 경우(session) => 연산기호
	String mnm = (String)hs.getAttribute("mnm");
	//Attribute가 String으로 변환을 제대로 못함 (NullpointException)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
</head>
<body>
<% if(mid == null || mnm == null){ %>
<input type="button" value="로그인">
<% } else { %>
[<%=mid %>]<%=mnm %> 님 환영합니다. <input type="button" value="로그아웃" onclick ="logout()">
<% } %>
</body>
<script>
function logout(){
	location.href='./logout.jsp';
}
</script>
</html>