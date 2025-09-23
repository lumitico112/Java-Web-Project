package com.cafeteria.controller;

import com.cafeteria.entity.Rol;
import com.cafeteria.entity.Usuario;
import com.cafeteria.enums.EstadoUsuario;
import com.cafeteria.service.UsuarioService;
import com.cafeteria.serviceImpl.UsuarioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/controller/UsuarioController")
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
            // Manejo centralizado de errores
            request.setAttribute("error", e.getMessage());
            request.setAttribute("exception", e);

            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            request.setAttribute("stackTrace", sw.toString());

            request.getRequestDispatcher("/WEB-INF/jspf/error.jsp").forward(request, response);
        }
    }

    // ======================
    // Métodos privados CRUD
    // ======================

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/usuario-lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/usuario-form.jsp").forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setContrasena(request.getParameter("contrasena")); // encripta en el service
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setFechaCreacion(LocalDateTime.now());

        Rol rol = new Rol();
        rol.setIdRol(Integer.parseInt(request.getParameter("idRol")));
        usuario.setRol(rol);

        usuarioService.registrarUsuario(usuario);
        response.sendRedirect(request.getContextPath() + "/usuario?action=listar");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioService.buscarPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/WEB-INF/usuario-form.jsp").forward(request, response);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setContrasena(request.getParameter("contrasena"));
        usuario.setEstado(com.cafeteria.enums.EstadoUsuario.valueOf(
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
}
