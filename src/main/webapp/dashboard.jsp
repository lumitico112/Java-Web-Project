<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Validar que el usuario sea ADMIN -->
<c:if test="${sessionScope.usuario == null || sessionScope.usuario.rol.idRol != 1}">
    <c:redirect url="/login" />
</c:if>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Dashboard - Admin</title>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
    <!-- CSS específico del dashboard -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container-fluid mt-5">
    <div class="row">
        <%@ include file="/WEB-INF/views/jspf/sidebar-offcanvas.jspf" %>
        <div class="col-md-9">
            <div class="card shadow-sm">
                <div class="card-header bg-coffee-dark text-white">
                    <h4 class="mb-0">Panel de Administración</h4>
                </div>
                <div class="card-body">
                    <p>¡Bienvenido, <strong>${sessionScope.usuario.nombre}</strong>!</p>
                    <p>Desde aquí puedes gestionar usuarios, productos, pedidos y reportes.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>

</body>
</html>