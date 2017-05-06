<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../includes/header.jsp" %>


<div class="ui stackable centered grid container">
    <div class="twelve wide column">
        <a class="ui basic compact button" href="/admin/users">
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


                <a class="item " href="/admin/user/6/info">
                    User information
                </a>


                <a class="item " href="/admin/user/6/change-email">
                    Change email address
                </a>


                <a class="item " href="/admin/user/6/change-account-type">
                    Change account type
                </a>


                <a class="item active" href="/admin/user/6/delete">
                    Delete user
                </a>

            </div>

        </div>
        <div class="eight wide column">

            <h3 class="ui red block header">
                <i class="warning circle icon"></i>
                <div class="content">
                    This action is permanent
                    <div class="sub header">
                        Deleting a user account is not a reversible change. Any information associated
                        with this account will be removed, and cannot be recovered.
                    </div>
                </div>
            </h3>

            <sf:form class="ui form">
                <div class="inline field">
                    <div class="ui deletion checkbox">
                        <input type="checkbox" tabindex="0" class="hidden">
                        <label>I understand that this action cannot be undone.</label>
                    </div>
                </div>
                <input type="submit" value="Delete this user" class="ui disabled negative deletion button"/>
            </sf:form>
        </div>

    </div>
</div>


<script type="text/javascript">
    $('.deletion.checkbox').checkbox({
        onChecked: function () {
            $('.deletion.button').removeClass('disabled')
                .attr('href', '/admin/user/6/_delete');
        },
        onUnchecked: function () {
            $('.deletion.button').addClass('disabled').removeAttr('href');
        }
    });
</script>


<%@include file="../includes/footer.jsp" %>
