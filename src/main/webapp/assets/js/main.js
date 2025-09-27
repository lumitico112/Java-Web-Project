// JavaScript para la página principal de la cafetería

document.addEventListener('DOMContentLoaded', function() {
    // Smooth scrolling para los enlaces de navegación
    const navLinks = document.querySelectorAll('a[href^="#"]');

    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            const targetId = this.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                e.preventDefault();
                const offsetTop = targetElement.offsetTop - 80; // Ajuste para navbar fijo

                window.scrollTo({
                    top: offsetTop,
                    behavior: 'smooth'
                });
            }
        });
    });

    // Actualizar navegación activa al hacer scroll
    const sections = document.querySelectorAll('section[id]');
    const navItems = document.querySelectorAll('.navbar-nav .nav-link');

    function updateActiveNav() {
        let current = '';

        sections.forEach(section => {
            const sectionTop = section.offsetTop - 100;
            const sectionHeight = section.clientHeight;

            if (pageYOffset >= sectionTop && pageYOffset < sectionTop + sectionHeight) {
                current = section.getAttribute('id');
            }
        });

        navItems.forEach(item => {
            item.classList.remove('active');
            if (item.getAttribute('href') === `#${current}`) {
                item.classList.add('active');
            }
        });
    }

    window.addEventListener('scroll', updateActiveNav);
    updateActiveNav(); // Llamar una vez al cargar

    // Animación de contadores en la sección "Nosotros"
    const counters = document.querySelectorAll('.stats-row h3');
    let countersStarted = false;

    function animateCounters() {
        if (countersStarted) return;

        const aboutSection = document.getElementById('nosotros');
        const rect = aboutSection.getBoundingClientRect();

        if (rect.top < window.innerHeight && rect.bottom > 0) {
            countersStarted = true;

            counters.forEach(counter => {
                const target = parseInt(counter.textContent.replace('+', ''));
                let current = 0;
                const increment = target / 60; // 60 frames para la animación

                const updateCounter = () => {
                    if (current < target) {
                        current += increment;
                        counter.textContent = Math.ceil(current) + '+';
                        requestAnimationFrame(updateCounter);
                    } else {
                        counter.textContent = target + '+';
                    }
                };

                updateCounter();
            });
        }
    }

    window.addEventListener('scroll', animateCounters);
    animateCounters(); // Verificar al cargar

    // Animaciones de entrada para las tarjetas
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);

    // Aplicar animación a las tarjetas
    const cards = document.querySelectorAll('.feature-card, .product-preview-card, .contact-card');
    cards.forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        card.style.transition = 'all 0.6s ease';
        observer.observe(card);
    });

    // Efecto parallax suave para el hero
    window.addEventListener('scroll', () => {
        const scrolled = window.pageYOffset;
        const heroContent = document.querySelector('.hero-content');
        const heroIcon = document.querySelector('.hero-icon');

        if (heroContent && scrolled < window.innerHeight) {
            heroContent.style.transform = `translateY(${scrolled * 0.5}px)`;
        }

        if (heroIcon && scrolled < window.innerHeight) {
            heroIcon.style.transform = `translateY(${scrolled * 0.3}px) scale(${1 + scrolled * 0.0001})`;
        }
    });

    // Efecto hover dinámico para los iconos
    const iconElements = document.querySelectorAll('.feature-icon, .product-icon, .contact-icon');

    iconElements.forEach(icon => {
        icon.addEventListener('mouseenter', function() {
            this.style.transform = 'scale(1.2) rotate(360deg)';
        });

        icon.addEventListener('mouseleave', function() {
            this.style.transform = 'scale(1) rotate(0deg)';
        });
    });

    // Animación para los enlaces sociales
    const socialLinks = document.querySelectorAll('.social-links a');

    socialLinks.forEach((link, index) => {
        link.addEventListener('mouseenter', function() {
            // Efecto dominó en los otros enlaces
            socialLinks.forEach((otherLink, otherIndex) => {
                if (otherIndex !== index) {
                    const distance = Math.abs(otherIndex - index);
                    const delay = distance * 50;

                    setTimeout(() => {
                        otherLink.style.transform = 'translateY(-2px) scale(0.95)';
                    }, delay);

                    setTimeout(() => {
                        otherLink.style.transform = 'translateY(0) scale(1)';
                    }, delay + 150);
                }
            });
        });
    });

    // Loading state para botones
    const ctaButtons = document.querySelectorAll('.btn');

    ctaButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            // Solo para botones que no son de navegación interna
            if (!this.getAttribute('href') || !this.getAttribute('href').startsWith('#')) {
                const originalText = this.innerHTML;

                // Simular loading state
                this.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Cargando...';
                this.disabled = true;

                setTimeout(() => {
                    this.innerHTML = originalText;
                    this.disabled = false;
                }, 2000);
            }
        });
    });

    // Mensaje de bienvenida inicial
    setTimeout(() => {
        // Solo mostrar si es la primera visita (simulado)
        if (!sessionStorage.getItem('welcomed')) {
            const welcomeToast = document.createElement('div');
            welcomeToast.className = 'position-fixed bottom-0 end-0 p-3';
            welcomeToast.style.zIndex = '9999';
            welcomeToast.innerHTML = `
                <div class="toast show" role="alert">
                    <div class="toast-header">
                        <i class="fas fa-coffee text-warning me-2"></i>
                        <strong class="me-auto">¡Bienvenido!</strong>
                        <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
                    </div>
                    <div class="toast-body">
                        Gracias por visitar Cafeteria El Aroma. ¡Esperamos que disfrutes navegando por nuestro sitio!
                    </div>
                </div>
            `;

            document.body.appendChild(welcomeToast);
            sessionStorage.setItem('welcomed', 'true');

            // Auto-remove después de 5 segundos
            setTimeout(() => {
                if (welcomeToast.parentNode) {
                    welcomeToast.remove();
                }
            }, 5000);
        }
    }, 1000);

    // Efecto de typing para el título principal (evita mostrar etiquetas HTML como texto)
    const heroTitle = document.querySelector('.hero-content h1');
    if (heroTitle) {
        // Usar solo texto para el efecto de tipeo
        const originalText = heroTitle.textContent;
        heroTitle.textContent = '';

        let i = 0;
        const typeEffect = () => {
            if (i < originalText.length) {
                heroTitle.textContent += originalText.charAt(i);
                i++;
                setTimeout(typeEffect, 50);
            } else {
                // Al finalizar, re-aplicar énfasis a "El Aroma"
                heroTitle.innerHTML = heroTitle.textContent.replace(/El Aroma/, '<span class="text-coffee-accent">El Aroma</span>');
            }
        };

        // Iniciar después de un breve delay
        setTimeout(typeEffect, 500);
    }
});

    document.addEventListener('DOMContentLoaded', function() {
    const welcomeMsg = document.getElementById('welcomeMessage');
    if (welcomeMsg) {
    setTimeout(() => {
    welcomeMsg.style.transition = 'opacity 0.5s';
    welcomeMsg.style.opacity = '0';
    setTimeout(() => welcomeMsg.remove(), 500);
}, 2000); // 2 segundos
}
});
