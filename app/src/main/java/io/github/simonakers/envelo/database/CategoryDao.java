package io.github.simonakers.envelo.database;

import androidx.room.*;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE name LIKE :name LIMIT 1")
    Category fromName(String name);

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);
}
