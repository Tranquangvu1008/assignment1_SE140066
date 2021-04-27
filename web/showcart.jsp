<%-- 
    Document   : showcart
    Created on : Jan 10, 2021, 5:20:05 PM
    Author     : SE140066
--%>

<%@page import="dtos.ItemDTO"%>
<%@page import="dtos.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Cart Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
        <link href="css/view.css" rel="stylesheet" type="text/css"/>
        <style>

        </style>
    </head>
    <body>
        <div class="container">
            <div class="heading">
                <h1>
                    <span class="shopper">Shopping Cart</span> 
                </h1> 
                <a href="MainController?btnAction=Menu" class="visibility-cart transition is-open"><i class="fas fa-long-arrow-alt-left"></i></a>   
            </div>


            <div class="cart transition is-open">
                <div class="table">

                    <div class="layout-inline row th">
                        <div class="col col-pro">Item ID</div>
                        <div class="col col-pro">Item Name</div>
                        <div class="col col-pro">Category</div>
                        <div class="col col-price align-center ">Price</div>
                        <div class="col col-qty align-center">QTY</div>
                        <div class="col">Total</div>
                        <div class="col col-pro">Update</div>
                        <div class="col col-pro">Delete</div>

                    </div>



                    <c:set var="listCart" value="${sessionScope.CART.cart}"/>
                    <c:if test="${not empty listCart}">
                        <c:forEach var="cart" items="${listCart}" varStatus="counter">
                            <c:set var="totalPrice" value="${cart.value.quantity * cart.value.price}"/>
                            <form action="MainController">
                                <div class="layout-inline row">
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.itemID}</p>
                                    </div>
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.itemName}</p>
                                    </div>
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.category}</p>
                                    </div>

                                    <div class="col col-price col-numeric align-center ">
                                        <p>${cart.value.price}</p>
                                    </div>

                                    <div class="col col-qty layout-inline">
                                        <input type="number" name="txtQuantity" min="1" value="${cart.value.quantity}"/>
                                    </div>
                                    <div class="col col-total col-numeric">               
                                        <p>${totalPrice}</p>
                                    </div> 
                                    <div class="col layout-inline">
                                        <input type="hidden" name="txtUserID" value="${cart.value.userID}"/>
                                        <input type="hidden" name="txtItemID" value="${cart.value.itemID}"/>
                                        <input type="hidden" name="txtQuantity" value="${cart.value.quantity}"/>
                                        <input type="submit" name="btnAction" value="Update Item"/>  
                                    </div> 
                                    <div class="col layout-inline">
                                        <input type="hidden" name="txtItemID" value="${cart.value.itemID}"/>
                                        <input type="submit" name="btnAction" value="Delete Item" onclick="return confirm('Are you sure you want to Delete this food/drink?');"/> 
                                    </div> 

                                    <c:set var="total" value="${total + totalPrice}"/>

                                </div>
                            </form>
                        </c:forEach>
                        <div class="tf">
                            <div class="row layout-inline">
                                <div class="col">
                                    <p>Total ${total}</p>
                                </div>
                                <div class="col"></div>
                            </div>
                        </div>   

                    </c:if>

                </div>
                <form action="ConfirmController">
                    <h2>Shipping Detail:</h2>
                    <input type="text" name="UserName" value="${sessionScope.USERID}" placeholder="Your Name *" required/>
                    <input type="email" name="Email" value="${param.Email}" placeholder="Your Email *" required/>
                    <input type="text" name="Phone" value="${param.Phone}" minlength="10" maxlength="15" placeholder="Your Phone *" required/>
                    <input type="text" name="Address" value="${param.Address}" placeholder="Your Address *" required/>
                    <p>Delivery time after 3-4 days from the date of order ^^</p>
                    <input type="submit" name="Confirm" class="btn btn-update"/>
                </form>


            </div>
            <c:set var="message" value="${requestScope.MESSAGE}"/>
            <c:if test="${not empty message}">
                <script>
                    alert("${message}");
                </script>
            </c:if>

                <c:set var="confirm" value="${requestScope.CONFIRM}"/>
            <c:if test="${not empty confirm}">
                <script>
                    alert("${confirm}");
                </script>
            </c:if>
    </body>
</html>
