<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="/WEB-INF/views/jspf/head.jspf" %>
    <title>Cafetería El Aroma - Inicio</title>
    <!-- CSS específico del index -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
</head>
<body>

<%@ include file="/WEB-INF/views/jspf/navbar.jspf" %>

<!-- Hero Section -->
<section id="inicio" class="hero-section">
    <div class="hero-overlay">
        <div class="container">
            <div class="row align-items-center min-vh-100">
                <div class="col-lg-6">
                    <div class="hero-content text-white">
                        <h1 class="display-3 fw-bold mb-4">
                            Bienvenidos a
                            <span class="text-coffee-accent">El Aroma</span>
                        </h1>
                        <p class="lead mb-4">
                            Donde cada taza cuenta una historia y cada aroma despierta tus sentidos.
                            Disfruta de la mejor experiencia cafetera en un ambiente acogedor y familiar.
                        </p>
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

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>

</body>
</html>
