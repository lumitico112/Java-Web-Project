<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Registro de Usuarios - Cafetería</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body class="bg-coffee">

<div class="container-fluid min-vh-100 d-flex align-items-center justify-content-center">
    <div class="row w-100 justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="card shadow-lg border-0 rounded-4">

                <!-- Header -->
                <div class="card-header bg-primary text-white text-center py-4 rounded-top-4">
                    <i class="fas fa-coffee fa-3x mb-3"></i>
                    <h2 class="mb-0">Registro de Usuarios</h2>
                    <p class="mb-0 opacity-75">Cafetería El Aroma</p>
                </div>

                <!-- Formulario -->
                <div class="card-body p-5">
                    <form action="${pageContext.request.contextPath}/usuario" method="post" id="registroForm">
                        <input type="hidden" name="action" value="insertar">

                        <!-- Nombre -->
                        <div class="mb-4">
                            <label for="nombre" class="form-label fw-bold">
                                <i class="fas fa-user me-2"></i>Nombre
                            </label>
                            <input type="text" class="form-control form-control-lg"
                                   id="nombre" name="nombre"
                                   placeholder="Ingresa tu nombre"
                                   maxlength="50" required>
                        </div>

                        <!-- Apellido -->
                        <div class="mb-4">
                            <label for="apellido" class="form-label fw-bold">
                                <i class="fas fa-user-tag me-2"></i>Apellido
                            </label>
                            <input type="text" class="form-control form-control-lg"
                                   id="apellido" name="apellido"
                                   placeholder="Ingresa tu apellido"
                                   maxlength="50" required>
                        </div>

                        <!-- Correo -->
                        <div class="mb-4">
                            <label for="correo" class="form-label fw-bold">
                                <i class="fas fa-envelope me-2"></i>Correo Electrónico
                            </label>
                            <input type="email" class="form-control form-control-lg"
                                   id="correo" name="correo"
                                   placeholder="usuario@ejemplo.com"
                                   maxlength="50" required>
                        </div>

                        <!-- Contraseña -->
                        <div class="mb-4">
                            <label for="contrasena" class="form-label fw-bold">
                                <i class="fas fa-lock me-2"></i>Contraseña
                            </label>
                            <div class="input-group">
                                <input type="password" class="form-control form-control-lg"
                                       id="contrasena" name="contrasena"
                                       placeholder="Contraseña segura" required>
                                <button class="btn btn-outline-secondary"
                                        type="button" id="togglePassword">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </div>
                            <div class="form-text">Mínimo 8 caracteres</div>
                        </div>

                        <!-- Confirmar Contraseña -->
                        <div class="mb-4">
                            <label for="confirmarContrasena" class="form-label fw-bold">
                                <i class="fas fa-lock me-2"></i>Confirmar Contraseña
                            </label>
                            <input type="password" class="form-control form-control-lg"
                                   id="confirmarContrasena" name="confirmarContrasena"
                                   placeholder="Confirma la contraseña" required>
                        </div>

                        <!-- Rol -->
                        <div class="mb-4">
                            <label for="idRol" class="form-label fw-bold">
                                <i class="fas fa-user-tag me-2"></i>Rol
                            </label>
                            <select class="form-select form-select-lg"
                                    id="idRol" name="idRol" required>
                                <option value="">Selecciona un rol</option>
                                <option value="1">Empleado</option>
                                <option value="2">Administrador</option>
                            </select>
                        </div>

                        <!-- Estado (por defecto activo en el controller) -->
                        <input type="hidden" name="estado" value="ACTIVO">

                        <!-- Botones -->
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button type="reset" class="btn btn-outline-secondary btn-lg me-md-2">
                                <i class="fas fa-undo me-2"></i>Limpiar
                            </button>
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="fas fa-user-plus me-2"></i>Registrar Usuario
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Footer -->
                <div class="card-footer text-center py-3 bg-light rounded-bottom-4">
                    <small class="text-muted">
                        <i class="fas fa-shield-alt me-1"></i>
                        Todos los datos son confidenciales y seguros
                    </small>
                </div>

            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>
