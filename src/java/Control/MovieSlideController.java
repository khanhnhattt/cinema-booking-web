package Control;

import Model.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MovieSlideController extends HttpServlet
{

          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {

          }

          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
          {
                    //get movie slide on homepage
                    //receive data

                    //proceed
                    Movie m = new Movie();

                    //return data
                    ArrayList<Movie> movieList = new ArrayList<>();
                    movieList = m.getMovieList();

                    
                    
                    req.setAttribute("movieList", movieList);
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
          }

}
