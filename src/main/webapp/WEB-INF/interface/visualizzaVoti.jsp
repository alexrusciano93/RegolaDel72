<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.voto.Voto" %>
<%@ page import="model.voto.VotoDAO" %>
<%@ page import="model.calendario.Calendario" %>
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
        .alert {
            padding: 20px;
            background-color: #f44336;
            color: white;
        }
        .alert.info {background-color: #2196F3;}

        .closebtn {
            margin-left: 15px;
            color: white;
            font-weight: bold;
            float: right;
            font-size: 22px;
            line-height: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        .closebtn:hover {
            color: black;
        }
    </style>
    <title>VisualizzaVoti</title>
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
            ArrayList<Voto> voti1= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra1");
            ArrayList<Voto> voti2= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra2");
            ArrayList<Voto> voti3= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra3");
            ArrayList<Voto> voti4= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra4");
            int ultimaGiornata= (int) request.getSession().getAttribute("ultimaGiornata");
            VotoDAO votoDAO=new VotoDAO();
        %> <!--Prelevo gli ultimi 4 Voti -->
        <a class="btn btn-warning" href="<%=request.getContextPath()%>/rs/sommario">Back</a>
        <button class="btn btn-info" onclick="info()">Info</button>
        <div class="alert info" id="info" style="display: none">
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <strong>Visualizza</strong> le ultime quattro prestazioni della tua rosa.
        </div>

        <%int i=0; ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri"); %>
        <table class="table table-hover allinea">
            <tr>
                <th>Ruolo</th>
                <th>Calciatore</th>
                <th><%=ultimaGiornata-3%></th>
                <th><%=ultimaGiornata-2%></th>
                <th><%=ultimaGiornata-1%></th>
                <th><%=ultimaGiornata%></th>
            </tr> <!--Intestazione tabella-->
            <c:choose>
                <c:when test="${portieriNull}">
                    <h5>Nessun Portiere Selezionato</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${portieri}" var="portiere">
                        <%
                            Calciatore portiere = portieri.get(i++);
                            Voto a=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-3,portiere.getCod());
                            Voto b=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-2,portiere.getCod());
                            Voto c=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-1,portiere.getCod());
                            Voto d=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,portiere.getCod());
                        %>
                        <tr class="table-warning">
                            <td><%=portiere.getRuolo()%></td>
                            <td style="text-align: start"><%=portiere.getNome()%></td>
                            <td><%=a.getVoto()%></td>
                            <td><%=b.getVoto()%></td>
                            <td><%=c.getVoto()%></td>
                            <td><%=d.getVoto()%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>  <!--Portieri-->

            <%i=0; ArrayList<Calciatore> difensori = (ArrayList<Calciatore>) request.getSession().getAttribute("difensori"); %>
            <c:choose>
                <c:when test="${difensoriNull}">
                    <h5>Nessun Difensore Selezionato</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${difensori}" var="difensore">
                        <%
                            Calciatore difensore = difensori.get(i++);
                            Voto a=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-3,difensore.getCod());
                            Voto b=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-2,difensore.getCod());
                            Voto c=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-1,difensore.getCod());
                            Voto d=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,difensore.getCod());
                        %>
                        <tr class="table-success">
                            <td><%=difensore.getRuolo()%></td>
                            <td style="text-align: start"><%=difensore.getNome()%></td>
                            <td><%=a.getVoto()%></td>
                            <td><%=b.getVoto()%></td>
                            <td><%=c.getVoto()%></td>
                            <td><%=d.getVoto()%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose> <!--Difensori-->

            <%i=0; ArrayList<Calciatore> centrocampisti = (ArrayList<Calciatore>) request.getSession().getAttribute("centrocampisti"); %>
            <c:choose>
                <c:when test="${centrocampistiNull}">
                    <h5>Nessun Centrocampista Selezionato</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${centrocampisti}" var="centrocampista">
                        <%
                            Calciatore centrocampista = centrocampisti.get(i++);
                            Voto a=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-3,centrocampista.getCod());
                            Voto b=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-2,centrocampista.getCod());
                            Voto c=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-1,centrocampista.getCod());
                            Voto d=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,centrocampista.getCod());
                        %>
                        <tr class="table-info">
                            <td><%=centrocampista.getRuolo()%></td>
                            <td style="text-align: start"><%=centrocampista.getNome()%></td>
                            <td><%=a.getVoto()%></td>
                            <td><%=b.getVoto()%></td>
                            <td><%=c.getVoto()%></td>
                            <td><%=d.getVoto()%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>  <!--Centrocampisti-->

            <%i=0; ArrayList<Calciatore> attaccanti = (ArrayList<Calciatore>) request.getSession().getAttribute("attaccanti"); %>
            <c:choose>
                <c:when test="${attaccantiNull}">
                    <h5>Nessun Attaccante Selezionato</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${attaccanti}" var="attaccante">
                        <%
                            Calciatore attaccante = attaccanti.get(i++);
                            Voto a=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-3,attaccante.getCod());
                            Voto b=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-2,attaccante.getCod());
                            Voto c=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-1,attaccante.getCod());
                            Voto d=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,attaccante.getCod());
                        %>
                        <tr class="table-danger">
                            <td><%=attaccante.getRuolo()%></td>
                            <td style="text-align: start"><%=attaccante.getNome()%></td>
                            <td><%=a.getVoto()%></td>
                            <td><%=b.getVoto()%></td>
                            <td><%=c.getVoto()%></td>
                            <td><%=d.getVoto()%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose> <!--Attaccanti-->
        </table>
    </div> <!--DIV VISUALIZZA VOTI-->
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
<script>
    function info() {
        var x = document.getElementById("info");
        if (x.style.display === "none")
            x.style.display = "block";
        else
            x.style.display = "none";
    }
</script>

</body>
</html>
