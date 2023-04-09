package Control;

import Model.Booking;
import Model.Movie;
import Model.Seat;
import Model.Show;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MovieDetailController extends HttpServlet
{

          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
                    //find ticket by booking ID
                    //receive
                    String input = req.getParameter("input");
                    String tel = req.getParameter("tel");

                    //proceed
                    Booking b = new Booking();
                    ArrayList<String> bookingId = new ArrayList<>();
                    try
                    {
                              b = new Booking(tel.trim());
                              bookingId = b.getBookingIdByTel();
//                              b.getBookingById();
                    }
                    catch (Exception e)
                    {
                              System.out.println("SearchById: " + e.getMessage());
                    }

//                    String err = "";
//                    if (b.getName()== null)
//                    {
//                              System.out.println("no booking ID");
//                              err = "Booking ID " + b.getId() + " does not exist.";
//                              req.setAttribute("err", err);
//                              req.getRequestDispatcher("BookingResult.jsp").forward(req, resp);
//                    }
                    System.out.println("bookingID not empty");

                    Seat se = new Seat();
                    Show s = new Show(b.getShowId(), 0, 0, "", "");
                    Movie m = new Movie(s.getMovieId());

                    s.getShowById();    //get show detail
                    m.getMovieById();      //get movie detail

                    ArrayList<String> dataAL = dataAL = se.getRSeatByBookingId(String.valueOf(b.getId()));

                    //return
                    req.setAttribute("bookingId", bookingId);
                    req.setAttribute("dataAL", dataAL);
                    req.setAttribute("m", m);
                    req.setAttribute("b", b);
                    req.setAttribute("s", s);
                    req.getRequestDispatcher("BookingList.jsp").forward(req, resp);
//                    req.getRequestDispatcher("BookingResult.jsp").forward(req, resp);
          }

          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
                    //show movie detail 
                    //receive data
                    String id = req.getParameter("id");

                    //proceed
//                    Movie m = new Movie();
                    Movie m = new Movie(Integer.parseInt(id));

                    //return data
//                    m.getMovieById(id);
                    m.getMovieById();
                    int curView = m.getView();
                    System.out.println("curView: "+curView);
                    m.setView(curView++);
                    m.setNewViewInDB();

                    req.setAttribute("m", m);
                    req.getRequestDispatcher("MovieDetail.jsp").forward(req, resp);
          }

}
