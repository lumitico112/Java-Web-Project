<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Empleados</title>
    <%@ include file="/WEB-INF/views/includes/head.jspf" %>
</head>
<body>
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h3 m-0">Lista de Empleados</h1>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=nuevo">Nuevo</a>
    </div>

    <c:if test="${empty empleados}">
        <div class="alert alert-info">No hay empleados registrados.</div>
    </c:if>

    <c:if test="${not empty empleados}">
        <div class="table-responsive">
            <table class="table table-striped align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Usuario</th>
                    <th>Correo</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Fecha Registro</th>
                    <th style="width: 160px;">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="e" items="${empleados}">
                    <tr>
                        <td>${e.empleadoId}</td>
                        <td>${e.nombre}</td>
                        <td>${e.usuario}</td>
                        <td>${e.correo}</td>
                        <td>${e.rol}</td>
                        <td>
                            <span class="badge ${e.estado ? 'bg-success' : 'bg-secondary'}">
                                ${e.estado ? 'Activo' : 'Inactivo'}
                            </span>
                        </td>
                        <td>${e.fechaRegistro}</td>
                        <td>
                            <a class="btn btn-sm btn-outline-secondary"
                               href="${pageContext.request.contextPath}/controller/EmpleadoController?action=editar&id=${e.empleadoId}">
                                Editar
                            </a>
                            <a class="btn btn-sm btn-outline-danger"
                               href="${pageContext.request.contextPath}/controller/EmpleadoController?action=eliminar&id=${e.empleadoId}"
                               onclick="return confirm('¿Eliminar empleado?');">
                                Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
