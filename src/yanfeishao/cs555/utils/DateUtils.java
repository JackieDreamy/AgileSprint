package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.enums.DateType;

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

    private boolean birthDateWithRange(Date specificDate, Date birthDate) {
        return birthDate.after(specificDate);
    }

    private boolean lifeDateWithRange(Date birthDate, Date deathDate) {
        return (CommonUtils.isNotNull(birthDate) && birthDate.after(CommonUtils.getCurrentDate())) || (CommonUtils.isNotNull(deathDate) && deathDate.after(CommonUtils.getCurrentDate()));
    }

    private boolean marriageDivorceDateWithRange(Date marriageDate, Date divorceDate) {
        return (CommonUtils.isNotNull(marriageDate) && marriageDate.after(CommonUtils.getCurrentDate())) || (CommonUtils.isNotNull(divorceDate) && divorceDate.after(CommonUtils.getCurrentDate()));
    }

    private boolean marriageDivorceDateWithRangeNotNull(Date marriageDate, Date divorceDate) {
        return marriageDate.after(divorceDate) || divorceDate.before(marriageDate);
    }

    private boolean us01ParseCondition(Date husbandBirthDate, Date husbandDeathDate, Date wifeBirthDate, Date wifeDeathDate, Date marriageDate, Date divorceDate) {
        return lifeDateWithRange(husbandBirthDate, husbandDeathDate) || lifeDateWithRange(wifeBirthDate, wifeDeathDate) || marriageDivorceDateWithRange(marriageDate, divorceDate);
    }

    private boolean us03ParseCondition(Date husbandBirthDate, Date husbandDeathDate, Date wifeBirthDate, Date wifeDeathDate) {
        return (CommonUtils.isNotNull(husbandBirthDate) && CommonUtils.isNotNull(husbandDeathDate) && birthDateWithRange(husbandDeathDate, husbandBirthDate)) || (CommonUtils.isNotNull(wifeBirthDate) && CommonUtils.isNotNull(wifeDeathDate) && birthDateWithRange(wifeDeathDate, wifeBirthDate));
    }

    private boolean us03ChildParseCondition(Date childBirthDate, Date childDeathDate) {
        return (CommonUtils.isNotNull(childBirthDate) && CommonUtils.isNotNull(childDeathDate)) && (birthDateWithRange(childDeathDate, childBirthDate));
    }

    private boolean us10ParseCondition(Date husbandBirthDate, Date wifeBirthDate, Date marriageDate) {
        return (CommonUtils.isNotNull(husbandBirthDate) && CommonUtils.isNotNull(marriageDate) && CommonUtils.compareDateDiff(husbandBirthDate, marriageDate, DateType.YEAR) < 14) || (CommonUtils.isNotNull(wifeBirthDate) && CommonUtils.isNotNull(marriageDate) && CommonUtils.compareDateDiff(wifeBirthDate, marriageDate, DateType.YEAR) < 14);
    }

    private void parseChildDateError(Set<String> result, FamilyEntity familyEntity, String prefix) {
        familyEntity.getChildList().forEach(child -> {
            if (CommonUtils.isNotNull(child)) {
                Date childBirthDate = child.getBirthDate();
                Date childDeathDate = child.getDeathDate();
                Date husbandDeathDate = familyEntity.getFather().getDeathDate();
                Date wifeDeathDate = familyEntity.getMother().getDeathDate();
                Date husbandBirthDate = familyEntity.getFather().getBirthDate();
                Date wifeBirthDate = familyEntity.getMother().getBirthDate();
                Date parentMarriedDate = familyEntity.getMarriedDate();
                Date parentDivorceDate = familyEntity.getDivorceDate();
                switch (prefix) {
                    case ErrorCode.US01: {
                        Date currentDate = CommonUtils.getCurrentDate();
                        if (lifeDateWithRange(childBirthDate, childDeathDate)) {
                            if (CommonUtils.isNotNull(childBirthDate) && birthDateWithRange(currentDate, childBirthDate))
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Birth, familyEntity.getIdentifier(), child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), CommonUtils.getFormattedDate(currentDate)));
                            else if (CommonUtils.isNotNull(childDeathDate) && childDeathDate.after(CommonUtils.getCurrentDate()))
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Death, familyEntity.getIdentifier(), child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childDeathDate), CommonUtils.getFormattedDate(currentDate)));
                        }
                    }
                    break;
                    case ErrorCode.US03: {
                        if (us03ChildParseCondition(childBirthDate, childDeathDate)) {
                            result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03, prefix, familyEntity.getIdentifier(), child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), CommonUtils.getFormattedDate(childDeathDate), child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate)));
                        }
                    }
                    break;
                    case ErrorCode.US08: {
                        if (CommonUtils.isNotNull(childBirthDate)) {
                            if (CommonUtils.isNotNull(parentMarriedDate) && parentMarriedDate.after(childBirthDate)) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), KeywordsConstant.BEFORE, KeywordsConstant.MARRIAGE, CommonUtils.getFormattedDate(familyEntity.getMarriedDate()), familyEntity.getIdentifier()));
                            } else if (CommonUtils.isNotNull(parentDivorceDate) && parentDivorceDate.before(childBirthDate)) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), KeywordsConstant.AFTER, KeywordsConstant.DIVORCE, CommonUtils.getFormattedDate(familyEntity.getDivorceDate()), familyEntity.getIdentifier()));
                            }
                        }
                    }
                    break;
                    case ErrorCode.US09: {
                        if (CommonUtils.isNotNull(childBirthDate)) {
                            if (CommonUtils.isNotNull(wifeDeathDate) && birthDateWithRange(wifeDeathDate, childBirthDate)) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), CommonUtils.getFormattedDate(wifeDeathDate)));
                            } else if (CommonUtils.isNotNull(husbandDeathDate) && (CommonUtils.compareDateDiff(husbandDeathDate, childBirthDate, DateType.MONTH) >= 9)) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), CommonUtils.getFormattedDate(husbandDeathDate)));
                            }
                        }
                    }
                    break;
                    case ErrorCode.US12: {
                        if (CommonUtils.isNotNull(childBirthDate)) {
                            if (CommonUtils.isNotNull(wifeBirthDate) && (CommonUtils.compareDateDiff(wifeBirthDate, childBirthDate, DateType.YEAR) >= Integer.parseInt(KeywordsConstant.MOTHERAGE))) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12, prefix, KeywordsConstant.MOTHER, familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate), KeywordsConstant.MOTHERAGE, KeywordsConstant.HER, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), familyEntity.getIdentifier()));
                            } else if (CommonUtils.isNotNull(husbandBirthDate) && (CommonUtils.compareDateDiff(husbandBirthDate, childBirthDate, DateType.YEAR) >= Integer.parseInt(KeywordsConstant.FATHERAGE))) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12, prefix, KeywordsConstant.FATHER, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate), KeywordsConstant.FATHER, KeywordsConstant.HIS, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), familyEntity.getIdentifier()));
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
        if (us01ParseCondition(husbandBirthDate, husbandDeathDate, wifeBirthDate, wifeDeathDate, marriageDate, divorceDate)) {
            Date currentDate = CommonUtils.getCurrentDate();
            if (CommonUtils.isNotNull(husbandBirthDate) && husbandBirthDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Birth, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(husbandDeathDate) && husbandDeathDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Death, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandDeathDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(wifeBirthDate) && wifeBirthDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Birth, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(wifeDeathDate) && wifeDeathDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Death, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeDeathDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(marriageDate) && marriageDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Marriage, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier() + " & " + familyEntity.getMother().getIdentifier(), familyEntity.getFather().getName() + " & " + familyEntity.getMother().getName(), CommonUtils.getFormattedDate(marriageDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(divorceDate) && divorceDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, prefix, KeywordsConstant.Divorce, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier() + " & " + familyEntity.getMother().getIdentifier(), familyEntity.getFather().getName() + " & " + familyEntity.getMother().getName(), CommonUtils.getFormattedDate(divorceDate), CommonUtils.getFormattedDate(currentDate)));
            }
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
        if (CommonUtils.isNotNull(marriageDate) && CommonUtils.isNotNull(husbandBirthDate) && CommonUtils.isNotNull(wifeBirthDate)) {
            if (birthDateWithRange(marriageDate, husbandBirthDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate), CommonUtils.getFormattedDate(marriageDate)));
            } else if (birthDateWithRange(marriageDate, wifeBirthDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02, prefix, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate), CommonUtils.getFormattedDate(marriageDate)));
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
        if (us03ParseCondition(husbandBirthDate, husbandDeathDate, wifeBirthDate, wifeDeathDate)) {
            if (husbandBirthDate.after(husbandDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate), CommonUtils.getFormattedDate(husbandDeathDate), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate)));
            } else if (wifeBirthDate.after(wifeDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03, prefix, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate), CommonUtils.getFormattedDate(wifeDeathDate), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate)));
            }
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
        if (CommonUtils.isNotNull(marriageDate) && CommonUtils.isNotNull(divorceDate)) {
            if (marriageDivorceDateWithRangeNotNull(marriageDate, divorceDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US04, prefix, CommonUtils.getFormattedDate(divorceDate), CommonUtils.getFormattedDate(marriageDate), familyEntity.getIdentifier()));
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
        if (CommonUtils.isNotNull(marriageDate) && CommonUtils.isNotNull(husbandDeathDate) && CommonUtils.isNotNull(wifeDeathDate)) {
            if (husbandDeathDate.before(marriageDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05, prefix, familyEntity.getIdentifier(), CommonUtils.getFormattedDate(marriageDate), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(familyEntity.getFather().getDeathDate())));
            } else if (wifeDeathDate.before(marriageDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05, prefix, familyEntity.getIdentifier(), CommonUtils.getFormattedDate(marriageDate), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(familyEntity.getMother().getDeathDate())));
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
        if (CommonUtils.isNotNull(divorceDate) && CommonUtils.isNotNull(husbandDeathDate) && CommonUtils.isNotNull(wifeDeathDate)) {
            if (divorceDate.after(husbandDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US06, prefix, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(familyEntity.getFather().getBirthDate()), CommonUtils.getFormattedDate(familyEntity.getFather().getDeathDate()), CommonUtils.getFormattedDate(divorceDate), familyEntity.getIdentifier()));
            } else if (divorceDate.after(wifeDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US06, prefix, familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(familyEntity.getMother().getBirthDate()), CommonUtils.getFormattedDate(familyEntity.getMother().getDeathDate()), CommonUtils.getFormattedDate(divorceDate), familyEntity.getIdentifier()));
            }
        }
    }

    /**
     * Parse us 08, 09, 12 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     */
    public void parseUS08US09US12Error(Set<String> result, String prefix, FamilyEntity familyEntity) {
        parseChildDateError(result, familyEntity, prefix);
    }

    /**
     * Parse us 10 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     * @param husbandBirthDate
     *         the husband birth date
     * @param wifeBirthDate
     *         the wife birth date
     * @param marriageDate
     *         the marriage date
     */
    public void parseUS10Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandBirthDate, Date wifeBirthDate, Date marriageDate) {
        if (CommonUtils.isNotNull(husbandBirthDate) && CommonUtils.isNotNull(wifeBirthDate) && CommonUtils.isNotNull(marriageDate) && us10ParseCondition(husbandBirthDate, wifeBirthDate, marriageDate)) {
            result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US10, prefix, CommonUtils.getFormattedDate(marriageDate), familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.compareDateDiff(wifeBirthDate, marriageDate, DateType.YEAR), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.compareDateDiff(husbandBirthDate, marriageDate, DateType.YEAR)));
        }
    }
}
