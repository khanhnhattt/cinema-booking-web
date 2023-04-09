package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Movie
{

          private int id, duration, view;
          private String title, director, cast, releaseDate, rated, desc, posterPath, backgroundPath;

          public Movie()
          {
                    connect();
          }

          public Movie(int id)
          {
                    this.id = id;
                    connect();
          }

          public Movie(int id, int duration, String title, String director, String cast, String releaseDate, String rated, String desc, String posterPath)
          {
                    this.id = id;
                    this.duration = duration;
                    this.title = title;
                    this.director = director;
                    this.cast = cast;
                    this.releaseDate = releaseDate;
                    this.rated = rated;
                    this.desc = desc;
                    this.posterPath = posterPath;
                    connect();
          }

          public Movie(int id, int duration, int view, String title, String director, String cast, String releaseDate, String rated, String desc, String posterPath, String backgroundPath)
          {
                    this.id = id;
                    this.duration = duration;
                    this.view = view;
                    this.title = title;
                    this.director = director;
                    this.cast = cast;
                    this.releaseDate = releaseDate;
                    this.rated = rated;
                    this.desc = desc;
                    this.posterPath = posterPath;
                    this.backgroundPath = backgroundPath;
                    connect();
          }

          public int getId()
          {
                    return id;
          }

          public void setId(int id)
          {
                    this.id = id;
          }

          public int getDuration()
          {
                    return duration;
          }

          public void setDuration(int duration)
          {
                    this.duration = duration;
          }

          public String getTitle()
          {
                    return title;
          }

          public void setTitle(String title)
          {
                    this.title = title;
          }

          public String getDirector()
          {
                    return director;
          }

          public void setDirector(String director)
          {
                    this.director = director;
          }

          public String getCast()
          {
                    return cast;
          }

          public void setCast(String cast)
          {
                    this.cast = cast;
          }

          public String getReleaseDate()
          {
                    return releaseDate;
          }

          public void setReleaseDate(String releaseDate)
          {
                    this.releaseDate = releaseDate;
          }

          public String getRated()
          {
                    return rated;
          }

          public void setRated(String rated)
          {
                    this.rated = rated;
          }

          public String getDesc()
          {
                    return desc;
          }

          public void setDesc(String desc)
          {
                    this.desc = desc;
          }

          public String getPosterPath()
          {
                    return posterPath;
          }

          public void setPosterPath(String posterPath)
          {
                    this.posterPath = posterPath;
          }

          public String getBackgroundPath()
          {
                    return backgroundPath;
          }

          public void setBackgroundPath(String backgroundPath)
          {
                    this.backgroundPath = backgroundPath;
          }

          public int getView()
          {
                    return view;
          }

          public void setView(int view)
          {
                    this.view = view;
          }

          //Khai báo các thành phần xử lý DB
          Connection cnn;     //Kết nối DB
          Statement stm;      //Thực thi câu lệnh SQL
          PreparedStatement ps;
          ResultSet rs;       //Lưu trữ xử lý dữ liệu

          private void connect()
          {
                    try
                    {
                              cnn = (new DBContext()).connection;
                              if (cnn != null)
                              {
                                        System.out.println("Connected to Movie");
                              }
                    }
                    catch (Exception e)
                    {
                    }
          }

          //get list movie
          public ArrayList<Movie> getMovieList()
          {
                    ArrayList<Movie> movieList = new ArrayList<>();
                    try
                    {
                              String strSelect = "Select * from Movie order by [view] desc";
                              stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                              rs = stm.executeQuery(strSelect);
                              while (rs.next())
                              {
                                        int id = rs.getInt(1);
                                        String title = rs.getString(2);
                                        String director = rs.getString(3);
                                        String cast = rs.getString(4);
                                        String date = "";
                                        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                                        if (rs.getDate(5) != null)
                                        {
                                                  date = f.format(rs.getDate(5));
                                        }
                                        int duration = rs.getInt(6);
                                        String rated = rs.getString(7);
                                        String movieDesc = rs.getString(8);
                                        String pPath = rs.getString(9);

                                        Movie newMovie = new Movie(id, duration, title, director, cast, date, rated, movieDesc, pPath);
                                        //add movie to arraylist
                                        movieList.add(newMovie);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("getMovieList: " + e.getMessage());
                    }
                    return movieList;
          }

          //Get Movie detail by id
          public void getMovieById()
          {
                    try
                    {
                              String strSelect = "select * from Movie where MovieID =?";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setInt(1, id);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        this.id = id;
                                        title = rs.getString(2);
                                        director = rs.getString(3);
                                        cast = rs.getString(4);
                                        releaseDate = "";
                                        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                                        if (rs.getDate(5) != null)
                                        {
                                                  releaseDate = f.format(rs.getDate(5));
                                        }
                                        duration = rs.getInt(6);
                                        rated = rs.getString(7);
                                        desc = rs.getString(8);
                                        posterPath = rs.getString(9);
                                        backgroundPath = rs.getString(10);
                                        view = rs.getInt(11);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetMovieById: " + e.getMessage());
                    }

          }

          public void setNewViewInDB()
          {
                    //set new views
                    try
                    {
                              System.out.println("view: "+(view+1));
                              String strAdd = "Update Movie Set [View] = ? where MovieID = ?";
                              ps = cnn.prepareStatement(strAdd);
                              ps.setInt(1, view+1);
                              ps.setInt(2, id);
                              ps.executeUpdate();
                    }
                    catch (Exception e)
                    {
                              System.out.println("UpdateView: " + e.getMessage());
                    }
          }
}
