<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Удалить данные о прибыли товара товар">
  <p class="error">${errorString}</p>
  <a href="profitList?dateStr=${dateStr}">Вернуться к отчету о валовой прибыли</a>
</t:wrapper>