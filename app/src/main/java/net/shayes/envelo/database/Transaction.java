package net.shayes.envelo.database;

import androidx.room.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    /** The ID of the transaction */
    @PrimaryKey
    public int id;

    /** The date this transaction was made */
    public LocalDate date;
    /** The name of the account this transaction belongs to */
    public String account;
    /** The name of the envelope this transaction belongs to */
    public String envelope;
    /** The name of the vendor that made this transaction */
    public String vendor;
    /** Notes about this transaction */
    public String notes;
    /** Transaction amount */
    public float amount;
    /** Whether this transaction was inflow */
    public boolean inflow;
}
