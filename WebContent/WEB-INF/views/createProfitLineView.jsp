<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Внести прибыль товара">

  <p style="color: red;">${errorString}</p>
  <form method="POST" action="${pageContext.request.contextPath}/createProfitLine?date=${dateStr}">
		 <input type="hidden" name="dateStr" value="${dateStr}" />
		<div class="form-group row">
			<label for="code" class="col-xs-2 col-form-label">Код продукта</label>
			<div class="col-xs-5">
				<select name="code">
				    <c:forEach items="${productList}" var="product">
				        <option value="${product.code}">${product.code}</option>
				    </c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group row">
			<label for="quantity" class="col-xs-2 col-form-label">Количество проданного товара</label>
			<div class="col-xs-5">
				<input type="text" class="form-control" id="quantity" name="quantity" value="${profit.quantity}">
			</div>
		</div>
		<input type="submit" class="btn btn-submit" value="Отправить">
		<a href="${pageContext.request.contextPath}/profitList?date=${dateStr}">Отменить</a>
  </form>
</t:wrapper>