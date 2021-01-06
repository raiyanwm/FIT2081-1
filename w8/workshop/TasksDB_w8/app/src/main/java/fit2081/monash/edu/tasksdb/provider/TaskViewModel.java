package fit2081.monash.edu.tasksdb.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    TasksRepository taskRepo;
    LiveData<List<Task>> myTasksViewMode;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        taskRepo = new TasksRepository(application);
        myTasksViewMode = taskRepo.getAllTasksRepo();
    }

    public LiveData<List<Task>> getAllTasksModelView() {
        return myTasksViewMode;
    }

    public void insertTaskViewModel(Task task) {
        taskRepo.inertTaskRepo(task);
    }

    public void deleteAllViewModel() {
        taskRepo.deleteAllRepo();
    }

    public void deleteById(int id) {
        taskRepo.deleteByid(id);
    }

}
