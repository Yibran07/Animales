<%-- 
    Document   : actualizar_animales
    Created on : 24-sep-2024, 21:33:05
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="configuration.ConnectionBD"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <h2>Actualizar Animal</h2> 
        <%
            String id = request.getParameter("id");
            String color = "";
            String especie = "";
            String tipo_animal = "";
            String tipo_alimento = "";
            double peso = 0.0;
            String habitad = "";
            String altura = "";
            ConnectionBD conexion = new ConnectionBD();
            Connection connection = conexion.getConnectionBD();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {

                // Consulta para obtener los datos del usuario por ID 
                String sql = "SELECT color, especie, tipo_animal, tipo_alimento, peso, habitad, altura"
                        + " FROM animales WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, id);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    id = id;
                    color = resultSet.getString("color");
                    especie = resultSet.getString("especie");
                    tipo_animal = resultSet.getString("tipo_animal");
                    tipo_alimento = resultSet.getString("tipo_alimento");
                    peso = resultSet.getDouble("peso");
                    habitad = resultSet.getString("habitad");
                    altura = resultSet.getString("altura");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %> 

        <!-- Formulario con los datos del usuario para actualizar --> 
        <form id="formActualizarAnimal"> 
            <label for="id">ID</label>
            <input type="text" id="txt_id" value="<%= id%>" readOnly><br>
            
            <label for="color">Color:</label>
            <input type="text" id="txt_color" value="<%= color%>" required><br>

            <label for="especie">Especie:</label>
            <input type="text" id="txt_especie" value="<%= especie%>" required><br>

            <label for="tipo_animal">Tipo de Animal:</label>
            <input type="text" id="txt_tipo_animal" value="<%= tipo_animal%>" required><br>

            <label for="tipo_alimento">Tipo de Alimento:</label>
            <input type="text" id="txt_tipo_alimento" value="<%= tipo_alimento%>" required><br>

            <label for="peso">Peso:</label>
            <input type="number" id="txt_peso" step="0.01" value="<%= peso%>" required><br>

            <label for="habitad">Habitad:</label>
            <input type="text" id="txt_habitad" value="<%= habitad%>" required><br>

            <label for="altura">Altura:</label>
            <input type="text" id="txt_altura" value="<%= altura%>" required><br>
            
            <input type="button" value="Actualizar" onclick="actualizarAnimal()"> 
        </form> 
        <script>
            function actualizarAnimal() {
                const id = document.getElementById("txt_id").value;
                const color = document.getElementById("txt_color").value;
                const especie = document.getElementById("txt_especie").value;
                const tipo_animal = document.getElementById("txt_tipo_animal").value;
                const tipo_alimento = document.getElementById("txt_tipo_alimento").value;
                const peso = document.getElementById("txt_peso").value;
                const habitad = document.getElementById("txt_habitad").value;
                const altura = document.getElementById("txt_altura").value;
                
                const datos = new URLSearchParams();
                datos.append("id", id);
                datos.append("color", color);
                datos.append("especie", especie);
                datos.append("tipo_animal", tipo_animal);
                datos.append("tipo_alimento", tipo_alimento);
                datos.append("peso", peso);
                datos.append("habitad", habitad);
                datos.append("altura", altura);
                console.log("datos "+datos);
                var urlParams = new URLSearchParams(window.location.search);
                var id22 = urlParams.get("id");
                console.log("id  " + id22);
                fetch(`${pageContext.request.contextPath}/animal`, {
                    method: "PUT",
                    body: datos,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        return response.text(); // O .json() si envías JSON
                    } else {
                        throw new Error('Error en la actualización');
                    }
                })
                .then(data => {
                    console.log(data);
                    alert('Animal actualizado exitosamente');

                    // Redireccionar a la página /animalesServelet/animal
                    window.location.href = `${pageContext.request.contextPath}/animal`;
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Ocurrió un error durante la actualización');
                });
            }

        </script> 
    </body> 
</html>
