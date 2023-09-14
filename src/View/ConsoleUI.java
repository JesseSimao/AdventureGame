package View;

import Controller.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {
    private GameController gameController;
    private Command cmd;

    private ArrayList<Integer> roomNumbers;

    public ConsoleUI() {
        gameController = new GameController();
    }


    private ArrayList<String> getAllRooms() throws SQLException, ClassNotFoundException {
        return gameController.getAllRoomsData();
    }

    private ArrayList<String> getAllMonsters() throws SQLException, ClassNotFoundException
    {
        return gameController.getAllMonstersData();
    }

    private ArrayList<String> getAllItems() throws SQLException, ClassNotFoundException
    {
        return gameController.getAllItemData();
    }

    private ArrayList<String> getAllPuzzles() throws SQLException, ClassNotFoundException{
        return gameController.getAllPuzzleData();
    }


    //starting menu of game after login
    public void start() throws SQLException, ClassNotFoundException {
        // Display welcome message and menu options

        System.out.println("Welcome to the Game!");

        Scanner scanner = new Scanner(System.in);
        int choice;

        //allows players to loop back to menu when they select 2,3,4,5
        do {
            System.out.println("1. Play Game");
            System.out.println("2. View Rooms");
            System.out.println("3. View Items");
            System.out.println("4. View Monsters");
            System.out.println("5. Help");
            System.out.println("6. Exit");

            // Get user input
            choice = scanner.nextInt();

            // Process choice
            switch (choice) {
                case 1:
                    playGame();
                    break;
                case 2:
                    viewRooms();
                    break;
                case 3:
                    viewItems();
                    break;
                case 4:
                    viewMonsters();
                    break;
                case 5:
                    helpCommands();
                    break;
                case 6:
                    System.out.println("Exiting the game...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }


    private void playGame() throws SQLException, ClassNotFoundException
    {
        int INITIAL_ROOM = 1;
        //create player object
        Player player = new Player();

        //create puzzle object
        Puzzle puzzle = new Puzzle();

        //create item object
        Item item = new Item();

        //create a room object
        Room room = new Room();


        // Game logic goes here
        boolean play = true;
        Scanner input = new Scanner(System.in);
        player.setCurrentRoom(Room.getRoom(INITIAL_ROOM));


        while(play){


            // Set the current room and the room for the player
            System.out.println(player.getCurrentRoom().displayRoom());
            System.out.println();

            //System.out.println(gameController.getPuzzleData());


            System.out.println("Enter 1. if you want to move.");
            System.out.println("Enter 2. to use an item command.");
            System.out.println("");
            String userInput = input.nextLine();


            try
            {
                int num = Integer.parseInt(userInput);

                switch(num)
                {
                    case 1:
                        System.out.println("Enter the direction you want to go:");
                        String direction = input.nextLine();
                        try {
                            cmd.moveCommand(direction);
                        } catch (GameException | NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        System.out.println("Enter 'get' to get an item from the current room.");
                        System.out.println("Enter 'remove' to remove an item from your inventory.");
                        System.out.println("Enter 'inventory' to view the items you are carrying.");

                        String itemCmd = input.nextLine();
                        //gameController.itemCommand(itemCmd);
                        break;
                }

            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid command. Please try again.");
            }
        }
    }


    //all the view functions to make sure all the data is there on the start screen
    private void viewRooms() {
        try {
            System.out.println("Fetching room data...");
            for(int i = 1; i <= getAllRooms().size();i++)
            {
                String roomData = gameController.getRoomData(i);
                System.out.println(roomData.toString());
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: Failed to fetch room data - " + e.getMessage());
        }
    }

    private void viewItems() {
        try {
            System.out.println("Fetching item data...");
            for(int i = 1; i <= getAllItems().size(); i++)
            {
                String itemData = gameController.getItemData(i);
                System.out.println(itemData.toString());
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: Failed to fetch item data - " + e.getMessage());
        }
    }

    private void viewMonsters()
    {
        try
        {
            System.out.println("Fetching monsters data...");
            for(int i = 1; i <= getAllMonsters().size(); i++)
            {
                String monsterData = gameController.getMonsterData(i);
                System.out.println(monsterData.toString());
            }

        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println("Error: Failed to fetch monster data - " + e.getMessage());
        }
    }

    //for future use of playing the game
    private void helpCommands()
    {
        try
        {
            System.out.println("These are the commands that help the game work.");
            System.out.println("");
        }
        catch(InputMismatchException ex)
        {
            System.out.println("Sorry, wrong command.");
        }
    }



}
