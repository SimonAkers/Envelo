package io.github.simonakers.envelo.database;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Converters {
    @TypeConverter
    public static LocalDate dateFromString(@NonNull String date) {
        return LocalDate.parse(date);
    }

    @NonNull
    @TypeConverter
    public static String dateToString(LocalDate date) {
        return date.toString();
    }
}
