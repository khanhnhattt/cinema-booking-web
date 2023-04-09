<%-- 
    Document   : BookingResult
    Created on : Mar 11, 2023, 7:31:15 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>JSP Page</title>
                    <link rel="stylesheet" href="common/styleBookingResult.css"/>
          </head>
          <body>
                    <div class="header"><a href="home">Home</a></div>
                    <div class="main">
                              <div class="booking-header">BOOKING CONFIRMATION</div>
                              <div class="booking-container">
                                        <div class="poster">
                                                  <img src="${m.getPosterPath()}" alt="" class="poster"/><br>
                                        </div>
                                        <div class="info">
                                                  <div class="booking-info">
                                                            <table>
                                                                      <c:if test="${not empty err}">
                                                                                <tr>
                                                                                          <td class="label"></td>
                                                                                          <td><b>${err}</b></td>
                                                                                </tr>
                                                                      </c:if>
                                                                      <tr>
                                                                                <td class="label">Booking ID</td>
                                                                                <td>${b.getId()}</td>
                                                                      </tr>
                                                                      <tr>
                                                                                <td class="label">Room</td>
                                                                                <td>${s.getRoomId()}</td>
                                                                      </tr>
                                                                      <tr>
                                                                                <td class="label">Seat</td>
                                                                                <td>
                                                                                          <c:forEach items="${dataAL}" var="item">
                                                                                                    ${item} 
                                                                                          </c:forEach>
                                                                                </td>
                                                                      </tr>
                                                                      <tr class="booking-info">
                                                                                <td class="label">Price</td>
                                                                                <td>${b.getPrice()} VNĐ</td>
                                                                      </tr>
                                                                      <tr>
                                                                                <td class="label">Customer name</td>
                                                                                <td>${b.getName()}</td>
                                                                      </tr>
                                                                      <tr>
                                                                                <td class="label">Customer tel</td>
                                                                                <td>${b.getTel()}</td>
                                                                      </tr>
                                                            </table>
                                                  </div>
                                                  <a href="home" class="button">Back to Home</a>
                                        </div>
                              </div>
                    </div>
          </body>
</html>
