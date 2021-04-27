<%-- 
    Document   : login
    Created on : Jan 5, 2021, 11:24:14 PM
    Author     : SE140066
--%>

<%@page import="javafx.scene.control.Alert"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <title>Login Page</title>
        <style>
            html,body{
                background-image: url("image/fastfoodtheme.jpg");
                background-size: cover;
                background-repeat: no-repeat;
                height: 100%;
                font-family: 'Numans', sans-serif;
            }

            .noti{
                color: white;
            }

            .container{
                height: 100%;
                align-content: center;
            }

            .card{
                height: 370px;
                margin-top: auto;
                margin-bottom: auto;
                width: 400px;
                background-color: rgba(0,0,0,0.5) !important;
            }

            .social_icon span{
                font-size: 60px;
                margin-left: 10px;
                color: #FFC312;
            }

            .social_icon span:hover{
                color: white;
                cursor: pointer;
            }

            .card-header h3{
                color: white;
            }

            .social_icon{
                position: absolute;
                right: 20px;
                top: -45px;
            }

            .input-group-prepend span{
                width: 50px;
                background-color: #FFC312;
                color: black;
                border:0 !important;
            }

            input:focus{
                outline: 0 0 0 0  !important;
                box-shadow: 0 0 0 0 !important;

            }

            .remember{
                color: white;
            }

            .remember input
            {
                width: 20px;
                height: 20px;
                margin-left: 15px;
                margin-right: 5px;
            }

            .login_btn{
                color: black;
                background-color: #FFC312;
                width: 100px;
            }

            .login_btn:hover{
                color: black;
                background-color: white;
            }

            .links{
                color: white;
            }

            .links a{
                margin-left: 4px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                    </div>
                    <div class="card-body">
                        <form action="MainController" method="POST">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" name="txtUserID" placeholder="username" required>

                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" name="txtPassword" placeholder="password" required>
                            </div>
                            <div class="g-recaptcha" data-sitekey="6Lf88oAaAAAAAHC3K0fv_LOJOBK-uatP8HYb-kae"></div>
                            <div class="form-group">
                                <input type="submit"  name="btnAction" value="Login" class="btn float-right login_btn">
                            </div>

                            <c:set var="message" value="${sessionScope.MESSAGE}"/>
                            <c:if test="${not empty message}">
                                <script>
                                    alert("${message}");
                                </script>
                            </c:if>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
