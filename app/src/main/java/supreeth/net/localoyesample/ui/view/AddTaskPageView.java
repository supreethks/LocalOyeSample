package supreeth.net.localoyesample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.event.AddNewTaskRequestedEvent;
import supreeth.net.localoyesample.util.BusProvider;
import timber.log.Timber;

/**
 * Created by supreeth on 2/6/15.
 */
public class AddTaskPageView extends FrameLayout {

    public AddTaskPageView(Context context) {
        super(context);
        init(context, null);
    }

    public AddTaskPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Timber.d("init");
        LayoutInflater.from(getContext()).inflate(R.layout.view_add_task, this);
        if (isInEditMode()) {
            return;
        }
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_add_new_task)
    public void onCreateNewTask() {
        Timber.d("onCreateNewTask");
        BusProvider.getBus().post(new AddNewTaskRequestedEvent());
    }
}
