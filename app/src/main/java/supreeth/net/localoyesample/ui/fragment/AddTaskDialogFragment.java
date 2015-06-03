package supreeth.net.localoyesample.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import supreeth.net.localoyesample.R;
import supreeth.net.localoyesample.model.Task;
import supreeth.net.localoyesample.persistance.AppLocal;
import supreeth.net.localoyesample.ui.view.AddNewTaskView;
import timber.log.Timber;

/**
 * Created by supreethks on 3/6/15.
 */
public class AddTaskDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new task");
        final AddNewTaskView addNewTaskView = new AddNewTaskView(getActivity());
        builder.setView(addNewTaskView);
        builder.setPositiveButton(getActivity().getString(R.string.btn_done), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Task task = addNewTaskView.getTask();
                Timber.d("onTaskAdded Task: %s", task);
                AppLocal appLocal = new AppLocal();
                appLocal.saveNewTask(task);
            }
        });
        return builder.create();
    }
}
