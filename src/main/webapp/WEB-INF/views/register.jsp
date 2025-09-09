<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Crear Cuenta - Café Alma</title>
    <%@ include file="includes/head.jspf" %>
</head>
<body class="bg-light">

<div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 px-0">
    <div class="card shadow-lg w-100 max-w-400 mx-3">
        <div class="card-body p-5 text-center">

            <!-- Icono y título -->
            <div class="mb-4">
                <i class="fas fa-user-plus fa-3x text-muted"></i>
            </div>
            <h2 class="fw-bold mb-2">Crear Cuenta</h2>
            <p class="text-muted mb-5">Únete a la comunidad de Café Alma</p>

            <!-- Formulario de registro -->
            <form id="registroForm" action="${pageContext.request.contextPath}/controller/EmpleadoController?action=insertar" method="POST">

                <!-- Nombre completo -->
                <div class="mb-4">
                    <label for="nombre" class="form-label fw-semibold">Nombre completo</label>
                    <input type="text" class="form-control rounded-4" id="nombre" name="nombre" required>
                </div>

                <!-- Usuario -->
                <div class="mb-4">
                    <label for="usuario" class="form-label fw-semibold">Usuario</label>
                    <input type="text" class="form-control rounded-4" id="usuario" name="usuario" required>
                </div>

                <!-- Correo electrónico -->
                <div class="mb-4">
                    <label for="correo" class="form-label fw-semibold">Correo electrónico</label>
                    <input type="email" class="form-control rounded-4" id="correo" name="correo" required>
                </div>

                <!-- Contraseña -->
                <div class="mb-4">
                    <label for="contrasena" class="form-label fw-semibold">Contraseña</label>
                    <input type="password" class="form-control rounded-4" id="contrasena" name="contrasena" required>
                </div>

                <!-- Rol oculto -->
                <input type="hidden" name="rol" value="EMPLEADO">

                <!-- Botón de registro -->
                <button type="submit" class="btn btn-dark btn-lg w-100 rounded-4 fw-bold">
                    Registrarse
                </button>
            </form>

<%--            <!-- Link EmpleadoController login -->--%>
<%--&lt;%&ndash;            <div class="mt-4 text-center">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <small class="text-muted">¿Ya tienes cuenta?&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <a href="${pageContext.request.contextPath}/LoginController" class="text-decoration-none">Inicia sesión</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </small>&ndash;%&gt;--%>
<%--            </div>--%>

        </div>
    </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>
