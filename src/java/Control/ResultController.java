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

public class ResultController extends HttpServlet
{

          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
          }

          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
                    //show booking
                    String bookingId = req.getParameter("id");

                    Booking b = new Booking();
                    try
                    {
                              b = new Booking(Integer.parseInt(bookingId));
                              b.getBookingById();
                    }
                    catch (Exception e)
                    {
                              System.out.println("SearchById: " + e.getMessage());
                    }

                    String err = "";
                    if (b.getName()== null)
                    {
                              System.out.println("no booking ID");
                              err = "Booking ID " + b.getId() + " does not exist.";
                              req.setAttribute("err", err);
                              req.getRequestDispatcher("BookingResult.jsp").forward(req, resp);
                    }

                    Seat se = new Seat();
                    Show s = new Show(b.getShowId(), 0, 0, "", "");
                    Movie m = new Movie(s.getMovieId());

                    s.getShowById();    //get show detail
                    m.getMovieById();      //get movie detail

                    ArrayList<String> dataAL = dataAL = se.getRSeatByBookingId(String.valueOf(b.getId()));

                    //return
//                    req.setAttribute("bookingId", bookingId);
                    req.setAttribute("dataAL", dataAL);
                    req.setAttribute("m", m);
                    req.setAttribute("b", b);
                    req.setAttribute("s", s);
//                    req.getRequestDispatcher("BookingList.jsp").forward(req, resp);
                    req.getRequestDispatcher("BookingResult.jsp").forward(req, resp);
          }

}
