package yanfeishao.cs555.utils;

import junit.framework.Assert;
import org.junit.Test;
import yanfeishao.cs555.abstracts.TestCases;

/**
 * Created by JackieDreamy on 2015.
 */
public class ParserUtilsTest extends TestCases {

    /**
     * Parser read bad case.
     */
    @Test (expected = RuntimeException.class)
    public void parserReadBadCase() {
        parserUtils.readGEDCOM(FILE_BAD_PATH);
    }

    /**
     * Parser read happy case.
     */
    @Test
    public void parserReadHappyCase() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertEquals(PERSON_EXPECTED_SIZE, simpleDBUtils.getPersonDBList().size());
        Assert.assertEquals(FAMILY_EXPECTED_SIZE, simpleDBUtils.getFamilyDBList().size());
    }
}
