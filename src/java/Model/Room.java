package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Room
{

          private int roomId, noOfRows;
          String name;

          public Room()
          {
                    connect();
          }

          public Room(int roomId, int noOfRows, String name)
          {
                    this.roomId = roomId;
                    this.noOfRows = noOfRows;
                    this.name = name;
                    connect();
          }

          public int getRoomId()
          {
                    return roomId;
          }

          public void setRoomId(int roomId)
          {
                    this.roomId = roomId;
          }

          public int getNoOfRows()
          {
                    return noOfRows;
          }

          public void setNoOfRows(int noOfSeats)
          {
                    this.noOfRows = noOfSeats;
          }

          public String getName()
          {
                    return name;
          }

          public void setName(String name)
          {
                    this.name = name;
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
                                        System.out.println("Connected to Room");
                              }
                    }
                    catch (Exception e)
                    {
                    }
          }
          
          //Get room detail by id
          public void getRoomById()
          {
                    try
                    {
                              String strSelect = "select * from Room where RoomID =?";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setInt(1, roomId);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        this.roomId = roomId;
                                        name = rs.getString(2);
                                        noOfRows = rs.getInt(3)/10;
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetRoomById: " + e.getMessage());
                    }
          }
}
