package sample;

/**
 * Created by MBI on 11/02/2019.
 */
public class Details {
    int Numarticle;
    String libelle ;
    int Qte;
    int HT ;
    double TVA ;
    double prix;

    public Details(int numarticle,String libelle,double Prix, int qte, int HT, double TVA) {
        Numarticle = numarticle;
        Qte = qte;
        this.HT = HT;
        this.TVA = TVA;
        prix=Prix;
        this.libelle=libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNumarticle() {
        return Numarticle;
    }

    public void setNumarticle(int numarticle) {
        Numarticle = numarticle;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    public int getHT() {
        return HT;
    }

    public void setHT(int HT) {
        this.HT = HT;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(int TVA) {
        this.TVA = TVA;
    }

}
