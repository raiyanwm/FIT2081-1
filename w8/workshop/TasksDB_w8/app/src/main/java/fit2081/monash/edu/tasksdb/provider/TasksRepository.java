package fit2081.monash.edu.tasksdb.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TasksRepository {

    private TaskDao tasksDao;
    private LiveData<List<Task>> myTasks;

    TasksRepository(Application app) {
        TasksDatabase db = TasksDatabase.getDB(app);
        tasksDao = db.taskDao();
        myTasks = tasksDao.getAllTasks();
    }

    LiveData<List<Task>> getAllTasksRepo() {
        return tasksDao.getAllTasks();
    }

    void inertTaskRepo(Task task){

        TasksDatabase.dbWrite.execute(()-> tasksDao.insetTask(task));
    }

    void deleteAllRepo(){

        TasksDatabase.dbWrite.execute(()->tasksDao.deleteAllTasks());
    }

    void deleteByid(int id){
        TasksDatabase.dbWrite.execute(()->tasksDao.deleteTask(id));
    }


}
