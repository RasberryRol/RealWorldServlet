<%@ page import="com.realworldservlet.models.User" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function noCheck(){
        if(document.querySelectorAll('input[type="checkbox"]:checked').length == 0){
            alert("You haven't checked any record to delete.")
            return false;
        }
    }
</script>

<div id="main">
    <form action="<%=request.getContextPath()%>/UserManagement?action=deleteList" method="post">
    <table border="1">
        <thead>
            <tr>
                <th>#</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Display Name</th>
                <th></th>
            </tr>
        </thead>
        <tbody>

<%--  Displaying user table using JSTL--%>
            <c:set var="i" value="1"/>
            <c:forEach items="${list}" var="u">
                <tr>
                    <td><a href="<%=request.getContextPath()%>/UserManagement?action=edit&userName=${u.userName}">${i}</a></td>
                    <td>${u.userName}</td>
                    <td>${u.password}</td>
                    <td>${u.email}</td>
                    <td>${u.displayName}</td>
                    <td><input type="checkbox" name="userNames" value="${u.userName}"></td>
                </tr>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>

<%--  Displaying user table using expression tags<%--%>
<%--                int i = 1;--%>
<%--                List<User> list = (List) request.getAttribute("list");--%>
<%--            %>--%>

<%--            <%--%>
<%--                for (User u : list){--%>
<%--            %>--%>
<%--            <tr>--%>
<%--                <td><%=i++%></td>--%>
<%--                <td><%=u.getUserName()%></td>--%>
<%--                <td><%=u.getPassword()%></td>--%>
<%--                <td><%=u.getEmail()%></td>--%>
<%--                <td><%=u.getDisplayName()%></td>--%>
<%--            </tr>--%>
<%--        <%}%>--%>
        </tbody>
    </table>
        <input type="submit" value="Delete" onclick="return noCheck();">
    </form>
    <a href="<%=request.getContextPath()%>/UserManagement?action=new">Add new User</a>
    <p class="login_err"><%=request.getAttribute("err") == null ? ""
            : request.getAttribute("err")%></p>
</div>