package com.cafeteria.controller;

import com.cafeteria.entity.Rol;
import com.cafeteria.entity.Usuario;
import com.cafeteria.enums.EstadoUsuario;
import com.cafeteria.service.UsuarioService;
import com.cafeteria.serviceImpl.UsuarioServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // Si viene desde "/registro" sin action, forzamos a mostrar formulario nuevo
        String servletPath = request.getServletPath();
        if (servletPath.equals("/registro") && (action == null || action.isEmpty())) {
            action = "nuevo";
        }
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "nuevo" -> mostrarFormularioNuevo(request, response);
                case "insertar" -> insertar(request, response);
                case "editar" -> mostrarEditar(request, response);
                case "actualizar" -> actualizar(request, response);
                case "eliminar" -> eliminar(request, response);
                default -> listar(request, response);
            }
        } catch (Exception e) {
            manejarError(request, response, e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/views/usuario/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }


    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setContrasena(request.getParameter("contrasena"));
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setFechaCreacion(LocalDateTime.now());

        String idRolParam = request.getParameter("idRol");
        Rol rol = new Rol();
        if (idRolParam != null && !idRolParam.isEmpty()) {
            rol.setIdRol(Integer.parseInt(idRolParam));
        } else {
            rol.setIdRol(3); //por defecto cliente
        }
        usuario.setRol(rol);

        usuarioService.registrarUsuario(usuario);

        request.setAttribute("mensajeExito", "Registro completado. Ahora puedes iniciar sesión.");
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.buscarPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/WEB-INF/views/usuario/form.jsp").forward(request, response);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setCorreo(request.getParameter("correo"));

        String contrasena = request.getParameter("contrasena");
        if (contrasena != null && !contrasena.isEmpty()) {
            usuario.setContrasena(contrasena);
        }

        usuario.setEstado(EstadoUsuario.valueOf(
                request.getParameter("estado").toUpperCase()
        ));

        Rol rol = new Rol();
        rol.setIdRol(Integer.parseInt(request.getParameter("idRol")));
        usuario.setRol(rol);

        usuarioService.actualizarUsuario(usuario);
        response.sendRedirect(request.getContextPath() + "/usuario?action=listar");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        usuarioService.eliminarUsuario(id);
        response.sendRedirect(request.getContextPath() + "/usuario?action=listar");
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        request.setAttribute("error", e.getMessage());
        request.setAttribute("exception", e);

        java.io.StringWriter sw = new java.io.StringWriter();
        e.printStackTrace(new java.io.PrintWriter(sw));
        request.setAttribute("stackTrace", sw.toString());

        request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
    }
}
