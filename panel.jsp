<%-- 
    Document   : panel
    Created on : 29/09/2025, 5:41:20 p. m.
    Author     : Manuela
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("nomusu") == null) {
        response.sendRedirect("index.html");
        return;
    }
    String usuario = (String) session.getAttribute("nomusu");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tienda - YP CELULARES</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>📱 YP CELULARES</header>

    <main class="container">
        <h1>Bienvenido, <%= usuario %> 👋</h1>
        <h2>Nuestros celulares</h2>

        <div class="grid">
            <div class="card">
                <img src="img/iphone14.jpg" alt="iPhone 14">
                <h3>iPhone 14 Pro</h3>
                <p>128 GB · Pantalla Super Retina · Triple cámara 48MP</p>
                <button>Agregar al carrito</button>
            </div>

            <div class="card">
                <img src="img/galaxy23.jpg" alt="Samsung S23">
                <h3>Samsung Galaxy S23</h3>
                <p>256 GB · AMOLED · Cámara 50MP · Batería 5000mAh</p>
                <button>Agregar al carrito</button>
            </div>

            <div class="card">
                <img src="img/xiaomi13.jpg" alt="Xiaomi 13">
                <h3>Xiaomi 13 Pro</h3>
                <p>12GB RAM · Snapdragon 8 Gen 2 · Carga rápida 120W</p>
                <button>Agregar al carrito</button>
            </div>

            <div class="card">
                <img src="img/motorola.jpg" alt="Motorola Edge">
                <h3>Motorola Edge 40</h3>
                <p>256 GB · Cámara 64MP · Pantalla OLED 144Hz</p>
                <button>Agregar al carrito</button>
            </div>
        </div>

        <br>
        <a href="index.html"><button>Cerrar sesión</button></a>
    </main>
</body>
</html>
