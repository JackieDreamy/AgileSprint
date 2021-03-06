package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * Created by Yanfei Shao on 2015.
 * Refactor by Yanfei Shao
 */
public class ErrorUtils {

    /**
     * Parse date error.
     *
     * @param dateUtils
     *         the date utils
     * @param simpleDBUtils
     *         the simple db utils
     * @param prefix
     *         the prefix
     * @param result
     *         the result
     */
    public void parseDateError(DateUtils dateUtils, SimpleDBUtils simpleDBUtils, String prefix, Set<String> result) {
        simpleDBUtils.getFamilyDBList().forEach(familyEntity -> {
            Date marriageDate = familyEntity.getMarriedDate();
            Date divorceDate = familyEntity.getDivorceDate();
            Date husbandBirthDate = familyEntity.getFather().getBirthDate();
            Date wifeBirthDate = familyEntity.getMother().getBirthDate();
            Date husbandDeathDate = familyEntity.getFather().getDeathDate();
            Date wifeDeathDate = familyEntity.getMother().getDeathDate();
            switch (prefix) {
                case ErrorCode.US01: {
                    dateUtils.parseUS01Error(result, prefix, familyEntity, marriageDate, divorceDate, husbandBirthDate, wifeBirthDate, husbandDeathDate, wifeDeathDate);
                }
                break;
                case ErrorCode.US02: {
                    dateUtils.parseUS02Error(result, prefix, familyEntity, marriageDate, husbandBirthDate, wifeBirthDate);
                }
                break;
                case ErrorCode.US03: {
                    dateUtils.parseUS03Error(result, prefix, familyEntity, husbandBirthDate, husbandDeathDate, wifeBirthDate, wifeDeathDate);
                }
                break;
                case ErrorCode.US04: {
                    dateUtils.parseUS04Error(result, prefix, familyEntity, marriageDate, divorceDate);
                }
                break;
                case ErrorCode.US05: {
                    dateUtils.parseUS05Error(result, prefix, familyEntity, husbandDeathDate, wifeDeathDate, marriageDate);
                }
                break;
                case ErrorCode.US06: {
                    dateUtils.parseUS06Error(result, prefix, familyEntity, husbandBirthDate, wifeBirthDate, divorceDate);
                }
                break;
                case ErrorCode.US10: {
                    dateUtils.parseUS10Error(result, prefix, familyEntity, husbandBirthDate, wifeBirthDate, marriageDate);
                }
                break;
                case ErrorCode.US08:
                case ErrorCode.US09:
                case ErrorCode.US12: {
                    dateUtils.parseUS08US09US12Error(result, prefix, familyEntity);
                }
                break;
            }
        });
    }

    /**
     * Parse attribute error.
     *
     * @param attributeUtils
     *         the name utils
     * @param simpleDBUtils
     *         the simple db utils
     * @param prefix
     *         the prefix
     * @param result
     *         the result
     */
    public void parseAttributeError(AttributeUtils attributeUtils, SimpleDBUtils simpleDBUtils, String prefix, Set<String> result) {
        simpleDBUtils.getFamilyDBList().forEach(familyEntity -> {
            switch (prefix) {
                case ErrorCode.US16: {
                    attributeUtils.parseUS16Error(result, prefix, familyEntity);
                }
                break;
                case ErrorCode.US21: {
                    attributeUtils.parseUS21Error(result, prefix, familyEntity);
                }
                break;
                case ErrorCode.US25: {
                    attributeUtils.parseUS25Error(result, prefix, familyEntity);
                }
                break;
            }
        });
    }

    /**
     * Read GED error.
     *
     * @param ioe
     *         the IOException
     */
    public static void readGEDError(IOException ioe) {
        String message = String.format(ErrorInfo.READ_ERROR);
        LogUtils.error(message);
        throw new RuntimeException(message, ioe);
    }

    /**
     * Parse error.
     *
     * @param currentDate
     *         the current date
     */

    public static void parseError(StringBuffer currentDate) {
        LogUtils.log(String.format(FormatterRegex.ERROR_TITLE, KeywordsConstant.ERROR, ErrorCode.US42));
        String message = String.format(ErrorInfo.US42, currentDate.toString());
        LogUtils.reason(message);
        LogUtils.line();
    }

    public static Set<String> uniqueIdError(Set<String> results) {
        results.forEach(result -> {
            LogUtils.log(String.format(FormatterRegex.ERROR_TITLE, KeywordsConstant.ERROR, ErrorCode.US22));
            String message = String.format(ErrorInfo.US22, result);
            LogUtils.reason(message);
            LogUtils.line();
        });
        return results;
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
        LogUtils.error(message);
        throw new RuntimeException(message, fnfe);
    }
}
