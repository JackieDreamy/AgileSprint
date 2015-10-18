package yanfeishao.cs555.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JackieDreamy on 2015.
 */
public class CommonUtils {

    /**
     * Is not null boolean.
     *
     * @param object
     *         the object
     *
     * @return the boolean
     */
    public static boolean isNotNull(Object object) {
        return object != null;
    }

    /**
     * Gets current date.
     *
     * @return the current date
     */
    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Gets LocalDate from Date.
     *
     * @return LocalDate
     */
    public static LocalDate getLocalDate(Date originalDate) {
        Instant instant = Instant.ofEpochMilli(originalDate.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     * Gets Formatted Date String DOW Short Month Day Year from Date.
     *
     * @return String
     */
    public static String getFormattedDate(Date originalDate) {
        Instant instant = Instant.ofEpochMilli(originalDate.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy"));
    }

    /**
     * Formats Local Date to DOW Short Month Day Year.
     *
     * @return String of Formatted Date
     */
    public static String getFormattedDate(LocalDate unformattedDate) {
        return unformattedDate.format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy"));
    }

    /**
     * Gets age in years.
     *
     * @return age at given time
     */
    public static int getAge(LocalDate birthDate, LocalDate compareDate) {
        return Period.between(birthDate, compareDate).getYears();
    }
}
