package Model;

import Controller.GameException;
import View.ConsoleUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsersDB {

    private String dbName;
    ConsoleUI consoleUI = new ConsoleUI();

    public UsersDB() {
        dbName = "Game.db";
    }

    public UsersDB(String dbName) {
        this.dbName = dbName;
    }

    public void buildUsers() throws SQLException, ClassNotFoundException, GameException {
        try{
        SQLiteDB sdb = new SQLiteDB(dbName);
        String sql = "CREATE TABLE IF NOT EXISTS users(user INTEGER PRIMARY KEY, username INTEGER NOT NULL, password TEXT NOT NULL);";
        sdb.updateDB(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }

        newUser();

    }

    public void newUser() throws ClassNotFoundException, SQLException, GameException {
        Scanner input = new Scanner(System.in);
        SQLiteDB sdb = new SQLiteDB(dbName);
        try {
            System.out.println("Enter a username: ");
            String username = input.nextLine();
            String sql = "SELECT username FROM users WHERE username = '" + username + "';";
            ResultSet rs = sdb.queryDB(sql);

            if (rs.next()) {
                System.out.println("Username already exists.");
                login();
            }
            else {
                System.out.println("Enter a password: ");
                String password = input.nextLine();
                String query = "INSERT INTO users(username, password) VALUES ('" + username + "', '" + password + "');";
                sdb.updateDB(query);
                System.out.println("User inserted successfully.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        consoleUI.start();

    }

    public void login() throws GameException, SQLException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        SQLiteDB sdb = new SQLiteDB(dbName);
        try {
            System.out.println("Enter a username: ");
            String username = input.nextLine();
            String sql = "SELECT username FROM users WHERE username = '" + username + "';";
            ResultSet rs = sdb.queryDB(sql);

            if (rs.next()) {
                System.out.println("Enter your password");
                String password = input.nextLine();
                String query = "SELECT password FROM users WHERE username = '" + username + "' AND  password = '" + password + "';";
                ResultSet rs2 = sdb.queryDB(query);
                if (rs2.next()) {
                    consoleUI.start();
                } else {
                    System.out.println("Password Incorrect. Try again");
                    login();
                }
            } else {
                System.out.println("username does not exist");
                newUser();
            }
        }catch(RuntimeException e){
            e.printStackTrace();
        }

    }




    public void gameLogin() throws SQLException, ClassNotFoundException, GameException {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to a Ride for Treasure!");
        System.out.println("You got an invitation because of your intelligence to see if you can solve 30 floors");
        System.out.println("of puzzles, you are riding an elevator where you will collect items and defeat monsters");
        System.out.println("on the way. Can you solve all the puzzles and defeat the monsters to get the Treasure?");
        System.out.println();
        System.out.println("Enter Option to sign in: ");
        System.out.println("1: Create an account");
        System.out.println("2: Login");
        System.out.println("3: Play as Guest");
        System.out.println("4: Exit");
        int option = input.nextInt();

        switch(option){
            case 1:
                buildUsers();
                break;
            case 2:
                login();
                break;
            case 3:
                consoleUI.start();
                break;
            case 4:
                System.out.println("exiting the game....");
                break;
            default:
                System.out.println("Not a valid option. Exiting the game....");
                break;
        }

    }

}


