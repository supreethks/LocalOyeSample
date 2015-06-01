package supreeth.net.localoyesample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.adapter.TaskListAdapter;
import supreeth.net.localoyesample.model.Task;
import timber.log.Timber;

/**
 * Created by supreeth on 1/6/15.
 */
public class TaskListView extends FrameLayout {

    List<Task> tasks;
    @InjectView(R.id.list)
    ListView list;
    TaskListAdapter adapter;
    @InjectView(R.id.tv_empty)
    TextView tvEmpty;

    public TaskListView(Context context) {
        super(context);
        init(context, null);
    }

    public TaskListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Timber.d("init");
        LayoutInflater.from(getContext()).inflate(R.layout.view_task_list, this);
        if (isInEditMode()) {
            return;
        }
        ButterKnife.inject(this);
        adapter = new TaskListAdapter(context, 0, new ArrayList<Task>());
        list.setAdapter(adapter);
        list.setVisibility(GONE);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        adapter.setTasksList(tasks);
        if(tasks.size() > 0) {
            list.setVisibility(VISIBLE);
            tvEmpty.setVisibility(GONE);
        } else {
            list.setVisibility(GONE);
            tvEmpty.setVisibility(VISIBLE);
        }
    }

}
