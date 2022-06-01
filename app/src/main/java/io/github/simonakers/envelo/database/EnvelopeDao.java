package io.github.simonakers.envelo.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface EnvelopeDao {
    @Query("SELECT * FROM `Envelope`")
    List<Envelope> getAll();

    @Query("SELECT * FROM `Envelope` WHERE name LIKE :name LIMIT 1")
    Envelope fromName(String name);

    @Insert
    void insert(Envelope envelope);

    @Delete
    void delete(Envelope envelope);
}
