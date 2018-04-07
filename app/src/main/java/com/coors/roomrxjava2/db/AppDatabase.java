package com.coors.roomrxjava2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ExpenseEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "app.db";
    public abstract ExpenseDao expenseDao();
}
