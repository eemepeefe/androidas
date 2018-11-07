package com.example.eva.practica2;

import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Delete;


import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Query("SELECT score FROM user WHERE username=:uid")
    int getScoreFromUser(String uid);

    @Query("UPDATE user SET score =:sc WHERE username =:uid")
    void setScore(String uid, int sc);

    @Query("SELECT difficulty FROM user WHERE username=:uid")
    int getDifficultyFromUser(String uid);

    @Query("UPDATE user SET difficulty =:dif WHERE username =:uid")
    void setDifficulty(String uid, int dif);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void nukeTable();

    @Query("SELECT * FROM user ORDER BY username DESC LIMIT 1")
    User getLastUser();

    @Query("SELECT * FROM user ORDER BY score DESC LIMIT 10")
    List<User> getTopTen();


}