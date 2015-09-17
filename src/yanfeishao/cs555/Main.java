package yanfeishao.cs555;

import yanfeishao.cs555.utils.ParserUtils;

public class Main {

    private static final String FILE_PATH = "/Users/YanfeiShao/IdeaProjects/ParserGEDCOM/out/production/ParserGEDCOM/data.ged";

    public static void main(String[] args) {
        System.out.println("This is for CS555 Project 02");
        ParserUtils parserUtils = new ParserUtils();
        StringBuilder gedData = parserUtils.readGEDCOM(FILE_PATH);
        System.out.println(gedData.toString());
    }
}
