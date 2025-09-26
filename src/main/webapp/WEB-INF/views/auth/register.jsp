<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Registro de Usuario - Cafetería</title>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>

<body class="login-page">
<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container-fluid min-vh-100 d-flex align-items-center justify-content-center bg-coffee">
    <div class="row w-100 justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header bg-primary text-white text-center py-4 rounded-top-4">
                    <i class="fas fa-user-plus fa-3x mb-3"></i>
                    <h2 class="mb-0">Registro de Usuario</h2>
                    <p class="mb-0 opacity-75">Cafetería El Aroma</p>
                </div>

                <div class="card-body p-5">
                    <!-- Mensaje de error si viene de la request -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center">
                                ${error}
                        </div>
                    </c:if>

                    <!-- Formulario de registro -->
                    <form id="registroForm"
                          action="${pageContext.request.contextPath}/registro"
                          method="post" novalidate>
                        <input type="hidden" name="action" value="insertar"/>

                        <!-- Nombre -->
                        <div class="mb-4">
                            <label for="nombre" class="form-label fw-bold">
                                <i class="fas fa-user me-2"></i>Nombre
                            </label>
                            <input type="text" class="form-control form-control-lg"
                                   id="nombre" name="nombre"
                                   placeholder="Ingresa tu nombre"
                                   required maxlength="50">
                            <div class="invalid-feedback">El nombre es obligatorio.</div>
                        </div>

                        <!-- Apellido -->
                        <div class="mb-4">
                            <label for="apellido" class="form-label fw-bold">
                                <i class="fas fa-user-tag me-2"></i>Apellido
                            </label>
                            <input type="text" class="form-control form-control-lg"
                                   id="apellido" name="apellido"
                                   placeholder="Ingresa tu apellido"
                                   required maxlength="50">
                            <div class="invalid-feedback">El apellido es obligatorio.</div>
                        </div>

                        <!-- Correo -->
                        <div class="mb-4">
                            <label for="correo" class="form-label fw-bold">
                                <i class="fas fa-envelope me-2"></i>Correo electrónico
                            </label>
                            <input type="email" class="form-control form-control-lg"
                                   id="correo" name="correo"
                                   placeholder="usuario@ejemplo.com"
                                   required maxlength="80">
                            <div class="invalid-feedback">Introduce un correo válido.</div>
                        </div>

                        <!-- Contraseña -->
                        <div class="mb-4">
                            <label for="contrasena" class="form-label fw-bold">
                                <i class="fas fa-lock me-2"></i>Contraseña
                            </label>
                            <div class="input-group">
                                <input type="password" class="form-control form-control-lg"
                                       id="contrasena" name="contrasena"
                                       placeholder="Crea una contraseña segura"
                                       required minlength="8">
                                <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </div>
                            <div class="form-text">Debe tener al menos 8 caracteres.</div>
                        </div>

                        <!-- Confirmar Contraseña -->
                        <div class="mb-4">
                            <label for="confirmarContrasena" class="form-label fw-bold">
                                <i class="fas fa-lock me-2"></i>Confirmar Contraseña
                            </label>
                            <input type="password" class="form-control form-control-lg"
                                   id="confirmarContrasena"
                                   placeholder="Repite la contraseña"
                                   required>
                            <div class="invalid-feedback">Las contraseñas no coinciden.</div>
                        </div>

                        <!-- Botón de envío -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="fas fa-user-plus me-2"></i> Registrarse
                            </button>
                        </div>
                    </form>
                </div>

                <div class="card-footer text-center py-3 bg-light rounded-bottom-4">
                    <small class="text-muted">
                        <i class="fas fa-shield-alt me-1"></i>
                        Tus datos estarán protegidos y son confidenciales
                    </small>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>

<!-- JS de validación -->
<script src="${pageContext.request.contextPath}/assets/js/register.js"></script>
</body>
</html>
