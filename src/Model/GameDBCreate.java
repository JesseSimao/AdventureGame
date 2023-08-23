package Model;

import Controller.GameException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class GameDBCreate {
    private String dbName;

    public GameDBCreate() {
        dbName = "Game.db";
    }

    public GameDBCreate(String dbName) {
        this.dbName = dbName;
    }

    public void buildTables() throws SQLException, ClassNotFoundException, GameException {
        buildRoom();
        buildMonster();
        buildPuzzles();
        buildItems();
    }

    public void buildRoom() throws SQLException, ClassNotFoundException, GameException {
        SQLiteDB sdb = new SQLiteDB(dbName);
        FileReader fr;
        try {
            fr = new FileReader("rooms.txt");
            Scanner inFile = new Scanner(fr);
            StringBuilder sqlStatements = new StringBuilder();

            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                sdb.updateDB(line);
            }
            inFile.close();

        } catch (FileNotFoundException e) {
            throw new GameException("rooms.txt was not found");
        }

        sdb.close();
    }


    public void buildMonster() throws SQLException, ClassNotFoundException, GameException {
        SQLiteDB sdb = new SQLiteDB(dbName);
        FileReader fr;
        try {
            fr = new FileReader("monsters.txt");
            Scanner inFile = new Scanner(fr);
            StringBuilder sqlStatements = new StringBuilder();

            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                sdb.updateDB(line);
            }
            inFile.close();

        } catch (FileNotFoundException e) {
            throw new GameException("monsters.txt was not found");
        }

        sdb.close();
    }

    public void buildPuzzles() throws SQLException, ClassNotFoundException, GameException {
        SQLiteDB sdb = new SQLiteDB(dbName);
        FileReader fr;
        try {
            fr = new FileReader("puzzles.txt");
            Scanner inFile = new Scanner(fr);

            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                sdb.updateDB(line);

            }

            inFile.close();
        } catch (FileNotFoundException e) {
            throw new GameException("puzzles.txt was not found");
        }

        sdb.close();
    }

    public void buildItems() throws SQLException, ClassNotFoundException, GameException {
        SQLiteDB sdb = new SQLiteDB(dbName);
        FileReader fr;
        try {
            fr = new FileReader("item.txt");
            Scanner inFile = new Scanner(fr);

            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                sdb.updateDB(line);
            }

            inFile.close();
        } catch (FileNotFoundException e) {
            throw new GameException("item.txt was not found");
        }

        sdb.close();
    }



}
