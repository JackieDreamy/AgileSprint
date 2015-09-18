package yanfeishao.cs555.enums;

/**
 * Created by Yanfei Shao on 2015.
 */
public enum ParseEnum {
    /**
     * The SPLIT_REGEX.
     */
    SPLIT_REGEX(" "),
    /**
     * The SPLIT_DATE.
     */
    SPLIT_DATE("-"),
    /**
     * The DATE_FORMAT.
     */
    DATE_FORMAT("dd-MMM-yyyy");

    private String parseTag;

    /**
     * Instantiates a new Parse enum.
     *
     * @param parseTag
     *         the parse tag
     */
    ParseEnum(String parseTag) {
        this.parseTag = parseTag;
    }

    @Override
    public String toString() {
        return parseTag;
    }
}
