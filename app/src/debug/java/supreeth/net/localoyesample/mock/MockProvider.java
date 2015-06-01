package supreeth.net.localoyesample.mock;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.util.TimeUtil;

/**
 * Created by supreeth on 2/6/15.
 */
public class MockProvider {

    public static Object get(Type type) throws ParseException {
        if (type == Task.class) {
            Task.Builder builder = new Task.Builder();
            builder.withTitle("Rock at Coding")
                    .withMessage("One step at a time")
                    .withTaskBeginTime(TimeUtil.getDate("12/05/15,14:25").getTime())
                    .withTaskEndTime(TimeUtil.getDate("12/05/15,18:25").getTime());
            return builder.build();
        }

        return null;
    }

    public static Object getList(Type type, int count) throws ParseException {
        Random random = new Random();
        if (type == Task.class) {
            List<Task> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Task.Builder builder = new Task.Builder();
                long time = TimeUtil.getDate(String.format(Locale.US, "%d/%d/15,%d:25", 1 + random.nextInt(29), 5 + random.nextInt(2), random.nextInt(23))).getTime();
                builder.withTitle("Rock at Coding " + i)
                        .withMessage("One step at a time")
                        .withTaskBeginTime(time)
                        .withTaskEndTime(time + 30 * 60 * 1000);

                Task task = builder.build();
                list.add(task);
            }
            Task.Builder builder = new Task.Builder();
            builder.withTitle("Rock at Coding now!")
                    .withMessage("One step at a time")
                    .withTaskBeginTime(System.currentTimeMillis() - 2000)
                    .withTaskEndTime(System.currentTimeMillis() + 30 * 60 * 1000);
            list.add(builder.build());
            return list;
        }
        return null;
    }
}
