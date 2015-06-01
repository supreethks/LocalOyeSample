package supreeth.net.localoyesample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.text.ParseException;

import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.adapter.TaskListAdapter;
import supreeth.net.localoyesample.mock.MockProvider;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.ui.view.TaskListView;
import supreeth.net.localoyesample.ui.view.TaskView;


public class TestActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TaskListView taskListView = new TaskListView(this);
//        try {
//            taskListView.setTasks((java.util.List<Task>) MockProvider.getList(Task.class, 5));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        setContentView(taskListView);
        setContentView(R.layout.view_task_list);
    }
}
