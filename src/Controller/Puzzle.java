package Controller;

import Model.PuzzleDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class Puzzle {
    private int puzzleId;
    private int puzzleRoom;
    private String puzzlePrompt;
    private String puzzleSolution;
    private boolean isSolved;




    private PuzzleDB puzzleDB;

    public Puzzle(){
        //empty constructor
    }

    public Puzzle(int puzzleId, int puzzleRoom, String puzzlePrompt, String puzzleSolution) throws GameException {
        this.puzzleId = puzzleId;
        this.puzzleRoom = puzzleRoom;
        this.puzzlePrompt = puzzlePrompt;
        this.puzzleSolution = puzzleSolution;

        puzzleDB = PuzzleDB.getInstance();

    }

    public boolean checkSolution(String userAnswer)
    {
        return userAnswer.equalsIgnoreCase(getPuzzleSolution());
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getPuzzleRoom() {
        return puzzleRoom;
    }

    public void setPuzzleRoom(int puzzleRoom) {
        this.puzzleRoom = puzzleRoom;
    }

    public String getPuzzlePrompt() {
        return puzzlePrompt;
    }

    public void setPuzzlePrompt(String puzzlePrompt) {
        this.puzzlePrompt = puzzlePrompt;
    }

    public String getPuzzleSolution() {
        return puzzleSolution;
    }

    public void setPuzzleSolution(String puzzleSolution) {
        this.puzzleSolution = puzzleSolution;
    }

    public boolean isSolved(){ return isSolved; }

    public void setSolved(boolean solved){ isSolved = solved; }

    public ArrayList<Puzzle> getAllPuzzles() throws SQLException, ClassNotFoundException
    {
        PuzzleDB pdb = new PuzzleDB();
        return pdb.getAllPuzzles();
    }


    public static Puzzle getPuzzle(int puzzleRoom) throws SQLException, ClassNotFoundException {
        PuzzleDB pdb = new PuzzleDB();
        return pdb.getPuzzle(puzzleRoom);
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleId=" + puzzleId +
                ", puzzleRoom=" + puzzleRoom +
                ", puzzlePrompt='" + puzzlePrompt + '\'' +
                ", puzzleSolution='" + puzzleSolution + '\'' +
                '}';
    }
}



