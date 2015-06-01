package supreeth.net.localoyesample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.ui.view.TaskView;

/**
 * Created by supreeth on 1/6/15.
 */
public class TaskListAdapter extends ArrayAdapter<Task> {

    List<Task> tasks;
    Context context;

    public TaskListAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TaskView(context);
        }
        ((TaskView) convertView).setTask(tasks.get(position));
        return convertView;
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    public void setTasksList(List<Task> update) {
        tasks.clear();
        tasks.addAll(update);
        notifyDataSetChanged();
    }
}
