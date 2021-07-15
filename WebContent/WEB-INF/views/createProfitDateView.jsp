<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Формирование маржинальной прибыли по месяцу">

  <form method="POST" action="${pageContext.request.contextPath}/createProfitDate">
		<div class="form-group row">
			<label for="dateReport" class="col-xs-2 col-form-label">Введите месяц и год</label>
			<div class="col-xs-5">
			<input type="month" class="form-control" id="dateReport" name="dateReport" value="${profitSum.dateReport}"> 
			</div>
		</div>
	<input type="submit" class="btn btn-submit" value="Отправить">
	<a href="${pageContext.request.contextPath}/">Отменить</a>
 </form>
</t:wrapper>