<%-- 
    Document   : findTicket
    Created on : Mar 1, 2023, 8:51:13 PM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>JSP Page</title>
                    <link rel="stylesheet" href="common/styleFindTicket.css"/>
          </head>
          <body>
                    <form action="detail" method="POST">
                              Search by <br><br>
<!--                              <div class="input-group">
                                        <label for="input">Booking ID</label>
                                        <input type="text" name="input" id="id">
                              </div>-->
                              <div class="input-group">
                                        <label for="input">Tel</label>
                                        <input type="text" name="tel" id="id">
                              </div>
                              <br>
                              <input type="Submit" class="button" value="Search"><br>
                    </form>
          </body>
</html>
