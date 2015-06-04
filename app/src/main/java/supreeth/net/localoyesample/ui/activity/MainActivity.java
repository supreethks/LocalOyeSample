package supreeth.net.localoyesample.ui.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.adapter.TasksTypePagerAdapter;
import supreeth.net.localoyesample.event.AddNewTaskRequestedEvent;
import supreeth.net.localoyesample.event.NewTaskAddedEvent;
import supreeth.net.localoyesample.event.RefreshTasksDisplayEvent;
import supreeth.net.localoyesample.helper.AlarmHelper;
import supreeth.net.localoyesample.mock.MockProvider;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.persistance.AppLocal;
import supreeth.net.localoyesample.receiver.TaskBegunAlarmReceiver;
import supreeth.net.localoyesample.receiver.TaskEndedAlarmReceiver;
import supreeth.net.localoyesample.ui.fragment.AddTaskDialogFragment;
import supreeth.net.localoyesample.ui.view.AddNewTaskView;
import supreeth.net.localoyesample.ui.view.SlidingTabLayout;
import supreeth.net.localoyesample.util.BusProvider;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.pager_title_strip)
    SlidingTabLayout pagerTitleStrip;
    @InjectView(R.id.pager)
    ViewPager pager;
    private TasksTypePagerAdapter tasksTypePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        AppLocal appLocal = new AppLocal();
        List<Task> tasks = appLocal.getSavedTasks();
        tasksTypePagerAdapter = new TasksTypePagerAdapter(this, tasks);
        pagerTitleStrip.setSelectedIndicatorColors(getResources().getColor(R.color.brand_primary));
        pager.setAdapter(tasksTypePagerAdapter);
        pagerTitleStrip.setViewPager(pager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshPager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mock) {
            List<Task> tasks = new ArrayList<>(tasksTypePagerAdapter.getTasks());
            try {
                tasks.addAll((List<Task>) MockProvider.getList(Task.class, 15));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tasksTypePagerAdapter.setTasks(tasks);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onAddNewTaskRequested(AddNewTaskRequestedEvent event) {
        Timber.v("onAddNewTaskRequested ");
        showAddNewTaskDialog();
    }

    @Subscribe
    public void onRefreshTaskList(RefreshTasksDisplayEvent event) {
        Timber.v("onRefreshTaskList");
        refreshPager();
    }

    private void refreshPager() {
        AppLocal appLocal = new AppLocal();
        tasksTypePagerAdapter.setTasks(appLocal.getSavedTasks());
    }

    @Subscribe
    public void onNewTaskAdded(NewTaskAddedEvent event) {
        Task task = event.getTask();
        if (task == null) {
            throw new NullPointerException();
        }
        Timber.v("onNewTaskAdded");
        refreshPager();

        AlarmHelper alarmHelper = new AlarmHelper(this,task);
        alarmHelper.setAlarm();

    }

    private void showAddNewTaskDialog() {
        DialogFragment addTaskDialogFragment = new AddTaskDialogFragment();
        addTaskDialogFragment.show(getSupportFragmentManager(), AddTaskDialogFragment.class.getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusProvider.getBus().unregister(this);
    }
}
