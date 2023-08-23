package Controller;

import Model.ItemDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class Item
{
    private int itemNumber;
    private int itemRoom;
    private String itemName;
    private String itemDescription;

    public Item(int itemId, int itemRoom, String itemName, String itemDescription)
    {
        this.itemNumber = itemId;
        this.itemRoom = itemRoom;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }
    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemId) {
        this.itemNumber = itemId;
    }

    public int getItemRoom() {
        return itemRoom;
    }

    public void setItemRoom(int itemRoom) {
        this.itemRoom = itemRoom;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }


    public Item()
    {
        ItemDB idb = new ItemDB();
        try
        {
            itemNumber = idb.getNextItemNumber();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Item getItem(int id) throws SQLException, ClassNotFoundException
    {
        ItemDB idb = new ItemDB();
        return idb.getItem(id);
    }

    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException
    {
        ItemDB idb = new ItemDB();
        return idb.getAllItems();
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", itemRoom=" + itemRoom +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
