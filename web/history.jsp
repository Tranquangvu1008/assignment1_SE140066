<%-- 
    Document   : history
    Created on : Jan 13, 2021, 10:59:09 AM
    Author     : SE140066
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <form action="MainController">
            <input type="text" name="txtSearchOrder" value="${param.txtSearchOrder}" placeholder="Item Name"/>
            <input type="hidden" name="txtUserID" value="${param.txtUserID}"/>
            <button <input class="btn-search" name="btnAction" type="submit" value="History"/>SEARCH</button>

            <c:if test="${not empty sessionScope.LISTITEM}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Detail ID</th>
                            <th>Order ID</th>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" varStatus="counter" items="${sessionScope.LISTITEM}">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.detailID}</td>
                                <td>${item.orderID}</td>
                                <td>${item.itemID}</td>
                                <td>${item.itemName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                                <td>${item.date}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </form>
            <a href="MainController?btnAction=Menu">Return Menu</a>
    </body>
</html>
