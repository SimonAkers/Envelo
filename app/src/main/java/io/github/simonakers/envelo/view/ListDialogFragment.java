package io.github.simonakers.envelo.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import io.github.simonakers.envelo.R;

public class ListDialogFragment extends DialogFragment {
    public static String TAG = "ListDialog";

    public ListDialogFragment() {
        super(R.layout.fragment_list_dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
            .setPositiveButton(getString(R.string.confirm), (dialog, which) -> {} )
            .create();
    }
}
