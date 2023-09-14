package Controller;

import View.ConsoleUI;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Command {
    private static final List<String> VALID_DIRECTIONS = Arrays.asList("up", "down");
    private static final List<String> ITEM_COMMANDS = Arrays.asList("inventory", "drop", "grab");
    private static final int EXIT_COMMAND = 5;
    private Player player;

    public Command(Player player) {
        this.player = player;
    }
    public ConsoleUI consoleUI = new ConsoleUI();


    //The players ability to move through the game
    public void moveCommand(String input) throws GameException, ClassNotFoundException {

        try {
            if (Room.getRoom(player.getCurrentRoomNum()).getExits().equalsIgnoreCase("up")) {
                player.setCurrentRoom(Room.getRoom(player.getCurrentRoomNum() + 1));
                player.incrementRoomNum();

            } else if (Room.getRoom(player.getCurrentRoomNum()).getExits().equalsIgnoreCase("down")) {
                player.setCurrentRoom(Room.getRoom(player.getCurrentRoomNum() - 1));
                player.DecrementRoomNum();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return;
    }

    //The player ability to get, remove, or inspect an item
    public String itemCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {
        String cmdChar = cmd.substring(0,1).toLowerCase();
        Room currentRoom = player.getCurrentRoom();
        String itemName = cmd.substring(1).trim(); // Extract the item name from the command
        switch (cmdChar) {
            case "g":
                //return get(itemName, currentRoom);
                consoleUI.start();
            case "r":
                //return remove(itemName, currentRoom);
                consoleUI.start();
            case "i":
                return player.printInventory();
            default:
                throw new GameException("Invalid command.");
        }
    }

    public String get(String itemName, Room room) throws GameException, SQLException, ClassNotFoundException {
        if (room.hasItem(itemName)) {
            room.removeItem(itemName);
            player.addItem(itemName);
            room.updateRoom();
            return "You picked up the " + itemName + ".";
        } else {
            throw new GameException("Item not found in the room.");
        }
    }

    public String remove(String itemName, Room room) throws GameException, SQLException, ClassNotFoundException {
        if (player.hasItem(itemName)) {
            player.removeItem(itemName);
            room.addItem(itemName);
            room.updateRoom();
            return "You have dropped " + itemName + " in the room.";
        } else {
            throw new GameException("You don't have the item in your inventory.");
        }
    }

    public String look() {
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getRoomDescription();
    }

    public String showInventory() {
        return player.printInventory();
    }
}
