package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.entities.FamilyEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * Created by JackieDreamy on 2015.
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Create date factory date utils.
     *
     * @return the date utils
     */
    public static DateUtils createDateFactory() {
        return new DateUtils();
    }

    private boolean isNotNull(Object object) {
        return object != null;
    }

    private boolean birthDateWithRange(Date specificDate, Date birthDate) {
        return birthDate.after(specificDate);
    }

    private boolean deathDateWithRange(Date date, Date husbandDeathDate, Date wifeDeathDate) {
        return husbandDeathDate.before(date) || wifeDeathDate.before(date);
    }

    private boolean marriageDivorceDateWithRangeNotNull(Date marriageDate, Date divorceDate) {
        return marriageDate.after(divorceDate) || divorceDate.before(marriageDate);
    }

    private boolean lifeDateWithRange(Date birthDate, Date deathDate) {
        return (isNotNull(birthDate) && birthDate.after(getCurrentDate())) || (isNotNull(deathDate) && deathDate.after(getCurrentDate()));
    }

    private boolean marriageDivorceDateWithRange(Date marriageDate, Date divorceDate) {
        return (isNotNull(marriageDate) && marriageDate.after(getCurrentDate())) || (isNotNull(divorceDate) && divorceDate.after(getCurrentDate()));
    }

    private Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    private void parseChildDateError(Set<String> result, FamilyEntity familyEntity, String prefix) {
        familyEntity.getChildList().forEach(child -> {
            if (isNotNull(child)) {
                Date childBirthDate = child.getBirthDate();
                Date childDeathDate = child.getDeathDate();
                switch (prefix) {
                    case ErrorCode.US01: {
                        if (lifeDateWithRange(childBirthDate, childDeathDate)) {
                            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01, prefix, familyEntity.getIdentifier()));
                        }
                    }
                    break;
                    case ErrorCode.US03: {
                        if (isNotNull(childBirthDate) && isNotNull(childDeathDate)) {
                            if (birthDateWithRange(childDeathDate, childBirthDate)) {
                                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US03, prefix, familyEntity.getIdentifier()));
                            }
                        }
                    }
                    break;
                }
            }
        });
    }

    /**
     * Parse us 01 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param marriageDate
     *         the marriage date
     * @param divorceDate
     *         the divorce date
     * @param husbandBirthDate
     *         the husband birth date
     * @param wifeBirthDate
     *         the wife birth date
     * @param husbandDeathDate
     *         the husband death date
     * @param wifeDeathDate
     *         the wife death date
     */
    public void parseUS01Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date marriageDate, Date divorceDate, Date husbandBirthDate, Date wifeBirthDate, Date husbandDeathDate, Date wifeDeathDate) {
        if (lifeDateWithRange(husbandBirthDate, husbandDeathDate) || lifeDateWithRange(wifeBirthDate, wifeDeathDate) || marriageDivorceDateWithRange(marriageDate, divorceDate)) {
            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01, prefix, familyEntity.getIdentifier()));
        }
        parseChildDateError(result, familyEntity, prefix);
    }

    /**
     * Parse us 02 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param marriageDate
     *         the marriage date
     * @param husbandBirthDate
     *         the husband birth date
     * @param wifeBirthDate
     *         the wife birth date
     */
    public void parseUS02Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date marriageDate, Date husbandBirthDate, Date wifeBirthDate) {
        if (isNotNull(marriageDate) && isNotNull(husbandBirthDate) && isNotNull(wifeBirthDate)) {
            if (birthDateWithRange(marriageDate, husbandBirthDate) || birthDateWithRange(marriageDate, wifeBirthDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US02, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    /**
     * Parse us 03 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param husbandBirthDate
     *         the husband birth date
     * @param husbandDeathDate
     *         the husband death date
     * @param wifeBirthDate
     *         the wife birth date
     * @param wifeDeathDate
     *         the wife death date
     */
    public void parseUS03Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandBirthDate, Date husbandDeathDate, Date wifeBirthDate, Date wifeDeathDate) {
        if ((isNotNull(husbandBirthDate) && isNotNull(husbandDeathDate) && birthDateWithRange(husbandDeathDate, husbandBirthDate)) || (isNotNull(wifeBirthDate) && isNotNull(wifeDeathDate) && birthDateWithRange(wifeDeathDate, wifeBirthDate))) {
            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US03, prefix, familyEntity.getIdentifier()));
        }
        parseChildDateError(result, familyEntity, prefix);
    }

    /**
     * Parse us 04 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param marriageDate
     *         the marriage date
     * @param divorceDate
     *         the divorce date
     */
    public void parseUS04Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date marriageDate, Date divorceDate) {
        if (isNotNull(marriageDate) && isNotNull(divorceDate)) {
            if (marriageDivorceDateWithRangeNotNull(marriageDate, divorceDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US04, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    /**
     * Parse us 05 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param husbandDeathDate
     *         the husband death date
     * @param wifeDeathDate
     *         the wife death date
     * @param marriageDate
     *         the marriage date
     */
    public void parseUS05Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandDeathDate, Date wifeDeathDate, Date marriageDate) {
        if (isNotNull(marriageDate) && isNotNull(husbandDeathDate) && isNotNull(wifeDeathDate)) {
            if (deathDateWithRange(marriageDate, husbandDeathDate, wifeDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US05, prefix, familyEntity.getIdentifier()));
            }
        }
    }

    /**
     * Parse us 06 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param husbandDeathDate
     *         the husband death date
     * @param wifeDeathDate
     *         the wife death date
     * @param divorceDate
     *         the divorce date
     */
    public void parseUS06Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandDeathDate, Date wifeDeathDate, Date divorceDate) {
        if (isNotNull(divorceDate) && isNotNull(husbandDeathDate) && isNotNull(wifeDeathDate)) {
            if (deathDateWithRange(divorceDate, husbandDeathDate, wifeDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US06, prefix, familyEntity.getIdentifier()));
            }
        }
    }
}
