package io.github.simonakers.envelo.database;

import androidx.room.*;

@Database(entities = {Envelope.class, Transaction.class}, version = 1)
public abstract class BudgetDatabase extends RoomDatabase {
    /** Gets the dao for envelopes */
    public abstract EnvelopeDao envelopeDao();
    /** Gets the dao for transactions */
    public abstract TransactionDao transactionDao();
}