package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;

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
        String message = String.format(ErrorInfo.READ_ERROR);
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
        System.out.println(String.format(FormatterRegex.ERROR_TITLE, KeywordsConstant.ERROR, ErrorCode.US42));
        String message = String.format(ErrorInfo.PARSE_ERROR, currentDate.toString());
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
        String message = String.format(ErrorInfo.PATH_ERROR, filePath);
        System.out.println(message);
        throw new RuntimeException(message, fnfe);
    }
}
