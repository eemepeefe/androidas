package com.example.eva.practica2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    private static QuestionDatabase INSTANCE;

    public abstract QuestionDao questionDao();

    public static QuestionDatabase getQuestionDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class, "question-database").allowMainThreadQueries().fallbackToDestructiveMigration()
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.

                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
