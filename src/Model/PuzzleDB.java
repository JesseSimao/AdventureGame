package Model;

import Controller.GameException;
import Controller.Puzzle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuzzleDB{

    private ArrayList<Puzzle> puzzleList;

    public static PuzzleDB instance;

    public static PuzzleDB getInstance() throws GameException {
        if (instance == null)
        {
            instance = new PuzzleDB();
        }
        return instance;
    }

    public ArrayList<Puzzle> getPuzzles() {
        return puzzleList;
    }

    public void setPuzzles(ArrayList<Puzzle> puzzles) {
        this.puzzleList = puzzles;
    }

    //specific
    public ArrayList<Puzzle> getPuzzleArray() throws SQLException, ClassNotFoundException
    {

        try {
            // Establish database connection
            SQLiteDB sdb = new SQLiteDB();

            // Execute the ery
            String query = "SELECT * FROM Puzzle";
            ResultSet resultSet = sdb.queryDB(query);

            // Create an ArrayList to store the retrieved records
            ArrayList<Puzzle> puzzleList = new ArrayList<>();

            // Iterate over the result set and populate the ArrayList
            while (resultSet.next()) {
                int puzzleNumber = resultSet.getInt("puzzleNumber");
                int puzzleRoom = resultSet.getInt("puzzleRoom");
                String puzzlePrompt = resultSet.getString("puzzlePrompt");
                String puzzleAnswer = resultSet.getString("puzzleAnswer");
                Puzzle puzzle = new Puzzle(puzzleNumber, puzzleRoom, puzzlePrompt, puzzleAnswer);
                puzzleList.add(puzzle);
            }

            // Print the retrieved records
            for (Puzzle puzzle: puzzleList) {
                System.out.println(puzzle);
            }

            sdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
        return puzzleList;
    }


    public ArrayList<Puzzle> getAllPuzzles() throws SQLException, ClassNotFoundException {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        SQLiteDB sdb = new SQLiteDB();
        Puzzle puz = new Puzzle();
        String sql = "Select * from Puzzle;";
        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            puz.setPuzzleId(rs.getInt("puzzleNumber"));
            puz.setPuzzleRoom(rs.getInt("puzzleRoom"));
            puz.setPuzzlePrompt(rs.getString("puzzlePrompt"));
            puz.setPuzzleSolution(rs.getString("puzzleAnswer"));
            puzzles.add(puz);
        }

        sdb.close();
        return puzzles;
    }

    public Puzzle getPuzzle(int id) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Puzzle puz = new Puzzle();
        String sql = "SELECT * FROM Puzzle WHERE puzzleNumber = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            puz.setPuzzleId(rs.getInt("puzzleNumber"));
            puz.setPuzzleRoom(rs.getInt("puzzleRoom"));
            puz.setPuzzlePrompt(rs.getString("puzzlePrompt"));
            puz.setPuzzleSolution(rs.getString("puzzleAnswer"));
        } else {
            throw new SQLException("Puzzle " + id + " not found");
        }
        sdb.close();
        return puz;
    }

}
