package Model;

import Controller.GameException;
import Controller.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDB{
    public static ItemDB instance;

    public static ItemDB getInstance() throws GameException {
        if (instance == null)
        {
            instance = new ItemDB();
        }
        return instance;
    }

    public int getNextItemNumber() throws SQLException {
        SQLiteDB idb = null;
        try {
            idb = new SQLiteDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int nextItem = idb.getMaxValue("itemNumber", "Item") + 1;
        idb.close();
        return nextItem;
    }

    public Item getItem(int id) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Item it = new Item();
        String sql = "SELECT * FROM Item WHERE itemNumber = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            it.setItemNumber(rs.getInt("itemNumber"));
            it.setItemRoom(rs.getInt("itemRoom"));
            it.setItemName(rs.getString("itemName"));
            it.setItemDescription(rs.getString("itemDescription"));
        } else {
            throw new SQLException("Item " + id + " not found");
        }
        sdb.close();
        return it;
    }

    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "SELECT * FROM Item";
        ResultSet rs = sdb.queryDB(sql);
        while (rs.next()) {
            Item it = new Item();
            it.setItemNumber(rs.getInt("itemNumber"));
            it.setItemRoom(rs.getInt("itemRoom"));
            it.setItemName(rs.getString("itemName"));
            it.setItemDescription(rs.getString("itemDescription"));
            items.add(it);
        }
        sdb.close();
        return items;
    }


    public Item getItemByName(String itemName) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Item it = null;
        String sql = "SELECT * FROM Item WHERE itemName = '" + itemName + "'";
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            it = new Item();
            it.setItemNumber(rs.getInt("itemNumber"));
            it.setItemRoom(rs.getInt("itemRoom"));
            it.setItemName(rs.getString("itemName"));
            it.setItemDescription(rs.getString("itemDescription"));
        }
        sdb.close();
        return it;
    }
}
