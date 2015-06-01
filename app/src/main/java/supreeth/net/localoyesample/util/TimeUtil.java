package supreeth.net.localoyesample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by supreeth on 1/6/15.
 */
public class TimeUtil {

    public static String getFormattedTime(long timeInMs) {
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.setTimeInMillis(timeInMs);
        return getFormattedTime(calendar.getTime());
    }

    public static String getFormattedTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy, hh:mm a", Locale.US);
        return sdf.format(date);
    }

    public static Date getDate(String string) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy,HH:mm", Locale.US);
        return sdf.parse(string);
    }
}
