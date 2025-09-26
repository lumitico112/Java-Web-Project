// JavaScript para el formulario de registro de usuarios
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registroForm');
    const togglePasswordBtn = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('contrasena');
    const confirmPasswordInput = document.getElementById('confirmarContrasena');
    const correoInput = document.getElementById('correo');

    // Mostrar/ocultar contraseña
    togglePasswordBtn.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);

        const icon = this.querySelector('i');
        icon.classList.toggle('fa-eye');
        icon.classList.toggle('fa-eye-slash');
    });

    // Validación en tiempo real de confirmación de contraseña
    confirmPasswordInput.addEventListener('input', function () {
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
    passwordInput.addEventListener('input', function () {
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

        if (confirmPasswordInput.value) {
            confirmPasswordInput.dispatchEvent(new Event('input'));
        }
    });

    // Validación de correo
    correoInput.addEventListener('input', function () {
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
    form.addEventListener('submit', function (e) {
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
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i> Procesando...';
        submitBtn.disabled = true;
    });

    // Efectos visuales en inputs
    const inputs = form.querySelectorAll('.form-control, .form-select');
    inputs.forEach(input => {
        input.addEventListener('focus', function () {
            this.closest('.mb-3').style.transform = 'scale(1.02)';
            this.closest('.mb-3').style.transition = 'transform 0.2s ease';
        });
        input.addEventListener('blur', function () {
            this.closest('.mb-3').style.transform = 'scale(1)';
        });
    });
});
