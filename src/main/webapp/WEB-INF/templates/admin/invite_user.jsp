<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta name="charset" content="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Flask-Base</title>

    <link rel="stylesheet" type="text/css"
          href="/static/webassets-external/437537b63b07c67498a558d83b1d66a5_semantic.min.css">
    <link rel="stylesheet" type="text/css" href="/static/styles/app.css?873aca08">

    <script type="text/javascript"
            src="/static/webassets-external/a769c05336ce91cf78642a96d57094f2_jquery.min.js"></script>
    <script type="text/javascript"
            src="/static/webassets-external/a43a7724c0a12df6f8a55e41f8b95aec_semantic.min.js"></script>
    <script type="text/javascript"
            src="/static/webassets-external/971a3a1a53f8270d66a056b28b9a23e2_tablesort.min.js"></script>
    <script type="text/javascript" src="/static/webassets-external/6c5db2fa0f5d0039a10128fece22ac04_zxcvbn.js"></script>
    <script type="text/javascript" src="/static/webassets-external/cf84caebcf095402b04a1854f34d3126_app.js"></script>


</head>
<body>


<nav class="ui navigation grid ">

    <div class="mobile only row">
        <div class="ui fixed inverted black main menu">


            <a class="item " href="/">

                <i class="home icon"></i>

                Flask-Base
            </a>

            <a class="item " href="/about">

                <i class="info icon"></i>

                About
            </a>

            <a class="item " href="/admin/">

                <i class="user icon"></i>

                Administrator Dashboard
            </a>


            <div class="right menu">
                <a class="icon item" id="open-nav"><i class="sidebar icon"></i></a>
            </div>
        </div>


        <div class="ui fixed vertical fluid menu">


            <a class="item " href="/account/manage/info">

                <i class="settings icon"></i>

                Your Account
            </a>

            <a class="item " href="/account/logout">

                <i class="sign out icon"></i>

                Log out
            </a>


        </div>
    </div>


    <div class="computer tablet only row">
        <div class="ui fixed inverted black main menu">
            <div class="ui container">


                <a class="item " href="/">

                    <i class="home icon"></i>

                    Flask-Base
                </a>

                <a class="item " href="/about">

                    <i class="info icon"></i>

                    About
                </a>

                <a class="item " href="/admin/">

                    <i class="user icon"></i>

                    Administrator Dashboard
                </a>


                <div class="right menu">


                    <a class="item " href="/account/manage/info">

                        <i class="settings icon"></i>

                        Your Account
                    </a>

                    <a class="item " href="/account/logout">

                        <i class="sign out icon"></i>

                        Log out
                    </a>


                </div>
            </div>
        </div>
    </div>

</nav>


<div class="ui text container">
    <div class="flashes">


    </div>
</div>


<div class="ui stackable centered grid container">
    <div class="twelve wide column">
        <a class="ui basic compact button" href="/admin/">
            <i class="caret left icon"></i>
            Back to dashboard
        </a>
        <h2 class="ui header">
            Add New User
            <div class="sub header">Create a new user account</div>
        </h2>


        <form action="" method="POST" enctype="" class="ui form
        ">
            <div style="display:none;"><input id="csrf_token" name="csrf_token" type="hidden"
                                              value="1485603286##a59a39e78b6bf37f9392488c2042b3486fa2ae4d"></div>


            <div class="field  ">


                <label for="role">Account type</label>

                <select id="role" name="role" placeholder="Account type">
                    <option value="2">User</option>
                    <option value="1">Administrator</option>
                </select>


            </div>


            <div class="two fields">


                <div class="field  ">


                    <label for="first_name">First name</label>

                    <input id="first_name" name="first_name" placeholder="First name" type="text" value="">


                </div>


                <div class="field  ">


                    <label for="last_name">Last name</label>

                    <input id="last_name" name="last_name" placeholder="Last name" type="text" value="">


                </div>

            </div>


            <div class="field  ">


                <label for="email">Email</label>

                <input id="email" name="email" placeholder="Email" type="email" value="">


            </div>


            <div class="field  ">


                <input class="ui button" id="submit" name="submit" type="submit" value="Invite">


            </div>


        </form>


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
            }
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

    </div>
</div>

</body>
</html>