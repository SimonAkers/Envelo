package io.github.simonakers.envelo.database;

import androidx.room.*;

@Entity
public class Transaction {
    /** The ID of the transaction */
    @PrimaryKey
    public int id;

    /** The name of the envelope this transaction belongs to */
    public String envelope;
    /** The name of the vendor that made this transaction */
    public String vendor;
    /** Notes about this transaction */
    public String notes;
    /** Transaction amount */
    public float amount;

    public enum Type {
        OUTFLOW,
        INFLOW
    }
}
