package Controller;

import Model.GameDBCreate;
import Model.UsersDB;

import java.sql.SQLException;

public class CreateFilesController {

    /**
     * Method: createFile
     * Purpose: Create the database for GameDB
     * @return void
     */
    public void createFile() throws GameException{
        try {
            GameDBCreate sdb = new GameDBCreate();
            sdb.buildTables();
        }
        catch (SQLException | ClassNotFoundException e) {

            System.out.println("Creation failed");
            e.printStackTrace();
            throw new GameException("Creation failed");
        }
    }
    /**
     * Method: createFile
     * Purpose: Create the database for GameDB
     * @return void
     */
    public void createFile(String dbName) throws GameException{
        try {
            GameDBCreate gdb = new GameDBCreate(dbName);
            gdb.buildTables();

            UsersDB udb = new UsersDB();
            udb.gameLogin();
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Creation failed");
            e.printStackTrace(); //this way you can see where the errors are located
            throw new GameException("Creation failed: " + e.getMessage());
        }
    }
}
