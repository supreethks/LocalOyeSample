package supreeth.net.localoyesample.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.List;

import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.receiver.TaskBegunAlarmReceiver;
import supreeth.net.localoyesample.receiver.TaskEndedAlarmReceiver;
import timber.log.Timber;

/**
 * Created by supreethks on 4/6/15.
 */
public class AlarmHelper {

    Context context;
    List<Task> tasks;
    Task task;
    AlarmManager alarmMgr;

    public AlarmHelper(Context context, Task task) {
        this.context = context;
        this.task = task;
    }

    public AlarmHelper(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        if (tasks == null) {
            throw new NullPointerException();
        }
    }

    public void setAlarm() {
        Timber.v("Setting alarms for %d tasks", task == null ? tasks.size(): 1);
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (task != null) {
            setAlarms(task);
        } else {
            for (Task task : tasks) {
                setAlarms(task);
            }
        }
    }

    private void setAlarms(Task task) {
        setTaskBeginAlarm(task);
        setTaskEndAlarm(task);
    }

    private void setTaskBeginAlarm(Task task) {
        Timber.v("Setting alarm for begin of task %s", task);
        Intent taskBeginIntent = new Intent(context, TaskBegunAlarmReceiver.class);
        taskBeginIntent.putExtra(TaskBegunAlarmReceiver.KEY_TASK_ID, task.getId());
        PendingIntent onAlarmTriggerIntent = PendingIntent.getBroadcast(context,
                task.getId(),
                taskBeginIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //On some xiaomi phones RTC_WAKEUP alarm won't br triggered
        alarmMgr.set(Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") ?
                        AlarmManager.RTC : AlarmManager.RTC_WAKEUP,
                task.getTaskBeginTime(),
                onAlarmTriggerIntent);
    }

    private void setTaskEndAlarm(Task task) {
        Timber.v("Setting alarm for end of task %s", task);
        Intent taskEndIntent = new Intent(context, TaskEndedAlarmReceiver.class);
        taskEndIntent.putExtra(TaskEndedAlarmReceiver.KEY_TASK_ID, task.getId());
        PendingIntent onAlarmTriggerIntent = PendingIntent.getBroadcast(context,
                task.getId(),
                taskEndIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //On some xiaomi phones RTC_WAKEUP alarm won't br triggered
        alarmMgr.set(Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") ?
                        AlarmManager.RTC : AlarmManager.RTC_WAKEUP,
                task.getTaskEndTime(),
                onAlarmTriggerIntent);
    }
}
