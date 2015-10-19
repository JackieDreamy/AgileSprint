package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;

import java.time.LocalDate;
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

    private boolean us04ParseCondition(Date marriageDate, Date divorceDate) {
        return (CommonUtils.isNotNull(marriageDate) && CommonUtils.isNotNull(divorceDate)) && (marriageDivorceDateWithRangeNotNull(marriageDate, divorceDate));
    }

    private boolean us10ParseCondition(LocalDate husbandBirthDate, LocalDate wifeBirthDate, LocalDate marriageDate) {
        return (CommonUtils.isNotNull(husbandBirthDate) && CommonUtils.isNotNull(marriageDate) && CommonUtils.getAge(husbandBirthDate, marriageDate) < 14) ||
                (CommonUtils.isNotNull(wifeBirthDate) && CommonUtils.isNotNull(marriageDate) && CommonUtils.getAge(wifeBirthDate, marriageDate) < 14);
    }

    private void parseChildDateError(Set<String> result, FamilyEntity familyEntity, String prefix) {
        familyEntity.getChildList().forEach(child -> {
            if (CommonUtils.isNotNull(child)) {
                Date childBirthDate = child.getBirthDate();
                Date childDeathDate = child.getDeathDate();
                Date husbandDeathDate = familyEntity.getFather().getDeathDate();
                Date wifeDeathDate = familyEntity.getMother().getDeathDate();
                switch (prefix) {
                    case ErrorCode.US01: {
                        Date currentDate = CommonUtils.getCurrentDate();
                        if (lifeDateWithRange(childBirthDate, childDeathDate)) {
                            if (CommonUtils.isNotNull(childBirthDate) && childBirthDate.after(currentDate))
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), child.getIdentifier(), child.getName(), KeywordsConstant.Birth, CommonUtils.getFormattedDate(childBirthDate), CommonUtils.getFormattedDate(currentDate)));
                            else if (CommonUtils.isNotNull(childDeathDate) && childDeathDate.after(CommonUtils.getCurrentDate()))
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), child.getIdentifier(), child.getName(), KeywordsConstant.Death, CommonUtils.getFormattedDate(childDeathDate), CommonUtils.getFormattedDate(currentDate)));
                        }
                    }
                    break;
                    case ErrorCode.US03: {
                        if (us03ChildParseCondition(childBirthDate, childDeathDate)) {
                            result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03_PERSON, prefix, familyEntity.getIdentifier(), child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate), CommonUtils.getFormattedDate(childDeathDate), child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(childBirthDate)));
                        }
                    }
                    break;
                    case ErrorCode.US09: {
                        if (CommonUtils.isNotNull(childBirthDate)) {
                            if (CommonUtils.isNotNull(wifeDeathDate) && birthDateWithRange(wifeDeathDate, childBirthDate)) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09_PERSON, prefix, child.getIdentifier(), child.getName(), childBirthDate, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), wifeDeathDate));
                            } else if (CommonUtils.isNotNull(husbandDeathDate) && ((childBirthDate.getTime() - husbandDeathDate.getTime()) / (24 * 3600 * 30) >= 9)) {
                                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09_PERSON, prefix, child.getIdentifier(), child.getName(), childBirthDate, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), husbandDeathDate));
                            }
                        }
                    }
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
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), KeywordsConstant.Birth, CommonUtils.getFormattedDate(husbandBirthDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(husbandDeathDate) && husbandDeathDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), KeywordsConstant.Death, CommonUtils.getFormattedDate(husbandDeathDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(wifeBirthDate) && wifeBirthDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), KeywordsConstant.Birth, CommonUtils.getFormattedDate(wifeBirthDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(wifeDeathDate) && wifeDeathDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), KeywordsConstant.Death, CommonUtils.getFormattedDate(wifeDeathDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(marriageDate) && marriageDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier() + " & " + familyEntity.getMother().getIdentifier(), familyEntity.getFather().getName() + " & " + familyEntity.getMother().getName(), KeywordsConstant.Marriage, CommonUtils.getFormattedDate(marriageDate), CommonUtils.getFormattedDate(currentDate)));
            } else if (CommonUtils.isNotNull(divorceDate) && divorceDate.after(currentDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier() + " & " + familyEntity.getMother().getIdentifier(), familyEntity.getFather().getName() + " & " + familyEntity.getMother().getName(), KeywordsConstant.Divorce, CommonUtils.getFormattedDate(divorceDate), CommonUtils.getFormattedDate(currentDate)));
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
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), husbandBirthDate, marriageDate));
            } else if (birthDateWithRange(marriageDate, wifeBirthDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), wifeBirthDate, marriageDate));
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
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate), CommonUtils.getFormattedDate(husbandDeathDate), familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), CommonUtils.getFormattedDate(husbandBirthDate)));
            } else if (wifeBirthDate.after(wifeDeathDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate), CommonUtils.getFormattedDate(wifeDeathDate), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getFormattedDate(wifeBirthDate)));
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
        if (us04ParseCondition(marriageDate, divorceDate)) {
            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US04_FAMILY, prefix, familyEntity.getIdentifier()));
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
            if (prefix.equals(ErrorCode.US05)) {
                if (husbandDeathDate.before(marriageDate)) {
                    result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05_PERSON, prefix, familyEntity.getIdentifier(), marriageDate, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), familyEntity.getFather().getDeathDate()));
                } else if (wifeDeathDate.before(marriageDate)) {
                    result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05_PERSON, prefix, familyEntity.getIdentifier(), marriageDate, familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), familyEntity.getMother().getDeathDate()));
                }
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
//        if (us0506ParseCondition(result, familyEntity, prefix, husbandDeathDate, wifeDeathDate, divorceDate)) {
//            result.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US06_FAMILY, prefix, familyEntity.getIdentifier()));
//        }
    }

    /**
     * Parse us 09 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     */
    public void parseUS09Error(Set<String> result, String prefix, FamilyEntity familyEntity) {
        parseChildDateError(result, familyEntity, prefix);
    }

    /**
     * Parse us 10 error.
     *
     * @param result           the result
     * @param prefix           the prefix
     * @param familyEntity     the family entity
     * @param husbandBirthDate the husband birth date
     * @param wifeBirthDate    the wife birth date
     * @param marriageDate     the marriage date
     */
    public void parseUS10Error(Set<String> result, String prefix, FamilyEntity familyEntity, Date husbandBirthDate, Date wifeBirthDate, Date marriageDate) {
        LocalDate husbandLocalBirthDate = null;
        LocalDate wifeLocalBirthDate = null;
        LocalDate marriageLocalDate = null;
        if (CommonUtils.isNotNull(familyEntity.getFather().getBirthDate())) {
            husbandLocalBirthDate = CommonUtils.getLocalDate(familyEntity.getFather().getBirthDate());
        }
        if (CommonUtils.isNotNull(familyEntity.getMother().getBirthDate())) {
            wifeLocalBirthDate = CommonUtils.getLocalDate(familyEntity.getMother().getBirthDate());
        }
        if (CommonUtils.isNotNull(familyEntity.getMarriedDate())) {
            marriageLocalDate = CommonUtils.getLocalDate(familyEntity.getMarriedDate());
        }
        if (us10ParseCondition(husbandLocalBirthDate, wifeLocalBirthDate, marriageLocalDate)) {
            if (CommonUtils.isNotNull(husbandLocalBirthDate) && CommonUtils.isNotNull(wifeLocalBirthDate) && CommonUtils.isNotNull(marriageLocalDate)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US10_PERSON, prefix, familyEntity.getIdentifier(), familyEntity.getMother
                                ().getIdentifier(), familyEntity.getMother().getName(), CommonUtils.getAge(wifeLocalBirthDate, marriageLocalDate), familyEntity.getFather().getIdentifier(),
                        familyEntity.getFather().getName(), CommonUtils.getAge(husbandLocalBirthDate, marriageLocalDate), CommonUtils.getFormattedDate(marriageLocalDate)));
            }
        }
    }
}
