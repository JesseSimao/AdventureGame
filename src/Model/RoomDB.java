package Model;

import Controller.GameException;
import Controller.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomDB{
    private ArrayList<Room> rooms;
    public static final int FIRST_ROOM = 1;
    public static RoomDB instance;

    public RoomDB() {
        //empty constructor
    }

    public int getNextRoomID() throws SQLException, NullPointerException {
        SQLiteDB sdb = null;
        try {
            sdb = new SQLiteDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int next = sdb.getMaxValue("roomNumber", "Room") + 1;
        sdb.close();
        return next;
    }

    public Room getRoom(int id) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Room rm = new Room();
        String sql = "SELECT * FROM Room WHERE roomNumber = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            rm.setRoomNumber(rs.getInt("roomNumber"));
            rm.setRoomName(rs.getString("roomName"));
            rm.setRoomDescription(rs.getString("roomDescription"));
            rm.setExits(rs.getString("exits"));
        } else {
            throw new SQLException("Room " + id + " not found");
        }
        sdb.close();
        return rm;
    }

    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room> rooms = new ArrayList<>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT * FROM Room";
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            Room rm = new Room();
            rm.setRoomNumber(rs.getInt("roomNumber"));
            rm.setRoomName(rs.getString("roomName"));
            rm.setRoomDescription(rs.getString("roomDescription"));
            rm.setExits(rs.getString("exits"));
            rooms.add(rm);
        }
        sdb.close();
        return rooms;
    }

    public static RoomDB getInstance() throws GameException, SQLException, ClassNotFoundException {
        if (instance == null)
        {
            instance = new RoomDB();
        }
        return instance;
    }

    public Map<String, Integer> getExitsFromRoom(int roomId) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Map<String, Integer> exitsMap = new HashMap<>();
        String sql = "SELECT exits FROM Room WHERE roomNumber = " + roomId;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            String exitsString = rs.getString("exits");
            String[] exitsArray = exitsString.split(",");
            for (String exit : exitsArray) {
                String[] exitData = exit.trim().split(":");
                if (exitData.length == 2) {
                    String direction = exitData[0].trim();
                    int destination = Integer.parseInt(exitData[1].trim());
                    exitsMap.put(direction, destination);
                }
            }
        } else {
            throw new SQLException("Room " + roomId + " not found");
        }
        sdb.close();
        return exitsMap;
    }


    public void updateRoom(Room rm) throws GameException {
        for (Room room : rooms)
        {
            if (room.getRoomNumber() == rm.getRoomNumber())
                {
                    room.setRoomName(rm.getRoomName());
                    room.setRoomDescription(rm.getRoomDescription());
                    //room.setRoomItems(rm.getRoomItems());
                    room.setVisited(rm.isVisited());
                    //room.setExits(rm.getExits());
                    return;
                }
            }
    }

}



