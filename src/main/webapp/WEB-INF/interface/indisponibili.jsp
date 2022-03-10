<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.calendario.Calendario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </style>
    <title>Rosa</title>
</head>
<body>
<nav class="navbar navbar-expand-lg text-dark" style="background-color: #eb9021">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="<%=request.getContextPath()%>/img/logo.png" alt="" width="100" height="100" class="d-inline-block align-text-top"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
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

<c:if test="${impossibileCalcolare}">
    <div>
        <p>Impossibile caricati meno di 4 Giornate!</p>
        <% request.getSession().setAttribute("impossibileCalcolare",false);%>
    </div>
</c:if>

<% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7 p-3 text-dark">
        <h4>Indica Indisponibili per la prossima giornata:</h4>
        <%
            int i=0;
            ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri");
            ArrayList<Calciatore> difensori = (ArrayList<Calciatore>) request.getSession().getAttribute("difensori");
            ArrayList<Calciatore> centrocampisti = (ArrayList<Calciatore>) request.getSession().getAttribute("centrocampisti");
            ArrayList<Calciatore> attaccanti = (ArrayList<Calciatore>) request.getSession().getAttribute("attaccanti");
        %>  <!--Carico gli array dei giocatori-->
        <form action="<%=request.getContextPath()%>/RegS/regola" method="post">
            <table class="table table-hover">
                <tr>
                    <th>Ruolo</th>
                    <th>Calciatore</th>
                    <th>Indisponibile</th>
                </tr>
                <c:forEach items="${portieri}" var="portiere">
                    <%Calciatore portiere = portieri.get(i); i++;%>
                    <tr class="table-warning">
                        <td><%=portiere.getRuolo()%></td>
                        <td><%=portiere.getNome()%></td>
                        <td><input type="checkbox" id="indisponibileP" name="indisponibile"
                                   value="<%=portiere.getCod()%>"></td>
                    </tr>
                </c:forEach>
                <% i=0;%>
                <c:forEach items="${difensori}" var="difensore">
                    <%Calciatore difensore = difensori.get(i); i++;%>
                    <tr class="table-success">
                        <td><%=difensore.getRuolo()%></td>
                        <td><%=difensore.getNome()%></td>
                        <td><input type="checkbox" id="indisponibileD" name="indisponibile"
                                   value="<%=difensore.getCod()%>"></td>
                    </tr>
                </c:forEach>
                <% i=0;%>
                <c:forEach items="${centrocampisti}" var="centrocampista">
                    <%Calciatore centrocampista = centrocampisti.get(i); i++;%>
                    <tr class="table-info">
                        <td><%=centrocampista.getRuolo()%></td>
                        <td><%=centrocampista.getNome()%></td>
                        <td><input type="checkbox" id="indisponibileC" name="indisponibile"
                                   value="<%=centrocampista.getCod()%>"></td>
                    </tr>
                </c:forEach>
                <% i=0;%>
                <c:forEach items="${attaccanti}" var="attaccante">
                    <%Calciatore attaccante = attaccanti.get(i); i++;%>
                    <tr class="table-danger">
                        <td><%=attaccante.getRuolo()%></td>
                        <td><%=attaccante.getNome()%></td>
                        <td><input type="checkbox" id="indisponibileA" name="indisponibile"
                                   value="<%=attaccante.getCod()%>"></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Consiglia">
        </form> <!--Form per Scelta Indisponibili-->
    </div> <!--DIV INDICA INDISPONIBILI-->
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
