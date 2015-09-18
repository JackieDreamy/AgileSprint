package yanfeishao.cs555;

import yanfeishao.cs555.utils.ParserUtils;

/**
 * Created by Yanfei Shao on 2015.
 */
public class Main {

    private static final String FILE_PATH = "/Users/YanfeiShao/GitHub/AgileSprint/AgileSprint/out/data.ged";

    /**
     * The entry point of application.
     *
     * @param args
     *         the input arguments
     */
    public static void main(String[] args) {
        ParserUtils parserUtils = new ParserUtils();
        parserUtils.readGEDCOM(FILE_PATH);
    }
}
