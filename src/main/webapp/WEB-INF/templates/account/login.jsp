<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>


<div class="ui grid container">
    <div class="eight wide computer sixteen wide mobile centered column">
        <h2 class="ui dividing header">Log in</h2>


        <sf:form action="/account/login" method="post" class="ui form error">

            <div class="field  ">
                <label for="username">Email</label>
                <input id="username" name="username" placeholder="Email" type="email" value=""/>
            </div>


            <div class="field  ">
                <label for="password">Password</label>
                <input id="password" name="password" placeholder="Password" type="password"
                       value=""/>

            </div>

            <div class="ui two column grid">
                <div class="field  column">
                    <div class="ui checkbox">
                        <input id="remember-me" name="remember-me" type="checkbox" value="y"/>
                        <label for="remember-me">Keep me logged in</label>
                    </div>
                </div>

                <div class="right aligned column">
                    <a href="${WEB_ROOT}/account/reset-password">Forgot password?</a>
                </div>
            </div>

            <c:if test="${not empty messageList}">
                <div class="ui ${messageList.type} message">
                    <div class="header">${messageList.header}</div>
                    <ul class="list">

                        <c:forEach var="ms" items="${messageList.messages}">
                            <li>${ms}</li>
                        </c:forEach>

                    </ul>
                </div>
            </c:if>

            <div class="field  ">
                <input class="ui button" id="submit" name="submit" type="submit" value="Log in"/>
            </div>
        </sf:form>

    </div>
</div>

<%@include file="../includes/footer.jsp" %>
