<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Список товаров">

  <p class="error">${errorString}</p>

  <table class="table table-striped">
    <tr>
      <th>Код</th>
      <th>Наименование</th>
      <th>Цена</th>
      <th>Себестоимость</th>
      <th>Изменить</th>
      <th>Удалить</th>
    </tr>
    <c:forEach items="${productList}" var="product" >
      <tr>
        <td>${product.code}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.cost}</td>
        <td>
          <a href="editProduct?code=${product.code}">Изменить</a>
        </td>
        <td>
          <a href="deleteProduct?code=${product.code}">Удалить</a>
        </td>
      </tr>
    </c:forEach>
  </table>

  <a class="btn btn-primary" role="button" href="createProduct" >Добавить товар</a>

</t:wrapper>