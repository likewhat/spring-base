<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>


<div class="ui grid container">
    <div class="eight wide computer sixteen wide mobile centered column">
        <h2 class="ui dividing header">Reset your password</h2>

        <sf:form commandName="form" action="" method="POST" enctype="" class="ui form">

            <div class="field  ">

                <sf:label path="email">Email</sf:label>

                <sf:input path="email" placeholder="Email" type="email" value=""/>

            </div>

            <div class="field  ">


                <input class="ui button" id="submit" name="submit" type="submit" value="Reset password">


            </div>

        </sf:form>


    </div>
</div>


<%@include file="../includes/footer.jsp" %>
