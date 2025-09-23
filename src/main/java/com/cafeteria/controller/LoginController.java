package com.cafeteria.controller;

import com.cafeteria.entity.Usuario;
import com.cafeteria.service.LoginService;
import com.cafeteria.serviceImpl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/controller/LoginController")
public class LoginController extends HttpServlet {

    private final LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || "login".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else if ("registro".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else if ("logout".equalsIgnoreCase(action)) {
                HttpSession session = request.getSession(false);
                if (session != null) session.invalidate();
                response.sendRedirect(request.getContextPath() + "/controller/LoginController?action=login");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (Exception e) {
            manejarError(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("login".equalsIgnoreCase(action)) {
                String identifier = request.getParameter("loginUser");
                String password = request.getParameter("loginPassword");

                if (identifier == null || password == null) {
                    request.setAttribute("loginError", "Completa usuario/correo y contraseña.");
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    return;
                }

                Usuario usuario = loginService.autenticar(identifier, password);

                if (usuario == null) {
                    request.setAttribute("loginError", "Credenciales inválidas.");
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    return;
                }

                // 🔹 Autenticación correcta
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                response.sendRedirect(request.getContextPath() + "/index.jsp");

            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
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
        request.getRequestDispatcher("/WEB-INF/jspf/error.jsp").forward(request, response);
    }
}
