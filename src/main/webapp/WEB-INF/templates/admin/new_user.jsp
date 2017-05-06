<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>


<div class="ui stackable centered grid container">
    <div class="twelve wide column">
        <a class="ui basic compact button" href="${WEB_ROOT}/admin/">
            <i class="caret left icon"></i>
            Back to dashboard
        </a>
        <h2 class="ui header">
            Add New User
            <div class="sub header">Create a new user account</div>
        </h2>


        <sf:form action="" commandName="form" method="POST" enctype="" class="ui form">
            <div class="field  ">

                <sf:label path="accountType">Account type</sf:label>

                <sf:select path="accountType" placeholder="Account type">
                    <sf:option value="User">User</sf:option>
                    <sf:option value="Administrator">Administrator</sf:option>
                </sf:select>
            </div>


            <div class="two fields">

                <div class="field  ">


                    <sf:label path="firstName">First name</sf:label>

                    <sf:input path="firstName" placeholder="First name" type="text" value=""/>

                </div>


                <div class="field  ">
                    <sf:label path="lastName">Last name</sf:label>
                    <sf:input path="lastName" placeholder="Last name" type="text" value=""/>
                </div>

            </div>


            <div class="field  ">
                <sf:label path="email">Email</sf:label>
                <sf:input path="email" placeholder="Email" type="email" value=""/>
            </div>


            <div class="two fields">


                <div class="field  ">
                    <sf:label path="password">Password</sf:label>
                    <sf:password path="password" placeholder="Password" value=""/>
                </div>


                <div class="field  ">
                    <sf:label path="password2">Confirm password</sf:label>
                    <sf:password path="password2" placeholder="Confirm password" value=""/>
                </div>

            </div>


            <div class="field  ">
                <input class="ui button" id="submit" name="submit" type="submit" value="Create"/>

            </div>
        </sf:form>

    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
<script>
    $('#submit').attr('disabled', true);
    $('#password').after('<progress value="0" max="4" id="password-strength-meter"></progress><p id="password-strength-text"></p>');
    var strength = {
        0: "Bad",
        1: "Okay",
        2: "Good",
        3: "Very Good",
        4: "Strong"
    };

    var meter = document.getElementById('password-strength-meter');
    var text = $('#password-strength-text');

    $('#password').keyup(function () {
        var result = zxcvbn($(this).val());
        // Update the password strength meter
        meter.value = result.score;
        if (result.score >= 0) {
            $('#submit').attr('disabled', false);
        } else {
            $('#submit').attr('disabled', true);
        }
        // Update the text indicator
        if ($(this).val() !== "") {
            $(text).html("Strength: " + strength[result.score]);
        } else {
            $(text).html("");
        }
    });
</script>

<style type="text/css">
    progress {
        /* Reset the default appearance */
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;

        margin: 10 auto 1em;
        width: 100%;
        height: 0.5em;
        background-color: rgba(0, 0, 0, 0.1);
    }

    /* Chrome */
    progress::-webkit-progress-value {
        background: rgba(0, 0, 0, 0.1);
    }

    /* Webkit based browsers */
    progress[value^="1"]::-webkit-progress-value {
        background-color: red;
    }

    progress[value^="2"]::-webkit-progress-value {
        background-color: yellow;
    }

    progress[value^="3"]::-webkit-progress-value {
        background-color: orange;
    }

    progress[value^="4"]::-webkit-progress-value {
        background-color: green;
    }

    /* Gecko based browsers */
    progress[value^="1"]::-moz-progress-bar {
        background-color: red;
    }

    progress[value^="2"]::-moz-progress-bar {
        background-color: yellow;
    }

    progress[value^="3"]::-moz-progress-bar {
        background-color: orange;
    }

    progress[value^="4"]::-moz-progress-bar {
        background-color: green;
    }
</style>

<%@include file="../includes/footer.jsp" %>
