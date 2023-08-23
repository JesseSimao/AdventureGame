package Model;

import Controller.Monster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonsterDB
{
    public int getNextMonsterID() throws SQLException {
        SQLiteDB sdb = null;
        try {
            sdb = new SQLiteDB();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int next = sdb.getMaxValue("monsterNumber", "Monster") + 1;
        sdb.close();
        return next;
    }

    public Monster getMonster(int id) throws SQLException, ClassNotFoundException {
        SQLiteDB sdb = new SQLiteDB();
        Monster mon = new Monster();
        String sql = "Select * from Monster WHERE monsterNumber = " + id;
        ResultSet rs = sdb.queryDB(sql);
        if (rs.next()) {
            mon.setMonsterId(rs.getInt("monsterNumber"));
            mon.setMonsterName(rs.getString("monsterName"));
            mon.setMonsterDescription(rs.getString("monsterDescription"));
            mon.setMonsterHealth(rs.getInt("monsterHealth"));
            mon.setMonsterDamage(rs.getInt("monsterDamage"));
        }
        else {
            throw new SQLException("Monster " + id + " not found.");
        }

        sdb.close();
        return mon;
    }

    public ArrayList<Monster> getAllMonsters() throws SQLException, ClassNotFoundException {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        SQLiteDB sdb = new SQLiteDB();
        String sql = "Select * from Monster";

        ResultSet rs = sdb.queryDB(sql);

        while (rs.next()) {
            Monster mon = new Monster();
            mon.setMonsterId(rs.getInt("monsterNumber"));
            mon.setMonsterName(rs.getString("monsterName"));
            mon.setMonsterDescription(rs.getString("monsterDescription"));
            mon.setMonsterHealth(rs.getInt("monsterHealth"));
            mon.setMonsterDamage(rs.getInt("monsterDamage"));
            monsters.add(mon);
        }

        sdb.close();
        return monsters;
    }
}
