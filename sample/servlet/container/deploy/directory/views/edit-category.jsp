<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sửa Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #c3ec52, #0ba29d);
            font-family: Arial, sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card {
            border-radius: 20px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.2);
        }
        .form-control { border-radius: 10px; }
        .btn-custom { border-radius: 10px; font-weight: bold; }
        img.preview {
            border: 2px solid #ddd;
            border-radius: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4 bg-dark text-light">
                <h2 class="text-center mb-3">✏️ Chỉnh sửa danh mục</h2>

                <!-- chọn form action theo role -->
                <c:choose>
                <c:when test="${sessionScope.account.roleid == 1}">
                <form role="form" action="${pageContext.request.contextPath}/admin/home/categories/edit" method="post">
                    </c:when>
                    <c:when test="${sessionScope.account.roleid == 2}">
                    <form role="form" action="${pageContext.request.contextPath}/manager/home/categories/edit" method="post">
                        </c:when>
                        <c:otherwise>
                        <form role="form" action="${pageContext.request.contextPath}/user/home/categories/edit" method="post">
                            </c:otherwise>
                            </c:choose>

                            <!-- Hidden ID -->
                            <input type="hidden" name="id" value="${category.id}"/>

                            <!-- Name -->
                            <div class="mb-3">
                                <label class="form-label fw-bold">📂 Tên danh mục:</label>
                                <input type="text" name="name" value="${category.name}"
                                       class="form-control" required/>
                            </div>

                            <!-- Current image -->
                            <div class="mb-3">
                                <label class="form-label fw-bold">🖼 Ảnh hiện tại:</label><br/>
                                <c:if test="${not empty category.images}">
                                    <img src="${category.images}" width="150" class="preview" alt="current image"/>
                                </c:if>
                                <c:if test="${empty category.images}">
                                    <span class="text-muted">Chưa có ảnh</span>
                                </c:if>
                            </div>

                            <!-- New image (URL) -->
                            <div class="mb-3">
                                <label class="form-label fw-bold">📤 Ảnh (URL):</label>
                                <input type="url" name="images" value="${category.images}"
                                       class="form-control" placeholder="https://..."/>
                            </div>

                            <!-- Buttons -->
                            <div class="d-flex justify-content-between">
                                <button type="submit" class="btn btn-success btn-custom">✅ Cập nhật</button>
                                <c:choose>
                                    <c:when test="${sessionScope.account.roleid == 1}">
                                        <a href="${pageContext.request.contextPath}/admin/home/categories"
                                           class="btn btn-secondary btn-custom">⬅ Quay lại</a>
                                    </c:when>
                                    <c:when test="${sessionScope.account.roleid == 2}">
                                        <a href="${pageContext.request.contextPath}/manager/home/categories"
                                           class="btn btn-secondary btn-custom">⬅ Quay lại</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/user/home/categories"
                                           class="btn btn-secondary btn-custom">⬅ Quay lại</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
