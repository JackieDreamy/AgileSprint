package yanfeishao.cs555;

import yanfeishao.cs555.utils.ParserUtils;

/**
 * Created by Yanfei Shao on 2015.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args
     *         the input arguments
     */
    public static void main(String[] args) {
        ParserUtils parserUtils = ParserUtils.createParserFactory();
        parserUtils.readGEDCOM(args[0]);
    }
}
