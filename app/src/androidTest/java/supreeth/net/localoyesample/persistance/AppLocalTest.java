package supreeth.net.localoyesample.persistance;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

import supreeth.net.localoyesample.mock.MockProvider;
import supreeth.net.localoyesample.model.Task;

public class AppLocalTest extends TestCase {

    AppLocal appLocal;

    public void setUp() throws Exception {
        super.setUp();
        appLocal = new AppLocal(AppLocal.PREF_FILE_NAME + "_test");
    }

    public void tearDown() throws Exception {
        appLocal.clear();
    }

    public void testSaveNewTask() throws Exception {
        List<Task> list = (List<Task>) MockProvider.getList(Task.class, 5);
        appLocal.saveNewTask(list.get(0));
        appLocal.saveNewTask(list.get(1));


        Assert.assertEquals(list.get(0).getTitle(), appLocal.getSavedTasks().get(0).getTitle());
        Assert.assertEquals(list.get(0).getTaskBeginTime(), appLocal.getSavedTasks().get(0).getTaskBeginTime());

        Assert.assertEquals(list.get(1).getTitle(), appLocal.getSavedTasks().get(1).getTitle());
        Assert.assertEquals(list.get(1).getTaskBeginTime(), appLocal.getSavedTasks().get(1).getTaskBeginTime());
    }
}