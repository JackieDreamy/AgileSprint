package yanfeishao.cs555.utils;

import lombok.Data;
import yanfeishao.cs555.entities.FamilyEntity;
import yanfeishao.cs555.entities.PersonEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yanfei Shao on 2015.
 */
@Data
public class SimpleDBUtils {

    private Map<String, PersonEntity> personDB;
    private Map<String, FamilyEntity> familyDB;

    /**
     * Instantiates a new Simple dB utils.
     */
    public SimpleDBUtils() {
        personDB = new HashMap<>();
        familyDB = new HashMap<>();
    }

}
