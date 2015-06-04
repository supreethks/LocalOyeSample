package supreeth.net.localoyesample.service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import supreeth.net.localoyesample.helper.AlarmHelper;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.persistance.AppLocal;
import timber.log.Timber;

/**
 * Created by supreethks on 4/6/15.
 */
public class ResetAlarmsAfterRebootService extends IntentService {

    public ResetAlarmsAfterRebootService() {
        super(ResetAlarmsAfterRebootService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Timber.v("onHandleIntent");
        AppLocal appLocal = new AppLocal();
        List<Task> savedTasks = appLocal.getPendingTasks();
        AlarmHelper alarmHelper = new AlarmHelper(this, savedTasks);
        alarmHelper.setAlarm();
    }
}
