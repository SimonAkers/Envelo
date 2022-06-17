package io.github.simonakers.envelo.database;

import androidx.room.*;

@Database(entities = {Category.class, Transaction.class, Vendor.class}, version = 1)
public abstract class BudgetDatabase extends RoomDatabase {
    /** Gets the dao for categories */
    public abstract CategoryDao categoryDao();
    /** Gets the dao for transactions */
    public abstract TransactionDao transactionDao();
    /** Gets the dao for transactions */
    public abstract VendorDao vendorDao();

}