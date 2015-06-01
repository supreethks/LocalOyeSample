package supreeth.net.localoyesample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.util.TimeUtil;
import timber.log.Timber;

/**
 * Created by supreeth on 1/6/15.
 */
public class TaskView extends FrameLayout {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_message)
    TextView tvMessage;
    @InjectView(R.id.tv_duration)
    TextView tvDuration;
    Task task;

    public TaskView(Context context) {
        super(context);
        init(context,null);
    }

    public TaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Timber.d("init");
        LayoutInflater.from(getContext()).inflate(R.layout.view_task, this);
        if (isInEditMode()) {
            return;
        }
        ButterKnife.inject(this);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        if (task.getTitle() != null) {
            tvTitle.setText(task.getTitle());
        } else {
            tvTitle.setText("Untitled task");
        }
        if (task.getMessage() != null) {
            tvMessage.setVisibility(VISIBLE);
            tvMessage.setText(task.getMessage());
        } else {
            tvMessage.setVisibility(GONE);
        }

        String dateString = String.format(Locale.US,
                "%s - %s",
                TimeUtil.getFormattedTime(task.getTaskBeginTime()),
                TimeUtil.getFormattedTime(task.getTaskEndTime()));
        tvDuration.setText(dateString);
    }
}
