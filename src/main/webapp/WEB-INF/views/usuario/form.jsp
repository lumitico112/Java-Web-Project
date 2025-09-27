<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Perfil de Usuario</title>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/perfil.css">
</head>
<body class="bg-coffee-light">
<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header bg-coffee-dark text-white text-center py-4 rounded-top-4">
                    <i class="fas fa-user-cog fa-2x mb-2"></i>
                    <h3 class="mb-0">Mi Perfil</h3>
                </div>

                <div class="card-body p-5">
                    <form id="perfilForm" action="${pageContext.request.contextPath}/usuario?action=actualizar" method="post">
                        <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                        <input type="hidden" name="origen" value="perfil" />

                        <!-- Nombre (solo lectura) -->
                        <div class="mb-4">
                            <label class="form-label fw-bold">
                                <i class="fas fa-user me-2"></i>Nombre
                            </label>
                            <input type="text" class="form-control form-control-lg"
                                   value="${usuario.nombre}" readonly>
                        </div>

                        <!-- Apellido (solo lectura) -->
                        <div class="mb-4">
                            <label class="form-label fw-bold">
                                <i class="fas fa-user-tag me-2"></i>Apellido
                            </label>
                            <input type="text" class="form-control form-control-lg"
                                   value="${usuario.apellido}" readonly>
                        </div>

                        <!-- Correo (solo lectura) -->
                        <div class="mb-4">
                            <label class="form-label fw-bold">
                                <i class="fas fa-envelope me-2"></i>Correo electrónico
                            </label>
                            <input type="email" class="form-control form-control-lg"
                                   value="${usuario.correo}" readonly>
                        </div>

                        <!-- Datos de cliente (solo si es CLIENTE) -->
                        <c:if test="${usuario.rol.nombre eq 'CLIENTE'}">
                            <hr class="my-5">
                            <h5 class="text-coffee-primary mb-4">Información de contacto</h5>

                            <div class="mb-4">
                                <label class="form-label fw-bold">
                                    <i class="fas fa-phone me-2"></i>Teléfono
                                </label>
                                <input type="text" class="form-control form-control-lg" name="telefono"
                                       value="${perfilCliente.telefono}">
                            </div>

                            <div class="mb-4">
                                <label class="form-label fw-bold">
                                    <i class="fas fa-map-marker-alt me-2"></i>Dirección
                                </label>
                                <input type="text" class="form-control form-control-lg" name="direccion"
                                       value="${perfilCliente.direccion}">
                            </div>
                        </c:if>

                        <!-- Botón para cambiar contraseña -->
                        <hr class="my-5">
                        <div class="d-grid">
                            <button type="button" class="btn btn-outline-primary btn-lg" data-bs-toggle="modal" data-bs-target="#cambiarContrasenaModal">
                                <i class="fas fa-lock me-2"></i>Cambiar contraseña
                            </button>
                        </div>

                        <!-- Botones de acción -->
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                            <a href="${pageContext.request.contextPath}/" class="btn btn-outline-secondary btn-lg px-4">
                                <i class="fas fa-arrow-left me-2"></i>Volver
                            </a>
                            <button type="submit" class="btn btn-primary btn-lg px-4">
                                <i class="fas fa-save me-2"></i>Guardar cambios
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal para cambiar contraseña -->
<div class="modal fade" id="cambiarContrasenaModal" tabindex="-1" aria-labelledby="cambiarContrasenaLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cambiarContrasenaLabel">Cambiar contraseña</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="cambiarContrasenaForm" action="${pageContext.request.contextPath}/usuario?action=actualizar" method="post">
                <div class="modal-body">
                    <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                    <div class="mb-3">
                        <label class="form-label">Nueva contraseña</label>
                        <input type="password" class="form-control" name="contrasena" required minlength="8">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Confirmar contraseña</label>
                        <input type="password" class="form-control" id="confirmarContrasena" required minlength="8">
                        <div class="invalid-feedback">Las contraseñas no coinciden.</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
<script src="${pageContext.request.contextPath}/assets/js/perfil.js"></script>
</body>
</html>