package Controller;

import Model.ItemDB;
import Model.PuzzleDB;
import Model.RoomDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room
{
    private int roomNumber;
    private String roomName;
    private String roomDescription;
    private String exits;
    private boolean visited;
    private ArrayList<Integer> items;
    private ArrayList<String> puzzles;
    private Map<String, Integer> exitsMap;
    private RoomDB roomDB;
    private ItemDB itemDB;
    private PuzzleDB puzzleDB;
    private Player player;

    public Room(int roomNumber, String roomName, String roomDescription, String exits) throws GameException, SQLException, ClassNotFoundException {
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
        this.roomName = roomName;
        this.exits = exits;
        this.visited = false;

        roomDB = RoomDB.getInstance();
        itemDB = ItemDB.getInstance();
        puzzleDB = PuzzleDB.getInstance();
        items = new ArrayList<>();
        exitsMap = new HashMap<>();

        exitsMap = roomDB.getExitsFromRoom(roomNumber);
    }

    public Room() {
        RoomDB rdb = new RoomDB();
        try{
            roomNumber = rdb.getNextRoomID();
        }
        catch(SQLException sqe){
            System.out.println(sqe.getMessage());
        }
    }

    public static Room getRoom(int id) throws SQLException, ClassNotFoundException {
        RoomDB rdb = new RoomDB();
        return rdb.getRoom(id);
    }

    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        RoomDB rdb = new RoomDB();
        return rdb.getAllRooms();
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomId) {
        this.roomNumber = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getExits() {
        return exits;
    }

    public void setExits(String exits) {
        this.exits = exits;
    }

    public boolean isVisited() {
        return visited;
    }

    public Room retrieveByID(int roomNum) throws GameException, SQLException, ClassNotFoundException {
        roomDB = RoomDB.getInstance();
        return roomDB.getRoom(roomNum);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


    public int validDirection(String cmd) throws GameException {
        String direction = cmd.toLowerCase();
        if (exits.contains(direction)) {
            return exits.indexOf(direction) + 1;
        } else {
            throw new GameException("That's not a valid direction!");
        }
    }

    public void move(String cmd) throws SQLException, ClassNotFoundException, GameException, NullPointerException {
        Room room = new Room();
        int key = room.getRoomNumber();
        if(validDirection(cmd) != 0)
        {
            for(Room rm: player.getCurrentRoom().getAllRooms())
            {
                if(rm.getRoomNumber() == exitsMap.get(key))
                {
                    player.setCurrentRoom(rm);
                    break;
                }
            }
        }
    }

    public void removeItem(String itemName) throws GameException, SQLException, ClassNotFoundException {
        ItemDB itemDB = ItemDB.getInstance();
        Item item = itemDB.getItemByName(itemName);
        if (item != null) {
            items.add(item.getItemNumber());
            updateRoom();
        } else {
            throw new GameException("Item not found in the database.");
        }
    }

    public void addItem(String itemName) throws GameException, SQLException, ClassNotFoundException {
        ItemDB itemDB = ItemDB.getInstance();
        Item item = itemDB.getItemByName(itemName);
        if (item != null && items.contains(item.getItemNumber())) {
            items.remove(Integer.valueOf(item.getItemNumber()));
            updateRoom();
        } else {
            throw new GameException("Item not found in the room.");
        }
    }

    public void dropItem(Item item) throws GameException {
        items.add(item.getItemNumber());
        updateRoom();
    }

    public boolean hasItem(String itemName) throws GameException, SQLException, ClassNotFoundException {
        for (int itemId : items) {
            Item item = itemDB.getItem(itemId);
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void updateRoom() throws GameException {
        roomDB.updateRoom(this);
    }

    public Map<String, Integer> getExitsMap() throws SQLException, ClassNotFoundException {
        if (exitsMap == null) {
            exitsMap = new HashMap<>();
            String[] exitsArray = exits.split(",");
            for (String exit : exitsArray) {
                String direction = exit.trim().toLowerCase();
                exitsMap.put(direction, 1); // Set the destination initially as -1
            }
        }
        return exitsMap;
    }



    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomName='" + roomName + '\'' +
                ", roomDescription='" + roomDescription + '\'' +
                ", exits='" + exits + '\'' +
                '}';
    }
}
