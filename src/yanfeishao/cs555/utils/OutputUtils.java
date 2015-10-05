package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;
import yanfeishao.cs555.enums.ParseEnum;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xiaonan Zhang on 9/18/15.
 * Refactor by Yanfei Shao on 10/5/15.
 */
public class OutputUtils {

    private String[] splitName(String name) {
        String[] splitResult = name.split(ParseEnum.PERSON_NAME_SPLIT.toString());
        return splitResult;
    }

    private void parseUS01Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date todayDate, Date marriageDate, Date divorceDate, Date husbandBirthDate, Date wifeBirthDate, Date husbandDeathDate, Date wifeDeathDate) {
        boolean husbandCondition = (husbandBirthDate != null && husbandBirthDate.after(todayDate)) || (husbandDeathDate != null && husbandDeathDate.after(todayDate));
        boolean wifeCondition = (wifeBirthDate != null && wifeBirthDate.after(todayDate)) || (wifeDeathDate != null && wifeDeathDate.after(todayDate));
        boolean marriageCondition = (marriageDate != null && marriageDate.after(todayDate)) || (divorceDate != null && divorceDate.after(todayDate));
        if (husbandCondition || wifeCondition || marriageCondition) {
            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01, prefix, familyEntity.getIdentifier()));
        }
        familyEntity.getChildList().forEach(child -> {
            if (child != null) {
                Date childBirthDate = child.getBirthDate();
                Date childDeathDate = child.getDeathDate();
                if ((childBirthDate != null && childBirthDate.after(todayDate)) || (child.getDeathDate() != null && childDeathDate.after(todayDate))) {
                    result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01, prefix, familyEntity.getIdentifier()));
                }
            }
        });
    }

    private void parseUS02Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date marriageDate, Date husbandBirthDate, Date wifeBirthDate) {
        if (marriageDate != null && husbandBirthDate != null && wifeBirthDate != null) {
            if (husbandBirthDate.after(marriageDate) || wifeBirthDate.after(marriageDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US02, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    private void parseUS03Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandBirthDate, Date husbandDeathDate, Date wifeBirthDate, Date wifeDeathDate) {
        if ((husbandBirthDate != null && husbandDeathDate != null && husbandBirthDate.after(husbandDeathDate)) || (wifeBirthDate != null && wifeDeathDate != null && wifeBirthDate.after(wifeDeathDate))) {
            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US03, prefix, familyEntity.getIdentifier()));
        }
        familyEntity.getChildList().forEach(child -> {
            if (child != null) {
                Date childBirthDate = child.getBirthDate();
                Date childDeathDate = child.getDeathDate();
                if (childBirthDate != null && childDeathDate != null && childBirthDate.after(childDeathDate)) {
                    result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US03, prefix, familyEntity.getIdentifier()));
                }
            }
        });
    }

    private void parseUS04Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date marriageDate, Date divorceDate) {
        if (marriageDate != null && divorceDate != null) {
            if (marriageDate.after(divorceDate) || divorceDate.before(marriageDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US04, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    private void parseUS05Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandDeathDate, Date wifeDeathDate, Date marriageDate) {
        if (marriageDate != null && husbandDeathDate != null && wifeDeathDate != null) {
            if (husbandDeathDate.before(marriageDate) || wifeDeathDate.before(marriageDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US05, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    private void parseUS06Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandDeathDate, Date wifeDeathDate, Date divorceDate) {
        if (divorceDate != null && husbandDeathDate != null && wifeDeathDate != null) {
            if (husbandDeathDate.before(divorceDate) || wifeDeathDate.before(divorceDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US06, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    private void parseDateError(SimpleDBUtils simpleDBUtils, String prefix, Set<String> result) {
        Date todayDate = Calendar.getInstance().getTime();
        simpleDBUtils.getFamilyDBList().forEach(familyEntity -> {
            Date marriageDate = familyEntity.getMarriedDate();
            Date divorceDate = familyEntity.getDivorceDate();
            Date husbandBirthDate = familyEntity.getFather().getBirthDate();
            Date wifeBirthDate = familyEntity.getMother().getBirthDate();
            Date husbandDeathDate = familyEntity.getFather().getDeathDate();
            Date wifeDeathDate = familyEntity.getMother().getDeathDate();
            switch (prefix) {
                case ErrorCode.US01: {
                    parseUS01Error(result, prefix, familyEntity, todayDate, marriageDate, divorceDate, husbandBirthDate, wifeBirthDate, husbandDeathDate, wifeDeathDate);
                }
                break;
                case ErrorCode.US02: {
                    parseUS02Error(result, prefix, familyEntity, marriageDate, husbandBirthDate, wifeBirthDate);
                }
                break;
                case ErrorCode.US03: {
                    parseUS03Error(result, prefix, familyEntity, husbandBirthDate, husbandDeathDate, wifeBirthDate, wifeDeathDate);
                }
                break;
                case ErrorCode.US04: {
                    parseUS04Error(result, prefix, familyEntity, marriageDate, divorceDate);
                }
                break;
                case ErrorCode.US05: {
                    parseUS05Error(result, prefix, familyEntity, husbandDeathDate, wifeDeathDate, marriageDate);
                }
                break;
                case ErrorCode.US06: {
                    parseUS06Error(result, prefix, familyEntity, husbandBirthDate, wifeBirthDate, divorceDate);
                }
                break;
            }
        });
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
                System.out.println(String.format(FormatterRegex.PERSON_TABLE_TITLE, KeywordsConstant.IDENTIFIER, KeywordsConstant.NAME));
                for (PersonEntity personEntity : simpleDBUtils.getPersonDBList()) {
                    try {
                        System.out.println(String.format(FormatterRegex.PERSON_TABLE_DATA, personEntity.getIdentifier(), personEntity.getName()));
                    } catch (NullPointerException npe) {
                        continue;
                    }
                }
            }
            break;
            case KeywordsConstant.FAM: {
                System.out.println(String.format(FormatterRegex.FAMILY_TABLE_TITLE, KeywordsConstant.IDENTIFIER, KeywordsConstant.HUSBAND_NAME, KeywordsConstant.WIFE_NAME));
                for (FamilyEntity familyEntity : simpleDBUtils.getFamilyDBList()) {
                    System.out.println(String.format(FormatterRegex.FAMILY_TABLE_DATA, familyEntity.getIdentifier(), familyEntity.getFather().getName(), familyEntity.getMother().getName()));
                }
            }
            break;
        }
        System.out.println();
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
        parseDateError(simpleDBUtils, prefix, results);
        results.forEach((result -> System.out.println(result)));
        System.out.println();
        return results;
    }

}
