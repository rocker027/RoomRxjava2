package com.coors.roomrxjava2;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.internal.deps.guava.base.Predicate;
import android.support.test.runner.AndroidJUnit4;

import com.coors.roomrxjava2.db.AppDatabase;
import com.coors.roomrxjava2.db.DatabaseCallback;
import com.coors.roomrxjava2.db.DatabaseManager;
import com.coors.roomrxjava2.db.ExpenseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
    public void closeDB() throws Exception{
        manager.closeDB();
    }

////     規則添加到您的測試中，以確保Room立即執行所有數據庫操作
//    @Rule
//    public InstantTaskExecutorRule instantTaskExecutorRule =
//            new InstantTaskExecutorRule();

    @Test
    public void checkData() {
//        assertEquals(manager.getAllExpense().size(),2);
    }

    @Test
    public void checkPrice() {
//        assertEquals(manager.getAllExpense().get(0).getPrice(),100);
    }

    @Test
    public void checkDataSize() {
//        assertEquals(manager.getAllExpense().get(1).getPrice(),150);
    }

    @Test
    public void checkDataValue() {
//        long uid = manager.getAllExpense().get(0).getUid();
//        System.out.println("data1 uid : " + uid );
//        System.out.println("data1 price : " + manager.getExpenseByUid(uid).getPrice() );
//        assertEquals(manager.getExpenseByUid(uid).getPrice(),150);

        manager.getAllExpense(new DatabaseCallback() {
            @Override
            public void onDataLoaded(List<ExpenseEntity> lists) {
                assertEquals(lists.size(),2);
            }

            @Override
            public void onAdded() {

            }

            @Override
            public void onQueryAll() {

            }
        });

    }
}
