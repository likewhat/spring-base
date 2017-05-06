<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>


<div class="ui stackable centered grid container">
    <div class="twelve wide column">
        <h2 class="ui header">
            Account Settings
            <div class="sub header">Manage your account settings and change your login information.</div>
        </h2>
    </div>
    <div class="stretched divided very relaxed row">

        <%@include file="left_sidebar.jsp" %>


        <div class="eight wide column">

            <form action="" method="POST" enctype="" class="ui form fluid">

                <div class="field  ">


                    <label for="email">New email</label>

                    <input id="email" name="email" placeholder="New email" type="email" value="">

                </div>


                <div class="field  ">


                    <label for="password">Password</label>

                    <input id="password" name="password" placeholder="Password" type="password" value="">


                </div>


                <div class="field  ">


                    <input class="ui button" id="submit" name="submit" type="submit" value="Update email">


                </div>


            </form>


        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
<script>
    $('#submit').attr('disabled', true);
    $('#new_password').after('<progress value="0" max="4" id="password-strength-meter"></progress><p id="password-strength-text"></p>');
    var strength = {
        0: "Bad",
        1: "Okay",
        2: "Good",
        3: "Very Good",
        4: "Strong"
    }
    var meter = document.getElementById('password-strength-meter');
    var text = $('#password-strength-text');

    $('#new_password').keyup(function () {
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
