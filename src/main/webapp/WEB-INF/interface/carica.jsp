<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CaricaVoti</title>
</head>

<body>
<a href="<%=request.getContextPath()%>/rs/sommario">Sommario</a>
<div>
    <% int giornata= (int) request.getSession().getAttribute("prossimaGiornata");%>
    <h2>Prossima Giornata: <%=giornata%>° Giornata</h2>
    <h4>Seleziona la giornata da Caricare:</h4>
    <% for(int i=1; i<39; i++){%>
        <a href="<%=request.getContextPath()%>/vs/caricaVoti?numGiornata=<%=i%>"><span><%=i%></span></a>
    <%}%>
</div>
</body>
</html>
