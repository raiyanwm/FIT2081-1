package com.fit2081.rooms.provider;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Customer.class}, version = 1)
public abstract class CustomerDatabase extends RoomDatabase {

    public static final String CUSTOMER_DATABASE_NAME = "customer_database";

    public abstract CustomerDao customerDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile CustomerDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CustomerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CustomerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CustomerDatabase.class, CUSTOMER_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
