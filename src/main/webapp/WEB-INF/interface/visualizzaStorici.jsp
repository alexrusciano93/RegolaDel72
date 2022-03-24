<%@ page import="java.util.ArrayList" %>
<%@ page import="model.storico.Storico" %>
<%@ page import="model.calendario.Calendario" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.math.RoundingMode" %>
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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
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
        <table class="table table-hover table-striped" id="tableRegola">
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
                    <c:forEach items="${storici}" var="storico">
                        <%Storico storico = storici.get(i); i++;%>
                        <tr class="allinea">
                            <td><%=storico.getnGiornata()%></td>
                            <%BigDecimal bd = new BigDecimal(storico.getTotalePredetto()).setScale(2, RoundingMode.HALF_UP);%>
                            <td id="totalePredetto"><%=bd%></td>
                            <td id="totaleVero"><%=storico.getTotaleVero()%></td>
                            <%Double scarto=storico.getTotalePredetto()-storico.getTotaleVero();
                                if (scarto<0)
                                    scarto=scarto*(-1);%>
                            <%BigDecimal bd2 = new BigDecimal(scarto).setScale(2, RoundingMode.HALF_UP);%>
                            <td><%=bd2%></td>
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
</div> <!--1°riga - tabellaRegola e prossima giornata-->
<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7">
        <canvas id="canvas"></canvas>
    </div>
    <div class="col-sm-4 p-3 text-dark"></div>
</div> <!--2°riga - grafico Regola-->
<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7 p-3 text-dark">
        <%
            i=0;
            ArrayList<Storico> storiciModulo= (ArrayList<Storico>) request.getSession().getAttribute("storiciModulo");
        %>
        <h3>Storici - Best Modulo </h3>
        <table class="table table-hover table-striped allinea" id="tableModulo">
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
                    <c:forEach items="${storiciModulo}" var="storicoM">
                        <%Storico storicoM = storiciModulo.get(i); i++;%>
                        <tr class="allinea">
                            <td><%=storicoM.getnGiornata()%></td>
                            <%BigDecimal bd3 = new BigDecimal(storicoM.getTotalePredetto()).setScale(2, RoundingMode.HALF_UP);%>
                            <td id="totalePredetto2"><%=bd3%></td>
                            <td id="totaleVero2"><%=storicoM.getTotaleVero()%></td>
                            <%Double scarto=storicoM.getTotalePredetto()-storicoM.getTotaleVero();
                            if (scarto<0)
                                scarto=scarto*(-1);%>
                            <%BigDecimal bd4 = new BigDecimal(scarto).setScale(2, RoundingMode.HALF_UP);%>
                            <td><%=bd4%></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>
    </div> <!--DIV VISUALIZZA STORICI MODULO-->
    <div class="col-sm-4 p-3 text-dark"></div>
</div> <!--3°riga - tabellaModulo-->
<div class="row">
    <div class="col-sm-1 p-3 text-dark"></div>
    <div class="col-sm-7">
        <canvas id="canvas2"></canvas>
    </div>
    <div class="col-sm-4 p-3 text-dark"></div>
</div> <!--4°riga - grafico Modulo-->
<script>
    var totPredetto=[];
    var totVero=[];
    var Table = document.getElementById('tableRegola');
    var rowLength = Table.rows.length;
    var i;
    for (i = 0; i < rowLength; i++){
        var oCells = Table.rows.item(i).cells;
        var cellLength = oCells.length;
        var j;
        for(j = 0; j < cellLength; j++){
            var cellVal = oCells.item(j).innerHTML;
            if (oCells.item(j).getAttribute("id")=='totalePredetto'){
                totPredetto.push(cellVal)
            }
            if (oCells.item(j).getAttribute("id")=='totaleVero') {
                totVero.push(cellVal)
            }
        }
    }
    var totPredettoModulo=[];
    var totVeroModulo=[];
    var TableModulo = document.getElementById('tableModulo');
    var rowLengthModulo = TableModulo.rows.length;
    for (i = 0; i < rowLengthModulo; i++){
        var oCellsModulo = TableModulo.rows.item(i).cells;
        var cellLengthModulo = oCellsModulo.length;
        for( j = 0; j < cellLengthModulo; j++){
            var cellValModulo = oCellsModulo.item(j).innerHTML;
            if (oCellsModulo.item(j).getAttribute("id")=='totalePredetto2'){
                totPredettoModulo.push(cellValModulo)
            }
            if (oCellsModulo.item(j).getAttribute("id")=='totaleVero2'){
                totVeroModulo.push(cellValModulo)
            }
        }
    }
    function myFunction(value) {parseFloat(value);}
    totPredetto.forEach(myFunction);
    totVero.forEach(myFunction);
    totPredettoModulo.forEach(myFunction);
    totVeroModulo.forEach(myFunction)


    var lineChartData = {
        labels: ["5", "6", "7", "8","9","10",
            "11", "12", "13", "14", "15", "16", "17", "18","19","20",
            "21", "22", "23", "24", "25", "26", "27", "28","29","30",
            "31", "32", "33", "34", "35", "36", "37", "38"],
        datasets: [{
            fillColor: "rgba(220,220,220,0)",
            strokeColor: "rgba(220,180,0,1)",
            pointColor: "rgba(220,180,0,1)",
            data: totPredetto
        }, {
            fillColor: "rgba(151,187,205,0)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            data: totVero
        }]
    }
    var lineChartData2 = {
        labels: ["5", "6", "7", "8","9","10",
            "11", "12", "13", "14", "15", "16", "17", "18","19","20",
            "21", "22", "23", "24", "25", "26", "27", "28","29","30",
            "31", "32", "33", "34", "35", "36", "37", "38"],
        datasets: [{
            fillColor: "rgba(220,220,220,0)",
            strokeColor: "rgba(220,180,0,1)",
            pointColor: "rgba(220,180,0,1)",
            data: totPredettoModulo
        }, {
            fillColor: "rgba(151,187,205,0)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            data: totVeroModulo
        }]
    }
    Chart.defaults.global.animationSteps = 50;
    Chart.defaults.global.tooltipYPadding = 16;
    Chart.defaults.global.tooltipCornerRadius = 0;
    Chart.defaults.global.tooltipTitleFontStyle = "normal";
    Chart.defaults.global.tooltipFillColor = "rgba(0,160,0,0.8)";
    Chart.defaults.global.animationEasing = "easeOutBounce";
    Chart.defaults.global.responsive = true;
    Chart.defaults.global.scaleLineColor = "black";
    Chart.defaults.global.scaleFontSize = 16;

    var ctx = document.getElementById("canvas").getContext("2d");
    var LineChartDemo = new Chart(ctx).Line(lineChartData, {
        pointDotRadius: 10,
        bezierCurve: false,
        scaleShowVerticalLines: false,
        scaleGridLineColor: "black"
    });
    var ctx2 = document.getElementById("canvas2").getContext("2d");
    var LineChartDemo2 = new Chart(ctx2).Line(lineChartData2, {
        pointDotRadius: 10,
        bezierCurve: false,
        scaleShowVerticalLines: false,
        scaleGridLineColor: "black"
    });

</script>


<!--
<script>
const labels = ['5', '6', '7', '8', '9', '10',
        '11', '12', '13', '14', '15', '16', '17','18', '19', '20',
        '21', '22', '23', '24', '25', '26', '27', '28', '29', '30',
        '31', '32', '33', '34', '35', '36', '37', '38',
    ];

    const data = {
        labels: labels,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: totPredetto,
        }]
    };
    const data2 = {
        labels: labels,
        datasets: [{
            label: 'My Second dataset',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: totVero,
        }]
    };

    const config = {
        type: 'line',
        data: data, data2,
        options: {}
    };
</script>
<script>
    const myChart = new Chart(
        document.getElementById('canvas'),
        config
    );





</script>-->


</body>
</html>
