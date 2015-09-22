package yanfeishao.cs555.abstracts;

import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;
import yanfeishao.cs555.enums.ParseEnum;
import yanfeishao.cs555.utils.ParserUtils;
import yanfeishao.cs555.utils.SimpleDBUtils;
import yanfeishao.cs555.utils.TagsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JackieDreamy on 2015.
 */
public class TestCases {

    /**
     * The constant TAG_EXPECTED_SIZE.
     */
    protected static final int TAG_EXPECTED_SIZE = 17;
    /**
     * The constant FAMILY_EXPECTED_SIZE.
     */
    protected static final int FAMILY_EXPECTED_SIZE = 12;
    /**
     * The constant PERSON_EXPECTED_SIZE.
     */
    protected static final int PERSON_EXPECTED_SIZE = 27;
    /**
     * The constant FILE_PATH.
     */
    protected static final String FILE_PATH = "src/yanfeishao/cs555/data/data.ged";
    /**
     * The constant FILE_BAD_PATH.
     */
    protected static final String FILE_BAD_PATH = "src/data.ged";

    /**
     * The constant tagsUtils.
     */
    protected static TagsUtils tagsUtils;
    /**
     * The constant parserUtils.
     */
    protected static ParserUtils parserUtils;
    /**
     * The constant simpleDBUtils.
     */
    protected static SimpleDBUtils simpleDBUtils;

    static {
        tagsUtils = new TagsUtils();
        parserUtils = new ParserUtils();
        simpleDBUtils = new SimpleDBUtils();
    }

    /**
     * Gets family dB.
     *
     * @param simpleDBUtils
     *         the simple dB utils
     *
     * @return the family dB
     */
    protected List<FamilyEntity> getFamilyDB(SimpleDBUtils simpleDBUtils) {
        List<FamilyEntity> familyEntities = new ArrayList<>();
        for (int index = 1; index <= simpleDBUtils.getFamilyDB().size(); index++) {
            familyEntities.add(simpleDBUtils.getFamilyDB().get(ParseEnum.FAMILY_PREFIX.toString() + index + ParseEnum.PREFIX.toString()));
        }
        return familyEntities;
    }

    /**
     * Gets person dB.
     *
     * @param simpleDBUtils
     *         the simple dB utils
     *
     * @return the person dB
     */
    protected List<PersonEntity> getPersonDB(SimpleDBUtils simpleDBUtils) {
        List<PersonEntity> personEntities = new ArrayList<>();
        for (int index = 1; index <= simpleDBUtils.getPersonDB().size(); index++) {
            personEntities.add(simpleDBUtils.getPersonDB().get(ParseEnum.PERSON_PREFIX.toString() + index + ParseEnum.PREFIX.toString()));
        }
        return personEntities;
    }

}
