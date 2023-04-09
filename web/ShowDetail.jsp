<%-- 
    Document   : ShowDetail
    Created on : Mar 5, 2023, 10:31:42 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>${m.getTitle()}</title>
                    <link rel="stylesheet" href="common/styleShowDetail.css"/>
                    <script src="">
                              function myhref(link) {
                                        window.location.href = link;
                              }
                    </script>
          </head>
          <body background="${m.getBackgroundPath()}">
                    <div class="header"><a href="home">Home</a></div>
                    <div class="main">
                              <div class="movie-title">${m.getTitle()}</div>
                              <div class="booking-container">
                                        <div class="poster"><img src="${m.getPosterPath()}" alt="" class="poster"/></div>
                                        <div class="datetime-container">
                                                  <c:forEach items="${showList}" var="item">
                                                            <c:choose>
                                                                      <c:when test="${item.isStatus()}">
                                                                                <a class="button" style="color: red">
                                                                                          ${item.getDate()}<br> 
                                                                                          <span>${item.getStartTime()}</span><br>
                                                                                          Hết chỗ
                                                                                </a>
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                                <a class="button" href="booking?id=${item.getShowId()}&movieId=${item.getMovieId()}&mod=2">
                                                                                          ${item.getDate()}<br> 
                                                                                          <span>${item.getStartTime()}</span>
                                                                                </a>
                                                                      </c:otherwise>
                                                            </c:choose>

                                                  </c:forEach>
                                        </div>

                              </div>
                    </div>
          </body>
</html>

