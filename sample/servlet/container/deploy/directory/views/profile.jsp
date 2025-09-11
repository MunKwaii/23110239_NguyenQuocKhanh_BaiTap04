<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật thông tin cá nhân</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

    <style>
        body {
            margin: 0;
            min-height: 100vh;
            display: grid;
            grid-template-rows: auto 1fr auto; /* header - main - footer */
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #74ebd5, #9face6);
        }

        main {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .profile-box {
            width: 500px;
            border: 1px solid #ddd;
            padding: 30px;
            border-radius: 12px;
            background: #fff;
            box-shadow: 0px 4px 15px rgba(0,0,0,0.15);
        }

        .profile-box h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .input-group-custom {
            display: flex;
            align-items: center;
            margin: 12px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            background: #f9f9f9;
            padding: 6px 10px;
        }
        .input-group-custom i {
            color: #666;
            min-width: 24px;
            text-align: center;
        }
        .input-group-custom input {
            flex: 1;
            padding: 8px;
            border: none;
            background: transparent;
            outline: none;
        }

        .avatar-preview {
            text-align: center;
            margin-bottom: 20px;
        }
        .avatar-preview img {
            width: 120px;
            height: 120px;
            object-fit: cover;
            border-radius: 50%;
            border: 3px solid #ddd;
            box-shadow: 0 3px 6px rgba(0,0,0,0.2);
        }

        .btn-update {
            width: 100%;
            background: #28a745;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-update:hover {
            background: #218838;
        }

        .btn-back {
            margin-top: 12px;
            width: 100%;
        }
    </style>
</head>
<body>


<main>
    <div class="profile-box">
        <h2><i class="fa fa-user"></i> Cập nhật hồ sơ</h2>

        <!-- Lấy user từ session -->
        <c:choose>
            <c:when test="${not empty sessionScope.account}">
                <c:set var="me" value="${sessionScope.account}"/>
            </c:when>
            <c:otherwise>
                <c:set var="me" value="${sessionScope.user}"/>
            </c:otherwise>
        </c:choose>

        <!-- Avatar hiện tại -->
        <div class="avatar-preview">
            <c:choose>
                <c:when test="${not empty me.avatar}">
                    <img src="${pageContext.request.contextPath}/uploads/${me.avatar}" alt="avatar"/>
                </c:when>
                <c:otherwise>
                    <img src="https://via.placeholder.com/120?text=Avatar" alt="no-avatar"/>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Form theo role -->
        <c:choose>
        <c:when test="${me.roleid == 1}">
        <form action="${pageContext.request.contextPath}/admin/home/profile" method="post" enctype="multipart/form-data">
            </c:when>
            <c:when test="${me.roleid == 2}">
            <form action="${pageContext.request.contextPath}/manager/home/profile" method="post" enctype="multipart/form-data">
                </c:when>
                <c:otherwise>
                <form action="${pageContext.request.contextPath}/user/home/profile" method="post" enctype="multipart/form-data">
                    </c:otherwise>
                    </c:choose>

                    <div class="input-group-custom">
                        <i class="fa fa-user"></i>
                        <input type="text" name="fullname" value="${me.fullName}" placeholder="Họ và tên" required />
                    </div>

                    <div class="input-group-custom">
                        <i class="fa fa-phone"></i>
                        <input type="text" name="phone" value="${me.phone}" placeholder="Số điện thoại" required />
                    </div>

                    <div class="input-group-custom">
                        <i class="fa fa-image"></i>
                        <input type="file" name="avatar" accept="image/*" />
                    </div>

                    <button type="submit" class="btn-update">✅ Cập nhật</button>

                    <!-- Nút quay lại -->
                    <c:choose>
                        <c:when test="${me.roleid == 1}">
                            <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary btn-back">⬅ Quay lại</a>
                        </c:when>
                        <c:when test="${me.roleid == 2}">
                            <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary btn-back">⬅ Quay lại</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/user/home" class="btn btn-secondary btn-back">⬅ Quay lại</a>
                        </c:otherwise>
                    </c:choose>
                </form>
    </div>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
