package yanfeishao.cs555.utils;

import yanfeishao.cs555.constant.KeywordsConstant;

/**
 * Created by Xiaonan Zhang on 9/18/15.
 */
public class OutputUtils {

    /**
     * Read all of the data of GEDCOM file
     * print the unique identifiers and names of each of the individuals in order by their unique identifiers.
     */

    public void outputPerson(SimpleDBUtils simpleDBUtils) {
        System.out.println("IDENTIFIER              NAME");
        for (int index = 1; index <= simpleDBUtils.getPersonDB().size(); index++) {
            System.out.println(String.format("%6s                %-20s",
                    simpleDBUtils.getPersonDB().get("@I" + index + "@").getIdentifier(),
                    simpleDBUtils.getPersonDB().get("@I" + index + "@").getName()));
        }
        System.out.println();
    }

    /**
     * Read all of the data of GEDCOM file
     * print the unique identifiers and names of the husbands and wives, in order by unique family identifiers.
     */
    public void outputFamily(SimpleDBUtils simpleDBUtils) {
        System.out.println("IDENTIFIER            HUSBAND_NAME                      WIFE_NAME");
        for (int index = 1; index <= simpleDBUtils.getFamilyDB().size(); index++) {
            System.out.println(String.format("%6s                %-20s              %-10s",
                    simpleDBUtils.getFamilyDB().get("@F" + index + "@").getIdentifier(),
                    simpleDBUtils.getFamilyDB().get("@F" + index + "@").getFather().getName(),
                    simpleDBUtils.getFamilyDB().get("@F" + index + "@").getMother().getName()));
        }
    }
}
