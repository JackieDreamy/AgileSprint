package yanfeishao.cs555.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yanfei Shao on 2015.
 */
@Data
public class FamilyEntity {
    private String identifier;
    private PersonEntity father;
    private PersonEntity mother;
    private Date marriedDate;
    private Date divorceDate;
    private List<PersonEntity> childList;

    /**
     * Instantiates a new Family entity.
     */
    public FamilyEntity() {
        childList = new ArrayList<>();
    }
}
