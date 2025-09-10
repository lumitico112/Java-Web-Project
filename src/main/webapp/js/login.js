// JavaScript para la página de login

document.addEventListener('DOMContentLoaded', function() {
    // Referencias a elementos del DOM
    const loginForm = document.getElementById('loginForm');
    const togglePasswordBtn = document.getElementById('toggleLoginPassword');
    const passwordInput = document.getElementById('loginPassword');
    const userInput = document.getElementById('loginUser');
    const rememberCheckbox = document.getElementById('rememberMe');

    // Función para mostrar/ocultar contraseña
    togglePasswordBtn.addEventListener('click', function() {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);

        // Cambiar icono
        const icon = this.querySelector('i');
        icon.classList.toggle('fa-eye');
        icon.classList.toggle('fa-eye-slash');
    });

    // Validación en tiempo real del usuario/email
    userInput.addEventListener('input', function() {
        const value = this.value.trim();

        // Validar si es email o usuario
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const usernamePattern = /^[a-zA-Z0-9_]{3,20}$/;

        if (value.length === 0) {
            this.classList.remove('is-valid', 'is-invalid');
        } else if (emailPattern.test(value) || usernamePattern.test(value)) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }
    });

    // Validación de contraseña
    passwordInput.addEventListener('input', function() {
        const password = this.value;

        if (password.length === 0) {
            this.classList.remove('is-valid', 'is-invalid');
        } else if (password.length >= 6) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }
    });

    // Cargar datos guardados si existe
    if (localStorage.getItem('rememberUser')) {
        userInput.value = localStorage.getItem('savedUser') || '';
        rememberCheckbox.checked = true;
    }

    // Manejo del envío del formulario
    // Nota: No usamos e.preventDefault() para permitir que el form llegue al servlet
    loginForm.addEventListener('submit', function() {
        // Guardar preferencia de recordar usuario
        if (rememberCheckbox.checked) {
            localStorage.setItem('rememberUser', 'true');
            localStorage.setItem('savedUser', userInput.value.trim());
        } else {
            localStorage.removeItem('rememberUser');
            localStorage.removeItem('savedUser');
        }

        // Mostrar indicador de carga
        const submitBtn = loginForm.querySelector('button[type="submit"]');
        const originalText = submitBtn.innerHTML;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Iniciando sesión...';
        submitBtn.disabled = true;
        // Nota: El submit real se realiza aquí, no necesitamos setTimeout ni validación simulada
    });

    // Función para mostrar mensaje de éxito
    function showSuccessMessage() {
        const alert = document.createElement('div');
        alert.className = 'alert alert-success alert-dismissible fade show position-fixed';
        alert.style.cssText = 'top: 100px; right: 20px; z-index: 9999; min-width: 300px;';
        alert.innerHTML = `
            <i class="fas fa-check-circle me-2"></i>
            <strong>¡Bienvenido!</strong> Login exitoso. Redirigiendo...
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        document.body.appendChild(alert);

        // Remover automáticamente después de 3 segundos
        setTimeout(() => {
            if (alert.parentNode) {
                alert.remove();
            }
        }, 3000);
    }

    // Función para mostrar mensaje de error
    function showErrorMessage() {
        const alert = document.createElement('div');
        alert.className = 'alert alert-danger alert-dismissible fade show position-fixed';
        alert.style.cssText = 'top: 100px; right: 20px; z-index: 9999; min-width: 300px;';
        alert.innerHTML = `
            <i class="fas fa-exclamation-triangle me-2"></i>
            <strong>Error:</strong> Usuario o contraseña incorrectos.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        document.body.appendChild(alert);

        // Remover automáticamente después de 4 segundos
        setTimeout(() => {
            if (alert.parentNode) {
                alert.remove();
            }
        }, 4000);
    }

    // Efectos visuales para los campos
    const inputs = loginForm.querySelectorAll('.form-control');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.closest('.mb-4').style.transform = 'scale(1.02)';
            this.closest('.mb-4').style.transition = 'transform 0.2s ease';
        });

        input.addEventListener('blur', function() {
            this.closest('.mb-4').style.transform = 'scale(1)';
        });
    });

    // Animación para las info cards
    const infoCards = document.querySelectorAll('.info-card');
    infoCards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';

        setTimeout(() => {
            card.style.transition = 'all 0.6s ease';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, 300 + (index * 150));
    });

    // Efecto parallax suave para el fondo
    window.addEventListener('scroll', () => {
        const scrolled = window.pageYOffset;
        const loginSection = document.querySelector('.login-section');

        if (loginSection && scrolled < window.innerHeight) {
            loginSection.style.transform = `translateY(${scrolled * 0.3}px)`;
        }
    });

    // Mostrar credenciales de prueba (solo visual)
    setTimeout(() => {
        const credentialsInfo = document.createElement('div');
        credentialsInfo.className = 'position-fixed bottom-0 start-0 p-3';
        credentialsInfo.style.zIndex = '9999';
        credentialsInfo.innerHTML = `
            <div class="toast show" role="alert">
                <div class="toast-header">
                    <i class="fas fa-info-circle text-primary me-2"></i>
                    <strong class="me-auto">Credenciales de Prueba</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
                </div>
                <div class="toast-body">
                    <small>
                        <strong>Usuario:</strong> admin | <strong>Contraseña:</strong> admin123<br>
                        <strong>Usuario:</strong> empleado | <strong>Contraseña:</strong> emp123
                    </small>
                </div>
            </div>
        `;
        document.body.appendChild(credentialsInfo);

        // Auto-remove después de 8 segundos
        setTimeout(() => {
            if (credentialsInfo.parentNode) {
                credentialsInfo.remove();
            }
        }, 8000);
    }, 2000);

    // Enter key shortcuts
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Enter' && document.activeElement.tagName !== 'BUTTON') {
            if (userInput.value && passwordInput.value) {
                loginForm.submit(); // envía al servlet
            }
        }
    });

    // Auto-focus en el campo de usuario al cargar
    setTimeout(() => {
        userInput.focus();
    }, 500);
});
