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
<%int i=0; ArrayList<Calciatore> giocatori = (ArrayList<Calciatore>) request.getSession().getAttribute("giocatori");
  int max= (int) request.getSession().getAttribute("maxGiocatori");%>
<a href="<%=request.getContextPath()%>/rs/sommario">Rosa Attuale</a>
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