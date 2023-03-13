package dao.daoVolatile;

import dao.Idao;
import lombok.Data;
import lombok.NoArgsConstructor;
import modele.releve;
import org.springframework.stereotype.Component;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.DoubleToIntFunction;
@Data
@NoArgsConstructor
@Component

public class releveDao  implements Idao<releve, Long> {
public static Set<releve> BDreleve(){
    return new HashSet<releve>(
             Arrays.asList(
                     new releve(1, "Aya", 18,1, 16.3, "Assia", 0),
                     new releve(2, "Arij", 19,1, 17, "Kaoutar", 0),

                     new releve(13, "Soufiane", 17,1, 16, "Said", 0),

                     new releve(7, "Safouane", 20,1, 14, "Aicha", 0)

             ));


    }




    public releve trouverParId(Long id) {
        System.out.println("[DAO-DS volatile] trouver le releve n°:"+id);
        return BDreleve()
                .stream()
                .filter(releve -> releve.getId()== id)
                .findFirst()
                .orElse(null);
    }
}
