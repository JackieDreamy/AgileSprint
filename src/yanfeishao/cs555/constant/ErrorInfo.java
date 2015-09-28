package yanfeishao.cs555.constant;

/**
 * Created by JackieDreamy on 2015.
 */
public class ErrorInfo {
    /**
     * The constant US01.
     */
    public static final String US01 = "Dates(birth, marriage, divorce, death) of family [%s] should not be after the current date\n";
    /**
     * The constant US02.
     */
    public static final String US02 = "Birth of one of family [%s] member date should occur before marriage of an individual\n";
    /**
     * The constant US03.
     */
    public static final String US03 = "Birth of one of family [%s] member date should occur before death of an individual\n";
    /**
     * The constant US04.
     */
    public static final String US04 = "Family [%s] marriage date should occur before divorce of spouses, and divorce can only occur after marriage\n";
    /**
     * The constant US05.
     */
    public static final String US05 = "Family [%s] marriage date should occur before death of either spouse\n";
    /**
     * The constant US06.
     */
    public static final String US06 = "One of family [%s] member's divorce date can only occur before death of both spouses\n";
    /**
     * The constant US08.
     */
    public static final String US08 = "Child should be born after marriage of parents (and before their divorce)";
    /**
     * The constant US09.
     */
    public static final String US09 = "Child should be born before death of mother and before 9 months after death of father";
    /**
     * The constant US10.
     */
    public static final String US10 = "Marriage should be at least 14 years after birth of both spouses";
    /**
     * The constant US12.
     */
    public static final String US12 = "Mother should be less than 60 years older than her children and father should be less than 80 years older than his children";
    /**
     * The constant US16.
     */
    public static final String US16 = "All male members of a family should have the same last name";
    /**
     * The constant US21.
     */
    public static final String US21 = "Husband in family should be male and wife in family should be female";
    /**
     * The constant US22.
     */
    public static final String US22 = "All individual IDs should be unique and all family IDs should be unique";
    /**
     * The constant US25.
     */
    public static final String US25 = "No more than one child with the same name and birth date should appear in a family";
    /**
     * The constant US29.
     */
    public static final String US29 = "List all deceased individuals in a GEDCOM file";
    /**
     * The constant US30.
     */
    public static final String US30 = "List all living married people in a GEDCOM file";
    /**
     * The constant US31.
     */
    public static final String US31 = "List all living people over 30 who have never been married in a GEDCOM file";
    /**
     * The constant US33.
     */
    public static final String US33 = "List all orphaned children (both parents dead and child < 18 years old) in a GEDCOM file";
    /**
     * The constant US35.
     */
    public static final String US35 = "List all people in a GEDCOM file who were born in the last 30 days";
    /**
     * The constant US36.
     */
    public static final String US36 = "List all people in a GEDCOM file who died in the last 30 days";
    /**
     * The constant US38.
     */
    public static final String US38 = "List all living people in a GEDCOM file whose birthdays occur in the next 30 days";
    /**
     * The constant US39.
     */
    public static final String US39 = "List all living couples in a GEDCOM file whose marriage anniversaries occur in the next 30 days";
    /**
     * The constant US40.
     */
    public static final String US40 = "List line numbers from GEDCOM source file when reporting errors";
    /**
     * The constant US42.
     */
    public static final String US42 = "All dates should be legitimate dates for the months specified (e.g., 2/30/2015 is not legitimate)";
    /**
     * The constant READ_ERROR.
     */
    public static final String READ_ERROR = "cannot read line from ged";
    /**
     * The constant PARSE_ERROR.
     */
    public static final String PARSE_ERROR = "%s is not a valid date format";
    /**
     * The constant PATH_ERROR.
     */
    public static final String PATH_ERROR = "%s path is wrong, please check the path whether is correct";
}
