<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Формирование отчета о прибыли">

  <p class="error">${errorString}</p>

    <form method="POST" action="${pageContext.request.contextPath}/createProfitSpendings">
      <input type="hidden" name="sumProfitDate" value="${sumProfit.dateReport}" />
        <div class="form-group row">
          <label for="sumProfitSpendings" class="col-xs-2 col-form-label">Операционные расходы</label>
          <div class="col-xs-5">
			<input type="text" class="form-control" id="sumProfitSpendings" name="sumProfitSpendings" value="${sumProfit.spendings}">
		  </div>
        </div>
		
		<input type="submit" class="btn btn-submit" value="Отправить"/>
		<a href="${pageContext.request.contextPath}/profitReport">Отменить</a>
    </form>

</t:wrapper>