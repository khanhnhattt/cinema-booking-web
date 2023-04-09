<%-- 
    Document   : BookingList
    Created on : Mar 14, 2023, 11:07:18 AM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>JSP Page</title>
          </head>
          <body>
                    Booking List of ${b.getTel()}: <br>
                    <c:forEach items="${bookingId}" var="item">
                              <a href="result?id=${item}">Booking ID ${item}</a> <br>
                    </c:forEach>
          </body>
</html>
