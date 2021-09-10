<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Изменить товар">

  <p class="error">${errorString}</p>

  <c:if test="${not empty product}">
    <form method="POST" action="${pageContext.request.contextPath}/editProduct">
      <input type="hidden" name="code" value="${product.code}" />
        <div class="form-group row">
          <label for="name" class="col-xs-2 col-form-label">Наименование</label>
          <div class="col-xs-5">
						<input type="text" class="form-control" id="name" name="name" value="${product.name}">
					</div>
        </div>
        <div class="form-group row">
          <label for="price" class="col-xs-2 col-form-label">Цена</label>
          <div class="col-xs-5">
						<input type="text" class="form-control" id="price" name="price" value="${product.price}">
					</div>
				</div>
		        <div class="form-group row">
          <label for="price" class="col-xs-2 col-form-label">Себестоимость</label>
          <div class="col-xs-5">
						<input type="text" class="form-control" id="cost" name="cost" value="${product.cost}">
					</div>
				</div>
				<input type="submit" class="btn btn-submit" value="Отправить"/>
				<a href="${pageContext.request.contextPath}/productList">Отменить</a>
    </form>
  </c:if>
</t:wrapper>