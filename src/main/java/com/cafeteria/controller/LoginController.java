package com.cafeteria.controller;

import com.cafeteria.entity.Usuario;
import com.cafeteria.service.LoginService;
import com.cafeteria.serviceImpl.LoginServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginController extends HttpServlet {

    private final LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("logout".equalsIgnoreCase(action)) {
                // Cerrar sesión
                HttpSession session = request.getSession(false);
                if (session != null) session.invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                // Mostrar formulario login
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            manejarError(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String identifier = request.getParameter("loginUser");
            String password = request.getParameter("loginPassword");

            if (identifier == null || password == null ||
                    identifier.trim().isEmpty() || password.trim().isEmpty()) {
                request.setAttribute("loginError", "Completa usuario/correo y contraseña.");
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                return;
            }

            Usuario usuario = loginService.autenticar(identifier.trim(), password);

            if (usuario == null) {
                request.setAttribute("loginError", "Credenciales invalidas.");
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                return;
            }

            // Autenticación correcta
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);

            //Redirección según el rol
            String targetPage;
            if ("ADMIN".equalsIgnoreCase(usuario.getRol().getNombre())) {
                targetPage = "/dashboard.jsp";
            } else {
                targetPage = "/index.jsp";
            }

            response.sendRedirect(request.getContextPath() + targetPage + "?msg=welcome");

        } catch (Exception e) {
            manejarError(request, response, e);
        }
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        request.setAttribute("error", e.getMessage());
        java.io.StringWriter sw = new java.io.StringWriter();
        e.printStackTrace(new java.io.PrintWriter(sw));
        request.setAttribute("stackTrace", sw.toString());
        request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
    }
}
