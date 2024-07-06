<%@ page import="com.realworldservlet.models.User" %>
<link rel="stylesheet" href="../../css/style.css"/>

<div id="header">
    <div style="text-align: center; font-size: 35px; color: black;">${CAPTION}</div>
    <div style="position: fixed; right: 10px; top: 10px; color: black;">Hello <%=((User) session.getAttribute("CURRENT_USER")).getDisplayName()%></div>
    <div style="position: fixed; right: 10px; top: 30px; color: white;"><a href="<%=request.getContextPath()%>/logout">Logout</a> </div>
</div>
