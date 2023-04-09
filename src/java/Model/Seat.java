package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Seat
{

          private int seatId, seatNo, roomId;
          private String rowNo;
//          private boolean status;

          public Seat()
          {
                    connect();
          }

          public Seat(int seatId, int seatNo, int roomId, String rowNo)
          {
                    this.seatId = seatId;
                    this.seatNo = seatNo;
                    this.roomId = roomId;
                    this.rowNo = rowNo;
//                    this.status = status;
          }

          public int getSeatId()
          {
                    return seatId;
          }

          public void setSeatId(int seatId)
          {
                    this.seatId = seatId;
          }

          public int getSeatNo()
          {
                    return seatNo;
          }

          public void setSeatNo(int seatNo)
          {
                    this.seatNo = seatNo;
          }

          public int getRoomId()
          {
                    return roomId;
          }

          public void setRoomId(int roomId)
          {
                    this.roomId = roomId;
          }

          public String getRowNo()
          {
                    return rowNo;
          }

          public void setRowNo(String rowNo)
          {
                    this.rowNo = rowNo;
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
                                        System.out.println("Connected to Seat");
                              }
                    }
                    catch (Exception e)
                    {
                    }
          }
          
          //get list of reserved seats in a show
          public ArrayList<Integer> getListRSeatByShow(String showId)
          {
                    ArrayList<Integer> rsList = new ArrayList<>();
                    try
                    {
                              String strSelect = "SELECT SReservedID FROM SeatReserved WHERE ShowID= ? ";
                              System.out.println(strSelect);
                              ps = cnn.prepareStatement(strSelect);
                              ps.setString(1, showId);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        int seatId = rs.getInt(1);

                                        rsList.add(seatId);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetListRSeat: " + e.getMessage());
                    }

                    return rsList;
          }

          //get list of all seats in a room
          public ArrayList<Seat> getListSeatByShow(String showId)
          {
                    ArrayList<Seat> sList = new ArrayList<>();
                    try
                    {
                              String strSelect = "SELECT * FROM seat WHERE RoomID = (select roomID from Show where ShowID=?)";
                              System.out.println(strSelect);
                              ps = cnn.prepareStatement(strSelect);
                              ps.setString(1, showId);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        int seatId = rs.getInt(1);
                                        String rowNo = rs.getString(2);
                                        int seatNo = rs.getInt(3);
                                        int roomId = rs.getInt(4);

                                        Seat newSeat = new Seat(seatId, seatNo, roomId, rowNo);

                                        sList.add(newSeat);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetListSeat: " + e.getMessage());
                    }

                    return sList;
          }
          
          //add new reserved seat to DB
          public void addNewRS(String[] dataArray, String bookingId, String showId)
          {
                    String rsId = "";
                    try
                    {
                              for (int i = 0; i < dataArray.length; i++)
                              {
                                        rsId = dataArray[i];
                                        String strAdd = "insert into SeatReserved values (?, ?, ?)";
                                        ps = cnn.prepareStatement(strAdd);
                                        ps.setInt(1, Integer.parseInt(rsId));
                                        ps.setInt(2, Integer.parseInt(bookingId));
                                        ps.setInt(3, Integer.parseInt(showId));
                                        ps.executeUpdate();
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("AddNewBooking: " + e.getMessage());
                    }
          }

          //get list of reserved seats by booking id (for searching)
          public ArrayList<String> getRSeatByBookingId(String bookingId)
          {
                    ArrayList<String> dataAL = new ArrayList<>();
                    try
                    {
                              String strSelect = "select RowNo, SeatNo from SeatReserved inner join Seat on SReservedID=SeatID where BookingID = ?";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setString(1, bookingId);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        String seatNo = rs.getString(1)+rs.getString(2);
                                        System.out.println(seatNo);
                                        dataAL.add(seatNo);
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("GetRSeatByBookingId: " + e.getMessage());
                    }
                    return dataAL;
          }

          //check for duplicate RS (prevent reloading BookingConfirmation)
          public boolean containsId(String rsId, String showId)
          {
                    try
                    {
                              String strSelect = "select SReservedID from SeatReserved where showID = ?";
                              ps = cnn.prepareStatement(strSelect);
                              ps.setString(1, showId);
                              rs = ps.executeQuery();
                              while (rs.next())
                              {
                                        if (rsId.equals(rs.getString(1)))
                                                  return true;
                              }
                    }
                    catch (Exception e)
                    {
                              System.out.println("ContainsId: " + e.getMessage());
                    }
                    return false;
          }

}
