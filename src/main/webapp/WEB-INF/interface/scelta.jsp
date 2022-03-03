<%@ page import="java.util.ArrayList" %>
<%@ page import="model.calciatore.Calciatore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Scelta</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        var conta=0;
        function ctr(quale,max) {
            if (quale.checked) {
                conta++;
                if (conta > max) {
                    alert("Hai superato il max consentito!");
                    quale.checked=false;
                    conta--;
                }
            } else {
                conta>0? conta--:null;
            }
        }
    </script>

</head>
<body>
<nav class="navbar navbar-expand-lg text-dark" style="background-color: #c4a489">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="<%=request.getContextPath()%>/img/logo.png" alt="" width="100" height="100" class="d-inline-block align-text-top"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link text-decoration-none" href="<%=request.getContextPath()%>/rs/sommario">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-decoration-none" href="<%=request.getContextPath()%>/RegS/indisponibili">Regola del 72</a>

                </li>
                <li class="nav-item">
                    <a class="nav-link text-decoration-none" href="<%=request.getContextPath()%>/Modulo/indisponibili">Best Modulo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-decoration-none" href="<%=request.getContextPath()%>/RegS/storico">Storico</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-decoration-none" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
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
<%int i=0; ArrayList<Calciatore> giocatori = (ArrayList<Calciatore>) request.getSession().getAttribute("giocatori");
  int max= (int) request.getSession().getAttribute("maxGiocatori");%>

<h3><c:out value="${ruolo}"></c:out></h3>
<form action="<%=request.getContextPath()%>/rs/salva" method="post">
    <input type="submit" value="Salva">
    <table>
        <tr>
            <th>Calciatore</th>
            <th>Ruolo</th>
            <th>Squadra</th>
            <th>Quotazione</th>
        </tr>
    <c:forEach items="${giocatori}" var="giocatore">
        <%Calciatore giocatore = giocatori.get(i++);%>
            <tr>
                <td><%=giocatore.getNome()%></td>
                <td><%=giocatore.getRuolo()%></td>
                <td><%=giocatore.getSquadra()%></td>
                <td><%=giocatore.getQuotazione()%></td>
                <td><input type="checkbox" id="selezioneSquadra" name="selezioneSquadra"
                           value="<%=giocatore.getCod()%>" onclick="ctr(this,<%=max%>)"></td>
            </tr>
    </c:forEach>
    </table>
</form>
</body>
</html>
