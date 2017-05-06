<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../includes/header.jsp" %>


<div class="ui stackable grid container">
    <div class="sixteen wide tablet twelve wide computer centered column">
        <a class="ui basic compact button" href="/admin/">
            <i class="caret left icon"></i>
            Back to dashboard
        </a>
        <h2 class="ui header">
            Registered Users
            <div class="sub header">
                View and manage currently registered users.
            </div>
        </h2>

        <div class="ui menu">
            <div id="select-role" class="ui dropdown item">
                <div class="text">
                    All account types
                </div>
                <i class="dropdown icon"></i>
                <div class="menu">
                    <div class="item" data-value="">All account types</div>

                    <div class="item" data-value="Administrator">Administrators</div>

                    <div class="item" data-value="User">Users</div>

                </div>
            </div>
            <div class="ui right search item">
                <div class="ui transparent icon input">
                    <input id="search-users" type="text" placeholder="Search users…">
                    <i class="search icon"></i>
                </div>
            </div>
        </div>


        <div style="overflow-x: scroll;">
            <table class="ui searchable sortable unstackable selectable celled table">
                <thead>
                <tr>
                    <th>First name</th>
                    <th class="sorted ascending">Last name</th>
                    <th>Email address</th>
                    <th>Account type</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${users}">
                    <tr onclick="window.location.href = '/admin/user/${user.id}/info';">
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td class="user role">${user.accountType}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#search-users').keyup(function () {
            var searchText = $(this).val();
            if (searchText.length > 0) {
                $('tbody td:icontains(' + searchText + ')').addClass('positive');
                $('td.positive').not(':icontains(' + searchText + ')').removeClass('positive');
                $('tbody td').not(':icontains(' + searchText + ')').closest('tr').addClass('hidden').hide();
                $('tr.hidden:icontains(' + searchText + ')').removeClass('hidden').show();
            } else {
                $('td.positive').removeClass('positive');
                $('tr.hidden').removeClass('hidden').show();
            }
        });

        $('#select-role').dropdown({
            onChange: function (value, text, $selectedItem) {
                $('td.user.role:contains(' + value + ')').closest('tr').removeClass('hidden').show();
                $('td.user.role').not(':contains(' + value + ')').closest('tr').addClass('hidden').hide();
            }
        });
    });
</script>


<%@include file="../includes/footer.jsp" %>
