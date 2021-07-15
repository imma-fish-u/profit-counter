<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Войти">
  <p class="error">${errorString}</p>

  <form method="POST" action="${pageContext.request.contextPath}/login">
    <div class="form-group row">
      <label for="userName" class="col-xs-2 col-form-label">Имя пользователя</label>
      <div class="col-xs-5">
        <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}">
      </div>
    </div>

    <div class="form-group row">	
      <label for="password" class="col-xs-2 col-form-label">Пароль</label>
      <div class="col-xs-5">
        <input type="password" class="form-control" id="password" name="password" value="${user.password}">
      </div>
    </div>

    <div class="form-group">
			<label class="custom-control custom-checkbox">
				<input type="checkbox" class="custom-control-input">
				<span class="custom-control-indicator"></span>
				<span class="custom-control-description">Запомнить меня</span>
			</label>
    </div>

    <input type="submit" class="btn btn-submit" value= "Отправить" />
    <a href="${pageContext.request.contextPath}/">Отменить</a>
  </form>

  <p class="login-info">Имя пользователя/пароль tom/tom001 or jerry/jerry001</p>
  <p class="login-info">Если нет личного аккаунта перейдите в <a href="${pageContext.request.contextPath}/register">регистрацию</a></p>
  
</t:wrapper>