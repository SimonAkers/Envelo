package io.github.simonakers.envelo.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM `Transaction`")
    List<Transaction> getAll();

    @Query("SELECT * FROM `Transaction` WHERE envelope LIKE :envelopeName")
    List<Transaction> getAll(String envelopeName);
}
