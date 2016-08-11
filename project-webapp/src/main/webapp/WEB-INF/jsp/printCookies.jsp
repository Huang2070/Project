<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.setAttribute("test", "123123");
%>

<html>
<head>
    <title></title>
</head>
<body>

<%
    Cookie[] cookies = request.getCookies();
    if(cookies == null) {
        out.print("no cookies");
        return;
    }

    for(Cookie cookie : cookies) {
%>
<p>
    <b>Cookie name:</b>
    <%=cookie.getName()%>

    <b>Cookie value:</b>
    <%=cookie.getValue()%>

    <b>Cookie age:</b>
    <%=cookie.getMaxAge()%>
</p>

<%
    }
%>





</body>
</html>
