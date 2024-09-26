<%-- 
    Document   : añadir_animales
    Created on : 24-sep-2024, 20:50:01
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agrega un animal</h1>
        <form action="/AnimalesServelet/animal" method="post">

            <label for="color">Color:</label>
            <input type="text" name="txt_color" required><br>

            <label for="especie">Especie:</label>
            <input type="text" name="txt_especie" required><br>

            <label for="tipo_animal">Tipo de Animal:</label>
            <input type="text" name="txt_tipo_animal" required><br>

            <label for="tipo_alimento">Tipo de Alimento:</label>
            <input type="text" name="txt_tipo_alimento" required><br>

            <label for="peso">Peso:</label>
            <input type="number" name="txt_peso" step="0.01" required><br>

            <label for="habitad">Habitad:</label>
            <input type="text" name="txt_habitad" required><br>

            <label for="altura">Altura:</label>
            <input type="text" name="txt_altura" required><br>

            <input type="submit" value="Agregar Animal">
        </form>
    </body>
</html>
