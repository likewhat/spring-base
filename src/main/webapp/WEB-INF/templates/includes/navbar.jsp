<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="ui navigation grid ">

    <div class="mobile only row">
        <div class="ui fixed inverted black main menu">
            <a class="item active" href="${WEB_ROOT}/">
                <i class="home icon"></i>
                <spring:message code="app.name"/>
            </a>

            <a class="item " href="${WEB_ROOT}/about">
                <i class="info icon"></i>
                About
            </a>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div>
                    <a class="item " href="${WEB_ROOT}/admin/">
                        <i class="user icon"></i>
                        Administrator Dashboard
                    </a>
                </div>
            </sec:authorize>

            <div class="right menu">
                <a class="icon item" id="open-nav"><i class="sidebar icon"></i></a>
            </div>
        </div>

        <sec:authorize access="isAuthenticated()">
            <div class="ui fixed vertical fluid menu">
                <a class="item " href="${WEB_ROOT}/account/manage/info">
                    <i class="settings icon"></i>
                    Your Account
                </a>

                <a class="item " href="${WEB_ROOT}/account/logout">
                    <i class="sign out icon"></i>
                    Log out
                </a>
            </div>
        </sec:authorize>
    </div>

    <div class="computer tablet only row">
        <div class="ui fixed inverted black main menu">
            <div class="ui container">

                <a class="item active" href="${WEB_ROOT}/">
                    <i class="home icon"></i>
                    <spring:message code="app.name"/>
                </a>
                <a class="item " href="${WEB_ROOT}/about">
                    <i class="info icon"></i>
                    About
                </a>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div>
                        <a class="item " href="${WEB_ROOT}/admin/">
                            <i class="user icon"></i>
                            Administrator Dashboard
                        </a>
                    </div>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <div class="right menu">
                        <a class="item " href="${WEB_ROOT}/account/manage/info">
                            <i class="settings icon"></i>
                            Your Account
                        </a>
                        <a class="item " href="${WEB_ROOT}/account/logout">
                            <i class="sign out icon"></i>
                            Log out
                        </a>
                    </div>
                </sec:authorize>

                <sec:authorize access="isAnonymous()">
                    <div class="right menu">
                        <a class="item " href="${WEB_ROOT}/account/register">
                            <i class="list layout icon"></i>
                            Register
                        </a>
                        <a class="item " href="${WEB_ROOT}/account/login">
                            <i class="sign in icon"></i>
                            Log In
                        </a>
                    </div>
                </sec:authorize>
            </div>
        </div>
    </div>
</nav>


<div class="ui text container">
    <div class="flashes">

        <c:if test="${not empty message}">
            <div class="ui ${message.type} message">
                <i class="close icon"></i>
                    ${message.text}
            </div>
        </c:if>

    </div>
</div>
