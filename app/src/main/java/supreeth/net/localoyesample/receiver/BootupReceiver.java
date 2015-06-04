package supreeth.net.localoyesample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import supreeth.net.localoyesample.service.ResetAlarmsAfterRebootService;
import timber.log.Timber;

/**
 * Created by supreethks on 4/6/15.
 */
public class BootupReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.v("onReceive action: %s", intent.getAction());
        context.startService(new Intent(context, ResetAlarmsAfterRebootService.class));
    }
}
