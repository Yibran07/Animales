/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AnimalesModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 *
 * @author Dell
 */
@WebServlet(name = "Animal", urlPatterns = {"/animal"})
public class Animal extends HttpServlet {
    
    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Animal</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Animal at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Se ejecuta el doGet");
        ConnectionBD conexion = new ConnectionBD();
        List<AnimalesModel> listaAnimales = new ArrayList<>();
        String sql = "SELECT id, color, especie, tipo_animal, tipo_alimento, peso, habitad, altura FROM animales";

        try {
            conn = conexion.getConnectionBD();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Itera sobre los resultados y crea objetos UsuarioModel
            while (rs.next()) {
                AnimalesModel animal = new AnimalesModel();
                animal.setColor(rs.getString("color"));
                animal.setEspecie(rs.getString("especie"));
                animal.setTipo_animal(rs.getString("tipo_animal"));
                animal.setTipo_alimento(rs.getString("tipo_alimento"));
                animal.setPeso(rs.getDouble("peso"));
                animal.setHabitad(rs.getString("habitad"));
                animal.setAltura(rs.getString("altura"));
                animal.setId(rs.getInt("id"));
                listaAnimales.add(animal);
            }

            // Pasa la lista de usuarios al JSP
            request.setAttribute("animales", listaAnimales);
            request.getRequestDispatcher("/vistas/mostrar_animales.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los animales" + e);
        } finally {
            // Close resources
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         ConnectionBD conexion = new ConnectionBD();
          request.setCharacterEncoding("UTF-8"); // Configura la codificación de la solicitud
    response.setCharacterEncoding("UTF-8"); // Configura la codificación de la respuesta
    response.setContentType("text/html; charset=UTF-8"); // Establece el tipo de contenido
    
         String method = request.getParameter("_method");
         if (method != null && method.equalsIgnoreCase("PUT")) {
        doPut(request, response);  // Reenvía la solicitud a doPut si es un update
    } else {
        // Obtener los parámetros del formulario 
        String color = request.getParameter("txt_color");
        String especie = request.getParameter("txt_especie");
        String tipo_animal = request.getParameter("txt_tipo_animal");
        String tipo_alimento = request.getParameter("txt_tipo_alimento");
        String pesoString = request.getParameter("txt_peso");
        String habitad = request.getParameter("txt_habitad");
        String altura = request.getParameter("txt_altura");
   
        double peso = Double.parseDouble(pesoString);

        try {
            // Crear la consulta SQL para insertar el usuario 
            String sql = "INSERT INTO animales (color, especie, tipo_animal, tipo_alimento, "
                    + "peso, habitad, altura) VALUES (?, ?, ?, ?, ?, ?, ?)";
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setString(1, color);
            ps.setString(2, especie);
            ps.setString(3, tipo_animal);
            ps.setString(4, tipo_alimento);
            ps.setDouble(5, peso);
            ps.setString(6, habitad);
            ps.setString(7, altura);
            
            ps.executeUpdate();
            // Pasa la lista de usuarios al JSP
            response.sendRedirect(request.getContextPath() + "/animal");
            System.out.println("Agregado correctamente el animal");
            
        } catch (SQLException e) {
            e.printStackTrace();
            //request.setAttribute("mensaje", "Ocurrió un error: " + e.getMessage());
            //request.getRequestDispatcher("/views/resultado.jsp").forward(request, response);
            System.out.println("Ocurrio un erro al agregar un animal");
        } finally {
            // Cerrar los recursos 
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionBD conexion = new ConnectionBD();
        String id = request.getParameter("id");
        // Validate input
        if (id == null || id.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Invalid request
            return;
        }

        String sql = "DELETE FROM animales WHERE id = ?";

        try {
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                response.setStatus(HttpServletResponse.SC_OK); // Eliminar exitoso 
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // No se encontró el usuario 
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Error del servidor 
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ConnectionBD conexion = new ConnectionBD();
    String id = null;
    String color = null;
    String especie = null;
    String tipo_animal = null;
    String tipo_alimento = null;
    String pesoString = null;
    String habitad = null;
    String altura = null;
    
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        
        // Procesa el cuerpo de la solicitud manualmente
        String[] params = sb.toString().split("&");
        for (String param : params) {
            String[] pair = param.split("=");
            if (pair.length == 2) {
                String key = pair[0];
                String value = URLDecoder.decode(pair[1], "UTF-8");
                
                // Asigna los valores de acuerdo a los nombres de los parámetros
                switch (key) {
                    case "id":
                        id = value;
                        break;
                    case "color":
                        color = value;
                        break;
                    case "especie":
                        especie = value;
                        break;
                    case "tipo_animal":
                        tipo_animal = value;
                        break;
                    case "tipo_alimento":
                        tipo_alimento = value;
                        break;
                    case "peso":
                        pesoString = value;
                        break;
                    case "habitad":
                        habitad = value;
                        break;
                    case "altura":
                        altura = value;
                        break;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Verifica si el id fue proporcionado
    if (id == null || id.isEmpty()) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Id not found in request body");
        return;
    }

    // Si los parámetros fueron correctamente obtenidos, realiza la actualización
    try {
        conn = conexion.getConnectionBD();
        
        String sql = "UPDATE animales SET color = ?, especie = ?, tipo_animal = ?, tipo_alimento = ?, "
                   + "peso = ?, habitad = ?, altura = ? WHERE id = ?";
        
        ps = conn.prepareStatement(sql);
        ps.setString(1, color);
        ps.setString(2, especie);
        ps.setString(3, tipo_animal);
        ps.setString(4, tipo_alimento);
        ps.setString(5, pesoString);
        ps.setString(6, habitad);
        ps.setString(7, altura);
        ps.setString(8, id);
        
        // Ejecutar la consulta de actualización
        ps.executeUpdate();
        System.out.println("Animal actualizado exitosamente.");
    } catch (SQLException e) {
        e.printStackTrace();
        response.getWriter().write("Error al actualizar el animal."+ e);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}
