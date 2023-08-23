package Controller;

import Model.ItemDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Item> inventory;
    private Player player;

    public Player() throws SQLException, ClassNotFoundException {
        currentRoom = Room.getRoom(1);
        inventory = new ArrayList<>(); // Should start empty
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    public boolean hasItem(String itemName)
    {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(String itemName) throws SQLException, ClassNotFoundException, GameException {
        ItemDB itemDB = ItemDB.getInstance();
        Item item = itemDB.getItemByName(itemName);
        inventory.add(item);
    }

    protected void removeItem(String itemName) throws GameException, SQLException, ClassNotFoundException {
        ItemDB itemDB = ItemDB.getInstance();
        Item item = itemDB.getItemByName(itemName);
        inventory.remove(itemName);
    }

    protected String printInventory() {
       // StringBuilder content = new StringBuilder("Content of Inventory:\n");
        String items = "";
        if (inventory.isEmpty()) {
            //content.append("There's nothing there");
            return "There is nothing there.";
        } else {
            for (Item item : inventory) {
                //content.append(item.getItemName()).append("\n");
                items = item.toString();
            }
        }

        return items;
    }


}
