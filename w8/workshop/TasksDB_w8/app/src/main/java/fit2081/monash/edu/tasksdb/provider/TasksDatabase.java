package fit2081.monash.edu.tasksdb.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={Task.class},version=1)
public abstract class TasksDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public static final String Tasks_DB_NAME = "tasks_db5";

    private static volatile TasksDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService dbWrite = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TasksDatabase getDB(Context context) {
        if (instance == null) {
            synchronized (TasksDatabase.class) {
                if (instance == null)
                    instance = Room.databaseBuilder(context.getApplicationContext(), TasksDatabase.class, Tasks_DB_NAME).build();
            }
        }
        return instance;
    }
}
