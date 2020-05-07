package edu.ktu.guessthenumber;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class User implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "numberOfWins")
    private int numberOfWins;
    @ColumnInfo(name = "numberOfLose")
    private int numberOfLose;
    @ColumnInfo (name = "level")
    private int level;
    @ColumnInfo (name = "winInRow")
    private int winInRow;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getNumberOfWins(){
        return numberOfWins;
    }
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
    public int getNumberOfLose(){
        return numberOfLose;
    }
    public void setNumberOfLose(int numberOfLose) {
        this.numberOfLose = numberOfLose;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getWinInRow() {
        return winInRow;
    }
    public void setWinInRow(int winInRow) {
        this.winInRow = winInRow;
    }
}
