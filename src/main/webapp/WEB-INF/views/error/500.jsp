<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 24/09/2025
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>500 - Error interno del servidor</title>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
</head>
<body class="bg-light">
<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container py-5">
    <div class="alert alert-danger shadow-lg rounded text-center">
        <h4 class="alert-heading">
            <i class="fa-solid fa-triangle-exclamation me-2"></i>
            Error interno del servidor
        </h4>
        <p class="mb-3">
            <strong>¡Ups! Algo salió mal en nuestro servidor.</strong><br>
            Estamos trabajando para solucionarlo lo antes posible.
        </p>
        <hr>
        <div class="d-flex justify-content-between">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">🏠 Ir al inicio</a>
            <a class="btn btn-outline-danger" href="javascript:history.back()">⬅ Volver</a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>