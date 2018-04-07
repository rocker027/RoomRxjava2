package com.coors.roomrxjava2.db;

import java.util.List;

public interface DatabaseCallback {
    void onDataLoaded(List<ExpenseEntity> lists);

    void onAdded();

    void onQueryAll();
}
