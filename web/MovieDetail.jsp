<%-- 
    Document   : MovieDetail
    Created on : Mar 5, 2023, 2:00:44 PM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>${m.getTitle()}</title>
                    <link rel="stylesheet" href="common/styleMovieDetail.css"/>
          </head>
          <body background="${m.getBackgroundPath()}">
                    <div class="header"><a href="home">Home</a></div>
                    <div class="main">
                              <div class="movie-container">
                                        <div class="movie-title">${m.getTitle()}</div>
                                        <div class="movie-desc">${m.getDesc()}</div>
                                        <div class="movie-content">
                                                  <div class="movie-content-left">
                                                            <div><b>Director: </b>${m.getDirector()}</div>
                                                            <div><b>Cast: </b>${m.getCast()}</div>
                                                            <div><b>Release Date: </b>${m.getReleaseDate()}</div>
                                                            <div><b>Duration: </b>${m.getDuration()} minutes</div>
                                                            <div><b>Rated: </b>${m.getRated()}</div>
                                                  </div>
                                                  <div class="movie-content-right">
                                                            <br>
                                                            <a href="booking?id=${m.getId()}&mod=1" class="button">BOOK NOW</a>
                                                  </div>
                                        </div>
                              </div>
                    </div>
          </body>
</html>
