package yanfeishao.cs555.constant;

/**
 * The type Error info.
 */
public class ErrorInfo {
    /**
     * The constant US01.
     */
    public static final String US01 = "%s of member [FamilyId: %s PersonId: %s Name: %s Date: %s] should not be after the current date [%s]\n";
    /**
     * The constant US02.
     */
    public static final String US02 = "Birth of member [FamilyId: %s PersonId: %s Name: %s BirthDate: %s] date should occur before marriage date [%s] of an individual\n";
    /**
     * The constant US03.
     */
    public static final String US03 = "Birth of one of member [FamilyId: %s PersonId: %s Name: %s BirthDate: %s]  date should occur before death [%s] of an individual [Person Id: %s Name: %s BirthDate: %s]\n";
    /**
     * The constant US04.
     */
    public static final String US04 = "Divorce date [%s] occurs before marriage date [%s] in Family [%s]\n";
    /**
     * The constant US05.
     */
    public static final String US05 = "Family [%s] marriage date [%s] should occur before death of either spouse [PersonId: %s Name: %s DeathDate: %s]\n";
    /**
     * The constant US06.
     */
    public static final String US06 = "Individual [PersonId: %s Name: %s BirthDate: %s]'s death date [%s] occurs before divorce date [%s] in Family [%s]\n";
    /**
     * The constant US08.
     */
    public static final String US08 = "Child [PersonId: %s Name: %s]'s birth date [%s] %s parents %s date [%s] occurs in Family [%s]\n";
    /**
     * The constant US09.
     */
    public static final String US09 = "Child [PersonId: %s Name: %s BirthDate: %s] from family [%s] should be born before death of mother and before 9 months after death of father [%s's deathDate: %s]\n";
    /**
     * The constant US10.
     */
    public static final String US10 = "Marriage should be at least fourteen years after birth of both spouses at marriage [Marriage Date: %s] Family [%s] Wife [PersonId: %s Name: %s Age: %s] and Husband [PersonId: %s Name: %s Age: %s]\n";
    /**
     * The constant US12.
     */
    public static final String US12 = "%s [PersonId: %s Name: %s Birth Date: %s]'s age is more than %s years older than %s child [PersonId: %s Name: %s Birth Date: %s] in Family %s \n";
    /**
     * The constant US16.
     */
    public static final String US16 = "Male members [PersonId: %s Name: %s] of a family [%s] have not the same last name [%s]\n";
    /**
     * The constant US21.
     */
    public static final String US21 = "The husband in a family should be male and wife in a family should be female, in family [%s %s is %s], the husband is [PersonId: %s Name: %s Gender: %s] and the wife is [PersonId: %s Name: %s Gender: %s]";
    /**
     * The constant US22.
     */
    public static final String US22 = "All individual IDs should be unique and all family IDs should be unique";
    /**
     * The constant US25.
     */
    public static final String US25 = "Child [PersonId: %s Name: %s Birth Date: %s] and child [PersonId: %s Name: %s Birth Date: %s] have the same name and birth date in family %s";
    /**
     * The constant US29.
     */
    public static final String US29 = "The individual [PersonId: %s Name: %s] has already deceased on [%s]\n";
    /**
     * The constant US30.
     */
    public static final String US30 = "The individual [PersonId: %s Name: %s] has already married on [%s] with people [PersonId: %s Name: %s]\n";
    /**
     * The constant US31.
     */
    public static final String US31 = "The individual [PersonId: %s Name: %s Age: %s] is over age 30 and has not married\n";
    /**
     * The constant US33.
     */
    public static final String US33 = "The individual [PersonId: %s Name: %s Orphaned at Age: %s is an orphan (both parents dead and  age < 18 years old\n";
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
    public static final String US42 = "%s dates should be legitimate dates for the months specified (e.g., APR-1905 is not legitimate)";
    /**
     * The constant READ_ERROR.
     */
    public static final String READ_ERROR = "cannot read line from ged";
    /**
     * The constant PATH_ERROR.
     */
    public static final String PATH_ERROR = "%s path is wrong, please check the path whether is correct";
}
