package supreeth.net.localoyesample.persistance;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import supreeth.net.localoyesample.App;
import supreeth.net.localoyesample.BuildConfig;


/**
 * Created by supreethks on 12/5/15.
 */
public abstract class BasePref {

    String prefFile;
    public SharedPreferences preference;
    public SharedPreferences.Editor editor;

    protected BasePref(String prefFile) {
        this.prefFile = prefFile;
        preference = App.getInstance().getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        editor = preference.edit();
    }

    public String toJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }


    public Object fromJson(Type c, String key) {
        Gson gson;
        if (BuildConfig.DEBUG) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();

            gson = gsonBuilder.create();
        } else {
            gson = new Gson();
        }
        String json = preference.getString(key, null);
        if (json == null) {
            return null;
        }
        return gson.fromJson(json, c);
    }

    public void clear() {
        editor.clear().apply();
    }
}
