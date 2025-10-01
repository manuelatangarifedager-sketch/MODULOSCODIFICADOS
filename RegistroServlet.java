package com.mycompany.proyectoservlet.RegistroServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/serv", "root", "admin"); // cambia usuario/clave según tu config

            // Verificar si el usuario ya existe
            String checkSql = "SELECT * FROM usuarios WHERE nomusu = ?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, usuario);
            ResultSet rs = checkPs.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Registro</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; text-align: center; background-color: white; }");
            out.println(".mensaje { margin-top: 100px; padding: 20px; border-radius: 8px; display: inline-block; background: #fff8dc; }"); // fondo amarillo suave
            out.println(".exito { color: #d4a017; font-size: 18px; font-weight: bold; }"); // amarillo fuerte
            out.println(".error { color: red; font-size: 18px; font-weight: bold; }");
            out.println("a { display: block; margin-top: 15px; color: #d4a017; text-decoration: none; font-weight: bold; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='mensaje'>");

            if (rs.next()) {
                // Usuario ya existe
                out.println("<p class='error'>El usuario <b>" + usuario + "</b> ya está registrado.</p>");
                out.println("<p class='exito'>Intente con otro nombre de usuario.</p>");
                out.println("<a href='registro.html'>Volver a registro</a>");
            } else {
                // Insertar nuevo usuario
                String sql = "INSERT INTO usuarios (nomusu, passusu) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, password);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    out.println("<p class='exito'>Usuario registrado con éxito</p>");
                    out.println("<a href='index.html'>Ir al inicio de sesión</a>");
                } else {
                    out.println("<p class='error'>Error al registrar usuario</p>");
                    out.println("<a href='registro.html'>Intentar de nuevo</a>");
                }
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}