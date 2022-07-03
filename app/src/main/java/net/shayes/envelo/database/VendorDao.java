package net.shayes.envelo.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface VendorDao {
    @Query("SELECT * FROM Vendor")
    List<Vendor> getAll();

    @Query("SELECT * FROM Vendor WHERE name LIKE :name LIMIT 1")
    Vendor fromName(String name);

    @Insert
    void insert(Vendor vendor);

    @Delete
    void delete(Vendor vendor);
}