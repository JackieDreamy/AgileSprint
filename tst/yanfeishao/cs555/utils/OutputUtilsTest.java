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
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, ErrorCode.US01, "Death", "@F4@", "@P12@", "EdwinBurton/Hague/", "Jul-19-2019", CommonUtils.getFormattedDate(CommonUtils.getCurrentDate())));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, ErrorCode.US01, "Death", "@F10@", "@P26@", "Elizabeth/Ockers/", "Aug-13-2017", CommonUtils.getFormattedDate(CommonUtils.getCurrentDate())));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, ErrorCode.US01, "Death", "@F7@", "@P12@", "EdwinBurton/Hague/", "Jul-19-2019", CommonUtils.getFormattedDate(CommonUtils.getCurrentDate())));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US01, ErrorCode.US01, "Death", "@F11@", "@P26@", "Elizabeth/Ockers/", "Aug-13-2017", CommonUtils.getFormattedDate(CommonUtils.getCurrentDate())));
        return expectedResult;
    }

    private Set<String> expectedUS02Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US02, ErrorCode.US02, "@F11@", "@P16@", "Inez/Youngster/", "Jun-04-1970", "Dec-25-1928"));
        return expectedResult;
    }

    private Set<String> expectedUS03Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03, ErrorCode.US03, "@F5@", "@P16@", "Inez/Youngster/", "Jun-04-1970", "Dec-29-1966", "@P16@", "Inez/Youngster/", "Jun-04-1970"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US03, ErrorCode.US03, "@F11@", "@P16@", "Inez/Youngster/", "Jun-04-1970", "Dec-29-1966", "@P16@", "Inez/Youngster/", "Jun-04-1970"));
        return expectedResult;
    }

    private Set<String> expectedUS04Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US04, ErrorCode.US04, "Mar-15-1921", "Jun-20-1922", "@F8@"));
        return expectedResult;
    }

    private Set<String> expectedUS05Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05, ErrorCode.US05, "@F9@", "Jun-10-1953", "@P15@", "John/Cook/", "May-17-1947"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US05, ErrorCode.US05, "@F10@", "Nov-06-1997", "@P28@", "KennethArvid/Lindfors/", "Dec-10-1995"));
        return expectedResult;
    }

    private Set<String> expectedUS06Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US06, ErrorCode.US06, "@P12@", "EdwinBurton/Hague/", "Nov-29-1906", "Jul-19-2019", "Sep-20-1972", "@F7@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US06, ErrorCode.US06, "@P28@", "KennethArvid/Lindfors/", "May-20-1935", "Dec-10-1995", "May-09-2001", "@F10@"));
        return expectedResult;
    }

    private Set<String> expectedUS08Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08, ErrorCode.US08, "@P15@", "John/Cook/", "Dec-26-1922", "after", "divorce", "Mar-15-1921", "@F8@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08, ErrorCode.US08, "@P24@", "John/Cook/Jr", "Feb-08-1952", "before", "marriage", "Jun-10-1953", "@F9@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08, ErrorCode.US08, "@P1@", "EdwinWilliam/Hague/", "Jan-21-1933", "before", "marriage", "Jun-12-1933", "@F2@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US08, ErrorCode.US08, "@P14@", "Jillian/Cook/", "Oct-15-1924", "after", "divorce", "Mar-15-1921", "@F8@"));
        return expectedResult;
    }

    private Set<String> expectedUS09Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US09, ErrorCode.US09, "@P16@", "Inez/Youngster/", "Jun-04-1970", "@F5@", "@P9@", "Apr-11-1963"));
        return expectedResult;
    }

    private Set<String> expectedUS12Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12, ErrorCode.US12, "Mother", "@P14@", "Jillian/Cook/", "Oct-15-1924", "60", "her", "@P20@", "Elisabeth/Elsasser/", "May-30-1989", "@F12@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12, ErrorCode.US12, "Mother", "@P14@", "Jillian/Cook/", "Oct-15-1924", "60", "her", "@P19@", "John/Elsaesser/", "Oct-29-1992", "@F12@"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US12, ErrorCode.US12, "Mother", "@P9@", "Elizabeth/VanHaste/", "Sep-10-1880", "60", "her", "@P16@", "Inez/Youngster/", "Jun-04-1970", "@F5@"));
        return expectedResult;
    }

    private Set<String> expectedUS10Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US10, ErrorCode.US10, "Jun-12-1933", "@F2@", "@P3@", "VernaMay/Youngster/", "13", "@P2@", "EdwinBurton/Hague/", "26"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US10, ErrorCode.US10, "Dec-25-1928", "@F11@", "@P16@", "Inez/Youngster/", "-41", "@P25@", "EdwardMorris/Ockers/", "25"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US10, ErrorCode.US10, "Nov-22-1924", "@F7@", "@P18@", "Barbara/Fritschi/", "8", "@P12@", "EdwinBurton/Hague/", "17"));
        return expectedResult;
    }

    private Set<String> expectedUS16Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US16, ErrorCode.US16, "@P19@", "John/Elsaesser/", "@F12@", "Jakob/Elsasser/"));
        return expectedResult;
    }

    private Set<String> expectedUS21Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US21, ErrorCode.US21, "@F4@", "Husband", "Female", "@P5@", "WilliamHenry/Hague/", "F", "@P6@", "Ursula/Braddock/", "F"));
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US21, ErrorCode.US21, "@F7@", "Wife", "Male", "@P12@", "EdwinBurton/Hague/", "M", "@P18@", "Barbara/Fritschi/", "M"));
        return expectedResult;
    }

    private Set<String> expectedUS25Result() {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add(String.format(FormatterRegex.ERROR_PERSON + ErrorInfo.US25, ErrorCode.US25, "@P2@", "EdwinBurton/Hague/", "Nov-29-1906", "@P12@", "EdwinBurton/Hague/", "Nov-29-1906", "@F4@"));
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
    @Test
    public void parseUS04CaseTest() {
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
     */
    @Test
    public void parseUS08CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS08Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US08).toArray());
    }

    /**
     * Parse us 09 case test.
     */
    @Test
    public void parseUS09CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS09Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US09).toArray());
    }

    /**
     * Parse us 12 case test.
     */
    @Test
    public void parseUS12CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS12Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US12).toArray());
    }

    /**
     * Parse us 10 case test.
     */
    @Test
    public void parseUS10CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS10Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US10).toArray());
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

    /**
     * Parse us 21 case test.
     */
    @Test
    public void parseUS21CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS21Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US21).toArray());
    }

    /**
     * Parse us 25 case test.
     */

    @Test
    public void parseUS25CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertArrayEquals(expectedUS25Result().toArray(), outputUtils.outputError(simpleDBUtils, ErrorCode.US25).toArray());
    }

    /**
     * Parse us 29 case test.
     */
    @Test
    public void parseUS29CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertEquals(US29_EXPECTED_SIZE, outputUtils.outputSpecialConditionResult(simpleDBUtils, ErrorCode.US29).size());
    }

    /**
     * Parse us 30 case test.
     */
    @Test
    public void parseUS30CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertEquals(US30_EXPECTED_SIZE, outputUtils.outputSpecialConditionResult(simpleDBUtils, ErrorCode.US30).size());
    }

    /**
     * Parse us 31 case test.
     */
    @Test
    public void parseUS31CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertEquals(US31_EXPECTED_SIZE, outputUtils.outputSpecialConditionResult(simpleDBUtils, ErrorCode.US31).size());
    }

    /**
     * Parse us 33 case test.
     */
    @Test
    public void parseUS33CaseTest() {
        SimpleDBUtils simpleDBUtils = parserUtils.readGEDCOM(FILE_PATH);
        Assert.assertNotNull(simpleDBUtils);
        Assert.assertEquals(US33_EXPECTED_SIZE, outputUtils.outputSpecialConditionResult(simpleDBUtils, ErrorCode.US33).size());
    }

}

