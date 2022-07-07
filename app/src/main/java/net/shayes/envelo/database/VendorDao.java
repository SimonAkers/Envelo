package net.shayes.envelo.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface VendorDao {
    @Query("SELECT * FROM Vendor")
    List<Vendor> getAll();

    @Query("SELECT * FROM Vendor WHERE name LIKE :name LIMIT 1")
    Vendor fromName(String name);

    @Query("SELECT * FROM Vendor WHERE id LIKE :id LIMIT 1")
    Vendor fromID(int id);

    @Insert
    void insert(Vendor vendor);

    @Delete
    void delete(Vendor vendor);
}