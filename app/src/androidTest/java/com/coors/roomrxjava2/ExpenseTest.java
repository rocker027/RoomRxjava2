package com.coors.roomrxjava2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.coors.roomrxjava2.db.AppDatabase;
import com.coors.roomrxjava2.db.DatabaseManager;
import com.coors.roomrxjava2.db.ExpenseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExpenseTest {

    private DatabaseManager manager;

    @Before
    public void initDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        AppDatabase db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        manager = new DatabaseManager(context);
        manager.setDb(db);

        addData();
    }

    public void addData() {
        ExpenseEntity data1 = new ExpenseEntity();
        ExpenseEntity data2 = new ExpenseEntity();

        data1.setCdate("2018-04-07 16:14:55");
        data1.setInfo("Cafe");
        data1.setPrice(100);

        data2.setCdate("2018-04-07 18:14:55");
        data2.setInfo("Eat");
        data2.setPrice(150);

        manager.addExpense(data1,data2);
    }

    @After
    public void closeDB() {
        manager.closeDB();
    }

    @Test
    public void checkData() {
        assertEquals(manager.getAllExpense().size(),2);
    }

    @Test
    public void checkPrice() {
        assertEquals(manager.getAllExpense().get(0).getPrice(),100);
    }

    @Test
    public void checkDataSize() {
        assertEquals(manager.getAllExpense().get(1).getPrice(),150);
    }
}
