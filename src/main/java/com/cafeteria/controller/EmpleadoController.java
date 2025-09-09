package com.cafeteria.controller;

import com.cafeteria.model.entity.Empleado;
import com.cafeteria.model.enums.Rol;
import com.cafeteria.model.serviceImpl.EmpleadoServiceImpl;
import com.cafeteria.service.EmpleadoService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/controller/EmpleadoController")
public class EmpleadoController extends HttpServlet{
    private EmpleadoService empleadoService = new EmpleadoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listar(request, response);
            } else {
                switch (action) {
                    case "listar":
                        listar(request, response);
                        break;
                    case "editar":
                        mostrarEditar(request, response);
                        break;
                    case "eliminar":
                        eliminar(request, response);
                        break;
                    case "nuevo":
                        request.getRequestDispatcher("/WEB-INF/views/empleado-form.jsp").forward(request, response);
                        break;
                    default:
                        listar(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insertar":
                    insertar(request, response);
                    break;
                case "actualizar":
                    actualizar(request, response);
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    // ======================
    // Métodos privados CRUD
    // ======================

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/WEB-INF/views/empleado-lista.jsp").forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Empleado empleado = new Empleado();
        empleado.setEmpleadoId(request.getParameter("empleadoId"));
        empleado.setNombre(request.getParameter("nombre"));
        empleado.setUsuario(request.getParameter("usuario"));
        empleado.setCorreo(request.getParameter("correo"));
        empleado.setContrasenaHash(request.getParameter("contrasena")); // el Service la encripta
        empleado.setRol(Rol.valueOf(request.getParameter("rol").toUpperCase()));
        empleado.setEstado(true);
        empleado.setFechaRegistro(LocalDate.now());

        empleadoService.registrarEmpleado(empleado);
        response.sendRedirect("EmpleadoController?action=listar");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        Empleado empleado = empleadoService.buscarPorId(id);
        request.setAttribute("empleado", empleado);
        request.getRequestDispatcher("/WEB-INF/views/empleado-form.jsp").forward(request, response);
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Empleado empleado = new Empleado();
        empleado.setEmpleadoId(request.getParameter("empleadoId"));
        empleado.setNombre(request.getParameter("nombre"));
        empleado.setUsuario(request.getParameter("usuario"));
        empleado.setCorreo(request.getParameter("correo"));
        empleado.setContrasenaHash(request.getParameter("contrasena")); // el Service la encripta
        empleado.setRol(Rol.valueOf(request.getParameter("rol").toUpperCase()));
        empleado.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        empleado.setFechaRegistro(LocalDate.parse(request.getParameter("fechaRegistro")));

        empleadoService.actualizarEmpleado(empleado);
        response.sendRedirect("EmpleadoController?action=listar");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        empleadoService.eliminarEmpleado(id);
        response.sendRedirect("EmpleadoController?action=listar");
    }
}