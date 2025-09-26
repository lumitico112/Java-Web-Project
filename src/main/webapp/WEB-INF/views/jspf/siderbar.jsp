<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-3">
    <div class="card shadow-sm">
        <div class="card-header bg-coffee-dark text-white">
            <h6 class="mb-0">
                <i class="fas fa-cog me-2"></i>Panel de Control
            </h6>
        </div>
        <div class="card-body p-0">
            <div class="list-group list-group-flush">
                <a href="${pageContext.request.contextPath}/usuario"
                   class="list-group-item list-group-item-action">
                    <i class="fas fa-users me-2"></i>Gestionar Usuarios
                </a>
                <a href="${pageContext.request.contextPath}/productos"
                   class="list-group-item list-group-item-action">
                    <i class="fas fa-coffee me-2"></i>Productos
                </a>
                <a href="${pageContext.request.contextPath}/pedidos"
                   class="list-group-item list-group-item-action">
                    <i class="fas fa-receipt me-2"></i>Pedidos
                </a>
                <a href="${pageContext.request.contextPath}/reportes"
                   class="list-group-item list-group-item-action">
                    <i class="fas fa-chart-bar me-2"></i>Reportes
                </a>
            </div>
        </div>
    </div>
</div>