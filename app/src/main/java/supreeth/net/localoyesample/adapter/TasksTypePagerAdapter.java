package supreeth.net.localoyesample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import supreeth.net.localoyesample.App;
import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.model.TaskType;
import supreeth.net.localoyesample.ui.view.AddTaskView;
import supreeth.net.localoyesample.ui.view.TaskListView;
import timber.log.Timber;

/**
 * Created by supreeth on 1/6/15.
 */
public class TasksTypePagerAdapter extends PagerAdapter {

    String[] taskTypeTitles = App.getInstance().getResources().getStringArray(R.array.task_types);
    Context context;
    List<Task> tasks;

    public TasksTypePagerAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return taskTypeTitles.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Timber.d("position: %d", position);
        View view;
        if (position == 0) {
            view = new AddTaskView(context);
        } else {
            view = new TaskListView(context);
            TaskType taskType = TaskType.values()[position - 1];
            Timber.d("Task type: %s at position: %d", taskType, position);
            ((TaskListView) view).setTasks(getTasksOfType(taskType));
        }
        container.addView(view);
        return view;
    }

    public List<Task> getTasksOfType(TaskType taskType) {
        List<Task> subList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskType() == taskType) {
                subList.add(task);
            }
        }
        return subList;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return taskTypeTitles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
}
