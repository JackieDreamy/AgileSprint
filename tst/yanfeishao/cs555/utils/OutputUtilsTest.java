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

    private Set<String> expectedUS01Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01_FAMILY, ErrorCode.US01, "@F4@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01_FAMILY, ErrorCode.US01, "@F7@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01_FAMILY, ErrorCode.US01, "@F10@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US01_FAMILY, ErrorCode.US01, "@F11@"));
        return expectedResult;
    }

    private Set<String> expectedUS02Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02_PERSON, ErrorCode.US02, "@F9@", "@P15@", "Darlene/Cook/", "Sun Oct 18 00:00:00 EDT 1998", "Wed Jun 10 00:00:00 EDT 1953"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02_PERSON, ErrorCode.US02, "@F11@", "@P16@", "Inez/Youngster/", "Thu Jun 04 00:00:00 EDT 1970", "Tue Dec 25 00:00:00 EST 1928"));
        return expectedResult;
    }

    private Set<String> expectedUS03Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US03_FAMILY, ErrorCode.US03, "@F11@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_FAMILY + ErrorInfo.US03_FAMILY, ErrorCode.US03, "@F5@"));
        return expectedResult;
    }

    private Set<String> expectedUS04Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US04_PERSON, ErrorCode.US04, "Tue Mar 15 00:00:00 EST 1921", "Tue Jun 20 00:00:00 EDT 1922", "@F8@"));
        return expectedResult;
    }

    private Set<String> expectedUS05Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05_PERSON, ErrorCode.US05, "@F10@", "Thu Nov 06 00:00:00 EST 1997", "@P28@", "KennethArvid/Lindfors/", "Sun Dec 10 00:00:00 EST 1995"));
        return expectedResult;
    }

    private Set<String> expectedUS06Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US06_PERSON, ErrorCode.US06, "@P28@", "KennethArvid/Lindfors/", "Mon May 20 00:00:00 EDT 1935", "Sun Dec 10 00:00:00 EST 1995", "Wed May 09 00:00:00 EDT 2001", "@F10@"));
        return expectedResult;
    }

    private Set<String> expectedUS08US09US12Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09_PERSON, ErrorCode.US08US09US12, "@P16@", "Inez/Youngster/", "Thu Jun 04 00:00:00 EDT 1970", "@F5@", "@P9@", "Thu Apr 11 00:00:00 EST 1963"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08_PERSON, ErrorCode.US08US09US12, "@P15@", "Darlene/Cook/", "Sun Oct 18 00:00:00 EDT 1998", "after", "divorce", "Tue Mar 15 00:00:00 EST 1921", "@F8@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08_PERSON, ErrorCode.US08US09US12, "@P24@", "John/Cook/Jr", "Fri Feb 08 00:00:00 EST 1952", "before", "marriage", "Wed Jun 10 00:00:00 EDT 1953", "@F9@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08_PERSON, ErrorCode.US08US09US12, "@P1@", "EdwinWilliam/Hague/", "Sat Jan 21 00:00:00 EST 1933", "before", "marriage", "Mon Jun 12 00:00:00 EDT 1933","@F2@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08_PERSON, ErrorCode.US08US09US12, "@P14@", "Jillian/Cook/", "Wed Oct 15 00:00:00 EST 1924", "after", "divorce", "Tue Mar 15 00:00:00 EST 1921", "@F8@"));
        //expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08_PERSON, ErrorCode.US08, "@P15@", "John /Cook/", "26 Dec 1922", "Tue Mar 15 00:00:00 EST 1921", "@F8@"));
        // F8 should not be here, but we do not handle duplicate Identifier, after we start sprint 3, this record should be removed.
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09_PERSON, ErrorCode.US08US09US12, "@P15@", "Darlene/Cook/", "Sun Oct 18 00:00:00 EDT 1998", "@F8@", "@P6@", "Tue Feb 02 00:00:00 EST 1965"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12_PERSON, ErrorCode.US08US09US12, "Mother", "@P14@", "Jillian/Cook/", "Wed Oct 15 00:00:00 EST 1924", "60", "her", "@P20@", "Elisabeth/Elsasser/", "Tue May 30 00:00:00 EDT 1989", "@F12@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12_PERSON, ErrorCode.US08US09US12, "Mother", "@P6@", "Ursula/Braddock/", "Wed Aug 11 00:00:00 EST 1886", "60", "her", "@P15@", "Darlene/Cook/",  "Sun Oct 18 00:00:00 EDT 1998", "@F8@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12_PERSON, ErrorCode.US08US09US12, "Mother", "@P14@", "Jillian/Cook/", "Wed Oct 15 00:00:00 EST 1924", "60", "her", "@P19@", "John/Elsaesser/", "Thu Oct 29 00:00:00 EST 1992", "@F12@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12_PERSON, ErrorCode.US08US09US12, "Mother", "@P9@", "Elizabeth/VanHaste/", "Fri Sep 10 00:00:00 EST 1880", "60", "her", "@P16@", "Inez/Youngster/", "Thu Jun 04 00:00:00 EDT 1970", "@F5@"));
        return expectedResult;
    }

    private Set<String> expectedUS16Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US16_PERSON, ErrorCode.US16, "@P19@", "John/Elsaesser/", "@F12@", "Jakob/Elsasser/"));
        return expectedResult;
    }

    /**
     * Parse uS 01 case test.
     */
    @Test
    public void parseUS01CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS01Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US01).toArray());
    }

    /**
     * Parse uS 02 case test.
     */
    @Test
    public void parseUS02CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS02Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US02).toArray());
    }

    /**
     * Parse uS 03 case test.
     */
    @Test
    public void parseUS03CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS03Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US03).toArray());
    }

    /**
     * Parse uS 04 case test.
     */
    @Test public void parseUS04CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS04Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US04).toArray());
    }

    /**
     * Parse uS 05 case test.
     */
    @Test
    public void parseUS05CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS05Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US05).toArray());
    }

    /**
     * Parse uS 06 case test.
     */
    @Test
    public void parseUS06CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS06Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US06).toArray());
    }

    /**
     * Parse uS 08 case test.
     * */
    @Test
    public void parseUS08US09US12CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS08US09US12Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US08US09US12).toArray());
    }

    /**
     * Parse us 16 case test.
     */
    @Test
    public void parseUS16CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS16Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US16).toArray());
    }
}

