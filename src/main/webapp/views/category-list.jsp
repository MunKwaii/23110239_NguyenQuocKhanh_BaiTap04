<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách Category</title>
</head>
<body>
<h2>Danh sách Category</h2>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Tên Category</th>
    </tr>

    <c:forEach items="${listcate}" var="list">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>   <!-- đổi ở đây -->
        </tr>
    </c:forEach>

    <c:if test="${empty listcate}">
        <tr><td colspan="2">Chưa có dữ liệu</td></tr>
    </c:if>
</table>
</body>
</html>
