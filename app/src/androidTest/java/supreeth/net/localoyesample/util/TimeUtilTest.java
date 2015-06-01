package supreeth.net.localoyesample.util;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtilTest extends TestCase {

    public void testGetFormattedTime() throws Exception {
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DATE, 3);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 30);

        Assert.assertEquals("Jun 03 2015, 04:30 PM", TimeUtil.getFormattedTime(calendar.getTime()));
        Assert.assertEquals("Jun 03 2015, 04:30 PM", TimeUtil.getFormattedTime(calendar.getTimeInMillis()));
    }

    public void testGetDateFromString() throws Exception {
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DATE, 12);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 25);

        Date date = TimeUtil.getDate("12/05/15,14:25");
        Calendar result = Calendar.getInstance(Locale.US);
        result.setTime(date);
        Assert.assertEquals(calendar.get(Calendar.YEAR), result.get(Calendar.YEAR));
        Assert.assertEquals(calendar.get(Calendar.MONTH), result.get(Calendar.MONTH));
        Assert.assertEquals(calendar.get(Calendar.DATE), result.get(Calendar.DATE));
        Assert.assertEquals(calendar.get(Calendar.HOUR_OF_DAY), result.get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(calendar.get(Calendar.MINUTE), result.get(Calendar.MINUTE));
    }


}