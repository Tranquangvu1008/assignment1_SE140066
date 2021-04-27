<%-- 
    Document   : contact
    Created on : Jan 17, 2021, 10:49:39 PM
    Author     : SE140066
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Page</title>
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
                </div>
            </nav>
        </header>
        <!-- End header -->
        <!-- Start About -->
        <div class="about-section-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-12 text-center">
                        <div class="inner-column">

                            <h1>Welcome To <span>Fast Food Restaurant</span></h1>
                            <h4>Little Story</h4>
                            <p>In 1952 at the age of 65, when most people are looking at slowing down and retiring, Harland David Sanders began Kentucky Fried Chicken. </p>
                            <p>We are fortunate that Colonel Harland Sanders did not believe in the adage that “the future belongs to the young,” or we would likely have never tasted the “finger-lickin'” fried chicken so many enjoy today. </p>
                            <p>FFR is arguably one of the most widely recognized brands worldwide, and the Colonel is one of the pioneers of modern franchising.</p>
                            <a class="btn btn-lg btn-circle btn-outline-new-white" href="contact.jsp">Contact</a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-12">
                        <img src="image/about-img.jpg" alt="" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
        <!-- End About -->

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
    </body>
</html>
