<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>


<div class="ui stackable centered grid container">
    <div class="twelve wide column">
        <a class="ui basic compact button" href="${WEB_ROOT}/admin/users">
            <i class="caret left icon"></i>
            Back to all users
        </a>
        <h2 class="ui header">
            ${user.firstName} ${user.lastName}
            <div class="sub header">View and manage ${user.firstName}’s account.</div>
        </h2>
    </div>

    <div class="stretched divided very relaxed row">
        <div class="four wide column">

            <div class="ui vertical fluid secondary menu">


                <a class="item " href="${WEB_ROOT}/admin/user/${user.id}/info">
                    User information
                </a>


                <a class="item active" href="${WEB_ROOT}/admin/user/${user.id}/change-email">
                    Change email address
                </a>


                <a class="item " href="${WEB_ROOT}/admin/user/${user.id}/change-account-type">
                    Change account type
                </a>


                <a class="item " href="${WEB_ROOT}/admin/user/${user.id}/delete">
                    Delete user
                </a>

            </div>

        </div>


        <div class="eight wide column">

            <sf:form action="" method="POST" enctype="" class="ui form">

                <div class="field  ">

                    <label for="email">New email</label>

                    <input id="email" name="email" placeholder="New email" type="email" value=""/>
                </div>


                <div class="field  ">


                    <input class="ui button" id="submit" name="submit" type="submit" value="Update email">


                </div>


            </sf:form>


        </div>
    </div>
</div>

<script type="text/javascript">
    $('.deletion.checkbox').checkbox({
        onChecked: function () {
            $('.deletion.button').removeClass('disabled')
                .attr('href', '/admin/user/${user.id}/_delete');
        },
        onUnchecked: function () {
            $('.deletion.button').addClass('disabled').removeAttr('href');
        }
    });
</script>

<%@include file="../includes/footer.jsp" %>
