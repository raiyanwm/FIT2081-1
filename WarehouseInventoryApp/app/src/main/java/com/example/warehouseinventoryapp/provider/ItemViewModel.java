package com.example.warehouseinventoryapp.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private ItemRepository mRepository;
    private LiveData<List<Item>> mAllItem;

    public ItemViewModel(@NonNull Application application){
        super(application);
        mRepository = new ItemRepository(application);
        mAllItem = mRepository.getAllItem();
    }

    public LiveData<List<Item>> getAllItem(){ return mAllItem;}

    public void insert(Item item) { mRepository.insert(item);}
    public void deleteAll() { mRepository.deleteAll();}
}
