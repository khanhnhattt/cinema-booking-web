<%-- 
    Document   : SeatBooking
    Created on : Mar 8, 2023, 7:56:06 PM
    Author     : khanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
          <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>${m.getTitle()}</title>
                    <link rel="stylesheet" href="common/styleSeatBooking.css"/>
          </head>
          <body background="${m.getBackgroundPath()}">
                    <script>
                              var rsList = [
                              <c:forEach items="${rsList}" var="rs">
                                        "<c:out value="${rs}" />",
                              </c:forEach>
                              ];
                              window.onload = innit;
                              function innit() {
                                        // the code to be called when the dom has loaded
                                        // #document has its nodes
                                        for (var i = 0; i < rsList.length; i++) {
                                                  var elementArray = document.getElementById(rsList[i]);
                                                  elementArray.className = "seat sold";
                                        }
                              }

                              var selectedSeatId = [];      //list of selected seatId
                              function selectedSeat(id) {
                                        let clName = document.getElementById(id).className;
                                        if (clName === "seat sold")
                                        {

                                        } else if (clName === "seat selected")
                                        {
                                                  document.getElementById(id).className = "seat";
                                                  selectedSeatId.pop(document.getElementById(id).getAttribute('id'));   //remove seatId
                                                  console.log(selectedSeatId);

                                        } else
                                        {
                                                  document.getElementById(id).className = "seat selected";
                                                  selectedSeatId.push(document.getElementById(id).getAttribute('id'));  //add seatId
                                                  console.log(selectedSeatId);
                                        }

                              }

                              //check empty customer info
                              function validateForm() {
                                        var a = document.forms["Form"]["name"].value;
                                        var b = document.forms["Form"]["tel"].value;
                                        if ((a === null || a === "") || (b === null || b === "")) {
                                                  alert("Please fill in all required fields");
                                                  return false;
                                        }
                              }

                              //pass array from js to servlets
                              function book() {
                                        if (selectedSeatId.length < 1)
                                        {
                                                  alert("Please select at least one seat");
                                                  return false;
                                        } else
                                        {
                                                  let selectedSeatIdString = JSON.stringify(selectedSeatId);
                                                  document.getElementById('selectedSeatId').value = selectedSeatIdString;
                                                  console.log("selectedSeatIdString: " + selectedSeatIdString);
                                        }
                              }

//                              function validateTel() {
//                                        // Define the regex
//                                        var phoneRegex = /^0\d{9}$/;
//
//                                        // Get the phone number from user input
//                                        var phoneNumber = document.getElementByName('tel').value;
//
//                                        // Test the phone number against the regex
//                                        if (phoneRegex.test(phoneNumber)) {
//                                                  // Phone number is valid
//                                                  alert("Valid phone number");
//                                        } else {
//                                                  // Phone number is invalid
//                                                  alert("Invalid phone number");
//                                        }
//                              }
                    </script>
                    <div class="header"><a href="home">Home</a></div>
                    <div class="main">
                              <div class="movie-title">${m.getTitle()}</div>
                              <div class="booking-container">
                                        <div class="poster">
                                                  <img src="${m.getPosterPath()}" alt="" class="poster"/>
                                                  <div>Date: ${s.getDate()}</div>
                                                  <div>Start Time: ${s.getStartTime()}</div>
                                                  <div>Room: ${s.getRoomId()}</div>
                                        </div>
                                        <div class="screen-container">
                                                  <div class="seat-legend">
                                                            <ul>
                                                                      <li>
                                                                                <div class="seat"></div>
                                                                                <small>Available</small>
                                                                      </li>
                                                                      <li>
                                                                                <div class="seat selected"></div>
                                                                                <small>Selected</small>
                                                                      </li>
                                                                      <li>
                                                                                <div class="seat sold"></div>
                                                                                <small>Sold</small>
                                                                      </li>
                                                            </ul>
                                                  </div>

                                                  <div class="seat-container">
                                                            <div class="screen">Screen</div>
                                                            <div class="row">
                                                                      <c:set var="count" value="${0}"/>
                                                                      <c:forEach var="se" items="${sList}">
                                                                                <c:if test="${count == 10}">
                                                                                          <div class="row">
                                                                                                    <c:set var="count" value="${0}"/>
                                                                                          </c:if>
                                                                                          <div id="${se.getSeatId()}" class="seat" onclick="selectedSeat(this.id)"></div>
                                                                                          <c:set var="count" value="${count + 1}" />
                                                                                          <c:if test="${count == 10}">
                                                                                          </div>
                                                                                </c:if>
                                                                      </c:forEach>

                                                            </div>
                                                            <form name="Form" action="booking" method="POST" onsubmit="return validateForm()">
                                                                      <input type="hidden" id="selectedSeatId" name="selectedSeatId" value="">
                                                                      <input type="hidden" id="showId" name="showId" value="${s.getShowId()}">
                                                                      <input type="hidden" id="showId" name="movieId" value="${m.getId()}"><br>
                                                                      <div class="customer-info-input">
                                                                                <div class="input-group">
                                                                                          <label for="input">Fullname</label>
                                                                                          <input type="text" name="name" id="input">
                                                                                </div>
                                                                                <div class="input-group">
                                                                                          <label for="input">Tel</label>
                                                                                          <input type="text" name="tel" id="input">
                                                                                </div>
                                                                                <input type="submit" class="button" value="Next" onclick="return book()">
                                                                      </div>
                                                            </form>
                                                  </div>
                                        </div>

                              </div>
          </body>
</html>
