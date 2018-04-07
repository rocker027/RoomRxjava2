package com.coors.roomrxjava2.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

public class DatabaseManager {
    private Context context;
    private AppDatabase db;
    public static DatabaseManager INSTANCE;

    public DatabaseManager(Context context) {
        this.context = context;
        this.db = Room.databaseBuilder(
                this.context,
                AppDatabase.class,
                AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    // 給單元測試用
    public void setDb(AppDatabase db) {
        this.db = db;
    }

    public static DatabaseManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseManager(context);
        }
        return INSTANCE;
    }

    public void closeDB() {
        if (INSTANCE != null) {
            INSTANCE.db.close();
            INSTANCE = null;
        }
    }

    // TABLE Expense
    // insert expense
    public void addExpense(ExpenseEntity... entities) {
        db.expenseDao().insert(entities);
    }

    // query expense
    public List<ExpenseEntity> getAllExpense() {
        return db.expenseDao().getAllExpense();
    }
}
