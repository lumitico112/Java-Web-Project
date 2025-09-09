<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Empleados - Cafetería</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="container-fluid min-vh-100 d-flex align-items-center justify-content-center bg-coffee">
    <div class="row w-100 justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header bg-primary text-white text-center py-4 rounded-top-4">
                    <i class="fas fa-coffee fa-3x mb-3"></i>
                    <h2 class="mb-0">Registro de Empleados</h2>
                    <p class="mb-0 opacity-75">Cafetería El Aroma</p>
                </div>

                <div class="card-body p-5">
                    <form action="${pageContext.request.contextPath}/controller/EmpleadoController" method="post" id="registroForm">
                        <input type="hidden" name="action" value="insertar">
                        <!-- ID Empleado -->
                        <div class="mb-4">
                            <label for="empleadoId" class="form-label fw-bold">
                                <i class="fas fa-id-badge me-2"></i>ID Empleado
                            </label>
                            <input type="text"
                                   class="form-control form-control-lg"
                                   id="empleadoId"
                                   name="empleadoId"
                                   placeholder="Ej: EMP001"
                                   maxlength="10"
                                   required>
                            <div class="form-text">Máximo 10 caracteres</div>
                        </div>

                        <!-- Nombre -->
                        <div class="mb-4">
                            <label for="nombre" class="form-label fw-bold">
                                <i class="fas fa-user me-2"></i>Nombre Completo
                            </label>
                            <input type="text"
                                   class="form-control form-control-lg"
                                   id="nombre"
                                   name="nombre"
                                   placeholder="Ingresa el nombre completo"
                                   maxlength="50"
                                   required>
                        </div>

                        <!-- Usuario -->
                        <div class="mb-4">
                            <label for="usuario" class="form-label fw-bold">
                                <i class="fas fa-at me-2"></i>Usuario
                            </label>
                            <input type="text"
                                   class="form-control form-control-lg"
                                   id="usuario"
                                   name="usuario"
                                   placeholder="Nombre de usuario único"
                                   maxlength="20"
                                   required>
                            <div class="form-text">Máximo 20 caracteres, debe ser único</div>
                        </div>

                        <!-- Correo -->
                        <div class="mb-4">
                            <label for="correo" class="form-label fw-bold">
                                <i class="fas fa-envelope me-2"></i>Correo Electrónico
                            </label>
                            <input type="email"
                                   class="form-control form-control-lg"
                                   id="correo"
                                   name="correo"
                                   placeholder="usuario@ejemplo.com"
                                   maxlength="50"
                                   required>
                        </div>

                        <!-- Contraseña -->
                        <div class="mb-4">
                            <label for="contrasena" class="form-label fw-bold">
                                <i class="fas fa-lock me-2"></i>Contraseña
                            </label>
                            <div class="input-group">
                                <input type="password"
                                       class="form-control form-control-lg"
                                       id="contrasena"
                                       name="contrasena"
                                       placeholder="Contraseña segura"
                                       required>
                                <button class="btn btn-outline-secondary"
                                        type="button"
                                        id="togglePassword">
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
                            <input type="password"
                                   class="form-control form-control-lg"
                                   id="confirmarContrasena"
                                   name="confirmarContrasena"
                                   placeholder="Confirma la contraseña"
                                   required>
                        </div>

                        <!-- Rol -->
                        <div class="mb-4">
                            <label for="rol" class="form-label fw-bold">
                                <i class="fas fa-user-tag me-2"></i>Rol
                            </label>
                            <select class="form-select form-select-lg"
                                    id="rol"
                                    name="rol"
                                    required>
                                <option value="">Selecciona un rol</option>
                                <option value="EMPLEADO">Empleado</option>
                                <option value="ADMINISTRADOR">Administrador</option>
                            </select>
                        </div>

                        <!-- Estado -->
                        <div class="mb-4">
                            <div class="form-check form-switch">
                                <input class="form-check-input"
                                       type="checkbox"
                                       id="estado"
                                       name="estado"
                                       checked>
                                <label class="form-check-label fw-bold" for="estado">
                                    <i class="fas fa-toggle-on me-2"></i>Empleado Activo
                                </label>
                            </div>
                        </div>

                        <!-- Botones -->
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button type="reset" class="btn btn-outline-secondary btn-lg me-md-2">
                                <i class="fas fa-undo me-2"></i>Limpiar
                            </button>
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="fas fa-user-plus me-2"></i>Registrar Empleado
                            </button>
                        </div>
                    </form>
                </div>

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

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>