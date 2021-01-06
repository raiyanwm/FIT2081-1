package com.example.warehouseinventoryapp.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("select * from items")
    LiveData<List<Item>> getAllItem();

    @Query("select * from items where itemName=:itemName")
    List<Item> getItem(String itemName);

    @Insert
    void addItem(Item item);

    @Query("delete from items where itemName= :name")
    void deleteItem(String name);

    @Query("delete FROM items")
    void deleteAllItem();

}
