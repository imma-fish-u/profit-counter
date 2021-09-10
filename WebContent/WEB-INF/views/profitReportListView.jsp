<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="График чистой прибыли">
	
<canvas id="myChart"></canvas>

<table class="table table-striped">
    <tr>
      <th>Дата</th>
      <th>Маржинальная прибыль</th>
      <th>Операционные расходы</th>
      <th>Чистая прибыль</th>
      <th>Рентабельность чистой прибыли</th>
    </tr>
    <c:forEach items="${profitList}" var="profit" >
      <tr>
        <td>${profit.dateReport}</td>
        <td>${profit.sumMarginProfit}</td>
        <td>${profit.spendings}</td>
        <td>${profit.pureProfit}</td>
        <td><fmt:formatNumber value="${profit.pureProfit/profit.sumPrice*100}" maxFractionDigits="0"/></td>
      </tr>
    </c:forEach>
  </table>
  <script>
function renderChart(data, labels) {
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Чистая прибыль',
                data: data,
            }]
        },
    });
}

     data = ${profitArr};
     labels =  ${dateArr};
     renderChart(data, labels);


</script>
</t:wrapper>
