package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Booking
{

          private int id, price, showId;
          private String name, tel;

          public Booking()
          {
                    connect();
          }

          public Booking(int id)
          {
                    this.id = id;
                    connect();

          }

          public Booking(String tel)
          {
                    this.tel = tel;
                    connect();

          }

          public Booking(int id, int price, int showId, String name, String tel)
          {
                    this.id = id;
                    this.price = price;
                    this.showId = showId;
                    this.name = name;
                    this.tel = tel;
                    connect();
          }

          public Booking(int price, int showId, String name, String tel)
          {
                    this.price = price;
                    this.showId = showId;
                    this.name = name;
                    this.tel = tel;
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

          public int getPrice()
          {
                    return price;
          }

          public void setPrice(int price)
          {
                    this.price = price;
          }

          public int getShowId()
          {
                    return showId;
          }

          public void setShowId(int showId)
          {
                    this.showId = showId;
          }

          public String getName()
          {
                    return name;
          }

          public void setName(String name)
          {
                    this.name = name;
          }

          public String getTel()
          {
                    return tel;
          }

          public void setTel(String tel)
          {
                    this.tel = tel;
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
                                        System.out.println("Connected to Booking");
                              }
                    }
                    catch (Exception e)
                    {
                    }
          }

          //add new booking
          public void addNewBooking()
          {
                    try
                    {
                              String strAdd = "insert into Booking(CustomerName, CustomerTel, TotalPrice, ShowID) "
                                      + "values (?, ?, ?, ?)";
                              ps = cnn.prepareStatement(strAdd);
                              ps.setString(1, name);
                              ps.setString(2, tel);
                              ps.setInt(3, price);
                              ps.setInt(4, showId);
                              ps.executeUpdate();
                    }
                    catch (Exception e)
                    {
                              System.out.println("AddNewBooking: " + e.getMessage());
                    }
          }

          public ArrayList<String> getBookingIdByTel()
          {
                    ArrayList<String> bookId = new ArrayList<>();
                    try
                    {
                              String strSelect = "select BookingID from Booking where CustomerTel= ? ";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setString(1, tel);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        bookId.add(rs.getString(1));
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetBookingIdByTel: " + e.getMessage());
                    }
                    return bookId;
          }

          public String getLatestBookingId()
          {
                    String bookId = "";
                    try
                    {
                              String strSelect = "select MAX(BookingID) from Booking";
                              ps = cnn.prepareStatement(strSelect);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        bookId = rs.getString(1);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetLatestBookingId: " + e.getMessage());
                    }
                    return bookId;
          }

          public void getBookingById()
          {
                    try
                    {
                              String strSelect = "select * from Booking where BookingID = ?";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setInt(1, id);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        name = rs.getString(2);
                                        tel = rs.getString(3);
                                        price = rs.getInt(4);
                                        showId = rs.getInt(5);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetBookingById: " + e.getMessage());
                    }
          }

//          public void getBookingIdByTel()
//          {
//                    try
//                    {
//                              String strSelect = "select  from Booking where BookingID = ?";
//                              ps = cnn.prepareStatement(strSelect);
//                              ps.setInt(1, id);
//                              rs = ps.executeQuery();
//                              while (rs.next())
//                              {
//                                        name = rs.getString(2);
//                                        tel = rs.getString(3);
//                                        price = rs.getInt(4);
//                                        showId = rs.getInt(5);
//                              }
//                    }
//                    catch (Exception e)
//                    {
//                              System.out.println("GetBookingById: " + e.getMessage());
//                    }
//          }
}
