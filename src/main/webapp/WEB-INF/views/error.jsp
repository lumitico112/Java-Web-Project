<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Error</title>
    <%@ include file="/WEB-INF/views/includes/head.jspf" %>
</head>
<body>
<div class="container py-5">
    <div class="alert alert-danger">
        <h4 class="alert-heading">Se produjo un error</h4>
        <p>${requestScope.error != null ? requestScope.error : 'Ha ocurrido un error inesperado.'}</p>
        <hr>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=listar">Volver</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
