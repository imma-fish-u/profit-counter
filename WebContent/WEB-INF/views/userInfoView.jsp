<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Мой аккаунт">
  Имя пользователя: <b>${logginedUser.userName}</b>
  <br/>

</t:wrapper>

 <c:if test="${loginedUser.role == 'admin'}">
 <br/>
    <table class="table table-striped">
    <tr>
      <th>id</th>
      <th>Имя пользователя</th>
      <th>Пароль</th>
      <th>Роль</th>
      <th>E-mail</th>
      <th>Подтвержден</th>
      <th>Изменить</th>
      <th>Удалить</th>
    </tr>
    <c:forEach items="${userList}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.password}</td>
        <td>${user.role}</td>
        <td>${user.email}</td>
        <td>${user.accepted}</td>
        <td>
          <a href="editUser?id=${user.id}">Изменить</a>
        </td>
        
        <td>
          <a href="deleteUser?id=${user.id}">Удалить</a>
        </td> 
      </tr>
    </c:forEach>
  </table>
 </c:if>