package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by MBI on 11/02/2019.
 */
public class Client {
    int N_client;
    String Nom;
    String Address;
    String Rc;
    String Mat_Fiscal;
    Button Delete ;

    public Button getDelete() {
        return Delete;
    }

    public void setDelete(Button delete) {
        this.Delete = delete;
    }
public Client(Button btn){Delete=btn;
    }
    public Client(int n_client, String nom, String address, String rc, String mat_Fiscal) {
        N_client = n_client;
        Nom = nom;
        Address = address;
        Rc = rc;
        Mat_Fiscal = mat_Fiscal;
       // Delete = new Button();
        //delete.setOnAction("#");
    }
    public Client(int n_client, String nom,String ad,String s) {
        N_client = n_client;
        Nom = nom;
        Address=ad;
        Mat_Fiscal = s;
        Delete = new Button();
       /* Delete.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                Controller c = new Controller() ;
                if(c.table.getSelectionModel().isEmpty()){
                    c.c.setContentText("You should select a client first ");
                }
                String x = String.valueOf(c.table.getSelectionModel().getSelectedItem().getN_client());
                String sql = "delete from client where client.NumClient = " + x;
                try {
                    Statement s = c.cnx();
                    s.executeUpdate(sql);
                    ResultSet rs = s.executeQuery("select * from client");
                    while (rs.next()) {
                        System.out.println(rs.getInt(1) + ";" + rs.getString(2));
                    }
                    c.getClients();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                System.out.println("it worked ");

            }
        });*/
        /*Delete.setOnAction( new EventHandler(){
                                @Override
                                public void handle(Event event) {

                                }
                            }
        );*/
    }
    public Client(int n_client, String nom,String ad) {
        N_client = n_client;
        Nom = nom;
        Address=ad;
    }


    public int getN_client() {
        return N_client;
    }

    public void setN_client(int n_client) {
        N_client = n_client;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRc() {
        return Rc;
    }

    public void setRc(String rc) {
        Rc = rc;
    }

    public String getMat_Fiscal() {
        return Mat_Fiscal;
    }

    public void setMat_Fiscal(String mat_Fiscal) {
        Mat_Fiscal = mat_Fiscal;
    }
}
