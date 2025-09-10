package com.cafeteria.controller;

import com.cafeteria.model.entity.Empleado;
import com.cafeteria.model.serviceImpl.EmpleadoServiceImpl;
import com.cafeteria.model.serviceImpl.LoginServiceImpl;
import com.cafeteria.service.EmpleadoService;
import com.cafeteria.service.LoginService;
import org.mindrot.jbcrypt.BCrypt;

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
    private final EmpleadoService empleadoService = new EmpleadoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || "login".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            } else if ("registro".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            } else if ("logout".equalsIgnoreCase(action)) {
                HttpSession session = request.getSession(false);
                if (session != null) session.invalidate();
                response.sendRedirect(request.getContextPath() + "/controller/LoginController?action=login");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            request.setAttribute("stackTrace", sw.toString());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("login".equalsIgnoreCase(action)) {
                String identifierRaw = request.getParameter("loginUser");
                String password = request.getParameter("loginPassword");

                if (identifierRaw == null || password == null) {
                    request.setAttribute("loginError", "Completa usuario/correo y contraseña.");
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                    return;
                }

                String identifierNorm = identifierRaw.trim().toLowerCase();

                // 1) Buscar usuario por usuario/correo (normalizado y crudo como compatibilidad)
                Empleado candidato = empleadoService.buscarPorUsuario(identifierNorm);
                if (candidato == null) candidato = empleadoService.buscarPorUsuario(identifierRaw);
                if (candidato == null) candidato = empleadoService.buscarPorCorreo(identifierNorm);
                if (candidato == null) candidato = empleadoService.buscarPorCorreo(identifierRaw);

                if (candidato == null) {
                    // Usuario/correo no existe en BD
                    request.setAttribute("loginErrorUsuario", "El usuario o correo no existe.");
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                    return;
                }

                // 2) Validar hash de contraseña contra lo ingresado
                String storedHash = candidato.getContrasenaHash();
                if (storedHash == null || storedHash.length() < 55) {
                    request.setAttribute("loginError", "Error interno: hash de contraseña inválido en BD.");
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                    return;
                }

                boolean ok;
                try {
                    ok = BCrypt.checkpw(password, storedHash);
                } catch (IllegalArgumentException ex) {
                    request.setAttribute("loginError", "Error interno: formato de hash inválido en BD.");
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                    return;
                }

                if (!ok) {
                    // Contraseña incorrecta
                    request.setAttribute("loginErrorContrasena", "Contraseña incorrecta.");
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                    return;
                }

                // 3) Autenticación correcta: crear sesión y redirigir
                HttpSession session = request.getSession(true);
                session.setAttribute("empleado", candidato);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            java.io.StringWriter sw = new java.io.StringWriter();
            e.printStackTrace(new java.io.PrintWriter(sw));
            request.setAttribute("stackTrace", sw.toString());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
