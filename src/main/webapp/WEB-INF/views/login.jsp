<%--
  Created by IntelliJ IDEA.
  User: luism
  Date: 8/09/2025
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Cafetería El Aroma</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<!-- Navigation Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-coffee-dark fixed-top">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/index.jsp#inicio">
            <i class="fas fa-coffee me-2"></i>
            Cafetería El Aroma
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#inicio">
                        <i class="fas fa-home me-1"></i>Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#productos">
                        <i class="fas fa-coffee me-1"></i>Productos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#nosotros">
                        <i class="fas fa-users me-1"></i>Nosotros
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#contacto">
                        <i class="fas fa-envelope me-1"></i>Contacto
                    </a>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/controller/LoginController?action=login">
                        <i class="fas fa-sign-in-alt me-1"></i>Login
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/LoginController?action=registro">
                        <i class="fas fa-user-plus me-1"></i>Registro Empleados
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Login Section -->
<section class="login-section min-vh-100 d-flex align-items-center">
    <div class="login-overlay w-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-5 col-md-7 col-sm-9">
                    <div class="login-card">
                        <!-- Login Header -->
                        <div class="login-header text-center">
                            <div class="login-icon-container">
                                <i class="fas fa-coffee login-main-icon"></i>
                            </div>
                            <h2 class="fw-bold text-coffee-dark">Bienvenido de vuelta</h2>
                            <p class="text-coffee-primary">Ingresa a tu cuenta de Cafetería El Aroma</p>
                        </div>

                        <!-- Login Form -->
                        <form id="loginForm" class="login-form">
                            <!-- Usuario o Email -->
                            <div class="mb-4">
                                <label for="loginUser" class="form-label fw-bold">
                                    <i class="fas fa-user me-2"></i>Usuario o Email
                                </label>
                                <div class="input-group">
                                        <span class="input-group-text">
                                            <i class="fas fa-at"></i>
                                        </span>
                                    <input type="text"
                                           class="form-control form-control-lg"
                                           id="loginUser"
                                           name="loginUser"
                                           placeholder="Ingresa tu usuario o email"
                                           required>
                                </div>
                            </div>

                            <!-- Contraseña -->
                            <div class="mb-4">
                                <label for="loginPassword" class="form-label fw-bold">
                                    <i class="fas fa-lock me-2"></i>Contraseña
                                </label>
                                <div class="input-group">
                                        <span class="input-group-text">
                                            <i class="fas fa-key"></i>
                                        </span>
                                    <input type="password"
                                           class="form-control form-control-lg"
                                           id="loginPassword"
                                           name="loginPassword"
                                           placeholder="Tu contraseña"
                                           required>
                                    <button class="btn btn-outline-secondary"
                                            type="button"
                                            id="toggleLoginPassword">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </div>
                            </div>

                            <!-- Recordar sesión -->
                            <div class="mb-4">
                                <div class="form-check">
                                    <input class="form-check-input"
                                           type="checkbox"
                                           id="rememberMe"
                                           name="rememberMe">
                                    <label class="form-check-label" for="rememberMe">
                                        <i class="fas fa-memory me-2"></i>Recordar mi sesión
                                    </label>
                                </div>
                            </div>

                            <!-- Botón de Login -->
                            <div class="d-grid mb-4">
                                <button type="submit" class="btn btn-primary btn-lg">
                                    <i class="fas fa-sign-in-alt me-2"></i>Iniciar Sesión
                                </button>
                            </div>

                            <!-- Enlaces adicionales -->
                            <div class="text-center">
                                <a href="#" class="text-coffee-primary text-decoration-none small">
                                    <i class="fas fa-question-circle me-1"></i>¿Olvidaste tu contraseña?
                                </a>
                            </div>
                        </form>

                        <!-- Login Footer -->
                        <div class="login-footer">
                            <div class="text-center">
                                <hr class="my-4">
                                <p class="text-muted small mb-3">
                                    <i class="fas fa-info-circle me-1"></i>
                                    ¿Eres empleado nuevo?
                                </p>
                                <a href="${pageContext.request.contextPath}/controller/LoginController?action=registro" class="btn btn-outline-coffee">
                                    <i class="fas fa-user-plus me-2"></i>Registrar Empleado
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Info Cards Row -->
            <div class="row mt-5">
                <div class="col-md-4 mb-3">
                    <div class="info-card text-center">
                        <div class="info-icon">
                            <i class="fas fa-shield-alt"></i>
                        </div>
                        <h6 class="fw-bold">Seguro</h6>
                        <small class="text-muted">Tus datos están protegidos</small>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="info-card text-center">
                        <div class="info-icon">
                            <i class="fas fa-clock"></i>
                        </div>
                        <h6 class="fw-bold">Rápido</h6>
                        <small class="text-muted">Acceso inmediato al sistema</small>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="info-card text-center">
                        <div class="info-icon">
                            <i class="fas fa-users"></i>
                        </div>
                        <h6 class="fw-bold">Equipo</h6>
                        <small class="text-muted">Únete al equipo El Aroma</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>