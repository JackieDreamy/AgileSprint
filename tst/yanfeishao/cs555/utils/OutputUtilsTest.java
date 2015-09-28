package yanfeishao.cs555.utils;

import org.junit.Assert;
import org.junit.Test;
import yanfeishao.cs555.abstracts.TestCases;
import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.ErrorInfo;
import yanfeishao.cs555.constant.FormatterRegex;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by JackieDreamy on 2015.
 */
public class OutputUtilsTest extends TestCases {

    private Set<String> expectedUS02Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US02, ErrorCode.US02, "@F11@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US02, ErrorCode.US02, "@F9@"));
        return expectedResult;
    }

    private Set<String> expectedUS05Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US05, ErrorCode.US05, "@F10@"));
        return expectedResult;
    }

    /**
     * Parse uS 02 case test.
     */
    @Test
    public void parseUS02CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS02Result().toArray(), outputUtils.parseError(simpleDBUtils, ErrorCode.US02).toArray());
    }

    /**
     * Parse uS 05 case test.
     */
    @Test
    public void parseUS05CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS05Result().toArray(), outputUtils.parseError(simpleDBUtils, ErrorCode.US05).toArray());
    }
}

