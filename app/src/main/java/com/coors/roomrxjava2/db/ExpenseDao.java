package com.coors.roomrxjava2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ExpenseEntity... entities);

    @Query("SELECT * FROM " + ExpenseEntity.TABLE_EXPENSE)
    Maybe<List<ExpenseEntity>> getAllExpense();

    @Query("SELECT * FROM " + ExpenseEntity.TABLE_EXPENSE + " WHERE " + ExpenseEntity.COL_E_UID + " = :uid")
    Maybe<ExpenseEntity> getExpenseByUid(long uid);
}
