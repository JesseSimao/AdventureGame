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


    public int validateCommand(String cmdLine) throws GameException {
        String cmd = cmdLine.toLowerCase();
        if (VALID_DIRECTIONS.contains(cmd)) {
            return 1; // Movement commands
        } else if (ITEM_COMMANDS.contains(cmd)) {
            return 2; // Item commands
        } else if (cmd == "look") {
            return 3; // Look command
        } else if (cmd == "inventory") {
            return 4; // Backpack command
        } else if (cmd == "exit") {
            return EXIT_COMMAND; // Exit command
        } else {
            throw new GameException("Invalid command."); // Invalid command
        }
    }

    public String executeCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {
        int cmdType = validateCommand(cmd);
        switch (cmdType) {
            case 1:
                return moveCommand(cmd);
            case 2:
                return itemCommand(cmd);
            case 3:
                return look();
            case 4:
                return showInventory();
            case EXIT_COMMAND:
                return "Thanks for playing. Exiting the game...";
            default:
                throw new GameException("I don't understand.");
        }
    }

    private String moveCommand(String cmdRoom) throws GameException, SQLException, ClassNotFoundException {
        String direction = cmdRoom.toLowerCase();
        if (VALID_DIRECTIONS.contains(direction)) {
            Room currentRoom = player.getCurrentRoom();
            int destination = currentRoom.validDirection(direction);
            if (destination != -1) {
                currentRoom.setVisited(true);
                Room nextRoom = currentRoom.retrieveByID(destination);
                player.setCurrentRoom(nextRoom);
                return nextRoom.getRoomDescription();
            } else {
                throw new GameException("Invalid direction.");
            }
        } else {
            throw new GameException("Invalid command.");
        }
    }

    private String itemCommand(String cmd) throws GameException, SQLException, ClassNotFoundException {
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

    private String get(String itemName, Room room) throws GameException, SQLException, ClassNotFoundException {
        if (room.hasItem(itemName)) {
            room.removeItem(itemName);
            player.addItem(itemName);
            room.updateRoom();
            return "You picked up the " + itemName + ".";
        } else {
            throw new GameException("Item not found in the room.");
        }
    }

    private String remove(String itemName, Room room) throws GameException, SQLException, ClassNotFoundException {
        if (player.hasItem(itemName)) {
            player.removeItem(itemName);
            room.addItem(itemName);
            room.updateRoom();
            return "You have dropped " + itemName + " in the room.";
        } else {
            throw new GameException("You don't have the item in your inventory.");
        }
    }

    private String look() {
        Room currentRoom = player.getCurrentRoom();
        return currentRoom.getRoomDescription();
    }

    private String showInventory() {
        return player.printInventory();
    }
}
