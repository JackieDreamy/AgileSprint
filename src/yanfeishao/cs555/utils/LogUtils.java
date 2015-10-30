package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.KeywordsConstant;

/**
 * Created by JackieDreamy on 2015.
 */
public class LogUtils {

    /**
     * Info.
     *
     * @param message
     *         the message
     */
    public static void info(String message) {
        System.out.println(KeywordsConstant.INFO + message);
    }

    /**
     * Debug.
     *
     * @param message
     *         the message
     */
    public static void log(String message) {
        System.out.println(message);
    }

    /**
     * Error.
     *
     * @param message
     *         the message
     */
    public static void error(String message) {
        System.out.println(KeywordsConstant.ERROR + message);
    }

    /**
     * Reason.
     *
     * @param message
     *         the message
     */
    public static void reason(String message) {
        System.out.println(KeywordsConstant.REASON + message);
    }

    /**
     * Line.
     */
    public static void line() {
        System.out.println();
    }

}
