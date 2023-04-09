<%-- 
    Document   : home
    Created on : Feb 14, 2023, 10:26:15 AM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Home Page</title>
                    <link rel="stylesheet" href="common/styleHome.css"/>
          </head>
          <body>
                    <table class='main'>
                              <tr id="navbar">
                                        <td id='logo'><img src='poster/logo.png' width='300px' alt="LOGO"></td>
                                        <!--<td id='banner' background='images/banner2.jpg'></td>-->
                                        <td colspan='2' align='center'>ĐỒNG GIÁ 90K/VÉ NHÉ AE HEHE <br><br> MOVIES LIST</td>
                              </tr>

                              <tr>
                                        <td id="sidebar">
                                                  <%@include file="FindTicket.jsp"%>
                                        </td>
                                        <td id='main-content'>
                                                  <%@include file="MovieSlide.jsp"%>
                                        </td>
                              </tr>

<!--                              <tr id='footer1'>
                                        <td colspan='2' align='center'>@Copyright by Your fullname</td>	
                              </tr>-->
                    </table>
          </body>
</html>
