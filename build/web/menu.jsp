<%-- 
    Document   : menu
    Created on : Jan 7, 2021, 11:18:25 AM
    Author     : SE140066
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Fast Food Restaurant</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">  
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <!-- Site Metas -->
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">    
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style.css">    
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">

        <!--Custom-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
        <link href="css/search.css" rel="stylesheet" type="text/css"/>

        <style>
            .why-text{
                text-align: center;
            }
            .inner-menu-box{
                float: left;

            }
        </style>

    </head>
    <body>
        <!-- Start header -->
        <header class="top-navbar">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container">
                    <a class="logo" href="welcome.jsp">
                        <img src="https://www.pngitem.com/pimgs/m/189-1898946_kfc-thc-thc-kfc-logo-hd-png-download.png" width="147" height="85" alt=""/>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars-rs-food" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbars-rs-food">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active"><a class="nav-link" href="welcome.jsp">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="MainController?btnAction=Menu">Menu</a></li>
                            <li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>


                            <c:choose>
                                <c:when test="${empty sessionScope.USERID}">
                                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                                </c:when>
                                    
                                <c:when test="${not empty sessionScope.USERID}">
                                    <li class="nav-item"><a class="nav-link" href="MainController?btnAction=Logout">Logout</a></li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                    <c:out value="Hello: ${sessionScope.FULLNAME}"/>
                </div>
            </nav>
        </header>
        <!-- End header -->

        <!-- Start All Pages -->
        <div class="all-page-title page-breadcrumb">
            <div class="container text-center">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Special Menu</h1>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Pages -->

        <!-- Start Menu -->
        <div class="menu-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="heading-title text-center">
                            <h2>Special Menu</h2>

                            <!--Search Bar-->
                            <div class="s002">
                                <form action="MainController">
                                    <div class="inner-form">
                                        <div class="input-field first-wrap">
                                            <div class="icon-wrap">
                                                <i class="fas fa-utensils"></i>
                                            </div>
                                            <input id="search" type="text" name="txtSearch" value="${param.txtSearch}" />
                                        </div>
                                        <div class="input-field second-wrap">
                                            Min 
                                            <select data-trigger="" name="txtMinPrice">
                                                <option selected>${param.txtMinPrice}</option>
                                                <option disabled>---</option>
                                                <option>0</option>
                                                <option>5</option>
                                                <option>10</option>
                                                <option>15</option>
                                            </select>
                                            Max 
                                            <select data-trigger="" name="txtMaxPrice">
                                                <option selected>${param.txtMaxPrice}</option>
                                                <option disabled>---</option>
                                                <option>5</option>
                                                <option>10</option>
                                                <option>15</option>
                                                <option>+</option>
                                            </select>
                                        </div>
                                        <div class="input-field fouth-wrap">
                                            <div class="icon-wrap">
                                                <i class="fas fa-cannabis"></i>
                                            </div>
                                            <c:if test="${not empty requestScope.LIST_CATEGORY}">
                                                <select data-trigger="" name="txtCategory">
                                                    <option selected>${param.txtCategory}</option>
                                                    <option disabled>---</option>
                                                    <c:forEach var="item" varStatus="counter" items="${requestScope.LIST_CATEGORY}">
                                                        <option>${item}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>

                                        </div>
                                        <div class="input-field fifth-wrap">
                                            <button <input class="btn-search" name="btnAction" type="submit" value="Menu"/>SEARCH</button>
                                        </div>
                                    </div>
                                </form>
                            </div>                            
                        </div>


                        <!--Menu-->
                        <div class="row inner-menu-box">
                            <div class="col-12">
                                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                                    <a class="nav-link active" id="v-pills-home-tab" href="MainController?btnAction=Menu" role="tab" aria-controls="v-pills-home">All</a>
                                    <a class="nav-link" id="v-pills-profile-tab" href="MainController?txtSearch=&txtMinPrice=&txtMaxPrice=&txtCategory=Drink&btnAction=Menu" role="tab">Drink</a>
                                    <a class="nav-link" id="v-pills-messages-tab" href="MainController?txtSearch=&txtMinPrice=&txtMaxPrice=&txtCategory=Food&btnAction=Menu" role="tab">Food</a>
                                    <a class="nav-link" id="v-pills-messages-tab" href="MainController?btnAction=Show" role="tab"><i class="fas fa-cart-plus"></i> SHOW CART</a>
                                    <a class="nav-link" id="v-pills-messages-tab" href="MainController?btnAction=History&txtUserID=${sessionScope.USERID}" role="tab"><i class="fas fa-store"></i> PURCHASE HISTORY</a>
                                </div>
                            </div>
                        </div>

                        <div class="container">
                            <div class="row">
                                <c:forEach var="item" varStatus="counter" items="${requestScope.LIST}">
                                    <div class="col-md-3 col-sm-6">
                                        <form action="MainController">
                                            <div class="gallery-single fix">
                                                <img src="${item.picture}" class="img-fluid" alt="Image">

                                                <div class="why-text">
                                                    <h4>${item.itemName}</h4>
                                                    <p>${item.description}</p>
                                                    <h5>${item.price}</h5>
                                                    <input type="submit" name="btnAction" value="Order">
                                                </div>
                                            </div>
                                            <input type="hidden" name="txtUserID" value="${sessionScope.USERID}"/>
                                            <input type="hidden" name="txtItemName" value="${item.itemName}"/>
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Menu -->
    
    <c:forEach begin="1" end="${requestScope.NUMBER_PAGE}" var="i">
        <c:url var="paging" value="MenuController">
            <c:param name="index" value="${i}"/>
        </c:url>
        <a href="${paging}">${i}</a>
    </c:forEach>

    <!-- Start Contact info -->
    <div class="contact-imfo-box">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <i class="fa fa-volume-control-phone"></i>
                    <div class="overflow-hidden">
                        <h4>Phone</h4>
                        <p class="lead">
                            +01 123-456-4590
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <i class="fa fa-envelope"></i>
                    <div class="overflow-hidden">
                        <h4>Email</h4>
                        <p class="lead">
                            yourmail@gmail.com
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <i class="fa fa-map-marker"></i>
                    <div class="overflow-hidden">
                        <h4>Location</h4>
                        <p class="lead">
                            800, Lorem Street, US
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Contact info -->

    <!-- ALL JS FILES -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- ALL PLUGINS -->
    <script src="js/jquery.superslides.min.js"></script>
    <script src="js/images-loded.min.js"></script>
    <script src="js/isotope.min.js"></script>
    <script src="js/baguetteBox.min.js"></script>
    <script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>

</body>
</html>
