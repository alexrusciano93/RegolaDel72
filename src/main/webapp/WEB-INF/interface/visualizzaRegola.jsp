<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Regola72</title>
</head>
<body>
<% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
<h2>Prossima Giornata: <%=giornata%>° Giornata</h2>
<a href="<%=request.getContextPath()%>/rs/sommario">Sommario</a>
<%
    Double somma=(Double) request.getSession().getAttribute("sommaConsigliati");
    int i=0;
    ArrayList<Calciatore> consigliati = (ArrayList<Calciatore>) request.getSession().getAttribute("consigliati");
%>

<table>
    <tr>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th>Media</th>
    </tr>
    <c:forEach items="${consigliati}" var="consiglio">
        <%Calciatore consiglio= consigliati.get(i++); %>
        <tr>
            <td><%=consiglio.getRuolo()%></td>
            <td><%=consiglio.getNome()%></td>
            <td><%=consiglio.getMedia()%></td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td><%=somma%></td>
    </tr>
</table>
<a href="<%=request.getContextPath()%>/RegS/salva"><span>Salva Formazione</span></a>
</body>
</html>
