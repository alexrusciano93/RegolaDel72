<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- mobile metas -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Regola del 72</title>
    <style>
        body {
            background-color: #f5ece2;
        }
    </style>
</head>
<body>

<div class="container p-5 my-5 text-white text-center " style="background-color: #eb9021">
    <a class="navbar-brand" href="#">
        <img src="./img/logo.png" alt="" width="400" height="400" class="d-inline-block align-text-top rounded mx-auto d-block">
    </a>
    <h1>Crea la tua Rosa</h1>
    <a href="<%=request.getContextPath()%>/rs/prima" class="text-decoration-none text-light"><span>Iniziamo</span></a>
    <div class="spinner-border text-warning align-content-center" role="status" hidden>
        <span class="visually-hidden">Loading...</span>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>