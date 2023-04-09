package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Show
{

          private int showId, movieId, roomId;
          private String date, startTime;
          private boolean status;

          public Show()
          {
                    connect();
          }

          public Show(int showId, int movieId, int roomId, String date, String startTime)
          {
                    this.showId = showId;
                    this.movieId = movieId;
                    this.roomId = roomId;
                    this.date = date;
                    this.startTime = startTime;
                    connect();
          }

          public Show(int showId, int movieId, int roomId, String date, String startTime, boolean status)
          {
                    this.showId = showId;
                    this.movieId = movieId;
                    this.roomId = roomId;
                    this.date = date;
                    this.startTime = startTime;
                    this.status = status;
                    connect();
          }

          public int getShowId()
          {
                    return showId;
          }

          public void setShowId(int showId)
          {
                    this.showId = showId;
          }

          public int getMovieId()
          {
                    return movieId;
          }

          public void setMovieId(int movieId)
          {
                    this.movieId = movieId;
          }

          public int getRoomId()
          {
                    return roomId;
          }

          public void setRoomId(int roomId)
          {
                    this.roomId = roomId;
          }

          public String getStartTime()
          {
                    return startTime;
          }

          public void setStartTime(String startTime)
          {
                    this.startTime = startTime;
          }

          public String getDate()
          {
                    return date;
          }

          public void setDate(String date)
          {
                    this.date = date;
          }

          public boolean isStatus()
          {
                    return status;
          }

          public void setStatus(boolean status)
          {
                    this.status = status;
          }
          

          //Khai báo các thành phần xử lý DB
          Connection cnn;     //Kết nối DB
          PreparedStatement ps;
          ResultSet rs;       //Lưu trữ xử lý dữ liệu

          private void connect()
          {
                    try
                    {
                              cnn = (new DBContext()).connection;
                              if (cnn != null)
                              {
                                        System.out.println("Connected to Show");
                              }
                    }
                    catch (Exception e)
                    {
                    }
          }

          //get list show by movie
          public ArrayList<Show> getListShowByMovie(String movieId)
          {
                    ArrayList<Show> showList = new ArrayList<>();
                    try
                    {
                              String strSelect = "select * from Show where MovieID = " + movieId + " order by StartTime ";
                              ps = cnn.prepareStatement(strSelect);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        int showId = rs.getInt(1);
                                        String startTime = "", date = "";
                                        SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
                                        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
                                        if (rs.getDate(2) != null)
                                        {
                                                  startTime = ft.format(rs.getTime(2));
                                                  date = fd.format(rs.getDate(2));
                                        }
                                        int movId = rs.getInt(3);
                                        int roomId = rs.getInt(4);

                                        boolean status = checkStatus(showId);

                                        Show newShow = new Show(showId, movId, roomId, date, startTime, status);

                                        showList.add(newShow);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetListShow: " + e.getMessage());
                    }
                    return showList;
          }

          //get show detail by id
          public void getShowById()
          {
                    try
                    {
                              String strSelect = "select * from Show where ShowID =?";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setInt(1, showId);
                              rs = ps.executeQuery();
                              System.out.println("before while");
                              while (rs.next())
                              {
                                        System.out.println("in while");
                                        this.showId = showId;
                                        startTime = "";
                                        date = "";
                                        SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
                                        SimpleDateFormat ft = new SimpleDateFormat("hh:mm");
                                        if (rs.getDate(2) != null)
                                        {
                                                  startTime = ft.format(rs.getTime(2));
                                                  date = fd.format(rs.getDate(2));
                                        }
                                        movieId = rs.getInt(3);
                                        roomId = rs.getInt(4);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetShowById: " + e.getMessage());
                    }
          }

          private boolean checkStatus(int showId)
          {
                    Seat se = new Seat();
                    ArrayList<Integer> rsList = se.getListRSeatByShow(String.valueOf(showId));
                    ArrayList<Seat> sList = se.getListSeatByShow(String.valueOf(showId));
                    if (rsList.size() == sList.size())
                    {
                              //full
                              System.out.println("het cho");
                              return true;
                    }
                    else
                    {
                              return false;
                    }
          }
}
