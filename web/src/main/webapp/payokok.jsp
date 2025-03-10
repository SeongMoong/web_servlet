<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] product = (String[]) request.getAttribute("product");
String totalprice = (String) request.getAttribute("totalprice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 페이지</title>
</head>
<body>
<form id="frm" method="post" action="http://172.30.1.28:8080/web/payok.do">
    <p>구매확정 물품</p>
    <ul>
    <% if (product != null) {
        for (String item : product) { %>
            <li><%= item %></li>
    <%  } } %>
    </ul>
    최종 결제 금액: <input type="text" name="totalprice" value="<%= totalprice %>" readonly><br>
    <input type="radio" name="card" value="y0">신용카드<br>
    <input type="radio" name="card" value="y1">가상계좌<br>
    <input type="radio" name="card" value="y2">휴대폰 결제<br>
    <input type="radio" name="card" value="y3">무통장 입금<br>
    <button type="button" onclick="part2()">결제진행</button>
</form>

<script>
function part2(){
    if(confirm("이 결제 방식으로 진행 하시겠습니까?")){
        document.getElementById("frm").submit();
    }
}
</script>
</body>
</html>
