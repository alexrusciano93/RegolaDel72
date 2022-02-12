<%@ page import="model.calciatore.Calciatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.voto.Voto" %>
<%@ page import="model.voto.VotoDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VisualizzaVoti</title>
</head>
<body>
<%ArrayList<Voto> voti= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra");
  int ultimaGiornata= (int) request.getSession().getAttribute("ultimaGiornata");
  VotoDAO votoDAO=new VotoDAO();
%>
<%int i=0; ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri"); %>
<h4>Portieri</h4>
<table>
    <tr>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th><%=ultimaGiornata%></th>
    </tr>
    <c:choose>
        <c:when test="${portieriNull}">
            <h5>Nessun Portiere Selezionato</h5>
        </c:when>
        <c:otherwise>
            <c:forEach items="${portieri}" var="portiere">
                <%
                    Calciatore portiere = portieri.get(i++);
                    Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,portiere.getCod());
                  %>
                <tr>
                    <td><%=portiere.getRuolo()%></td>
                    <td><%=portiere.getNome()%></td>
                    <td><%=y.getVoto()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>  <!--Portieri-->
</table> <!--Portieri-->
<%i=0; ArrayList<Calciatore> difensori = (ArrayList<Calciatore>) request.getSession().getAttribute("difensori"); %>
<h4>Difensori</h4>
<table>
    <tr>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th><%=ultimaGiornata%></th>
    </tr>
    <c:choose>
        <c:when test="${difensoriNull}">
            <h5>Nessun Difensore Selezionato</h5>
        </c:when>
        <c:otherwise>
            <c:forEach items="${difensori}" var="difensore">
                <%
                    Calciatore difensore = difensori.get(i++);
                    Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,difensore.getCod());
                %>
                <tr>
                    <td><%=difensore.getRuolo()%></td>
                    <td><%=difensore.getNome()%></td>
                    <td><%=y.getVoto()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose> <!--Difensori-->
</table> <!--Difensori-->

<%i=0; ArrayList<Calciatore> centrocampisti = (ArrayList<Calciatore>) request.getSession().getAttribute("centrocampisti"); %>
<h4>Centrocampisti</h4>
<table>
    <tr>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th><%=ultimaGiornata%></th>
    </tr>
    <c:choose>
        <c:when test="${centrocampistiNull}">
            <h5>Nessun Centrocampista Selezionato</h5>
        </c:when>
        <c:otherwise>
            <c:forEach items="${centrocampisti}" var="centrocampista">
                <%
                    Calciatore centrocampista = centrocampisti.get(i++);
                    Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,centrocampista.getCod());
                %>
                <tr>
                    <td><%=centrocampista.getRuolo()%></td>
                    <td><%=centrocampista.getNome()%></td>
                    <td><%=y.getVoto()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>  <!--Centrocampisti-->
</table> <!--Centrocampisti-->

<%i=0; ArrayList<Calciatore> attaccanti = (ArrayList<Calciatore>) request.getSession().getAttribute("attaccanti"); %>
<h4>Attaccanti</h4>
<table>
    <tr>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th><%=ultimaGiornata%></th>
    </tr>
    <c:choose>
        <c:when test="${attaccantiNull}">
            <h5>Nessun Attaccante Selezionato</h5>
        </c:when>
        <c:otherwise>
            <c:forEach items="${attaccanti}" var="attaccante">
                <%
                    Calciatore attaccante = attaccanti.get(i++);
                    Voto y=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,attaccante.getCod());

                %>
                <tr>
                    <td><%=attaccante.getRuolo()%></td>
                    <td><%=attaccante.getNome()%></td>
                    <td><%=y.getVoto()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose> <!--Attaccanti-->
</table> <!--Attaccanti-->


</body>
</html>
