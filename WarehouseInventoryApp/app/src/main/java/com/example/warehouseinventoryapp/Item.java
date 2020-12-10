package com.example.warehouseinventoryapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.internal.ParcelableSparseArray;

public class Item{
    private String itemName,itemDescription;
    private String itemQuantity;
    private String itemIsFrozen;
    private String itemCost;


    public Item(String itemName, String itemQuantity, String itemCost, String itemDescription, String itemIsFrozen) {
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

    public String getItemQuantity() {
        return itemQuantity;
    }

    public String isItemIsFrozen() {
        return itemIsFrozen;
    }

    public String getItemCost() {
        return itemCost;
    }
}
