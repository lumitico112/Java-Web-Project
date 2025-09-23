// JavaScript para el formulario de registro de usuarios

document.addEventListener('DOMContentLoaded', function() {
    // Referencias a elementos del DOM
    const form = document.getElementById('registroForm');
    const togglePasswordBtn = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('contrasena');
    const confirmPasswordInput = document.getElementById('confirmarContrasena');
    const usuarioIdInput = document.getElementById('usuarioId');
    const usuarioInput = document.getElementById('usuario');
    const correoInput = document.getElementById('correo');

    // Función para mostrar/ocultar contraseña
    togglePasswordBtn.addEventListener('click', function() {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);

        // Cambiar icono
        const icon = this.querySelector('i');
        icon.classList.toggle('fa-eye');
        icon.classList.toggle('fa-eye-slash');
    });

    // Validación en tiempo real de la confirmación de contraseña
    confirmPasswordInput.addEventListener('input', function() {
        if (passwordInput.value !== confirmPasswordInput.value) {
            confirmPasswordInput.setCustomValidity('Las contraseñas no coinciden');
            confirmPasswordInput.classList.add('is-invalid');
            confirmPasswordInput.classList.remove('is-valid');
        } else {
            confirmPasswordInput.setCustomValidity('');
            confirmPasswordInput.classList.remove('is-invalid');
            confirmPasswordInput.classList.add('is-valid');
        }
    });

    // Validación de contraseña
    passwordInput.addEventListener('input', function() {
        const password = this.value;
        const minLength = 8;
        const hasUpperCase = /[A-Z]/.test(password);
        const hasLowerCase = /[a-z]/.test(password);
        const hasNumbers = /\d/.test(password);

        if (password.length >= minLength && hasUpperCase && hasLowerCase && hasNumbers) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }

        // Revalidar confirmación
        if (confirmPasswordInput.value) {
            confirmPasswordInput.dispatchEvent(new Event('input'));
        }
    });

    // Validación de ID de usuario (formato: USR001, etc.)
    usuarioIdInput.addEventListener('input', function() {
        const pattern = /^[A-Z]{3}\d{3}$/;
        this.value = this.value.toUpperCase();

        if (pattern.test(this.value)) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }
    });

    // Validación de usuario (sin espacios, solo letras, números y guiones bajos)
    usuarioInput.addEventListener('input', function() {
        const pattern = /^[a-zA-Z0-9_]+$/;
        this.value = this.value.toLowerCase().replace(/\s/g, '');

        if (pattern.test(this.value) && this.value.length >= 3) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }
    });

    // Validación de correo electrónico
    correoInput.addEventListener('input', function() {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (emailPattern.test(this.value)) {
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }
    });

    // Manejo del envío del formulario
    form.addEventListener('submit', function(e) {
        if (!form.checkValidity()) {
            e.preventDefault();
            e.stopPropagation();
            form.classList.add('was-validated');
            return;
        }

        if (passwordInput.value !== confirmPasswordInput.value) {
            e.preventDefault();
            alert('Las contraseñas no coinciden');
            return;
        }

        const submitBtn = form.querySelector('button[type="submit"]');
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Procesando...';
        submitBtn.disabled = true;
    });

    // Función para mostrar mensaje de éxito
    function showSuccessMessage() {
        const alert = document.createElement('div');
        alert.className = 'alert alert-success alert-dismissible fade show position-fixed';
        alert.style.cssText = 'top: 20px; right: 20px; z-index: 9999; min-width: 300px;';
        alert.innerHTML = `
            <i class="fas fa-check-circle me-2"></i>
            <strong>¡Éxito!</strong> El usuario ha sido registrado correctamente.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;

        document.body.appendChild(alert);

        setTimeout(() => {
            if (alert.parentNode) {
                alert.remove();
            }
        }, 5000);
    }

    // Efectos visuales en inputs
    const inputs = form.querySelectorAll('.form-control, .form-select');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.closest('.mb-4').style.transform = 'scale(1.02)';
            this.closest('.mb-4').style.transition = 'transform 0.2s ease';
        });
        input.addEventListener('blur', function() {
            this.closest('.mb-4').style.transform = 'scale(1)';
        });
    });

    // Autogenerar sugerencia de usuario
    const nombreInput = document.getElementById('nombre');
    nombreInput.addEventListener('blur', function() {
        if (this.value && !usuarioInput.value) {
            const nombres = this.value.toLowerCase().split(' ');
            let sugerencia = nombres[0];
            if (nombres.length > 1) {
                sugerencia += nombres[nombres.length - 1].charAt(0);
            }
            sugerencia = sugerencia.replace(/[^a-z0-9]/g, '');
            usuarioInput.value = sugerencia;
            usuarioInput.dispatchEvent(new Event('input'));
        }
    });

    // Tooltips de ayuda
    const tooltips = [
        { element: usuarioIdInput, text: 'Formato: 3 letras + 3 números (ej: USR001)' },
        { element: usuarioInput, text: 'Solo letras, números y guión bajo. Mínimo 3 caracteres' },
        { element: passwordInput, text: 'Mínimo 8 caracteres, incluir mayúsculas, minúsculas y números' }
    ];

    tooltips.forEach(tooltip => {
        tooltip.element.setAttribute('data-bs-toggle', 'tooltip');
        tooltip.element.setAttribute('data-bs-placement', 'top');
        tooltip.element.setAttribute('title', tooltip.text);
    });

    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
});
