package com.example.warehouseinventoryapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.internal.ParcelableSparseArray;

public class Item{
    private String itemName,itemDescription;
    private int itemQuantity;
    private boolean itemIsFrozen;
    private double itemCost;


    public Item(String itemName, int itemQuantity, double itemCost, String itemDescription, boolean itemIsFrozen) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCost = itemCost;
        this.itemDescription = itemDescription;
        this.itemIsFrozen = itemIsFrozen;
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
}
