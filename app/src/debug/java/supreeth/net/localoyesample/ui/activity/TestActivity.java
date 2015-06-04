package supreeth.net.localoyesample.ui.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.text.ParseException;

import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.adapter.TaskListAdapter;
import supreeth.net.localoyesample.mock.MockProvider;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.receiver.TaskBegunAlarmReceiver;
import supreeth.net.localoyesample.ui.view.AddNewTaskView;
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
//        setContentView(R.layout.view_task_list);
//        setContentView(new AddNewTaskView(this));
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TaskBegunAlarmReceiver.class);
        intent.putExtra("task_id", 12);
        PendingIntent onAlarmTriggerIntent = PendingIntent.getBroadcast(this, 23, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //On some xiomi phones RTC_WAKEUP alarm won't br triggered
        alarmMgr.set(Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") ?
                        AlarmManager.RTC : AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 3000,
                onAlarmTriggerIntent);

        Intent intent2 = new Intent(this, TaskBegunAlarmReceiver.class);
        intent2.putExtra("task_id", 12);
        PendingIntent onAlarmTriggerIntent2 = PendingIntent.getBroadcast(this, 25, intent2,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //On some xiomi phones RTC_WAKEUP alarm won't br triggered
        alarmMgr.set(Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") ?
                        AlarmManager.RTC : AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 3000,
                onAlarmTriggerIntent2);
    }
}
