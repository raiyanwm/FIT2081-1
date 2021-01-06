package com.example.warehouseinventoryapp.provider;

import android.app.Application;
import android.media.session.PlaybackState;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    private ItemDao mItemDao;
    private LiveData<List<Item>> mAllItem;

    ItemRepository(Application application){
        ItemDatabase db = ItemDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItem = mItemDao.getAllItem();
    }
    LiveData<List<Item>> getAllItem(){return mAllItem;}
    void insert(Item item){
        ItemDatabase.databaseWriteExecutor.execute(() -> mItemDao.addItem(item));
    }

    void deleteAll(){
        ItemDatabase.databaseWriteExecutor.execute(() ->{
            mItemDao.deleteAllItem();
        });
    }
}
