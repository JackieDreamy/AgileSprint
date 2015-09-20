package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.FormatterRegex;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;
import yanfeishao.cs555.enums.ParseEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaonan Zhang on 9/18/15.
 * Refactor by Yanfei Shao on 9/20/15.
 */
public class OutputUtils {

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
                for (PersonEntity personEntity : getPersonDB(simpleDBUtils)) {
                    System.out.println(String.format(FormatterRegex.PERSON_TABLE_DATA, personEntity.getIdentifier(), personEntity.getName()));
                }
            }
            break;
            case KeywordsConstant.FAM: {
                System.out.println(String.format(FormatterRegex.FAMILY_TABLE_TITLE, KeywordsConstant.IDENTIFIER, KeywordsConstant.HUSBAND_NAME, KeywordsConstant.WIFE_NAME));
                for (FamilyEntity familyEntity : getFamilyDB(simpleDBUtils)) {
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
     */
    public void outputError(SimpleDBUtils simpleDBUtils, String prefix) {
        System.out.println(String.format(FormatterRegex.ERROR_TITLE, KeywordsConstant.ERROR, prefix));
        switch (prefix) {
            case ErrorCode.US01:
                break;
            case ErrorCode.US02:
                break;
            case ErrorCode.US03:
                break;
            case ErrorCode.US04:
                break;
            case ErrorCode.US05:
                break;
            case ErrorCode.US06:
                break;
            case ErrorCode.US08:
                break;
            case ErrorCode.US09:
                break;
            case ErrorCode.US10:
                break;
            case ErrorCode.US12:
                break;
            case ErrorCode.US16:
                break;
            case ErrorCode.US21:
                break;
            case ErrorCode.US22:
                break;
            case ErrorCode.US25:
                break;
            case ErrorCode.US29:
                break;
            case ErrorCode.US30:
                break;
            case ErrorCode.US31:
                break;
            case ErrorCode.US33:
                break;
            case ErrorCode.US35:
                break;
            case ErrorCode.US36:
                break;
            case ErrorCode.US38:
                break;
            case ErrorCode.US39:
                break;
            case ErrorCode.US40:
                break;
            case ErrorCode.US42:
                break;
        }
    }

    private List<FamilyEntity> getFamilyDB(SimpleDBUtils simpleDBUtils) {
        List<FamilyEntity> familyEntities = new ArrayList<>();
        for (int index = 1; index <= simpleDBUtils.getFamilyDB().size(); index++) {
            familyEntities.add(simpleDBUtils.getFamilyDB().get(ParseEnum.FAMILY_PREFIX.toString() + index + ParseEnum.PREFIX.toString()));
        }
        return familyEntities;
    }

    private List<PersonEntity> getPersonDB(SimpleDBUtils simpleDBUtils) {
        List<PersonEntity> personEntities = new ArrayList<>();
        for (int index = 1; index <= simpleDBUtils.getPersonDB().size(); index++) {
            personEntities.add(simpleDBUtils.getPersonDB().get(ParseEnum.PERSON_PREFIX.toString() + index + ParseEnum.PREFIX.toString()));
        }
        return personEntities;
    }

    private String[] splitName(String name) {
        String[] splitResult = name.split(ParseEnum.PERSON_NAME_SPLIT.toString());
        return splitResult;
    }
}
