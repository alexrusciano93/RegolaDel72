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

<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7 p-3 text-dark">
        <h2>La tua Rosa</h2>
        <h4>Aggiungi Calciatori:</h4>
        <div class="btn-group"><!-- Alert quando raggiungo il max per ogni reparto-->
            <a href="<%=request.getContextPath()%>/rs/estrai?id=1" class="btn btn-warning"><span>Portieri</span></a>
            <a href="<%=request.getContextPath()%>/rs/estrai?id=2" class="btn btn-success"><span>Difensori</span></a>
            <a href="<%=request.getContextPath()%>/rs/estrai?id=3" class="btn btn-info"><span>Centrocampisti</span></a>
            <a href="<%=request.getContextPath()%>/rs/estrai?id=4" class="btn btn-danger"><span>Attaccanti</span></a>
        </div> <!-- Aggiunta di Calciatori-->


        <div>
            <%int i=0; ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri"); %>
            <h4>Portieri</h4>
            <table class="table table-hover">
                <tr class="table-warning">
                    <th>ID</th>
                    <th>Ruolo</th>
                    <th>Calciatore</th>
                    <th>Squadra</th>
                    <th>Quotazione</th>
                </tr>
                <c:choose>
                    <c:when test="${portieriNull}">
                        <h5>Nessun Portiere Selezionato</h5>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${portieri}" var="portiere">
                            <%Calciatore portiere = portieri.get(i++);%>
                            <tr class="table-warning">
                                <td><%=portiere.getCod()%></td>
                                <td><%=portiere.getRuolo()%></td>
                                <td><%=portiere.getNome()%></td>
                                <td><%=portiere.getSquadra()%></td>
                                <td><%=portiere.getQuotazione()%></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>  <!--Portieri-->
            </table> <!--Portieri-->
            <%i=0; ArrayList<Calciatore> difensori = (ArrayList<Calciatore>) request.getSession().getAttribute("difensori"); %>
            <h4>Difensori</h4>
            <table class="table table-hover">
                <tr class="table-success">
                    <th>ID</th>
                    <th>Ruolo</th>
                    <th>Calciatore</th>
                    <th>Squadra</th>
                    <th>Quotazione</th>
                </tr>
                <c:choose>
                    <c:when test="${difensoriNull}">
                        <h5>Nessun Difensore Selezionato</h5>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${difensori}" var="difensore">
                            <%Calciatore difensore = difensori.get(i++);%>
                            <tr class="table-success">
                                <td><%=difensore.getCod()%></td>
                                <td><%=difensore.getRuolo()%></td>
                                <td><%=difensore.getNome()%></td>
                                <td><%=difensore.getSquadra()%></td>
                                <td><%=difensore.getQuotazione()%></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose> <!--Difensori-->
            </table> <!--Difensori-->

            <%i=0; ArrayList<Calciatore> centrocampisti = (ArrayList<Calciatore>) request.getSession().getAttribute("centrocampisti"); %>
            <h4>Centrocampisti</h4>
            <table class="table table-hover">
                <tr class="table-info">
                    <th>ID</th>
                    <th>Ruolo</th>
                    <th>Calciatore</th>
                    <th>Squadra</th>
                    <th>Quotazione</th>
                </tr>
                <c:choose>
                    <c:when test="${centrocampistiNull}">
                        <h5>Nessun Centrocampista Selezionato</h5>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${centrocampisti}" var="centrocampista">
                            <%Calciatore centrocampista = centrocampisti.get(i++);%>
                            <tr class="table-info">
                                <td><%=centrocampista.getCod()%></td>
                                <td><%=centrocampista.getRuolo()%></td>
                                <td><%=centrocampista.getNome()%></td>
                                <td><%=centrocampista.getSquadra()%></td>
                                <td><%=centrocampista.getQuotazione()%></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>  <!--Centrocampisti-->
            </table> <!--Centrocampisti-->

            <%i=0; ArrayList<Calciatore> attaccanti = (ArrayList<Calciatore>) request.getSession().getAttribute("attaccanti"); %>
            <h4>Attaccanti</h4>
            <table class="table table-hover">
                <tr class="table-danger">
                    <th>ID</th>
                    <th>Ruolo</th>
                    <th>Calciatore</th>
                    <th>Squadra</th>
                    <th>Quotazione</th>
                </tr>
                <c:choose>
                    <c:when test="${attaccantiNull}">
                        <h5>Nessun Attaccante Selezionato</h5>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${attaccanti}" var="attaccante">
                            <%Calciatore attaccante = attaccanti.get(i++);%>
                            <tr class="table-danger">
                                <td><%=attaccante.getCod()%></td>
                                <td><%=attaccante.getRuolo()%></td>
                                <td><%=attaccante.getNome()%></td>
                                <td><%=attaccante.getSquadra()%></td>
                                <td><%=attaccante.getQuotazione()%></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose> <!--Attaccanti-->
            </table> <!--Attaccanti-->
        </div>
    </div> <!--DIV ROSA ATTUALE-->
    <div class="col-sm-3 p-3 text-dark">
        <%
            int giornata= (int) request.getSession().getAttribute("prossimaGiornata");
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
