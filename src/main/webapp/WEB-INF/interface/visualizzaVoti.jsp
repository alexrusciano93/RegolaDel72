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
<% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
<h2>Prossima Giornata: <%=giornata%>° Giornata</h2>
<a href="<%=request.getContextPath()%>/rs/sommario">Sommario</a>
<%
    ArrayList<Voto> voti1= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra1");
    ArrayList<Voto> voti2= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra2");
    ArrayList<Voto> voti3= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra3");
    ArrayList<Voto> voti4= (ArrayList<Voto>) request.getSession().getAttribute("votiSquadra4");
    int ultimaGiornata= (int) request.getSession().getAttribute("ultimaGiornata");
    VotoDAO votoDAO=new VotoDAO();
%>
<%int i=0; ArrayList<Calciatore> portieri = (ArrayList<Calciatore>) request.getSession().getAttribute("portieri"); %>
<table>
    <tr>
        <th>Ruolo</th>
        <th>Calciatore</th>
        <th><%=ultimaGiornata-3%></th>
        <th><%=ultimaGiornata-2%></th>
        <th><%=ultimaGiornata-1%></th>
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
                    Voto a=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-3,portiere.getCod());
                    Voto b=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-2,portiere.getCod());
                    Voto c=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata-1,portiere.getCod());
                    Voto d=votoDAO.doRetrieveByCalciatoreAndGiornata(ultimaGiornata,portiere.getCod());
                  %>
                <tr>
                    <td><%=portiere.getRuolo()%></td>
                    <td><%=portiere.getNome()%></td>
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
                <tr>
                    <td><%=difensore.getRuolo()%></td>
                    <td><%=difensore.getNome()%></td>
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
                <tr>
                    <td><%=centrocampista.getRuolo()%></td>
                    <td><%=centrocampista.getNome()%></td>
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
                <tr>
                    <td><%=attaccante.getRuolo()%></td>
                    <td><%=attaccante.getNome()%></td>
                    <td><%=a.getVoto()%></td>
                    <td><%=b.getVoto()%></td>
                    <td><%=c.getVoto()%></td>
                    <td><%=d.getVoto()%></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose> <!--Attaccanti-->
</table>


</body>
</html>
