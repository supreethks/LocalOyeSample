package supreeth.net.localoyesample;

import android.app.Application;
import android.content.Context;

import supreeth.net.localoyesample.report.CrashReportingTree;
import timber.log.Timber;

/**
 * Created by supreeth on 1/6/15.
 */
public class App extends Application {

    static Context instance;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }
}
