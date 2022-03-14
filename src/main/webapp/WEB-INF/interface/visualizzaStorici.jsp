<%@ page import="java.util.ArrayList" %>
<%@ page import="model.storico.Storico" %>
<%@ page import="model.calendario.Calendario" %>
<%@ page import="java.text.DecimalFormat" %>
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
        .text-size{
            font-size: 26px;
        }
        .allinea{
            text-align: center;
        }
    </style>
    <title>Storici</title>
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
        <%int i=0; ArrayList<Storico> storici= (ArrayList<Storico>) request.getSession().getAttribute("storici");
        %>
        <h3>Storici - Regola del 72 </h3>
        <table class="table table-hover table-striped">
            <tr class="table-danger allinea">
                <td>Giornata</td>
                <td>Totale Predetto</td>
                <td>Totale Realizzato</td>
                <td>Scarto</td>
            </tr>
            <c:choose>
                <c:when test="${storicoNull}">
                    <h5>Nessun Storico Salvato</h5>
                    <% request.getSession().setAttribute("storicoNull",false);%>
                </c:when>
                <c:otherwise>
                    <%DecimalFormat df = new DecimalFormat("#.00");%>
                    <c:forEach items="${storici}" var="storico">
                        <%Storico storico = storici.get(i); i++;%>
                        <tr class="allinea">
                            <td><%=storico.getnGiornata()%></td>
                            <td><%=df.format(storico.getTotalePredetto())%></td>
                            <td><%=storico.getTotaleVero()%></td>
                            <%Double scarto=storico.getTotalePredetto()-storico.getTotaleVero();
                                if (scarto<0)
                                    scarto=scarto*(-1);%>
                            <td><%=df.format(scarto)%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>
    </div> <!--DIV VISUALIZZA STORICI REGOLA-->
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
<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7 p-3 text-dark">
        <%
            i=0;
            ArrayList<Storico> storiciModulo= (ArrayList<Storico>) request.getSession().getAttribute("storiciModulo");
        %>
        <h3>Storici - Best Modulo </h3>
        <table class="table table-hover table-striped allinea">
            <tr class="table-danger">
                <td>Giornata</td>
                <td>Totale Predetto</td>
                <td>Totale Realizzato</td>
                <td>Scarto</td>
            </tr>
            <c:choose>
                <c:when test="${storicoModuloNull}">
                    <h5>Nessun Storico Salvato</h5>
                    <% request.getSession().setAttribute("storicoModuloNull",false);%>
                </c:when>
                <c:otherwise>
                    <%DecimalFormat df = new DecimalFormat("#.00");%>
                    <c:forEach items="${storiciModulo}" var="storicoM">
                        <%Storico storicoM = storiciModulo.get(i); i++;%>
                        <tr class="allinea">
                            <td><%=storicoM.getnGiornata()%></td>
                            <td><%=df.format(storicoM.getTotalePredetto())%></td>
                            <td><%=storicoM.getTotaleVero()%></td>
                            <%Double scarto=storicoM.getTotalePredetto()-storicoM.getTotaleVero();
                            if (scarto<0)
                                scarto=scarto*(-1);%>
                            <td><%=df.format(scarto)%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>
    </div> <!--DIV VISUALIZZA STORICI MODULO-->
    <div class="col-sm-4 p-3 text-dark"></div>
</div>
</body>
</html>
