package fit2081.monash.edu.tasksdb.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insetTask(Task task);

    @Query("select * from tasks")
    LiveData<List<Task>> getAllTasks();

    @Query("select * from tasks where taskDescription like :description"  )
    List<Task> getTaskByDescription(String description);

    @Query("delete from tasks where _id=:id")
    void deleteTask(int id);

    @Query("delete from tasks")
    void deleteAllTasks();


}
