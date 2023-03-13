package modele;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class releve {

    private  int id;
    private String Etudiant;
    private Double note;
    private Integer coef;
    private Double releve_note;
    private String nom_demandeur;
    private Double mensualite;

    private int duree;


    public releve(long id, String Etudiant, double note, int coef, double releve_note, String nom_demandeur,
                  double mensualite) {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtudiant() {
        return Etudiant;
    }

    public void setEtudiant(String etudiant) {
        Etudiant = etudiant;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    public Double getReleve_note() {
        return releve_note;
    }

    public void setReleve_note(Double releve_note) {
        this.releve_note = releve_note;
    }

    public String getNom_demandeur() {
        return nom_demandeur;
    }

    public void setNom_demandeur(String nom_demandeur) {
        this.nom_demandeur = nom_demandeur;
    }

    public Double getMensualite() {
        return mensualite;
    }

    public void setMensualite(Double mensualite) {
        this.mensualite = mensualite;
    }

    @Override
    public String toString() {
        return "releve{" +
                "id=" + id +
                ", Etudiant='" + Etudiant + '\'' +
                ", note=" + note +
                ", coef=" + coef +
                ", releve_note=" + releve_note +
                ", nom_demandeur='" + nom_demandeur + '\'' +
                ", mensualite=" + mensualite +
                '}';
    }
}
