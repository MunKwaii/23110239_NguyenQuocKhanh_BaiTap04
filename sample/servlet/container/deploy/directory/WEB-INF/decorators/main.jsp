<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title><sitemesh:write property="title"/></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <style>
    /* Bắt buộc để sticky footer */
    html, body {
      height: 100%;
      margin: 0;
    }
    body {
      display: flex;
      flex-direction: column;
    }
    main {
      flex: 1;
    }
  </style>
  <sitemesh:write property="head"/>
</head>
<body class="bg-light">
<%@ include file="/views/common/topbar.jsp" %>

<main class="container py-4">
  <sitemesh:write property="body"/>
</main>

<jsp:include page="/views/common/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<sitemesh:write property="page.script"/>
</body>
</html>
