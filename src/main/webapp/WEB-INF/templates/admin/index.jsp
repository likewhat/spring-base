<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

<%@include file="../includes/navbar.jsp" %>


<div class="ui stackable centered grid container">
    <div class="twelve wide column">
        <h2 class="ui header">
            Admin Dashboard
        </h2>
        <div class="ui two column stackable grid">

            <a class="column" href="/admin/users">
                <div class="ui padded segment">
                    <h3 class="ui header">

                        <i class="users icon"></i>

                        <div class="content">
                            Registered Users

                            <div class="sub header">
                                View and manage user accounts
                            </div>

                        </div>
                    </h3>
                </div>
            </a>


            <a class="column" href="/admin/new-user">
                <div class="ui padded segment">
                    <h3 class="ui header">

                        <i class="add user icon"></i>

                        <div class="content">
                            Add New User

                            <div class="sub header">
                                Create a new user account
                            </div>

                        </div>
                    </h3>
                </div>
            </a>


            <a class="column" href="/admin/invite-user">
                <div class="ui padded segment">
                    <h3 class="ui header">

                        <i class="add user icon"></i>

                        <div class="content">
                            Invite New User

                            <div class="sub header">
                                Invites a new user to create their own account
                            </div>

                        </div>
                    </h3>
                </div>
            </a>

        </div>
    </div>
</div>


</body>
</html>