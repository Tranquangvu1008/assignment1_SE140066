<%-- 
    Document   : historyad
    Created on : Jan 19, 2021, 9:56:37 AM
    Author     : SE140066
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
    </head>
    <body>
        <a href="MainController?btnAction=Manage">Return</a>
        <a href="MainController?btnAction=Logout">Logout</a>
        
        <c:if test="${not empty sessionScope.HISTORYADMIN}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Update Date</th>
                        <th>Content</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" varStatus="counter" items="${sessionScope.HISTORYADMIN}">
                        <tr>
                            <td>${counter.count}</td>
                            <td><input type="text" name="txtUpdateDate" value="${item.updateDate}" readonly="true"/></td>
                            <td><input type="text" name="txtContent" value="${item.content}" readonly="true"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
