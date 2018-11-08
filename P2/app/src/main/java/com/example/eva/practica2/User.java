package com.example.eva.practica2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class User {

    @ColumnInfo(name = "id")
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "difficulty")
    private int difficulty;

    @ColumnInfo(name = "category")
    private String category;

    public int getId() {return id;}

    public void setId(int id){this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDifficulty() {return difficulty;}

    public void setDifficulty(int dif){this.difficulty = dif;}

    public String getCategory(){return category;}

    public void setCategory(String category){this.category=category;}
}