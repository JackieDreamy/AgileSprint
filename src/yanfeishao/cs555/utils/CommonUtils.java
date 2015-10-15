package yanfeishao.cs555.utils;

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
}
