package supreeth.net.localoyesample.model;

/**
 * Created by supreeth on 1/6/15.
 */
public class Task {

    TaskType taskType;
    String title;
    String message;
    long taskBeginTime;
    long taskEndTime;
    long systemCurrentTime;
    int id;

    private Task(Builder builder) {
        setTaskType(builder.taskType);
        setTitle(builder.title);
        setMessage(builder.message);
        setTaskBeginTime(builder.taskBeginTime);
        setTaskEndTime(builder.taskEndTime);
        setSystemCurrentTime(builder.systemCurrentTime);
        id = builder.id;
    }

    public TaskType getTaskType() {
        if (systemCurrentTime == 0) {
            systemCurrentTime = System.currentTimeMillis();
        }

        if (taskBeginTime <= systemCurrentTime
                && systemCurrentTime <= taskEndTime) {
            return TaskType.IN_PROGRESS;
        }
        if (taskEndTime < systemCurrentTime) {
            return TaskType.COMPLETED;
        }

        if (taskBeginTime > systemCurrentTime) {
            return TaskType.PENDING;
        }

        throw new NullPointerException("Cannot compute task type");
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public long getSystemCurrentTime() {
        return systemCurrentTime;
    }

    public void setSystemCurrentTime(long systemCurrentTime) {
        this.systemCurrentTime = systemCurrentTime;
    }

    public String getTitle() {
        return (title == null || title.trim().equalsIgnoreCase("")) ? "Untitled task" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(long taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public long getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(long taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskType=" + taskType +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", taskBeginTime=" + taskBeginTime +
                ", taskEndTime=" + taskEndTime +
                ", systemCurrentTime=" + systemCurrentTime +
                ", id=" + id +
                '}';
    }

    public static final class Builder {
        private TaskType taskType;
        private String title;
        private String message;
        private long taskBeginTime;
        private long taskEndTime;
        private long systemCurrentTime;
        private int id;

        public Builder() {
        }

        public Builder withTaskType(TaskType taskType) {
            this.taskType = taskType;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withTaskBeginTime(long taskBeginTime) {
            this.taskBeginTime = taskBeginTime;
            return this;
        }

        public Builder withTaskEndTime(long taskEndTime) {
            this.taskEndTime = taskEndTime;
            return this;
        }

        public Builder withSystemCurrentTime(long systemCurrentTime) {
            this.systemCurrentTime = systemCurrentTime;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
