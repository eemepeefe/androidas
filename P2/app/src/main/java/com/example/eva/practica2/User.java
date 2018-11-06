package com.example.eva.practica2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class User {

    @ColumnInfo(name = "username")
    @PrimaryKey
    @NonNull
    private String username;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "difficulty")
    private int difficulty;

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
}