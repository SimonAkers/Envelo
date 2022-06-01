package io.github.simonakers.envelo.database;

import androidx.room.*;

@Entity
public class Transaction {
    /** The ID of the transaction */
    @PrimaryKey
    public int id;

    /** The name of the envelope this transaction belongs to */
    public Envelope envelope;
    /** The name of the merchant that made this transaction */
    public String merchant;
    /** Notes about this transaction */
    public String notes;
    /** Transaction amount */
    public float amount;
}
