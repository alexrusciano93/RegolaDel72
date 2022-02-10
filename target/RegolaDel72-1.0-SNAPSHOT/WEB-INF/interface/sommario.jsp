<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rosa</title>
</head>
<body>
<h2>La tua Rosa</h2>
<div>
    <h4>Aggiungi:</h4>
    <a href="<%=request.getContextPath()%>/rs/estrai?id=1"><span>Portieri</span></a>
    <a href="<%=request.getContextPath()%>/rs/estrai?id=2"><span>Difensori</span></a>
    <a href="<%=request.getContextPath()%>/rs/estrai?id=3"><span>Centrocampisti</span></a>
    <a href="<%=request.getContextPath()%>/rs/estrai?id=4"><span>Attaccanti</span></a>
</div><!--Seleziona giocatori divisi per ruoli-->

<%int i=0; ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri"); %>
<h4>Portieri</h4>
<table>
    <tr>
        <th>ID</th>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th>Squadra</th>
        <th>Quotazione</th>
    </tr>
    <c:choose>
        <c:when test="${portieriNull}">
            <tr>
                <td>Nessun Portiere selezionato</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${portieri}" var="portiere">
                <%Calciatore portiere = portieri.get(i++);%>
                <tr>
                    <td><%=portiere.getCod()%></td>
                    <td><%=portiere.getRuolo()%></td>
                    <td><%=portiere.getNome()%></td>
                    <td><%=portiere.getSquadra()%></td>
                    <td><%=portiere.getQuotazione()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>  <!--Portieri-->
</table>
<%i=0; ArrayList<Calciatore> difensori = (ArrayList<Calciatore>) request.getSession().getAttribute("difensori"); %>
<h4>Difensori</h4>
<table>
    <tr>
        <th>ID</th>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th>Squadra</th>
        <th>Quotazione</th>
    </tr>
    <c:choose>
        <c:when test="${difensoriNull}">
            <tr>
                <td>Nessun Difensore selezionato</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${difensori}" var="difensore">
                <%Calciatore difensore = difensori.get(i++);%>
                <tr>
                    <td><%=difensore.getCod()%></td>
                    <td><%=difensore.getRuolo()%></td>
                    <td><%=difensore.getNome()%></td>
                    <td><%=difensore.getSquadra()%></td>
                    <td><%=difensore.getQuotazione()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose> <!--Difensori-->
</table>

<%i=0; ArrayList<Calciatore> centrocampisti = (ArrayList<Calciatore>) request.getSession().getAttribute("centrocampisti"); %>
<h4>Centrocampisti</h4>
<table>
    <tr>
        <th>ID</th>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th>Squadra</th>
        <th>Quotazione</th>
    </tr>
    <c:choose>
        <c:when test="${centrocampistiNull}">
            <tr>
                <td>Nessun Centrocampista selezionato</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${centrocampisti}" var="centrocampista">
                <%Calciatore centrocampista = centrocampisti.get(i++);%>
                <tr>
                    <td><%=centrocampista.getCod()%></td>
                    <td><%=centrocampista.getRuolo()%></td>
                    <td><%=centrocampista.getNome()%></td>
                    <td><%=centrocampista.getSquadra()%></td>
                    <td><%=centrocampista.getQuotazione()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>  <!--Centrocampisti-->
</table>

<%i=0; ArrayList<Calciatore> attaccanti = (ArrayList<Calciatore>) request.getSession().getAttribute("attaccanti"); %>
<h4>Attaccanti</h4>
<table>
    <tr>
        <th>ID</th>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th>Squadra</th>
        <th>Quotazione</th>
    </tr>
    <c:choose>
        <c:when test="${attaccantiNull}">
            <tr>
                <td>Nessun Attaccante selezionato</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${attaccanti}" var="attaccante">
                <%Calciatore attaccante = attaccanti.get(i++);%>
                <tr>
                    <td><%=attaccante.getCod()%></td>
                    <td><%=attaccante.getRuolo()%></td>
                    <td><%=attaccante.getNome()%></td>
                    <td><%=attaccante.getSquadra()%></td>
                    <td><%=attaccante.getQuotazione()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose> <!--Attaccanti-->
</table>

</body>
</html>
