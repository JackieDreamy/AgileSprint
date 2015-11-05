package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.enums.DateType;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
     * @param originalDate
     *         the original date
     *
     * @return LocalDate local date
     */
    private static LocalDate getLocalDate(Date originalDate) {
        Instant instant = Instant.ofEpochMilli(originalDate.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    /**
     * Gets Formatted Date String DOW Short Month Day Year from Date.
     *
     * @param originalDate
     *         the original date
     *
     * @return String formatted date
     */
    public static String getFormattedDate(Date originalDate) {
        Instant instant = Instant.ofEpochMilli(originalDate.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern(FormatterRegex.DATE_FORMAT.toString()));
    }

    /**
     * Gets date diff in years, months, days based on the date type.
     *
     * @param birthDate
     *         the birth date
     * @param compareDate
     *         the compare date
     * @param dateType
     *         the date type
     *
     * @return diff at given time and type
     */
    public static int compareDateDiff(Date birthDate, Date compareDate, DateType dateType) {
        LocalDate birthLocalDate = getLocalDate(birthDate);
        LocalDate compareLocalDate = getLocalDate(compareDate);
        switch (dateType) {
            case DAY:
                return Period.between(birthLocalDate, compareLocalDate).getDays();
            case MONTH:
                return Period.between(birthLocalDate, compareLocalDate).getMonths();
            case YEAR:
                return Period.between(birthLocalDate, compareLocalDate).getYears();
        }
        return 0;
    }

    /**
     * Compare with current date diff long. (Days Diff)
     *
     * @param compareDate
     *         the compare date
     * @param currentDate
     *         the current date
     *
     * @return the long
     */
    public static long compareWithCurrentDateDiff(Date compareDate, Date currentDate) {
        LocalDate compareLocalDate = getLocalDate(compareDate);
        LocalDate currentLocalDate = getLocalDate(currentDate);
        LocalDate nextDay = compareLocalDate.withYear(currentLocalDate.getYear());
        if (nextDay.isBefore(currentLocalDate) || nextDay.isEqual(currentLocalDate)) {
            nextDay = nextDay.plusYears(1);
        }
        return ChronoUnit.DAYS.between(currentLocalDate, nextDay);
    }
}
