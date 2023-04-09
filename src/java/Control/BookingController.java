package Control;

import Model.Booking;
import Model.Movie;
import Model.Room;
import Model.Seat;
import Model.Show;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BookingController extends HttpServlet
{

          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
                    //get show detail for seat booking
                    //receive data   
                    //get showId
                    String showId = "";
                    if (req.getParameter("showId") != null)
                    {
                              showId = String.valueOf(req.getParameter("showId"));
                    }
                    else
                    {

                    }

                    //get movieId
                    String movieId = "";
                    if (req.getParameter("movieId") != null)
                    {
                              movieId = String.valueOf(req.getParameter("movieId"));
                    }
                    else
                    {
                              System.out.println("Its null");
                    }

                    //get customer info (tel not duplicated allowed)
                    String name = "";
                    if (req.getParameter("name") != null)
                    {
                              name = req.getParameter("name");
                    }
                    else
                    {
                              System.out.println("name empty");
                              req.getRequestDispatcher("SeatBooking.jsp").forward(req, resp);

                    }
                    String tel = "";
                    if (req.getParameter("tel") != null)
                    {
                              tel = req.getParameter("tel");
                    }
                    else
                    {
                              System.out.println("tel empty");
                              req.getRequestDispatcher("SeatBooking.jsp").forward(req, resp);

                    }

                    //get selected seats
                    String dataString = req.getParameter("selectedSeatId");
                    String[] dataArray = dataString.replaceAll("\\[", "").replaceAll("]", "").replaceAll("\"", "").split(",");              //convert string to array

                    //proceed
                    Booking b = new Booking(dataArray.length * 90000, Integer.parseInt(showId), name, tel);
                    Seat se = new Seat();
                    Movie m = new Movie(Integer.parseInt(movieId));
                    Show s = new Show(Integer.parseInt(showId), 0, 0, "", "", true);
                    
                    //check if reload then forward to home page
                    if (se.containsId(dataArray[0], showId))
                    {
                              System.out.println("reload seat confirmation");
                              resp.sendRedirect("home");
                              return;
                    }
                    
                    s.getShowById();        //get show detail
                    m.getMovieById();      //get movie detail

                    b.addNewBooking();         //add new booking to DB
                    String bookingId = b.getLatestBookingId();        //set new bookingId
                    b.setId(Integer.parseInt(bookingId));
                    se.addNewRS(dataArray, bookingId, showId);        //add new reserved seat w/ booking
                    ArrayList<String> dataAL = new ArrayList<>();     //get list of seats by bookingId
                    dataAL = se.getRSeatByBookingId(bookingId);
                    
                    //return data
                    req.setAttribute("dataAL", dataAL);
                    req.setAttribute("m", m);
                    req.setAttribute("b", b);
                    req.setAttribute("s", s);
                    req.getRequestDispatcher("BookingConfirm.jsp").forward(req, resp);

          }

          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
                    if (req.getParameter("mod").equals("1"))                    //Booking Controller
                    {
                              //receive data
                              String movieId = req.getParameter("id");

                              //proceed data
//                              Show s = new Show();
//                              Movie m = new Movie();

                              //OOP
                              Show s = new Show();
                              Movie m = new Movie(Integer.parseInt(movieId));

                              //get show details
                              m.getMovieById();
                              ArrayList<Show> showList = s.getListShowByMovie(movieId);

                              req.setAttribute("showList", showList);
                              req.setAttribute("m", m);
                              req.getRequestDispatcher("ShowDetail.jsp").forward(req, resp);
                    }
                    else if (req.getParameter("mod").equals("2"))               //Seat Booking Controller
                    {
                              //receive data
                              String showId = req.getParameter("id");
                              String movieId = req.getParameter("movieId");

                              //OOP
                              Show s = new Show(Integer.parseInt(showId), 0, 0, "", "", true);
                              System.out.println("ShowID in BookingController: "+s.getShowId());
                              Movie m = new Movie(Integer.parseInt(movieId));
                              Seat se = new Seat();
                              

                              //proceed
                              m.getMovieById();
                              s.getShowById();
                              Room r = new Room(s.getRoomId(), 0, "");
                              r.getRoomById();

                              ArrayList<Integer> rsList = se.getListRSeatByShow(showId);
                              ArrayList<Seat> sList = se.getListSeatByShow(showId);
                              System.out.println("print in controller, rsList: " + rsList);

                              //return data
                              req.setAttribute("rsList", rsList);
                              req.setAttribute("sList", sList);
                              req.setAttribute("m", m);
                              req.setAttribute("s", s);
                              req.setAttribute("r", r);
                              req.getRequestDispatcher("SeatBooking.jsp").forward(req, resp);

                    }
          }

}
