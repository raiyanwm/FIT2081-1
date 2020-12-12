package com.fit2081.rooms.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {
    private CustomerRepository mRepository;
    private LiveData<List<Customer>> mAllCustomers;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CustomerRepository(application);
        mAllCustomers = mRepository.getAllCustomers();
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return mAllCustomers;
    }

    public void insert(Customer customer) {
        mRepository.insert(customer);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

}
