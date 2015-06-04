package supreeth.net.localoyesample;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

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
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                            .build());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }
}
