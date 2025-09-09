<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title><c:out value="${empty empleado ? 'Nuevo Empleado' : 'Editar Empleado'}"/></title>
    <%@ include file="/WEB-INF/views/includes/head.jspf" %>
</head>
<body>
<div class="container py-4">
    <h1 class="h4 mb-3">
        <c:choose>
            <c:when test="${empty empleado}">Nuevo Empleado</c:when>
            <c:otherwise>Editar Empleado</c:otherwise>
        </c:choose>
    </h1>

    <form method="post" action="${pageContext.request.contextPath}/controller/EmpleadoController">
        <input type="hidden" name="action" value="${empty empleado ? 'insertar' : 'actualizar'}"/>

        <div class="mb-3">
            <label class="form-label" for="empleadoId">ID</label>
            <input type="text" class="form-control" id="empleadoId" name="empleadoId"
                   value="${empleado.empleadoId}" ${empty empleado ? '' : 'readonly'}>
        </div>

        <div class="mb-3">
            <label class="form-label" for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${empleado.nombre}">
        </div>

        <div class="mb-3">
            <label class="form-label" for="usuario">Usuario</label>
            <input type="text" class="form-control" id="usuario" name="usuario" value="${empleado.usuario}">
        </div>

        <div class="mb-3">
            <label class="form-label" for="correo">Correo</label>
            <input type="email" class="form-control" id="correo" name="correo" value="${empleado.correo}">
        </div>

        <div class="mb-3">
            <label class="form-label" for="contrasena">Contraseña</label>
            <input type="password" class="form-control" id="contrasena" name="contrasena" value="">
            <div class="form-text">
                <c:choose>
                    <c:when test="${empty empleado}">Ingresa una contraseña para el nuevo usuario.</c:when>
                    <c:otherwise>Déjala vacía si no deseas cambiarla.</c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label" for="rol">Rol</label>
            <select class="form-select" id="rol" name="rol">
                <option value="ADMIN" ${empleado != null && empleado.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                <option value="EMPLEADO" ${empleado != null && empleado.rol == 'EMPLEADO' ? 'selected' : ''}>EMPLEADO</option>
            </select>
        </div>

        <c:if test="${not empty empleado}">
            <div class="mb-3">
                <label class="form-label" for="estado">Estado</label>
                <select class="form-select" id="estado" name="estado">
                    <option value="true" ${empleado.estado ? 'selected' : ''}>Activo</option>
                    <option value="false" ${empleado.estado ? '' : 'selected'}>Inactivo</option>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label" for="fechaRegistro">Fecha de Registro</label>
                <input type="date" class="form-control" id="fechaRegistro" name="fechaRegistro"
                       value="${empleado.fechaRegistro}">
            </div>
        </c:if>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=listar">Cancelar</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
