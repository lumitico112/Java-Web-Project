document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('cambiarContrasenaForm');
    const confirmInput = document.getElementById('confirmarContrasena');

    if (form && confirmInput) {
        confirmInput.addEventListener('input', function () {
            const password = form.querySelector('[name="contrasena"]').value;
            if (password !== confirmInput.value) {
                confirmInput.classList.add('is-invalid');
            } else {
                confirmInput.classList.remove('is-invalid');
            }
        });

        form.addEventListener('submit', function (e) {
            const password = form.querySelector('[name="contrasena"]').value;
            if (password !== confirmInput.value) {
                e.preventDefault();
                confirmInput.classList.add('is-invalid');
                return false;
            }
        });
    }
});