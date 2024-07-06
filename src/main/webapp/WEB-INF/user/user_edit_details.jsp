<script>
    function deleteUser() {
        var userName = document.getElementById("userName").value;
        if(confirm("Are you sure you want to delete user '" + userName + "'")){
            window.location.href = "<%=request.getContextPath()%>/UserManagement?action=delete&userName=" + userName;
        }
    }
</script>

<div id="main">
    <form action="<%=request.getContextPath()%>/UserManagement?action=save" method="post">
        <table border="1">
            <tbody>
            <tr>
                <td>User Name</td>
                <td><input type="text" name="userName" id="userName" readonly="readonly" value="${u.userName}"></td>

                <td>Password</td>
                <td><input type="password" name="password" value="${u.password}"></td>

                <td>Email</td>
                <td><input type="text" name="email" value="${u.email}"></td>

                <td>Display Name</td>
                <td><input type="text" name="displayName" value="${u.displayName}"></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="Save">
    </form>
    <a href="<%=request.getContextPath()%>/UserManagement?action=list">Back</a>
    <button onclick=deleteUser()>Delete</button>
    <p class="login_err"><%=request.getAttribute("err") == null ? ""
            : request.getAttribute("err")%></p>
</div>
