package com.example.warehouseinventoryapp.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemId")
    private int id;

    @ColumnInfo(name = "itemName")
    private String itemName;
    @ColumnInfo(name = "itemDescription")
    private String itemDescription;
    @ColumnInfo(name = "itemQuantity")
    private int itemQuantity;
    @ColumnInfo(name = "itemIsFrozen")
    private boolean itemIsFrozen;
    @ColumnInfo(name = "itemCost")
    private double itemCost;
    @ColumnInfo(name = "totalCost")
    protected double totalCost; //currency is better than double


    public Item(String itemName, int itemQuantity, double itemCost, String itemDescription, boolean itemIsFrozen) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCost = itemCost;
        this.itemDescription = itemDescription;
        this.itemIsFrozen = itemIsFrozen;
        //setTotalCost();
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public boolean isItemIsFrozen() {
        return itemIsFrozen;
    }

    public double getItemCost() {
        return itemCost;
    }

    public double getTotalCost(){ return totalCost;}

    public void setTotalCost(){this.totalCost = itemCost*itemQuantity;}

    public int getId(){return id;}

    public void setId(@NonNull int id){this.id = id;}
}
