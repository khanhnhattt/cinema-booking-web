<%-- 
    Document   : movieSlide
    Created on : Mar 1, 2023, 8:51:21 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>JSP Page</title>
                    <link rel="stylesheet" href="common/styleMovieSlide.css"/>
          </head>
          <body>
                    <div class="main">
                              <div class="movie-container">
                                        <c:forEach items="${movieList}" var="item">
                                                  <div class="movie">
                                                            <img src="${item.getPosterPath()}" alt="" class="poster"/><br>
                                                            <div class="movie-title">${item.getTitle()}</div>
                                                            <br>
                                                            <div class="feature-movie-content">
                                                                      <a class="button" href="booking?id=${item.getId()}&mod=1">BOOK TICKET</a>
                                                                      <a class="button" href="detail?id=${item.getId()}&see=1">SEE MORE</a>
                                                            </div>
                                                  </div>
                                        </c:forEach>
                              </div>
                    </div>

          </body>
</html>
