package supreeth.net.localoyesample.ui.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.util.TimeUtil;
import timber.log.Timber;

/**
 * Created by supreethks on 3/6/15.
 */
public class AddNewTaskView extends ScrollView {

    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.et_message)
    EditText etMessage;
    @InjectView(R.id.btn_start_date)
    Button btnStartDate;
    @InjectView(R.id.btn_end_date)
    Button btnEndDate;
    long taskBeginTime, taskEndTime;
    @InjectView(R.id.tv_begin_time)
    TextView tvBeginTime;
    @InjectView(R.id.tv_end_time)
    TextView tvEndTime;

    public AddNewTaskView(Context context) {
        super(context);
        init(context, null);
    }

    public AddNewTaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Timber.d("init");
        LayoutInflater.from(getContext()).inflate(R.layout.view_add_new_task, this);
        if (isInEditMode()) {
            return;
        }
        ButterKnife.inject(this);
        setTaskBeginTime(getTaskBeginTime());
        setTaskEndTime(getTaskEndTime());
    }

    public Task getTask() {
        Task.Builder builder = new Task.Builder();
        builder.withTitle(etTitle.getText().toString())
                .withMessage(etMessage.getText().toString())
                .withTaskBeginTime(getTaskBeginTime())
                .withTaskEndTime(getTaskEndTime());

        return builder.build();
    }

    public long getTaskBeginTime() {
        if (taskBeginTime == 0) {
            taskBeginTime = System.currentTimeMillis();
        }

        return taskBeginTime;
    }

    public long getTaskEndTime() {
        if (taskEndTime == 0) {
            taskEndTime = getTaskBeginTime() + 30 * 60 * 1000;
        }

        return taskEndTime;
    }

    public void setTaskBeginTime(long taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
        tvBeginTime.setText(TimeUtil.getFormattedTime(taskBeginTime));
    }

    public void setTaskEndTime(long taskEndTime) {
        this.taskEndTime = taskEndTime;
        tvEndTime.setText(TimeUtil.getFormattedTime(taskEndTime));
    }

    TimePickerDialog timePickerDialog;

    @OnClick({R.id.btn_start_date, R.id.btn_end_date})
    public void onDateClick(final View view) {
        Timber.v("onDateClick");
        final Calendar selectedTime = Calendar.getInstance();
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        Timber.d("onDateSet Year: %d Month: %d Date: %d", i, i2, i3);
                        if (timePickerDialog != null && timePickerDialog.isShowing()) {
                            return;
                        }
                        selectedTime.set(Calendar.YEAR, i);
                        selectedTime.set(Calendar.MONTH, i2);
                        selectedTime.set(Calendar.DAY_OF_MONTH, i3);

                        timePickerDialog = new TimePickerDialog(getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                                        Timber.d("onTimeSet Hour: %d Minute: %d", i, i2);
                                        selectedTime.set(Calendar.HOUR_OF_DAY, i);
                                        selectedTime.set(Calendar.MINUTE, i2);

                                        if (view.getId() == R.id.btn_start_date) {
                                            taskBeginTime = selectedTime.getTimeInMillis();
                                            setTaskBeginTime(taskBeginTime);
                                        } else if (view.getId() == R.id.btn_end_date) {
                                            taskEndTime = selectedTime.getTimeInMillis();
                                            setTaskEndTime(taskEndTime);
                                        }
                                    }
                                }, calendar.get(Calendar.HOUR_OF_DAY)
                                , calendar.get(Calendar.MINUTE),
                                false);
                        timePickerDialog.show();
                    }
                }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE));
        datePicker.show();
    }

}
