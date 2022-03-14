<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.calendario.Calendario" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #f5ece2;
        }
        .text-size{
            font-size: 26px;
        }
        .allinea{
            text-align: center;
        }
    </style>
    <title>BestModulo</title>
</head>
<body>
<nav class="navbar navbar-expand-lg text-dark" style="background-color: #eb9021">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="<%=request.getContextPath()%>/img/logo.png" alt="" width="100" height="100" class="d-inline-block align-text-top"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 text-size">
                <li class="nav-item">
                    <a class="nav-link text-decoration-none text-dark" href="<%=request.getContextPath()%>/rs/sommario">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-decoration-none text-dark" href="<%=request.getContextPath()%>/RegS/indisponibili">Regola del 72</a>

                </li>
                <li class="nav-item">
                    <a class="nav-link text-decoration-none text-dark" href="<%=request.getContextPath()%>/Modulo/indisponibili">Best Modulo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-decoration-none text-dark" href="<%=request.getContextPath()%>/RegS/storico">Storico</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-decoration-none text-dark" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Voti
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item text-decoration-none" href="<%=request.getContextPath()%>/vs/carica">Carica Voti</a></li>
                        <li><a class="dropdown-item text-decoration-none" href="<%=request.getContextPath()%>/vs/visualizza">Visualizza Voti</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav><!-- NAVBAR -->

<% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7 p-3 text-dark">
        <%
            Double somma=(Double) request.getSession().getAttribute("sommaConsigliati");
            int i=0;
            ArrayList<Calciatore> consigliati = (ArrayList<Calciatore>) request.getSession().getAttribute("consigliati");
        %> <!--Carico consigliati-->
        <table class="table table-hover">
            <tr class="table-danger allinea">
                <th>Ruolo</th>
                <th>Calciatore</th>
                <th>Media</th>
            </tr>
            <%DecimalFormat df = new DecimalFormat("#.00");%>
            <c:forEach items="${consigliati}" var="consiglio">
                <%Calciatore consiglio= consigliati.get(i++); %>
                <tr class="table-light">
                    <td><button class="btn btn-primary allinea"><%=consiglio.getRuolo()%></button></td>
                    <td><%=consiglio.getNome()%></td>
                    <td class="allinea"><%=df.format(consiglio.getMedia())%></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td style="text-align: end">Totale</td>
                <td class="table-danger text-danger allinea"><%=df.format(somma)%></td>
            </tr>
        </table> <!--Formazione BEST MODULO-->
        <a class="btn btn-danger" href="<%=request.getContextPath()%>/RegS/salva?reg=0"><span>Salva Formazione</span></a>
    </div> <!--DIV VISUALIZZA MODULO -->
    <div class="col-sm-3 p-3 text-dark">
        <%
            ArrayList<Calendario> partite= (ArrayList<Calendario>) request.getSession().getAttribute("partite");
            i=0;
        %>
        <h2>Prossima: <%=giornata%>° Giornata</h2>
        <table class="table table-hover table-striped">
            <c:forEach items="${partite}" var="partita">
                <%Calendario partita = partite.get(i++);%>
                <tr>
                    <td><%=partita.getCasa().getNome()%></td>
                    <td>-</td>
                    <td><%=partita.getTrasferta().getNome()%></td>
                </tr>
            </c:forEach>
        </table>
    </div> <!--DIV PROSSIMA GIORNATA-->
    <div class="col-sm-1 p-3 text-dark"></div>
</div>
</body>
</html>
