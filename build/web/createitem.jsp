<%-- 
    Document   : createitem
    Created on : Jan 18, 2021, 3:40:25 PM
    Author     : SE140066
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            .register{
                background: -webkit-linear-gradient(left, #3931af, #00c6ff);
            }

            .register-left{
                background-image: url("image/hotel2.jpg");
                background-size: cover;
                text-align: center;
                color: #fff;
            }

            .register-left h3{
                text-shadow: black 0.1em 0.1em 0.3em;
            }

            .intro{
                text-shadow: black 0.1em 0.1em 0.2em;
                font-size: 18px;
            }

            .register-left input{
                border: none;
                border-radius: 1.5rem;
                padding: 2%;
                width: 60%;
                background: #f8f9fa;
                font-weight: bold;
                color: #383d41;
                margin-top: 15%;
                margin-bottom: 3%;
                cursor: pointer;
            }
            .register-right{
                background: #f8f9fa;
            }
            .register-left img{
                margin-top: 35%;
                margin-bottom: 5%;
                width: 40%;
                -webkit-animation: mover 2s infinite  alternate;
                animation: mover 1s infinite  alternate;
            }
            @-webkit-keyframes mover {
                0% { transform: translateY(0); }
                100% { transform: translateY(-20px); }
            }
            @keyframes mover {
                0% { transform: translateY(0); }
                100% { transform: translateY(-20px); }
            }
            .register-left p{
                font-weight: lighter;
                padding: 12%;
                margin-top: -2%;
            }
            .register .register-form{
                padding: 10%;
                margin-top: 10%;
            }
            .btnRegister{
                float: right;
                margin-top: 10%;
                border: none;
                border-radius: 1.5rem;
                padding: 2%;
                background: #0062cc;
                color: #fff;
                font-weight: 600;
                width: 50%;
                cursor: pointer;
            }
            .register .nav-tabs{
                margin-top: 3%;
                border: none;
                background: #0062cc;
                border-radius: 1.5rem;
                width: 28%;
                float: right;
            }
            .register .nav-tabs .nav-link{
                padding: 2%;
                height: 34px;
                font-weight: 600;
                color: #fff;
                border-top-right-radius: 1.5rem;
                border-bottom-right-radius: 1.5rem;
            }
            .register .nav-tabs .nav-link:hover{
                border: none;
            }
            .register .nav-tabs .nav-link.active{
                width: 100px;
                color: #0062cc;
                border: 2px solid #0062cc;
                border-top-left-radius: 1.5rem;
                border-bottom-left-radius: 1.5rem;
            }
            .register-heading{
                text-align: center;
                margin-top: 8%;
                margin-bottom: -15%;
                color: #495057;
            }
        </style>
    </head>
    <body>

        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">
                    <img src="image/logo_white.png" alt=""/>
                    <form action="MainController">
                        <h3>Welcome</h3>
                        <p class="intro">HANA SHOP No.1 fast food in Vietnam</p>
                    </form>
                </div>
                <div class="col-md-9 register-right">
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <p class="register-heading">Create the dishes you desire</p>
                            <h3 class="register-heading">Create Item</h3>
                            <form action="MainController">
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="txtItemID" placeholder="Item ID *" value="${param.txtItemID}" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="txtItemName" placeholder="Item Name *" value="${param.txtItemName}" required/>
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control" name="txtCategory">
                                                <option value="Food" selected>Food</option>
                                                <option value="Drink">Drink</option>
                                            </select> </br>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="txtDescription" placeholder="Description *" value="${param.txtDescription}" required/>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="txtPicture"  placeholder="Link picture (1000 x 1000) *" value="" required/>  
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="txtPrice" placeholder="Price *" value="${param.txtPrice}" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="date" name="txtCreateDate" value="<%=session.getAttribute("DATE")%>" readonly />
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="txtQuantity" placeholder="Quantity *" value="${param.txtQuantity}" required/>
                                        </div>
                                        <input type="hidden" text="txtUserID" value="${sessionScope.USERID}"/>
                                        <input type="submit" name="btnAction" class="btnRegister" value="Add"/>
                                    </div>
                                </div>
                                        <c:out value="${sessionScope.ERROR}"/>
                            </form>

                            <a href="MainController?btnAction=Manage">Return</a>
                            <a href="MainController?btnAction=Logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
