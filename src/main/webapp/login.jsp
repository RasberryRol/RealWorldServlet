<link rel="stylesheet" href="css/style.css"/>

<body>
    <div class="bgr">
        <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="container">
                <div class="row">
                    <div class="col" style="width: 100px">
                        <b>User Name</b>
                    </div>
                    <div class="col">
                        <input type="text" placeholder="Enter your username"
                                name="username" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <b>Password</b>
                    </div>
                    <div class="col">
                        <input type="password" placeholder="Enter your password"
                               name="password" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col"></div>
                    <div class="col">
                        <button type="submit">Login</button>
                    </div>
                </div>

                <div class="row">
                    <div class="col"></div>
                    <div class="col">
                        <p class="login_err"><%=request.getAttribute("err") == null ? ""
                                                : request.getAttribute("err")%></p>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>