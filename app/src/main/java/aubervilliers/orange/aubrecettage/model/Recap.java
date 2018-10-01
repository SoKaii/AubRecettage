package aubervilliers.orange.aubrecettage.model;

import java.io.Serializable;

public class Recap implements Serializable {

    private String CI2Anum;
    private String dateRecetteI;
    private String dateRecetteD;
    private String typeRecette;
    private String validOrange;
    private String referentOrange;

    public Recap(String CI2Anum, String dateRecetteI, String dateRecetteD, String referentOrange) {

        this.CI2Anum = CI2Anum;
        this.dateRecetteI = dateRecetteI;
        this.dateRecetteD = dateRecetteD;
        this.typeRecette = null;
        this.validOrange = null;
        this.referentOrange = referentOrange;
    }

    public String getCI2Anum() {
        return CI2Anum;
    }

    public void setCI2Anum(String CI2Anum) {
        this.CI2Anum = CI2Anum;
    }

    public String getDateRecetteI() {
        return dateRecetteI;
    }

    public void setDateRecetteI(String dateRecetteI) {
        this.dateRecetteI = dateRecetteI;
    }

    public String getDateRecetteD() {
        return dateRecetteD;
    }

    public void setDateRecetteD(String dateRecetteD) {
        this.dateRecetteD = dateRecetteD;
    }

    public String getTypeRecette() {
        return typeRecette;
    }

    public void setTypeRecette(String typeRecette) {
        this.typeRecette = typeRecette;
    }

    public String getValidOrange() {
        return validOrange;
    }

    public void setValidOrange(String validOrange) {
        this.validOrange = validOrange;
    }

    public String getReferentOrange() {
        return referentOrange;
    }

    public void setReferentOrange(String referentOrange) {
        this.referentOrange = referentOrange;
    }

}
