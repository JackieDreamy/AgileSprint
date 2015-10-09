package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;
import yanfeishao.cs555.enums.ParseEnum;

import java.util.Set;

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

    private boolean us16ParseCondition(FamilyEntity familyEntity, PersonEntity child) {
        return CommonUtils.isNotNull(child) && !isLegalLastName(child, familyEntity);
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
                result.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US16_PERSON, prefix, child.getIdentifier(), child.getName(), familyEntity.getIdentifier(), familyEntity.getFather().getName()));
            }
        });
    }
}
