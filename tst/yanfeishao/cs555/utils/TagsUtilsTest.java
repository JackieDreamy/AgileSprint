package yanfeishao.cs555.utils;

import org.junit.Assert;
import org.junit.Test;
import yanfeishao.cs555.abstracts.TestCases;

/**
 * Created by JackieDreamy on 2015.
 */
public class TagsUtilsTest extends TestCases {

    /**
     * Tag utils keywords happy case.
     */
    @Test
    public void tagUtilsKeywordsHappyCase() {
        Assert.assertEquals(TAG_EXPECTED_SIZE, tagsUtils.getTagSets().size());
    }

}
