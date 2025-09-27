<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Error</title>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="alert alert-danger shadow-lg rounded">
        <h4 class="alert-heading">
            <i class="fa-solid fa-triangle-exclamation me-2"></i>
            Ocurrió un problema
        </h4>
        <p class="mb-3">
            <strong>Mensaje:</strong>
            <c:out value="${requestScope.error != null ? requestScope.error : 'Ha ocurrido un error inesperado.'}"/>
        </p>

        <!-- 🔹 Mostrar detalles solo si estamos en modo "debug" -->
        <c:if test="${not empty requestScope.stackTrace}">
            <div class="accordion" id="errorDetails">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            Ver detalles técnicos
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#errorDetails">
                        <div class="accordion-body">
                            <pre class="small bg-light p-3 border rounded" style="white-space: pre-wrap;">
                                <c:out value="${requestScope.stackTrace}"/>
                            </pre>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <hr>
        <div class="d-flex justify-content-between">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">🏠 Ir al inicio</a>
            <a class="btn btn-outline-danger" href="javascript:history.back()">⬅ Volver</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
