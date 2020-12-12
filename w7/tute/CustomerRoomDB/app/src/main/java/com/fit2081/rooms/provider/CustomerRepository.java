package com.fit2081.rooms.provider;
import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomerRepository  {

    private CustomerDao mCustomerDao;
    private LiveData<List<Customer>> mAllCustomers;

    CustomerRepository(Application application) {
        CustomerDatabase db = CustomerDatabase.getDatabase(application);
        mCustomerDao = db.customerDao();
        mAllCustomers = mCustomerDao.getAllCustomer();
    }
    LiveData<List<Customer>> getAllCustomers() {
        return mAllCustomers;
    }
    void insert(Customer customer) {
        CustomerDatabase.databaseWriteExecutor.execute(() -> mCustomerDao.addCustomer(customer));
    }

    void deleteAll(){
        CustomerDatabase.databaseWriteExecutor.execute(()->{
            mCustomerDao.deleteAllCustomers();
        });
    }
}
