package com.coors.roomrxjava2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ExpenseEntity... entities);

    @Query("SELECT * FROM " + ExpenseEntity.TABLE_EXPENSE)
    List<ExpenseEntity> getAllExpense();
}
