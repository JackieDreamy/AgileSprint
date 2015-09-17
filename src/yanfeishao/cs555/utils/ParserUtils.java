package yanfeishao.cs555.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by JackieDreamy on 2015.
 */
public class ParserUtils {

    private static final String INVALID_TAG = "Invalid tag";

    private BufferedReader gedReader;
    private StringBuilder dataBuffer;
    private TagsUtils tagsUtils;


    /**
     * Read GEDCOM from file.
     *
     * @return the string builder
     */
    public StringBuilder readGEDCOM(String filePath) {
        try {
            tagsUtils = new TagsUtils();
            gedReader = new BufferedReader(new FileReader(filePath));
            dataBuffer = new StringBuilder();
            String line = gedReader.readLine();
            while (line != null) {
                dataBuffer.append(line);
                dataBuffer.append(System.lineSeparator());
                dataBuffer.append("<level> : ");
                dataBuffer.append(parseLevelNumber(line));
                dataBuffer.append(System.lineSeparator());
                dataBuffer.append("<tag> : ");
                dataBuffer.append(parseKeywords(line));
                dataBuffer.append(System.lineSeparator());
                dataBuffer.append(System.lineSeparator());
                line = gedReader.readLine();
            }
            gedReader.close();
        } catch (FileNotFoundException fnfe) {
            String message = String.format("%s path is wrong, please check the path whether is correct", filePath);
            throw new RuntimeException(message, fnfe);
        } catch (IOException ioe) {
            String message = String.format("cannot read line from %s", filePath);
            throw new RuntimeException(message, ioe);
        }
        return dataBuffer;
    }

    private String parseLevelNumber(String line) {
        String levelNumber = line.split(" ")[0];
        return levelNumber;
    }

    private String parseKeywords(String line) {
        String tag;
        String[] lineArray = line.split(" ");
        if (parseLevelNumber(line).equals("0") && lineArray[1].charAt(0)=='@') {
            tag = lineArray[2];
        } else {
            tag = lineArray[1];
        }
        if (tagsUtils.getTagSets().contains(tag)) {
            return tag;
        } else {
            return INVALID_TAG;
        }
    }
}
