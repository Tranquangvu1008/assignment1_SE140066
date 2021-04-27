<%-- 
    Document   : manage
    Created on : Jan 15, 2021, 10:20:39 PM
    Author     : SE140066
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
    </head>
    <body>
        <form action="MainController">
            <a href="MainController?btnAction=Create"><i class="fas fa-plus-square"></i></a>
        </form>
        <form action="MainController">
            <input type="hidden" name="txtUserID" value="${sessionScope.USERID}"/>
            <input type="text" name="txtSearchItem" value="${param.txtSearchOrder}" placeholder="Item Name"/>
            <button <input class="btn-search" name="btnAction" type="submit" value="Find"/>SEARCH</button>
        </form>
        <a href="MainController?btnAction=HistoryAd">History</a>
        <a href="MainController?btnAction=Manage">Load</a>
        <a href="MainController?btnAction=Logout">Logout</a>
        

        <form action="MainController">
            <c:if test="${not empty sessionScope.LISTITEMS}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Description</th>
                            <th>Picture</th>
                            <th>Price</th>
                            <th>Date</th>
                            <th>Category</th>
                            <th>Quantity</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" varStatus="counter" items="${sessionScope.LISTITEMS}">
                        <form action="MainController">
                            <tr>
                                <td>${counter.count}</td>
                                <td><input type="text" name="txtItemID" value="${item.itemID}" readonly="true"/></td>
                                <td><input type="text" name="txtItemName" value="${item.itemName}" required/></td>
                                <td><input type="text" name="txtDescription" value="${item.description}" required/></td>
                                <td><img src= "${item.picture}" width="200" height="150" required/></td>
                                <td><input type="text" name="txtPrice" value="${item.price}" required/></td>
                                <td><input type="text" name="txtCreateDate" value="${item.createDate}" required/></td>
                                <td><input type="text" name="txtCategory" value="${item.category}" required/></td>
                                <td><input type="text" name="txtQuantity" value="${item.quantity}" required/></td>
                                <td>
                                    <input type="hidden" name="txtUserID" value="${sessionScope.USERID}"/>
                                    <input type="submit" name="btnAction" value="Update"/>
                                </td>
                                <td>
                                    <input type="hidden" name="txtUserID" value="${sessionScope.USERID}"/>
                                    <input type="submit" name="btnAction" value="Delete" onclick="return confirm('Are you sure you want to Delete this item?');"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:set var="message" value="${requestScope.NOTI}"/>
            <c:if test="${not empty message}">
                <script>
                    alert("${message}");
                </script>
            </c:if>
        </form>
    </body>
</html>
