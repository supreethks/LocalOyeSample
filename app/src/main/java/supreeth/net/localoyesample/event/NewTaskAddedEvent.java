package supreeth.net.localoyesample.event;

import supreeth.net.localoyesample.model.Task;

/**
 * Created by supreethks on 3/6/15.
 */
public class NewTaskAddedEvent {

    Task task;

    public NewTaskAddedEvent(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
