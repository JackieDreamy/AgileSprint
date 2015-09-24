package yanfeishao.cs555.abstracts;

import yanfeishao.cs555.utils.OutputUtils;
import yanfeishao.cs555.utils.ParserUtils;
import yanfeishao.cs555.utils.SimpleDBUtils;
import yanfeishao.cs555.utils.TagsUtils;

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
     * The constant US02_IDENTIFIER.
     */
    protected static final String[] US02_IDENTIFIER = {"@F11", "@F9"};
    /**
     * The constant US05_IDENTIFIER.
     */
    protected static final String[] US05_IDENTIFIER = {"@F10"};
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
    /**
     * The constant outputUtils.
     */
    protected static OutputUtils outputUtils;

    static {
        tagsUtils = new TagsUtils();
        parserUtils = new ParserUtils();
        simpleDBUtils = new SimpleDBUtils();
        outputUtils = new OutputUtils();
    }

}
