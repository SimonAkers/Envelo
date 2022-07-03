package net.shayes.envelo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {
    public static DateTimeFormatter formatterFull = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

    public static String formatFull(LocalDate date) {
        return date.format(formatterFull);
    }
}
