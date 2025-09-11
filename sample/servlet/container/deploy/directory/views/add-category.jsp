<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg rounded-4">
        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
            <h3 class="mb-0">➕ Thêm danh mục mới</h3>
            <a class="btn btn-outline-light btn-sm" href="${pageContext.request.contextPath}/admin/home/categories">⬅ Quay lại</a>
        </div>
        <div class="card-body">
            <!-- 🔑 Dùng formAction do controller truyền xuống -->
            <form role="form" action="${formAction}" method="post">
                <div class="mb-3">
                    <label class="form-label fw-bold">📂 Tên danh mục:</label>
                    <input type="text" name="name" class="form-control"
                           placeholder="Nhập tên danh mục" required>
                </div>
                <div class="mb-3">
                    <label class="form-label fw-bold">🖼 Ảnh (URL):</label>
                    <input type="url" name="images" class="form-control" placeholder="https://...">
                    <div class="form-text">Dán URL ảnh (hoặc để trống).</div>
                </div>
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-success px-4">✅ Thêm</button>
                    <button type="reset" class="btn btn-secondary px-4">❌ Hủy</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
