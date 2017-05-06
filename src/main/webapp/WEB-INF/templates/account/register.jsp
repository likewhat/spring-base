<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../includes/header.jsp" %>


<div class="ui grid container">
    <div class="eight wide computer sixteen wide mobile centered column">
        <h2 class="ui dividing header">Create an account</h2>


        <sf:form modelAttribute="form" action="" method="POST" class="ui form">
            <c:set var="firstNameError"><sf:errors path="firstName"/></c:set>
            <c:set var="lastNameError"><sf:errors path="lastName"/></c:set>
            <c:set var="emailError"><sf:errors path="email"/></c:set>
            <c:set var="passwordError"><sf:errors path="password"/></c:set>
            <c:set var="password2Error"><sf:errors path="password2"/></c:set>


            <div class="two fields">
                <div class="field <c:if test="${not empty firstNameError}">error</c:if> ">

                    <sf:label path="firstName">First name</sf:label>
                    <sf:input id="first_name" path="firstName" placeholder="First name" type="text" value=""/>

                    <sf:errors path="firstName" cssClass="ui red pointing label"/>

                </div>


                <div class="field <c:if test="${not empty lastNameError}">error</c:if> ">
                    <sf:label path="lastName">Last name</sf:label>
                    <sf:input id="last_name" path="lastName" placeholder="Last name" type="text" value=""/>
                    <sf:errors path="lastName" cssClass="ui red pointing label"/>

                </div>

            </div>


            <div class="field  <c:if test="${not empty emailError}">error</c:if> ">
                <sf:label path="email">Email</sf:label>
                <sf:input id="email" path="email" placeholder="Email" type="email" value=""/>

                <sf:errors path="email" cssClass="ui red pointing label"/>

            </div>


            <div class="two fields">

                <div class="field <c:if test="${not empty passwordError}">error</c:if> ">
                    <sf:label path="password">Password</sf:label>

                    <sf:input id="password" path="password" placeholder="Password" type="password"
                              value=""/>

                    <sf:errors path="password" cssClass="ui red pointing label"/>
                </div>

                <div class="field  <c:if test="${not empty password2Error}">error</c:if> ">
                    <sf:label path="password2">Confirm password</sf:label>

                    <sf:input id="password2" path="password2" placeholder="Confirm password"
                              type="password" value=""/>

                    <sf:errors path="password2" cssClass="ui red pointing label"/>

                </div>

                <sf:hidden path="accountType" value="User"/>

            </div>


            <div class="field  ">

                <input class="ui button" id="submit" name="submit" type="submit" value="Register"/>

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
<style>
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
