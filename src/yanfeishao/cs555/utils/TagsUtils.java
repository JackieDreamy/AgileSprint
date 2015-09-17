package yanfeishao.cs555.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by JackieDreamy on 2015.
 */
public class TagsUtils {

    private Set<String> tagSets;

    public TagsUtils() {
        tagSets = new HashSet<>();
        tagSets.add("INDI");
        tagSets.add("NAME");
        tagSets.add("SEX");
        tagSets.add("BIRT");
        tagSets.add("DEAT");
        tagSets.add("FAMC");
        tagSets.add("FAMS");
        tagSets.add("FAM");
        tagSets.add("MARR");
        tagSets.add("HUSB");
        tagSets.add("WIFE");
        tagSets.add("CHIL");
        tagSets.add("DIV");
        tagSets.add("DATE");
        tagSets.add("HEAD");
        tagSets.add("TRLR");
        tagSets.add("NOTE");
    }

    public Set<String> getTagSets() {
        return tagSets;
    }
}
