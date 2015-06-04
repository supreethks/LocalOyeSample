package supreeth.net.localoyesample.persistance;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import supreeth.net.localoyesample.event.NewTaskAddedEvent;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.util.BusProvider;

/**
 * Created by supreethks on 3/6/15.
 */
public class AppLocal extends BasePref {

    public static final String PREF_FILE_NAME = "app_local";

    private static final String KEY_TASKS_LIST = "tasks";

    public AppLocal() {
        this(PREF_FILE_NAME);
    }

    public AppLocal(String prefFile) {
        super(prefFile);
    }

    public void saveNewTask(Task task) {
        List<Task> savedTasks = getSavedTasks();
        if (savedTasks == null) {
            savedTasks = new ArrayList<>();
        }
        task.setId(savedTasks.size() + 1);
        savedTasks.add(task);
        String json = toJson(savedTasks);
        editor.putString(KEY_TASKS_LIST, json).commit();
        BusProvider.getBus().post(new NewTaskAddedEvent(task));
    }

    public List<Task> getSavedTasks() {
        Type type = new TypeToken<List<Task>>() {}.getType();
        List<Task> tasks = (List<Task>) fromJson(type, KEY_TASKS_LIST);
        return tasks == null ? new ArrayList<Task>() : tasks;
    }

    public Task getTask(int taskId) {
        List<Task> savedTasks = getSavedTasks();
        for (Task task : savedTasks) {
            if(task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }
}
