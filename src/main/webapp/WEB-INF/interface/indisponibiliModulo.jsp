<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rosa</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/rs/sommario">Sommario</a>
<% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
<h2>Prossima Giornata: <%=giornata%>° Giornata</h2>
<c:if test="${impossibileCalcolare}">
    <div>
        <p>Impossibile caricati meno di 4 Giornate!</p>
        <% request.getSession().setAttribute("impossibileCalcolare",false);%>
    </div>
</c:if>

<h4>Indica Indisponibili per la prossima giornata:</h4>
<%
    int i=0;
    ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri");
    ArrayList<Calciatore> difensori = (ArrayList<Calciatore>) request.getSession().getAttribute("difensori");
    ArrayList<Calciatore> centrocampisti = (ArrayList<Calciatore>) request.getSession().getAttribute("centrocampisti");
    ArrayList<Calciatore> attaccanti = (ArrayList<Calciatore>) request.getSession().getAttribute("attaccanti");
%>
<form action="<%=request.getContextPath()%>/Modulo/best" method="post">
    <table>
        <tr>
            <th>Ruolo</th>
            <th>Calciatore</th>
            <th>Indisponibile</th>
        </tr>
        <c:forEach items="${portieri}" var="portiere">
            <%Calciatore portiere = portieri.get(i); i++;%>
            <tr>
                <td><%=portiere.getRuolo()%></td>
                <td><%=portiere.getNome()%></td>
                <td><input type="checkbox" id="indisponibileP" name="indisponibile"
                           value="<%=portiere.getCod()%>"></td>
            </tr>
        </c:forEach>
        <% i=0;%>
        <c:forEach items="${difensori}" var="difensore">
            <%Calciatore difensore = difensori.get(i); i++;%>
            <tr>
                <td><%=difensore.getRuolo()%></td>
                <td><%=difensore.getNome()%></td>
                <td><input type="checkbox" id="indisponibileD" name="indisponibile"
                           value="<%=difensore.getCod()%>"></td>
            </tr>
        </c:forEach>
        <% i=0;%>
        <c:forEach items="${centrocampisti}" var="centrocampista">
            <%Calciatore centrocampista = centrocampisti.get(i); i++;%>
            <tr>
                <td><%=centrocampista.getRuolo()%></td>
                <td><%=centrocampista.getNome()%></td>
                <td><input type="checkbox" id="indisponibileC" name="indisponibile"
                           value="<%=centrocampista.getCod()%>"></td>
            </tr>
        </c:forEach>
        <% i=0;%>
        <c:forEach items="${attaccanti}" var="attaccante">
            <%Calciatore attaccante = attaccanti.get(i); i++;%>
            <tr>
                <td><%=attaccante.getRuolo()%></td>
                <td><%=attaccante.getNome()%></td>
                <td><input type="checkbox" id="indisponibileA" name="indisponibile"
                           value="<%=attaccante.getCod()%>"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Consiglia">
</form>

</body>
</html>
