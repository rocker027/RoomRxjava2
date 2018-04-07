package com.coors.roomrxjava2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = ExpenseEntity.TABLE_EXPENSE)
class ExpenseEntity {
    public static final String TABLE_EXPENSE = "expense";
    public static final String COL_E_UID = "expense_uid";
    public static final String COL_E_CDATE = "expense_date";
    public static final String COL_E_INFO = "expense_info";
    public static final String COL_E_PRICE = "expense_price";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_E_UID)
    private long uid;
    @ColumnInfo(name = COL_E_CDATE)
    private String cdate;
    @ColumnInfo(name = COL_E_INFO)
    private String info;
    @ColumnInfo(name = COL_E_PRICE)
    private int price;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
