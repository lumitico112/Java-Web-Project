<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cafetería El Aroma - Inicio</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<!-- Navigation Header -->
<!-- Navigation Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-coffee-dark fixed-top">
    <div class="container">
        <!-- Logo / marca -->
        <a class="navbar-brand fw-bold" href="#">
            <i class="fas fa-coffee me-2"></i>
            Cafetería El Aroma
        </a>

        <!-- Botón para dispositivos móviles -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Menú colapsable -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- Menú principal -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="#inicio">
                        <i class="fas fa-home me-1"></i>Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#productos">
                        <i class="fas fa-coffee me-1"></i>Productos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#nosotros">
                        <i class="fas fa-users me-1"></i>Nosotros
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#contacto">
                        <i class="fas fa-envelope me-1"></i>Contacto
                    </a>
                </li>
            </ul>

            <!-- Menú de usuario / acciones -->
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/LoginController?action=login">
                        <i class="fas fa-user me-1"></i>Login
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller/EmpleadoController?action=nuevo">
                        <i class="fas fa-user-plus me-1"></i>Registro Empleados
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<section id="inicio" class="hero-section">
    <div class="hero-overlay">
        <div class="container">
            <div class="row align-items-center min-vh-100">
                <div class="col-lg-6">
                    <div class="hero-content text-white text-center py-5">
                        <!-- Título principal -->
                        <h1 class="display-3 fw-bold mb-4">
                            Bienvenidos a <span class="text-coffee-accent">El Aroma</span>
                        </h1>

                        <!-- Subtítulo / descripción -->
                        <p class="lead mb-4">
                            Donde cada taza cuenta una historia y cada aroma despierta tus sentidos.<br>
                            Disfruta de la mejor experiencia cafetera en un ambiente acogedor y familiar.
                        </p>

                        <!-- Botones de acción -->
                        <div class="hero-buttons">
                            <a href="#productos" class="btn btn-primary btn-lg me-3">
                                <i class="fas fa-coffee me-2"></i>Ver Productos
                            </a>
                            <a href="#nosotros" class="btn btn-outline-light btn-lg">
                                <i class="fas fa-info-circle me-2"></i>Conoce más
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="hero-image text-center">
                        <i class="fas fa-mug-hot hero-icon"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Features Section -->
<section class="py-5 bg-light">
    <div class="container">
        <div class="row text-center">
            <div class="col-md-4 mb-4">
                <div class="feature-card">
                    <div class="feature-icon">
                        <i class="fas fa-seedling"></i>
                    </div>
                    <h4 class="fw-bold">Café Premium</h4>
                    <p class="text-muted">
                        Granos seleccionados de las mejores fincas,
                        tostados artesanalmente para garantizar el mejor sabor.
                    </p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="feature-card">
                    <div class="feature-icon">
                        <i class="fas fa-heart"></i>
                    </div>
                    <h4 class="fw-bold">Ambiente Acogedor</h4>
                    <p class="text-muted">
                        Un espacio diseñado para relajarte, trabajar o
                        compartir momentos especiales con amigos y familia.
                    </p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="feature-card">
                    <div class="feature-icon">
                        <i class="fas fa-clock"></i>
                    </div>
                    <h4 class="fw-bold">Servicio Rápido</h4>
                    <p class="text-muted">
                        Atención personalizada y eficiente para que disfrutes
                        tu café sin esperas innecesarias.
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- About Section -->
<section id="nosotros" class="py-5">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 mb-4">
                <h2 class="display-5 fw-bold text-coffee-dark">Nuestra Historia</h2>
                <p class="lead text-coffee-primary">
                    Desde 2020, hemos sido el refugio perfecto para los amantes del café.
                </p>
                <p>
                    En Cafetería El Aroma creemos que cada taza de café es una experiencia única.
                    Nuestro compromiso es ofrecerte no solo el mejor café, sino también un lugar
                    donde puedas sentirte como en casa.
                </p>
                <p>
                    Trabajamos con productores locales para garantizar la frescura y calidad
                    de nuestros productos, mientras apoyamos a la comunidad cafetera.
                </p>
                <div class="stats-row mt-4">
                    <div class="row text-center">
                        <div class="col-4">
                            <h3 class="text-coffee-primary fw-bold">5+</h3>
                            <small class="text-muted">Años de experiencia</small>
                        </div>
                        <div class="col-4">
                            <h3 class="text-coffee-primary fw-bold">1000+</h3>
                            <small class="text-muted">Clientes satisfechos</small>
                        </div>
                        <div class="col-4">
                            <h3 class="text-coffee-primary fw-bold">20+</h3>
                            <small class="text-muted">Variedades de café</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="about-image text-center">
                    <i class="fas fa-store about-icon"></i>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Products Preview Section -->
<section id="productos" class="py-5 bg-coffee-light">
    <div class="container">
        <div class="text-center mb-5">
            <h2 class="display-5 fw-bold text-coffee-dark">Nuestros Productos</h2>
            <p class="lead text-coffee-primary">Descubre nuestra selección de cafés y acompañamientos</p>
        </div>

        <div class="row">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="product-preview-card">
                    <div class="product-icon">
                        <i class="fas fa-coffee"></i>
                    </div>
                    <h4 class="fw-bold">Cafés Especiales</h4>
                    <p class="text-muted">
                        Americano, Espresso, Cappuccino, Latte y más.
                        Preparados con técnicas baristas profesionales.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="product-preview-card">
                    <div class="product-icon">
                        <i class="fas fa-cookie-bite"></i>
                    </div>
                    <h4 class="fw-bold">Repostería</h4>
                    <p class="text-muted">
                        Pasteles, galletas, muffins y postres artesanales
                        perfectos para acompañar tu café favorito.
                    </p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="product-preview-card">
                    <div class="product-icon">
                        <i class="fas fa-leaf"></i>
                    </div>
                    <h4 class="fw-bold">Bebidas Frías</h4>
                    <p class="text-muted">
                        Frappés, café helado, smoothies y bebidas refrescantes
                        para cualquier momento del día.
                    </p>
                </div>
            </div>
        </div>

        <div class="text-center mt-4">
            <p class="text-muted mb-3">¿Quieres ver toda nuestra carta?</p>
            <a href="#" class="btn btn-primary btn-lg">
                <i class="fas fa-list me-2"></i>Ver Carta Completa
            </a>
        </div>
    </div>
</section>

<!-- Contact Section -->
<section id="contacto" class="py-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto text-center">
                <h2 class="display-5 fw-bold text-coffee-dark mb-4">Visítanos</h2>
                <p class="lead text-coffee-primary mb-5">
                    Te esperamos en nuestro acogedor local para que disfrutes la mejor experiencia cafetera
                </p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="contact-card text-center">
                    <div class="contact-icon">
                        <i class="fas fa-map-marker-alt"></i>
                    </div>
                    <h5 class="fw-bold">Ubicación</h5>
                    <p class="text-muted">
                        Calle Principal #123<br>
                        Centro, Ciudad<br>
                        CP 12345
                    </p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="contact-card text-center">
                    <div class="contact-icon">
                        <i class="fas fa-clock"></i>
                    </div>
                    <h5 class="fw-bold">Horarios</h5>
                    <p class="text-muted">
                        Lunes a Viernes: 7:00 AM - 8:00 PM<br>
                        Sábados: 8:00 AM - 9:00 PM<br>
                        Domingos: 9:00 AM - 7:00 PM
                    </p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="contact-card text-center">
                    <div class="contact-icon">
                        <i class="fas fa-phone"></i>
                    </div>
                    <h5 class="fw-bold">Contacto</h5>
                    <p class="text-muted">
                        Teléfono: (555) 123-4567<br>
                        Email: info@elaroma.com<br>
                        WhatsApp: (555) 987-6543
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="bg-coffee-dark text-white py-4">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-6">
                <div class="d-flex align-items-center">
                    <i class="fas fa-coffee me-2"></i>
                    <span class="fw-bold">Cafetería El Aroma</span>
                </div>
                <small class="text-muted">Donde cada taza cuenta una historia</small>
            </div>
            <div class="col-md-6 text-md-end">
                <div class="social-links">
                    <a href="#" class="text-white me-3">
                        <i class="fab fa-facebook"></i>
                    </a>
                    <a href="#" class="text-white me-3">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a href="#" class="text-white me-3">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a href="#" class="text-white">
                        <i class="fab fa-whatsapp"></i>
                    </a>
                </div>
                <small class="text-muted d-block mt-2">
                    © 2025 Cafetería El Aroma. Todos los derechos reservados.
                </small>
            </div>
        </div>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>


