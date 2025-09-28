package com.cafeteria.controller;

import com.cafeteria.entity.PerfilCliente;
import com.cafeteria.entity.Rol;
import com.cafeteria.entity.Usuario;
import com.cafeteria.enums.Estado;
import com.cafeteria.service.UsuarioService;
import com.cafeteria.serviceImpl.UsuarioServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioController extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String servletPath = request.getServletPath();   // e.g. "/usuario" o "/registro"
        String pathInfo = request.getPathInfo();         // e.g. "/perfil" cuando la URL es /usuario/perfil

        // shortcuts
        if ("/registro".equals(servletPath) && (action == null || action.isEmpty())) {
            action = "nuevo";
        }
        if ("/usuario".equals(servletPath) && "/perfil".equals(pathInfo)) {
            action = "perfil";
        }
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "nuevo" -> forward(request, response, "/WEB-INF/views/auth/register.jsp");
                case "insertar" -> insertar(request, response);
                case "editar" -> mostrarEditar(request, response);      // admin edita a cualquier usuario
                case "perfil" -> mostrarPerfilPropio(request, response); // usuario ve/edita su perfil
                case "actualizarPerfilCliente" -> actualizarPerfilCliente(request, response);
                case "actualizar" -> actualizar(request, response);     // guardar cambios (admin o propio)
                case "eliminar" -> eliminar(request, response);         // solo admin
                default -> listar(request, response);
            }
        } catch (Exception e) {
            manejarError(request, response, e);
        }
    }

    /* ==================== operaciones ==================== */

    private void listar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        forward(request, response, "/WEB-INF/views/usuario/lista.jsp");
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Construir usuario desde parámetros (registro)
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setContrasena(request.getParameter("contrasena"));
        usuario.setEstado(Estado.ACTIVO);
        usuario.setFechaCreacion(LocalDateTime.now());

        String idRolParam = request.getParameter("idRol");
        Rol rol = new Rol();
        rol.setIdRol((idRolParam != null && !idRolParam.isEmpty()) ? Integer.parseInt(idRolParam) : 3);
        usuario.setRol(rol);

        usuarioService.registrarUsuario(usuario);

        // Si usas trigger en BD, se creará Perfil_Cliente automático; si no, lo puedes insertar manualmente en DAO.
        response.sendRedirect(request.getContextPath() + "/login?msg=success");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Mostrar formulario para que el ADMIN edite a otro usuario
        HttpSession session = request.getSession(false);
        Usuario ses = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (ses == null || !"ADMIN".equalsIgnoreCase(ses.getRol().getNombre())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No autorizado");
            return;
        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.buscarPorId(id);

        // si es cliente, traer perfil extra
        if ("CLIENTE".equalsIgnoreCase(usuario.getRol().getNombre())) {
            PerfilCliente perfil = usuarioService.buscarPerfilCliente(usuario.getIdUsuario());
            request.setAttribute("perfilCliente", perfil);
        }

        request.setAttribute("usuario", usuario);
        forward(request, response, "/WEB-INF/views/usuario/form.jsp");
    }

    private void mostrarPerfilPropio(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Mostrar formulario con los datos del usuario logueado
        HttpSession session = request.getSession(false);
        Usuario ses = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (ses == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuario = usuarioService.buscarPorId(ses.getIdUsuario());

        if ("CLIENTE".equalsIgnoreCase(usuario.getRol().getNombre())) {
            PerfilCliente perfil = usuarioService.buscarPerfilCliente(usuario.getIdUsuario());
            request.setAttribute("perfilCliente", perfil);
        }

        request.setAttribute("usuario", usuario);
        forward(request, response, "/WEB-INF/views/usuario/form.jsp");
    }

    private void actualizarPerfilCliente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idParam = request.getParameter("idUsuario");
        HttpSession session = request.getSession(false);
        Usuario ses = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        int idUsuario;
        if (idParam != null && !idParam.isEmpty()) {
            idUsuario = Integer.parseInt(idParam);
        } else if (ses != null) {
            idUsuario = ses.getIdUsuario();
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        PerfilCliente perfil = new PerfilCliente();
        perfil.setIdUsuario(idUsuario);
        perfil.setTelefono(request.getParameter("telefono"));
        perfil.setDireccion(request.getParameter("direccion"));

        // puntosFidelizacion puede venir o no; si no viene se mantiene (0 o el actual).
        String puntos = request.getParameter("puntosFidelizacion");
        if (puntos != null && !puntos.isEmpty()) {
            try {
                perfil.setPuntosFidelizacion(Integer.parseInt(puntos));
            } catch (NumberFormatException ex) {
                perfil.setPuntosFidelizacion(0);
            }
        } else {
            perfil.setPuntosFidelizacion(0);
        }

        usuarioService.actualizarPerfilCliente(perfil);

        // Si el usuario actualizó su propio perfil, redirigimos a perfil
        if (ses != null && ses.getIdUsuario() == idUsuario) {
            response.sendRedirect(request.getContextPath() + "/usuario/perfil?msg=perfilActualizado");
        } else {
            // Si admin actualizó el perfil de otro, volvemos al listado o al editar de admin
            response.sendRedirect(request.getContextPath() + "/usuario?action=listar&msg=perfilActualizado");
        }
    }


    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        Usuario ses = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        Usuario existente = usuarioService.buscarPorId(idUsuario);
        if (existente == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
            return;
        }

        // Construimos objeto usuario basado en los datos existentes
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        // Si no viene un campo en el request, mantenemos el valor existente
        usuario.setNombre(getOrDefault(request.getParameter("nombre"), existente.getNombre()));
        usuario.setApellido(getOrDefault(request.getParameter("apellido"), existente.getApellido()));
        usuario.setCorreo(getOrDefault(request.getParameter("correo"), existente.getCorreo()));

        String contrasena = request.getParameter("contrasena");
        boolean cambioSoloContrasena = false;

        if (contrasena != null && !contrasena.isEmpty()) {
            usuario.setContrasena(contrasena); // el DAO se encarga de hashear
            // Detectamos si los demás campos vinieron nulos/vacíos => solo cambió contraseña
            if ((request.getParameter("nombre") == null || request.getParameter("nombre").isEmpty())
                    && (request.getParameter("apellido") == null || request.getParameter("apellido").isEmpty())
                    && (request.getParameter("correo") == null || request.getParameter("correo").isEmpty())) {
                cambioSoloContrasena = true;
            }
        } else {
            usuario.setContrasena(existente.getContrasena());
        }

        // Rol y estado
        boolean updaterIsAdmin = (ses != null && "ADMIN".equalsIgnoreCase(ses.getRol().getNombre()));
        if (updaterIsAdmin) {
            String estadoParam = request.getParameter("estado");
            usuario.setEstado(
                    (estadoParam != null && !estadoParam.isEmpty())
                            ? Estado.valueOf(estadoParam.toUpperCase())
                            : existente.getEstado()
            );

            String idRolParam = request.getParameter("idRol");
            Rol rol = new Rol();
            rol.setIdRol(
                    (idRolParam != null && !idRolParam.isEmpty())
                            ? Integer.parseInt(idRolParam)
                            : existente.getRol().getIdRol()
            );
            usuario.setRol(rol);
        } else {
            usuario.setEstado(existente.getEstado());
            usuario.setRol(existente.getRol());
        }

        usuarioService.actualizarUsuario(usuario);

        // Actualizar perfil de cliente si corresponde
        String origen = request.getParameter("origen"); // "perfil" si viene del formulario propio
        if ("perfil".equalsIgnoreCase(origen) && "CLIENTE".equalsIgnoreCase(usuario.getRol().getNombre())) {
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            if (telefono != null || direccion != null) {
                PerfilCliente perfil = new PerfilCliente();
                perfil.setIdUsuario(idUsuario);
                perfil.setTelefono(getOrDefault(telefono, null));
                perfil.setDireccion(getOrDefault(direccion, null));
                perfil.setPuntosFidelizacion(0); // mantener/ajustar según tu lógica
                usuarioService.actualizarPerfilCliente(perfil);
            }
        }

        // Si el usuario actualizó su propia cuenta, refrescamos la sesión
        if (ses != null && ses.getIdUsuario() == idUsuario) {
            Usuario actualizado = usuarioService.buscarPorId(idUsuario);
            session.setAttribute("usuario", actualizado);

            if (cambioSoloContrasena) {
                response.sendRedirect(request.getContextPath() + "/usuario/perfil?msg=contrasena");
            } else {
                response.sendRedirect(request.getContextPath() + "/usuario/perfil?msg=actualizado");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/usuario?action=listar&msg=actualizado");
        }
    }

    /**
     * Helper para evitar nulos.
     */
    private String getOrDefault(String nuevo, String existente) {
        return (nuevo != null && !nuevo.isEmpty()) ? nuevo : existente;
    }


    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        Usuario ses = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (ses == null || !"ADMIN".equalsIgnoreCase(ses.getRol().getNombre())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No autorizado");
            return;
        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        usuarioService.eliminarUsuario(id);
        response.sendRedirect(request.getContextPath() + "/usuario?action=listar&msg=eliminado");
    }

    /* ==================== helpers ==================== */

    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        request.setAttribute("error", e.getMessage());
        request.setAttribute("exception", e);

        java.io.StringWriter sw = new java.io.StringWriter();
        e.printStackTrace(new java.io.PrintWriter(sw));
        request.setAttribute("stackTrace", sw.toString());

        forward(request, response, "/WEB-INF/views/error/error.jsp");
    }
}
