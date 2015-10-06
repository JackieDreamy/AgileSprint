package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.ErrorCode;
import yanfeishao.cs555.constant.KeywordsConstant;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;
import yanfeishao.cs555.enums.ParseEnum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yanfei Shao on 2015.
 */
public class ParserUtils {

    private BufferedReader gedReader;
    private TagsUtils tagsUtils;
    private SimpleDBUtils simpleDBUtils;
    private PersonEntity personEntity;
    private FamilyEntity familyEntity;
    private OutputUtils outputUtils;

    private ParserUtils() {
    }

    /**
     * Create parser factory parser utils.
     *
     * @return the parser utils
     */
    public static ParserUtils createParserFactory() {
        return new ParserUtils();
    }

    /**
     * Read GED from file.
     *
     * @param filePath
     *         the file path
     *
     * @return Simple NoSQL DB
     */
    public SimpleDBUtils readGEDCOM(String filePath) {
        try {
            tagsUtils = TagsUtils.createTagFactory();
            gedReader = new BufferedReader(new FileReader(filePath));
            simpleDBUtils = SimpleDBUtils.createDBFactory();
            outputUtils = OutputUtils.createOutputFactory();
            String line = gedReader.readLine();
            while (line != null) {
                parseTag(line);
                line = gedReader.readLine();
            }
            gedReader.close();
            outputResult();
            outputError();
            return simpleDBUtils;
        } catch (FileNotFoundException fnfe) {
            ErrorUtils.pathError(fnfe, filePath);
            return null;
        } catch (IOException ioe) {
            ErrorUtils.readGEDError(ioe);
            return null;
        }
    }

    private void outputResult() {
        outputUtils.outputResult(simpleDBUtils, KeywordsConstant.FAM);
        outputUtils.outputResult(simpleDBUtils, KeywordsConstant.INDI);
    }

    private void outputError() {
        outputUtils.outputError(simpleDBUtils, ErrorCode.US01);
        outputUtils.outputError(simpleDBUtils, ErrorCode.US02);
        outputUtils.outputError(simpleDBUtils, ErrorCode.US03);
        outputUtils.outputError(simpleDBUtils, ErrorCode.US04);
        outputUtils.outputError(simpleDBUtils, ErrorCode.US05);
        outputUtils.outputError(simpleDBUtils, ErrorCode.US06);
    }

    private Date parseDate(String line) {
        String[] lineArray = splitString(line);
        StringBuffer currentDate = new StringBuffer();
        for (int index = 2; index < lineArray.length - 1; index++) {
            currentDate.append(lineArray[index]);
            currentDate.append(ParseEnum.SPLIT_DATE.toString());
        }
        currentDate.append(lineArray[lineArray.length - 1]);
        DateFormat dateFormat = new SimpleDateFormat(ParseEnum.DATE_FORMAT.toString());
        try {
            return dateFormat.parse(currentDate.toString());
        } catch (ParseException pe) {
            ErrorUtils.parseError(currentDate);
            return null;
        }
    }

    private String parseLevel(String line) {
        return splitString(line)[0];
    }

    private void parseTag(String line) {
        String tag;
        String[] lineArray = splitString(line);
        if (parseLevel(line).equals("0") && lineArray[1].charAt(0) == '@' && lineArray[2].charAt(0) == 'I') {
            personEntity = new PersonEntity();
            tag = lineArray[2];
        } else if (parseLevel(line).equals("0") && lineArray[1].charAt(0) == '@' && lineArray[2].charAt(0) == 'F') {
            familyEntity = new FamilyEntity();
            tag = lineArray[2];
        } else {
            tag = lineArray[1];
        }
        if (personEntity != null) {
            writePersonEntity(tag, lineArray);
            simpleDBUtils.getPersonDB().put(personEntity.getIdentifier(), personEntity);
        }
        if (familyEntity != null) {
            writeFamilyEntity(tag, lineArray);
            simpleDBUtils.getFamilyDB().put(familyEntity.getIdentifier(), familyEntity);
        }
    }

    private PersonEntity getPersonEntity(String[] lineArray) {
        return simpleDBUtils.getPersonDB().get(lineArray[2]);
    }

    private void writeFamilyEntity(String tag, String[] lineArray) {
        if (tagsUtils.getTagSets().contains(tag)) {
            switch (tag) {
                case KeywordsConstant.FAM:
                    familyEntity.setIdentifier(lineArray[1]);
                    break;
                case KeywordsConstant.HUSB:
                    familyEntity.setFather(getPersonEntity(lineArray));
                    break;
                case KeywordsConstant.WIFE:
                    familyEntity.setMother(getPersonEntity(lineArray));
                    break;
                case KeywordsConstant.MARR:
                    try {
                        familyEntity.setMarriedDate(parseDate(gedReader.readLine()));
                    } catch (IOException ioe) {
                        ErrorUtils.readGEDError(ioe);
                    }
                    break;
                case KeywordsConstant.DIV:
                    try {
                        familyEntity.setDivorceDate(parseDate(gedReader.readLine()));
                    } catch (IOException ioe) {
                        ErrorUtils.readGEDError(ioe);
                    }
                    break;
                case KeywordsConstant.CHIL:
                    familyEntity.getChildList().add(getPersonEntity(lineArray));
                    break;
            }
        }
    }

    private void writePersonEntity(String tag, String[] lineArray) {
        if (tagsUtils.getTagSets().contains(tag)) {
            switch (tag) {
                case KeywordsConstant.INDI:
                    personEntity.setIdentifier(lineArray[1]);
                    break;
                case KeywordsConstant.NAME:
                    StringBuffer nameBuffer = new StringBuffer();
                    for (int index = 2; index < lineArray.length; index++) {
                        nameBuffer.append(lineArray[index]);
                    }
                    personEntity.setName(nameBuffer.toString());
                    break;
                case KeywordsConstant.SEX:
                    personEntity.setSex(lineArray[2]);
                    break;
                case KeywordsConstant.BIRT:
                    try {
                        personEntity.setBirthDate(parseDate(gedReader.readLine()));
                    } catch (IOException ioe) {
                        ErrorUtils.readGEDError(ioe);
                    }
                    break;
                case KeywordsConstant.DEAT:
                    try {
                        personEntity.setDeathDate(parseDate(gedReader.readLine()));
                    } catch (IOException ioe) {
                        ErrorUtils.readGEDError(ioe);
                    }
                    break;
            }
        }
    }

    private String[] splitString(String line) {
        return line.split(ParseEnum.SPLIT_REGEX.toString());
    }
}
