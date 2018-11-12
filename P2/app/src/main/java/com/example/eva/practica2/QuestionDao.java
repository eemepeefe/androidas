package com.example.eva.practica2;

import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Delete;


import java.util.List;

@Dao
public interface QuestionDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Question... questions);

    @Insert
    void insert(Question question);

    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Query("SELECT COUNT(*) from questions")
    int countQuestions();

    @Query("DELETE FROM questions")
    void nukeTable();

    @Query("SELECT * FROM questions WHERE type=:category")
    List<Question> getQuestions(String category);

}
