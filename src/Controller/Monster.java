package Controller;

import Model.MonsterDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class Monster {
    private int monsterId;
    private int monsterRoom;
    private String monsterName;
    private String monsterDescription;
    private int monsterHealth;
    private int monsterDamage;

    public Monster(int monsterId, int monsterRoom, String monsterName, String monsterDescription, int monsterHealth, int monsterDamage)
    {
        this.monsterId = monsterId;
        this.monsterRoom = monsterRoom;
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
        this.monsterHealth = monsterHealth;
        this.monsterDamage = monsterDamage;
    }

    public Monster() {
        MonsterDB mdb = new MonsterDB();
        try {
            monsterId = mdb.getNextMonsterID();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
    }

    public Monster getMonster(int id) throws SQLException, ClassNotFoundException {
        MonsterDB mdb = new MonsterDB();
        return mdb.getMonster(id);
    }

    public ArrayList<Monster> getAllMonsters() throws SQLException, ClassNotFoundException {
        MonsterDB mdb = new MonsterDB();
        return mdb.getAllMonsters();
    }
    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public int getMonsterRoom() {
        return monsterRoom;
    }

    public void setMonsterRoom(int monsterRoom) {
        this.monsterRoom = monsterRoom;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getMonsterDescription() {
        return monsterDescription;
    }

    public void setMonsterDescription(String monsterDescription) {
        this.monsterDescription = monsterDescription;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public int getMonsterDamage() {
        return monsterDamage;
    }

    public void setMonsterDamage(int monsterDamage) {
        this.monsterDamage = monsterDamage;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterId=" + monsterId +
                ", monsterRoom=" + monsterRoom +
                ", monsterName='" + monsterName + '\'' +
                ", monsterDescription='" + monsterDescription + '\'' +
                ", monsterHealth=" + monsterHealth +
                ", monsterDamage=" + monsterDamage +
                '}';
    }
}
