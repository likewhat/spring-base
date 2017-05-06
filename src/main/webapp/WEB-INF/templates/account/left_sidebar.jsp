<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="four wide column">

    <div class="ui vertical fluid secondary menu">

        <a class="item <c:if test="${active == 'INFO'}">active</c:if> " href="${WEB_ROOT}/account/manage/info">
            Account information
        </a>


        <a class="item <c:if test="${active == 'CHANGE_EMAIL'}">active</c:if>"
           href="${WEB_ROOT}/account/manage/change-email">
            Change email address
        </a>


        <a class="item <c:if test="${active == 'CHANGE_PASSWORD'}">active</c:if>"
           href="${WEB_ROOT}/account/manage/change-password">
            Change password
        </a>


        <a class="item " href="${WEB_ROOT}/account/logout">
            Log out
        </a>

    </div>

</div>
