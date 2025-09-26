<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Cafetería El Aroma - Iniciar Sesión</title>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
    <!-- CSS exclusivo para login & registro -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>
<body class="login-page">

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<!-- Contenedor de mensajes globales -->
<c:if test="${not empty mensajeExito}">
    <div class="alert alert-success text-center mb-0">
            ${mensajeExito}
    </div>
</c:if>

<!-- Login Section -->
<section class="login-section min-vh-100 d-flex align-items-center pt-5">
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

                        <!-- Mensaje de registro exitoso -->
                        <c:if test="${param.registration == 'success'}">
                            <div class="alert alert-success">
                                <i class="fas fa-check-circle me-2"></i>
                                ¡Usuario registrado exitosamente!
                            </div>
                        </c:if>

                        <!-- Mensajes de error -->
                        <c:if test="${not empty loginErrorUsuario}">
                            <div class="alert alert-warning">
                                <i class="fas fa-user-times me-2"></i>
                                <c:out value="${loginErrorUsuario}"/>
                            </div>
                        </c:if>
                        <c:if test="${not empty loginErrorContrasena}">
                            <div class="alert alert-danger">
                                <i class="fas fa-lock me-2"></i>
                                <c:out value="${loginErrorContrasena}"/>
                            </div>
                        </c:if>
                        <c:if test="${not empty loginError}">
                            <div class="alert alert-danger">
                                <i class="fas fa-exclamation-triangle me-2"></i>
                                <c:out value="${loginError}"/>
                            </div>
                        </c:if>

                        <!-- Login Form -->
                        <form id="loginForm"
                              action="${pageContext.request.contextPath}/login"
                              method="post" novalidate>
                            <input type="hidden" name="action" value="login"/>
                            <div class="mb-4">

                                <label for="loginUser" class="form-label fw-bold">
                                    <i class="fas fa-user me-2"></i>Usuario o Email
                                </label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-at"></i></span>
                                    <input type="text" class="form-control form-control-lg" id="loginUser" name="loginUser" placeholder="Ingresa tu usuario o email" required>
                                </div>
                            </div>
                            <div class="mb-4">
                                <label for="loginPassword" class="form-label fw-bold">
                                    <i class="fas fa-lock me-2"></i>Contraseña
                                </label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    <input type="password" class="form-control form-control-lg" id="loginPassword" name="loginPassword" placeholder="Tu contraseña" required>
                                    <button class="btn btn-outline-secondary" type="button" id="toggleLoginPassword">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="mb-4 form-check">
                                <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe">
                                <label class="form-check-label" for="rememberMe">
                                    <i class="fas fa-memory me-2"></i>Recordar mi sesión
                                </label>
                            </div>
                            <div class="d-grid mb-4">
                                <button type="submit" class="btn btn-primary btn-lg">
                                    <i class="fas fa-sign-in-alt me-2"></i>Iniciar Sesión
                                </button>
                            </div>
                            <div class="text-center">
                                <a href="#" class="text-coffee-primary text-decoration-none small">
                                    <i class="fas fa-question-circle me-1"></i>¿Olvidaste tu contraseña?
                                </a>
                            </div>
                        </form>

                        <!-- Footer del login -->
                        <div class="login-footer text-center">
                            <hr class="my-4">
                            <p class="text-muted small mb-3">
                                <i class="fas fa-info-circle me-1"></i>¿Eres usuario nuevo?
                            </p>
                            <a href="${pageContext.request.contextPath}/registro" class="btn btn-outline-coffee">
                                <i class="fas fa-user-plus me-2"></i>Registrar Usuario
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Info Cards -->
            <div class="row mt-5">
                <div class="col-md-4 mb-3">
                    <div class="info-card text-center">
                        <i class="fas fa-shield-alt"></i>
                        <h6 class="fw-bold">Seguro</h6>
                        <small class="text-muted">Tus datos están protegidos</small>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="info-card text-center">
                        <i class="fas fa-clock"></i>
                        <h6 class="fw-bold">Rápido</h6>
                        <small class="text-muted">Acceso inmediato al sistema</small>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="info-card text-center">
                        <i class="fas fa-users"></i>
                        <h6 class="fw-bold">Equipo</h6>
                        <small class="text-muted">Únete al equipo El Aroma</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>

<!-- JS exclusivo de login -->
<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>
</body>
</html>
