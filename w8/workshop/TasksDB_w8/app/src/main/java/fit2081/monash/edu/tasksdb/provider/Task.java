package fit2081.monash.edu.tasksdb.provider;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static fit2081.monash.edu.tasksdb.provider.Task.TABLE_NAME;

@Entity(tableName =TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name=COLUMN_ID)
    private int id;
    public static final String COLUMN_NAME = "taskName";

    @ColumnInfo(name=COLUMN_NAME)
    private String name;

    public static final String COLUMN_DESCRIPTION = "taskDescription";
    @ColumnInfo(name=COLUMN_DESCRIPTION)
    private String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }


    @NonNull
    public static Task fromContentValues(@Nullable ContentValues values) {
        final Task task = new Task();
        if (values != null && values.containsKey(COLUMN_ID)) {
            task.id = values.getAsInteger(COLUMN_ID);
        }
        if (values != null && values.containsKey(COLUMN_NAME)) {
            task.name = values.getAsString(COLUMN_NAME);
        }
        if (values != null && values.containsKey(COLUMN_DESCRIPTION)) {
            task.description = values.getAsString(COLUMN_DESCRIPTION);
        }
        return task;
    }

}
