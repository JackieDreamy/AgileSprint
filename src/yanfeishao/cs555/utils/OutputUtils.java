package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;

import java.util.HashSet;
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

}
