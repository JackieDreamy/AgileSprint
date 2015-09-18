package yanfeishao.cs555.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Yanfei Shao on 2015.
 */
@Data
@NoArgsConstructor
public class PersonEntity {
    private String identifier;
    private String name;
    private String sex;
    private Date birthDate;
    private Date deathDate;
}