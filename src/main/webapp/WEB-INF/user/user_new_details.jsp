<div id="main">
    <form action="<%=request.getContextPath()%>/UserManagement?action=insert" method="post">
        <table border="1">
            <tbody>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="userName" required></td>

                    <td>Password</td>
                    <td><input type="password" name="password"></td>

                    <td>Email</td>
                    <td><input type="text" name="email"></td>

                    <td>Display Name</td>
                    <td><input type="text" name="displayName"></td>
                </tr>
            </tbody>
        </table>
        <input type="submit" value="Save">
    </form>
    <a href="<%=request.getContextPath()%>/UserManagement?action=list">Back</a>
    <p class="login_err"><%=request.getAttribute("err") == null ? ""
            : request.getAttribute("err")%></p>
</div>