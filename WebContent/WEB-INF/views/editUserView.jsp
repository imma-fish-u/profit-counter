<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Изменить данные пользователя">

  <p class="error">${errorString}</p>

  <c:if test="${not empty user}">
    <form method="POST" action="${pageContext.request.contextPath}/editUser">
      <input type="hidden" name="Id" value="${user.id}" />
        <div class="form-group row">
          <label for="User_Name" class="col-xs-2 col-form-label">Имя пользователя</label>
          <div class="col-xs-5">
			<input type="text" class="form-control" id="User_Name" name="User_Name" value="${user.userName}">
		  </div>
        </div>
        <div class="form-group row">
          <label for="Password" class="col-xs-2 col-form-label">Пароль</label>
          <div class="col-xs-5">
			<input type="password" class="form-control" id="Password" name="Password" value="${user.password}">
		  </div>
	    </div>
		<div class="form-group row">
          <label for="Email" class="col-xs-2 col-form-label">Электронная почта</label>
          <div class="col-xs-5">
			<input type="text" class="form-control" id="Email" name="Email" value="${user.email}">
		  </div>
		</div>
		<div class="form-group row">
          <label for="Role" class="col-xs-2 col-form-label">Роль</label>
          <div class="col-xs-5">
			<label><input type="radio" class="form-control" id="Role" name="Role" value="${'admin'}">Администратор</label>
			<label><input type="radio" class="form-control" id="Role" name="Role" value="${'user'}">Пользователь</label>
		  </div>
		</div>
		<div class="form-group row">
          <label for="Accepted" class="col-xs-2 col-form-label">Доступ</label>
          <div class="col-xs-5">
			<label><input type="radio" class="form-control" id="Accepted" name="Accepted" value="${true}">Предоставить</label>
			<label><input type="radio" class="form-control" id="Accepted" name="Accepted" value="${false}">Отклонить</label>
		  </div>
		</div>
		
		<input type="submit" class="btn btn-submit" value="Отправить"/>
		<a href="${pageContext.request.contextPath}/userInfo">Отменить</a>
    </form>
  </c:if>
</t:wrapper>