<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Регистрация">
  <p class="error">${errorString}</p>

  <form method="POST" action="${pageContext.request.contextPath}/register">
    <div class="form-group row">
      <label for="userName" class="col-xs-2 col-form-label">Имя пользователя</label>
      <div class="col-xs-5">
        <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}">
      </div>
    </div>

    <div class="form-group row">
      <label for="email" class="col-xs-2 col-form-label">Электронная почта</label>
      <div class="col-xs-5">
        <input type="text" class="form-control" id="email" name="email" value="${user.email}">
      </div>
    </div>

    <div class="form-group row">
      <label for="password" class="col-xs-2 col-form-label">Пароль</label>
      <div class="col-xs-5">
        <input type="password" class="form-control" id="password" name="password" value="${user.password}">
      </div>
    </div>

    <input type="submit" class="btn btn-submit" value= "Отправить" />
    <a href="${pageContext.request.contextPath}/">Отменить</a>
  </form>
</t:wrapper>