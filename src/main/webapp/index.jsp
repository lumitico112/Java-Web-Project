<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Inicio</title>
    <%-- include del head.jspf (activar solo si el archivo existe en /WEB-INF/views/includes/) --%>
    <%@ include file="/WEB-INF/views/includes/head.jspf" %>
</head>
<body>
<div class="container py-5">
    <h1 class="display-5">Hello World!</h1>
    <p class="text-muted">Página de inicio</p>

    <!-- Botón corregido -->
    <a href="${pageContext.request.contextPath}/controller/EmpleadoController?action=nuevo">Ir a Registro</a>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
