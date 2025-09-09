<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Empleados</title>
    <%@ include file="/WEB-INF/views/includes/head.jspf" %>
</head>
<body>
<div class="container py-4">
    <h1 class="h3">Listado de Empleados</h1>
    <p class="text-muted">Vista en construcción.</p>
    <div class="mt-3 d-flex gap-2">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=nuevo">Registrar nuevo</a>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=login">Ir a Login</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
