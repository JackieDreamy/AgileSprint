package yanfeishao.cs555.utils;

import yanfeishao.cs555.enums.ParseEnum;

/**
 * Created by JackieDreamy on 2015.
 */
public class NameUtils {

    private String[] splitName(String name) {
        String[] splitResult = name.split(ParseEnum.PERSON_NAME_SPLIT.toString());
        return splitResult;
    }
}
