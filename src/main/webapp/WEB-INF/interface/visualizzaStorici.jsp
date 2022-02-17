<%@ page import="java.util.ArrayList" %>
<%@ page import="model.storico.Storico" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Storici</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/rs/sommario">Sommario</a>
<%
    int i=0;
    ArrayList<Storico> storici= (ArrayList<Storico>) request.getSession().getAttribute("storici");
%>
<% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
<h2>Prossima Giornata: <%=giornata%>° Giornata</h2>
<table>
    <tr>
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
            <c:forEach items="${storici}" var="storico">
                <%Storico storico = storici.get(i); i++;%>
                <tr>
                    <td><%=storico.getnGiornata()%></td>
                    <td><%=storico.getTotalePredetto()%></td>
                    <td><%=storico.getTotaleVero()%></td>
                    <td><%=(storico.getTotalePredetto()-storico.getTotaleVero())%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
