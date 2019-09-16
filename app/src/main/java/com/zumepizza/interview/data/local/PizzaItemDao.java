package com.zumepizza.interview.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zumepizza.interview.model.PizzaItem;

import java.util.List;

@Dao
public interface PizzaItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(PizzaItem pizzaItem);

    @Query("SELECT * FROM pizzaitems WHERE category = :category")
    LiveData<List<PizzaItem>> getCategoryPizzaItems(int category);

    @Query("SELECT * FROM pizzaitems WHERE id = :id")
    LiveData<PizzaItem> getPizzaItemWithId(int id);

}
