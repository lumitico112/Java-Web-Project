<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <p>
            <strong>Mensaje:</strong>
            <c:out value="${requestScope.error != null ? requestScope.error : 'Ha ocurrido un error inesperado.'}"/>
        </p>
        <c:if test="${not empty requestScope.stackTrace}">
            <hr />
            <p class="mb-2"><strong>Detalles técnicos (stack trace):</strong></p>
            <pre class="small bg-light p-3 border rounded" style="white-space: pre-wrap;"><c:out value="${requestScope.stackTrace}"/></pre>
        </c:if>
        <hr>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=listar">Volver</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
