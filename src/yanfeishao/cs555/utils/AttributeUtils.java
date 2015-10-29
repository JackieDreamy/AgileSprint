package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;
import yanfeishao.cs555.enums.ParseEnum;

import java.util.*;

/**
 * Created by JackieDreamy on 2015.
 */
public class AttributeUtils {

    private AttributeUtils() {
    }

    /**
     * Create name factory name utils.
     *
     * @return the name utils
     */
    public static AttributeUtils createAttributeFactory() {
        return new AttributeUtils();
    }

    private String[] splitName(String name) {
        String[] splitResult = name.split(ParseEnum.PERSON_NAME_SPLIT.toString());
        return splitResult;
    }

    private boolean isLegalLastName(PersonEntity child, FamilyEntity familyEntity) {
        return splitName(child.getName())[1].equals(splitName(familyEntity.getFather().getName())[1]);
    }

    private boolean isLegalMother(FamilyEntity familyEntity) {
        return familyEntity.getMother().getSex().equalsIgnoreCase(KeywordsConstant.F);
    }

    private boolean isLegalFather(FamilyEntity familyEntity) {
        return familyEntity.getFather().getSex().equalsIgnoreCase(KeywordsConstant.M);
    }

    private boolean us16ParseCondition(FamilyEntity familyEntity, PersonEntity child) {
        return CommonUtils.isNotNull(child) && !isLegalLastName(child, familyEntity);
    }

    private boolean us21ParseCondition(FamilyEntity familyEntity) {
        return CommonUtils.isNotNull(familyEntity.getMother()) && !isLegalMother(familyEntity) || CommonUtils.isNotNull(familyEntity.getFather()) && !isLegalFather
                (familyEntity);
    }

    /**
     * Parse us 16 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     */
    public void parseUS16Error(Set<String> result, String prefix, FamilyEntity familyEntity) {
        familyEntity.getChildList().forEach(child -> {
            if (us16ParseCondition(familyEntity, child)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US16, prefix, child.getIdentifier(), child.getName(), familyEntity.getIdentifier(), familyEntity.getFather().getName()));
            }
        });
    }

    /**
     * Parse us 21 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     */
    public void parseUS21Error(Set<String> result, String prefix, FamilyEntity familyEntity) {
        if (us21ParseCondition(familyEntity)) {
            if (CommonUtils.isNotNull(familyEntity.getMother()) && familyEntity.getMother().getSex().equalsIgnoreCase(KeywordsConstant.M)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US21, prefix, familyEntity.getIdentifier(), KeywordsConstant.Wife, KeywordsConstant.Male, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), familyEntity.getFather().getSex(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), familyEntity.getMother().getSex()));
            } else if (CommonUtils.isNotNull(familyEntity.getFather()) && familyEntity.getFather().getSex().equalsIgnoreCase(KeywordsConstant.F)) {
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US21, prefix, familyEntity.getIdentifier(), KeywordsConstant.Husband, KeywordsConstant.Female, familyEntity.getFather().getIdentifier(), familyEntity.getFather().getName(), familyEntity.getFather().getSex(), familyEntity.getMother().getIdentifier(), familyEntity.getMother().getName(), familyEntity.getMother().getSex()));
            }
        }
    }

    /**
     * Parse us 25 error.
     *
     * @param result
     *         the result
     * @param prefix
     *         the prefix
     * @param familyEntity
     *         the family entity
     */

    public void parseUS25Error(Set<String> result, String prefix, FamilyEntity familyEntity) {
        Map<String, PersonEntity> firstNameMap = new HashMap<>();
        Map<Date, PersonEntity> birthDateMap = new HashMap<>();
        familyEntity.getChildList().forEach(child -> {
            if (CommonUtils.isNotNull(child)){
                String childFirstName = splitName(child.getName())[0];
                Date childBirthDate = child.getBirthDate();
                if (firstNameMap.containsKey(childFirstName)) {
                    result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US25, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(child.getBirthDate()), firstNameMap.get(childFirstName).getIdentifier(), firstNameMap.get(childFirstName).getName(), CommonUtils.getFormattedDate(firstNameMap.get(childFirstName).getBirthDate()), familyEntity.getIdentifier()));
                } else {
                    firstNameMap.put(childFirstName, child);
                }
                if (birthDateMap.containsKey(child.getBirthDate())) {
                    result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US25, prefix, child.getIdentifier(), child.getName(), CommonUtils.getFormattedDate(child.getBirthDate()), birthDateMap.get(childBirthDate).getIdentifier(), birthDateMap.get(childBirthDate).getName(), CommonUtils.getFormattedDate(birthDateMap.get(childBirthDate).getBirthDate()), familyEntity.getIdentifier()));
                } else {
                    birthDateMap.put(childBirthDate, child);
                }
            }
        });
    }
}

