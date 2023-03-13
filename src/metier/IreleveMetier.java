package metier;

import modele.releve;

public interface IreleveMetier {

    releve calculer_Mensualite(int idreleve)
            throws Exception;
}
