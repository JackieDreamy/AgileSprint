package yanfeishao.cs555.constant;

/**
 * The type Error info.
 */
public class ErrorInfo {
    /**
     * The constant US01_FAMILY.
     */
    public static final String US01_FAMILY = "Dates(birth, marriage, divorce, death) of family [%s] should not be after the current date\n";
    /**
     * The constant US02_FAMILY.
     */
    public static final String US02_FAMILY = "Birth of one of family [%s] member date should occur before marriage of an individual\n";
    /**
     * The constant US03_FAMILY.
     */
    public static final String US03_FAMILY = "Birth of one of family [%s] member date should occur before death of an individual\n";
    /**
     * The constant US04_FAMILY.
     */
    public static final String US04_FAMILY = "Family [%s] marriage date should occur before divorce of spouses, and divorce can only occur after marriage\n";
    /**
     * The constant US05_FAMILY.
     */
    public static final String US05_FAMILY = "Family [%s] marriage date should occur before death of either spouse\n";
    /**
     * The constant US06_FAMILY.
     */
    public static final String US06_FAMILY = "One of family [%s] member's divorce date can only occur before death of both spouses\n";
    /**
     * The constant US08_FAMILY.
     */
    public static final String US08_FAMILY = "Child should be born after marriage of parents (and before their divorce)";
    /**
     * The constant US09_FAMILY.
     */
    public static final String US09_FAMILY = "Child from family [%s] should be born before death of mother and before 9 months after death of father";
    /**
     * The constant US10_FAMILY.
     */
    public static final String US10_FAMILY = "Marriage should be at least 14 years after birth of both spouses, family [%s] wife age [%s] and husband age [%s] at marriage";
    /**
     * The constant US12_FAMILY.
     */
    public static final String US12_FAMILY = "Mother should be less than 60 years older than her children and father should be less than 80 years older than his children";
    /**
     * The constant US16_FAMILY.
     */
    public static final String US16_FAMILY = "[%s] male members of a family have not the same last name";
    /**
     * The constant US21_FAMILY.
     */
    public static final String US21_FAMILY = "Husband in family [%s] should be male [%s] and wife in family should be female [%s]";
    /**
     * The constant US22_FAMILY.
     */
    public static final String US22_FAMILY = "All individual IDs should be unique and all family IDs should be unique";
    /**
     * The constant US25_FAMILY.
     */
    public static final String US25_FAMILY = "No more than one child with the same name and birth date should appear in a family";
    /**
     * The constant US29_FAMILY.
     */
    public static final String US29_FAMILY = "List all deceased individuals in a GEDCOM file";
    /**
     * The constant US30_FAMILY.
     */
    public static final String US30_FAMILY = "List all living married people in a GEDCOM file";
    /**
     * The constant US31_FAMILY.
     */
    public static final String US31_FAMILY = "List all living people over 30 who have never been married in a GEDCOM file";
    /**
     * The constant US33_FAMILY.
     */
    public static final String US33_FAMILY = "List all orphaned children (both parents dead and child < 18 years old) in a GEDCOM file";
    /**
     * The constant US35_FAMILY.
     */
    public static final String US35_FAMILY = "List all people in a GEDCOM file who were born in the last 30 days";
    /**
     * The constant US36_FAMILY.
     */
    public static final String US36_FAMILY = "List all people in a GEDCOM file who died in the last 30 days";
    /**
     * The constant US38_FAMILY.
     */
    public static final String US38_FAMILY = "List all living people in a GEDCOM file whose birthdays occur in the next 30 days";
    /**
     * The constant US39_FAMILY.
     */
    public static final String US39_FAMILY = "List all living couples in a GEDCOM file whose marriage anniversaries occur in the next 30 days";
    /**
     * The constant US40_FAMILY.
     */
    public static final String US40_FAMILY = "List line numbers from GEDCOM source file when reporting errors";
    /**
     * The constant US42_FAMILY.
     */
    public static final String US42_FAMILY = "All dates should be legitimate dates for the months specified (e.g., 2/30/2015 is not legitimate)";
    /**
     * The constant US01_PERSON.
     */
    public static final String US01_PERSON = "Dates(birth, marriage, divorce, death) of member should not be after the current date [Date: %s] for [FamilyId: %s PersonId: %s Name: %s Date Type: %s Date: %s] \n";
    /**
     * The constant US02_PERSON.
     */
    public static final String US02_PERSON = "Birth of member [FamilyId: %s PersonId: %s Name: %s BirthDate: %s] date should occur before marriage date [%s] of an individual\n";
    /**
     * The constant US03_PERSON.
     */
    public static final String US03_PERSON = "Birth of one of member [FamilyId: %s PersonId: %s Name: %s BirthDate: %s]  date should occur before death [%s] of an individual [Person Id: %s Name: %s BirthDate: %s]\n";
    /**
     * The constant US04_PERSON.
     */
    public static final String US04_PERSON = "Divorce date [%s] occurs before marriage date [%s] in Family [%s]\n";
    /**
     * The constant US05_PERSON.
     */
    public static final String US05_PERSON = "Family [%s] marriage date [%s] should occur before death of either spouse [PersonId: %s Name: %s DeathDate: %s]\n";
    /**
     * The constant US06_PERSON.
     */
    public static final String US06_PERSON = "Individual [PersonId: %s, Name: %s, BirthDate: %s]'s death date [%s] occurs before divorce date [%s] in Family [%s]\n";
    /**
     * The constant US08_PERSON.
     */
    public static final String US08_PERSON = "Child [PersonId: %s, Name: %s]'s birth date [%s] %s parents %s date [%s] occurs in Family [%s]\n";
    /**
     * The constant US09_PERSON.
     */
    public static final String US09_PERSON = "Child [PersonId: %s, Name: %s BirthDate: %s] from family [%s] should be born before death of mother and before 9 months after death of father [%s's deathDate: %s]\n";
    /**
     * The constant US10_PERSON.
     */
    public static final String US10_PERSON = "Marriage should be at least fourteen years after birth of both spouses at marriage [Marriage Date: %s] Family [%s] Wife [PersonId: %s Name: %s Age %s] and Husband [PersonId: %s Name: %s Age %s]\n";
    /**
     * The constant US12_PERSON.
     */
    public static final String US12_PERSON = "%s [PersonId: %s, Name: %s, Birth Date: %s]'s age is more than %s years older than %s child [PersonId: %s, Name: %s, Birth Date: %s] in Family %s \n";
    /**
     * The constant US16_PERSON.
     */
    public static final String US16_PERSON = "Male members [PersonId: %s, Name: %s] of a family [%s] have not the same last name [%s]\n";
    /**
     * The constant US21_PERSON.
     */
    public static final String US21_PERSON = "The husband in a family should be male and wife in a family should be female, in family [%s %s is %s], the husband is [PersonId: %s, Name: %s, Gender: %s] and the wife is [PersonId: %s, Name: %s, Gender: %s]";
    /**
     * The constant US22_PERSON.
     */
    public static final String US22_PERSON = "All individual IDs should be unique and all family IDs should be unique";
    /**
     * The constant US25_PERSON.
     */
    public static final String US25_PERSON = "No more than one child with the same name and birth date should appear in a family";
    /**
     * The constant US29_PERSON.
     */
    public static final String US29_PERSON = "List all deceased individuals in a GEDCOM file";
    /**
     * The constant US30_PERSON.
     */
    public static final String US30_PERSON = "List all living married people in a GEDCOM file";
    /**
     * The constant US31_PERSON.
     */
    public static final String US31_PERSON = "List all living people over 30 who have never been married in a GEDCOM file";
    /**
     * The constant US33_PERSON.
     */
    public static final String US33_PERSON = "List all orphaned children (both parents dead and child < 18 years old) in a GEDCOM file";
    /**
     * The constant US35_PERSON.
     */
    public static final String US35_PERSON = "List all people in a GEDCOM file who were born in the last 30 days";
    /**
     * The constant US36_PERSON.
     */
    public static final String US36_PERSON = "List all people in a GEDCOM file who died in the last 30 days";
    /**
     * The constant US38_PERSON.
     */
    public static final String US38_PERSON = "List all living people in a GEDCOM file whose birthdays occur in the next 30 days";
    /**
     * The constant US39_PERSON.
     */
    public static final String US39_PERSON = "List all living couples in a GEDCOM file whose marriage anniversaries occur in the next 30 days";
    /**
     * The constant US40_PERSON.
     */
    public static final String US40_PERSON = "List line numbers from GEDCOM source file when reporting errors";
    /**
     * The constant US42_PERSON.
     */
    public static final String US42_PERSON = "All dates should be legitimate dates for the months specified (e.g., 2/30/2015 is not legitimate)";
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
