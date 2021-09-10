<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Маржинальная прибыль">

  <p class="error">${errorString}</p>
  <h5>Дата:</h5>
  <p>${dateStr}</p>
  <table class="table table-striped">
    <tr>
      <th>Код продукта</th>
      <th>Наименование</th>
      <th>Количество продано</th>
      <th>Цена</th>
      <th>Себестоимость</th>
      <th>Маржинальная прибыль</th>
      <th>Валовая рентабельность, %</th>
      <th>Удалить</th>
    </tr>
    <c:forEach items="${profitList}" var="profit" >
      <tr>
        <td>${profit.product.code}</td>
        <td>${profit.product.name}</td>
        <td>${profit.quantity}</td>
        <td>${profit.price}</td>
        <td>${profit.cost}</td>
        <td>${profit.marginProfit}</td>
        <td><fmt:formatNumber value="${profit.marginProfit/profit.price*100}" maxFractionDigits="0"/></td>
        <td><a href="deleteProfit?dateStr=${dateStr}&id=${profit.id}">Удалить</a></td>
      </tr>
    </c:forEach>
      <tr>
        <td>Сумма</td>
        <td></td>
        <td>${profitSum.sumQuantity}</td>
        <td>${profitSum.sumPrice}</td>
        <td>${profitSum.sumCost}</td>
        <td>${profitSum.sumMarginProfit}</td>
      </tr>
  </table>

  <a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/createProfitLine?date=${dateStr}" >Добавить товар</a>
  
    <form method="POST" action="${pageContext.request.contextPath}/profitList?date=${dateStr}">
		<input type="hidden" name="dateStr" value="${dateStr}" />
		<input type="hidden" name="revenue" value="${profitSum.sumPrice}" />
		<input type="hidden" name="marginProfit" value="${profitSum.sumMarginProfit}" />
		<div class="form-group row">
			<label for="spendings" class="col-xs-2 col-form-label">Введите операционные расходы</label>
			<div class="col-xs-5">
			<input type="text" class="form-control" id="spendings" name="spendings" value="${profitSum.spendings}"> 
			</div>
		</div>
	<input type="submit" class="btn btn-submit" value="Создать отчет за месяц">
 </form>
</t:wrapper>
