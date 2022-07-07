package net.shayes.envelo.database;

import androidx.room.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    /** The ID of the transaction */
    @PrimaryKey(autoGenerate = true)
    public int id;

    /** The date this transaction was made */
    public LocalDate date;
    /** The ID of the account this transaction belongs to */
    public int account;
    /** The ID of the envelope this transaction belongs to */
    public int envelope;
    /** The ID of the vendor that made this transaction */
    public int vendor;
    /** Notes about this transaction */
    public String notes;
    /** Transaction amount */
    public float amount;
    /** Whether this transaction was inflow */
    public boolean inflow;
}
