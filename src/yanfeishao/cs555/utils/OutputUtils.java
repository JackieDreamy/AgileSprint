package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Xiaonan Zhang on 9/18/15.
 * Refactor by Yanfei Shao on 10/5/15.
 */
public class OutputUtils {


    private OutputUtils() {
    }

    /**
     * Create output factory output utils.
     *
     * @return the output utils
     */
    public static OutputUtils createOutputFactory() {
        return new OutputUtils();
    }

    /**
     * Read all of the data of GEDCOM file
     * print the unique identifiers and names of the husbands and wives, in order by unique family identifiers and names
     * of each of the individuals in order by their unique identifiers.
     *
     * @param simpleDBUtils
     *         the simple DB utils
     * @param prefix
     *         the output prefix
     */
    public void outputResult(SimpleDBUtils simpleDBUtils, String prefix) {
        switch (prefix) {
            case KeywordsConstant.INDI: {
                LogUtils.info(String.format(FormatterRegex.PERSON_TABLE_TITLE, KeywordsConstant.IDENTIFIER, KeywordsConstant.NAME));
                for (PersonEntity personEntity : simpleDBUtils.getPersonDBList()) {
                    try {
                        LogUtils.info(String.format(FormatterRegex.PERSON_TABLE_DATA, personEntity.getIdentifier(), personEntity.getName()));
                    } catch (NullPointerException npe) {
                        continue;
                    }
                }
            }
            break;
            case KeywordsConstant.FAM: {
                LogUtils.info(String.format(FormatterRegex.FAMILY_TABLE_TITLE, KeywordsConstant.IDENTIFIER, KeywordsConstant.HUSBAND_NAME, KeywordsConstant.WIFE_NAME));
                for (FamilyEntity familyEntity : simpleDBUtils.getFamilyDBList()) {
                    LogUtils.info(String.format(FormatterRegex.FAMILY_TABLE_DATA, familyEntity.getIdentifier(), familyEntity.getFather().getName(), familyEntity.getMother().getName()));
                }
            }
            break;
        }
        LogUtils.line();
    }

    /**
     * Read all of the data of GEDCOM file
     * print the error information based on the US case description
     *
     * @param simpleDBUtils
     *         the simple DB utils
     * @param prefix
     *         the output prefix
     *
     * @return the set
     */
    public Set<String> outputError(SimpleDBUtils simpleDBUtils, String prefix) {
        Set<String> results = new HashSet<>();
        new ErrorUtils().parseDateError(DateUtils.createDateFactory(), simpleDBUtils, prefix, results);
        new ErrorUtils().parseAttributeError(AttributeUtils.createAttributeFactory(), simpleDBUtils, prefix, results);
        results.forEach((result -> LogUtils.error(result)));
        LogUtils.line();
        return results;
    }

    private void parseChild(List<PersonEntity> childList, Set<String> results, String prefix) {
        childList.forEach(child -> {
            switch (prefix) {
                case ErrorCode.US29: {
                    if (CommonUtils.isNotNull(child) && CommonUtils.isNotNull(child.getDeathDate())) {
                        results.add(String.format(FormatterRegex.INFO_PERSON + ErrorInfo.US29, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(child.getDeathDate())));
                    }
                }
                break;
            }
        });
    }

    private void parseUS29Condition(FamilyEntity familyEntity, Set<String> results) {
        parseChild(familyEntity.getChildList(), results, ErrorCode.US29);
        if (CommonUtils.isNotNull(familyEntity.getFather()) && CommonUtils.isNotNull(familyEntity.getFather().getDeathDate())) {
            results.add(String.format(FormatterRegex.INFO_PERSON + ErrorInfo.US29, ErrorCode.US29, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(familyEntity.getFather().getDeathDate())));
        }
        if (CommonUtils.isNotNull(familyEntity.getMother()) && CommonUtils.isNotNull(familyEntity.getMother().getDeathDate())) {
            results.add(String.format(FormatterRegex.INFO_PERSON + ErrorInfo.US29, ErrorCode.US29, familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(familyEntity.getMother().getDeathDate())));
        }
    }

    private void parseUS30Condition(FamilyEntity familyEntity, Set<String> results) {
        if (CommonUtils.isNotNull(familyEntity.getMarriedDate())) {
            results.add(String.format(FormatterRegex.INFO_PERSON + ErrorInfo.US30, ErrorCode.US30, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(familyEntity.getMarriedDate()), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName()));
        }
    }

    /**
     * Output special condition result set.
     *
     * @param simpleDBUtils
     *         the simple db utils
     * @param prefix
     *         the prefix
     *
     * @return the set of special result
     */
    public Set<String> outputSpecialConditionResult(SimpleDBUtils simpleDBUtils, String prefix) {
        Set<String> results = new HashSet<>();
        simpleDBUtils.getFamilyDBList().forEach(familyEntity -> {
            switch (prefix) {
                case ErrorCode.US29: {
                    parseUS29Condition(familyEntity, results);
                }
                break;
                case ErrorCode.US30: {
                    parseUS30Condition(familyEntity, results);
                }
                break;
            }
        });
        results.forEach((result -> LogUtils.info(result)));
        LogUtils.line();
        return results;
    }
}
