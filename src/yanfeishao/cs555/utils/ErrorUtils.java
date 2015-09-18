package yanfeishao.cs555.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Yanfei Shao on 2015.
 */
public class ErrorUtils {

    /**
     * Read GED error.
     *
     * @param ioe
     *         the IOException
     */
    public static void readGEDError(IOException ioe) {
        String message = String.format("cannot read line from ged");
        System.out.println(message);
        throw new RuntimeException(message, ioe);
    }

    /**
     * Parse error.
     *
     * @param currentDate
     *         the current date
     */
    public static void parseError(StringBuffer currentDate) {
        String message = String.format("%s is not a valid date format", currentDate.toString());
        System.out.println(message);
    }

    /**
     * Path error.
     *
     * @param fnfe
     *         the FileNotFoundException
     * @param filePath
     *         the file path
     */
    public static void pathError(FileNotFoundException fnfe, String filePath) {
        String message = String.format("%s path is wrong, please check the path whether is correct", filePath);
        System.out.println(message);
        throw new RuntimeException(message, fnfe);
    }
}
