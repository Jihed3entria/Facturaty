package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Created by MBI on 11/02/2019.
 */
public class Article {
     private int Numarticle;
    String Libelle;
    double Prix;
    double TVA;
    CheckBox check;
    TextField txt ;

    public TextField getTxt() {
        return txt;
    }

    public void setTxt(TextField txt) {
        this.txt = txt;

    }

    public Article(String Libelle){this.Libelle = Libelle;}
    public Article(String Libelle,CheckBox ch){ check = ch ;this.Libelle = Libelle; txt= new TextField();
    //ch.setStyle();
        txt.setStyle("-fx-background-color: #2e354f; -fx-border-color: #8c90b3; -fx-border-radius: 2; -fx-text-inner-color:white;");
        txt.setPrefWidth(33.0);}
    public Article(int numarticle, String libelle, double prix) {
        Numarticle =numarticle ;
        Libelle = libelle;
        Prix = prix;
        TVA = Prix*0.19;
    }
    public int getNumarticle() {
        return Numarticle;
    }
    public String getLibelle() {
        return Libelle;
    }
    public void setLibelle(String libelle) {
        Libelle = libelle;
    }
    public double getPrix() {
        return Prix;
    }
    public void setPrix(int prix) {
        this.Prix = prix;
    }
    public double getTVA() {
        return TVA;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }
}
