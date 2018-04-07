package com.coors.roomrxjava2.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    public void getAllExpense(final DatabaseCallback callback) {
        db.expenseDao().getAllExpense()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<ExpenseEntity>>() {
                            @Override
                            public void accept(List<ExpenseEntity> expenseEntities) throws Exception {
                                callback.onDataLoaded(expenseEntities);
                            }
        });
    }

    // query expense by uid
    public Maybe<ExpenseEntity> getExpenseByUid(long uid) {
        return db.expenseDao().getExpenseByUid(uid);
    }
}
