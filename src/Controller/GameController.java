package Controller;

import Model.PuzzleDB;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import static Controller.Room.getRoom;

public class GameController {
    public void start() throws GameException {
        File dbFile = new File("Game.db");
        if (!dbFile.exists()) {
            CreateFilesController cfc = new CreateFilesController();
            cfc.createFile();
            //cfc.createFile("Game.db");
        }
    }

    public String getRoomData(int roomNumber) throws SQLException, ClassNotFoundException {
        Room rm = new Room();
        rm = getRoom(roomNumber);
        return rm.toString();
    }

    public ArrayList<String> getAllRoomsData() throws SQLException, ClassNotFoundException {
        ArrayList<Room> rooms = null;
        Room rm = new Room();
        rooms = rm.getAllRooms();
        ArrayList<String> roomStrs = new ArrayList<String>();
        for (Room room : rooms) {
            roomStrs.add(room.toString());
        }
        return roomStrs;
    }

    public String getItemData(int itemNumber) throws SQLException, ClassNotFoundException {
        Item it = new Item();
        it = it.getItem(itemNumber);
        return it.toString();
    }

    public ArrayList<String> getAllItemData() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = null;
        Item it = new Item();
        items = it.getAllItems();
        ArrayList<String> itemStr = new ArrayList<>();
        for (Item item : items) {
            itemStr.add(item.toString());
        }
        return itemStr;
    }

    public ArrayList<String> getAllPuzzleData() throws SQLException, ClassNotFoundException {
        ArrayList<Puzzle> puzzles = null;
        Puzzle puz = new Puzzle();
        puzzles = puz.getAllPuzzles();
        ArrayList<String> puzzleStr = new ArrayList<>();
        for (Puzzle puzzle : puzzles) {
            puzzleStr.add(puz.toString());
        }
        return puzzleStr;
    }

    public void getPuzzleData(int roomNumber) throws SQLException, ClassNotFoundException {
        PuzzleDB pdb = new PuzzleDB();
        Puzzle puz = new Puzzle();
        if(puz.getPuzzleRoom() == roomNumber){
            puzzleDesc(roomNumber);
        }
        else{
            System.out.println("No puzzles found in this room");

        }

    }


    public String getMonsterData(int monsterNumber) throws SQLException, ClassNotFoundException {
        Monster monster = new Monster();
        monster = monster.getMonster(monsterNumber);
        return monster.toString();
    }

    public ArrayList<String> getAllMonstersData() throws SQLException, ClassNotFoundException {
        Monster mon = new Monster();
        ArrayList<Monster> monster = null;
        monster = mon.getAllMonsters();
        ArrayList<String> monStr = new ArrayList<>();
        for (Monster monsters : monster) {
            monStr.add(monsters.toString());
        }
        return monStr;
    }

    public String roomDesc(int roomNumber) throws SQLException, ClassNotFoundException
    {
        System.out.println(getRoom(roomNumber).getRoomName());
        return getRoom(roomNumber).getRoomDescription();
    }

    public String puzzleDesc(int roomNum)throws SQLException, ClassNotFoundException{
        Puzzle puzzle = new Puzzle();
        return puzzle.getPuzzle(roomNum).getPuzzlePrompt();
    }
}
