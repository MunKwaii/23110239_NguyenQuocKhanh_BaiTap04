<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f6f9; }
        .sidebar {
            width: 220px;
            position: fixed;
            top: 0; left: 0;
            height: 100%;
            background: #007bff;
            color: #fff;
            padding-top: 20px;
        }
        .sidebar a {
            color: #fff;
            padding: 12px 20px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover { background: #0056b3; }
        .content { margin-left: 240px; padding: 20px; }
        .table img { border-radius: 8px; }
    </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <div class="text-center mb-4">
        <img src="https://i.ibb.co/ZX1xVYd/avatar.png" class="rounded-circle" width="100" alt="Admin"/>
        <h6 class="mt-2">Xin chào ${sessionScope.account.userName}</h6>
    </div>
    <a href="${pageContext.request.contextPath}/admin/home">🏠 Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/home/categories">📂 Quản lý danh mục</a>
    <a href="${pageContext.request.contextPath}/admin/home/categories/add">➕ Thêm danh mục mới</a>
    <a href="${pageContext.request.contextPath}/logout" class="text-danger">🚪 Đăng xuất</a>
</div>

<!-- Content -->
<div class="content">
    <div class="card shadow-lg rounded-4">
        <div class="card-header bg-white">
            <h3 class="fw-bold">📂 Danh sách danh mục</h3>
            <p class="text-muted">Nơi bạn có thể quản lý tất cả danh mục</p>
        </div>
        <div class="card-body">
            <table class="table table-striped table-hover align-middle text-center">
                <thead class="table-primary">
                <tr>
                    <th>STT</th>
                    <th>Hình ảnh</th>
                    <th>Tên danh mục</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="cate" varStatus="stt">
                    <tr>
                        <td>${stt.index + 1}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty cate.images}">
                                    <img src="${cate.images}" width="80" height="80" alt="image"/>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-muted">Không có ảnh</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="fw-bold">${cate.name}</td>

                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.account.roleid == 1}">
                                    <a href="${pageContext.request.contextPath}/admin/home/categories/edit?id=${cate.id}"
                                       class="btn btn-sm btn-warning">✏ Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/home/categories/delete?id=${cate.id}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Bạn có chắc muốn xóa?')">🗑 Xoá</a>
                                </c:when>
                                <c:when test="${sessionScope.account.roleid == 2}">
                                    <a href="${pageContext.request.contextPath}/manager/home/categories/edit?id=${cate.id}"
                                       class="btn btn-sm btn-warning">✏ Sửa</a>
                                    <a href="${pageContext.request.contextPath}/manager/home/categories/delete?id=${cate.id}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Bạn có chắc muốn xóa?')">🗑 Xoá</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/user/home/categories/edit?id=${cate.id}"
                                       class="btn btn-sm btn-warning">✏ Sửa</a>
                                    <a href="${pageContext.request.contextPath}/user/home/categories/delete?id=${cate.id}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Bạn có chắc muốn xóa?')">🗑 Xoá</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty categories}">
                    <tr>
                        <td colspan="4" class="text-muted">Chưa có danh mục nào</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <a href="${pageContext.request.contextPath}/admin/home/categories/add" class="btn btn-success">
                ➕ Thêm danh mục
            </a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
