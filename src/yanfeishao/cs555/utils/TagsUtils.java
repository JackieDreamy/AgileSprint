package yanfeishao.cs555.utils;

import lombok.Data;
import yanfeishao.cs555.constant.KeywordsConstant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yanfei Shao on 2015.
 */
@Data
public class TagsUtils {

    private Set<String> tagSets;

    /**
     * Instantiates a new Tags utils.
     */
    private TagsUtils() {
        tagSets = new HashSet<>();
        tagSets.add(KeywordsConstant.INDI);
        tagSets.add(KeywordsConstant.NAME);
        tagSets.add(KeywordsConstant.SEX);
        tagSets.add(KeywordsConstant.BIRT);
        tagSets.add(KeywordsConstant.DEAT);
        tagSets.add(KeywordsConstant.FAMC);
        tagSets.add(KeywordsConstant.FAMS);
        tagSets.add(KeywordsConstant.FAM);
        tagSets.add(KeywordsConstant.MARR);
        tagSets.add(KeywordsConstant.HUSB);
        tagSets.add(KeywordsConstant.WIFE);
        tagSets.add(KeywordsConstant.CHIL);
        tagSets.add(KeywordsConstant.DIV);
        tagSets.add(KeywordsConstant.DATE);
        tagSets.add(KeywordsConstant.HEAD);
        tagSets.add(KeywordsConstant.TRLR);
        tagSets.add(KeywordsConstant.NOTE);
    }

    /**
     * Create tag factory tags utils.
     *
     * @return the tags utils
     */
    public static TagsUtils createTagFactory() {
        return new TagsUtils();
    }
}
