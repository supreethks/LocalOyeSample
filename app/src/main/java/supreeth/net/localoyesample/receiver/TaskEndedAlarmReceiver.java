package supreeth.net.localoyesample.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.event.RefreshTasksDisplayEvent;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.persistance.AppLocal;
import supreeth.net.localoyesample.ui.activity.MainActivity;
import supreeth.net.localoyesample.util.BusProvider;
import timber.log.Timber;

/**
 * Created by supreethks on 4/6/15.
 */
public class TaskEndedAlarmReceiver extends BroadcastReceiver {

    public static final String KEY_TASK_ID = "task_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.v("onReceive");
        Bundle extras = intent.getExtras();
        if (extras == null) {
            throw new NullPointerException();
        }

        for (String key : extras.keySet()) {
            Timber.v("key: %s value: %s", key, extras.get(key));
        }

        int taskId = extras.getInt(KEY_TASK_ID);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(taskId);

        BusProvider.getBus().post(new RefreshTasksDisplayEvent());
    }
}
