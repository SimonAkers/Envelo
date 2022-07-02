package io.github.simonakers.envelo.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private LocalDate date = LocalDate.now();

    private OnDateSetListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle state) {
        int year = date.getYear();
        int month = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        setDate(LocalDate.of(year, month + 1, day));

        if (listener != null) listener.onDateSet(date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setListener(OnDateSetListener listener) {
        this.listener = listener;
    }

    public interface OnDateSetListener {
        void onDateSet(LocalDate date);
    }
}