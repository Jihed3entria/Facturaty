package sample;
import connexion.Myconnexion;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;

import javax.xml.soap.Detail;


public class Controller implements Initializable{
    @FXML
    ImageView del_im = new ImageView() ;
    @FXML Button btn = new Button() ;
    @FXML private DatePicker d;
    @FXML private DatePicker d2= new DatePicker();
    @FXML private DatePicker d21= new DatePicker();
    @FXML private Button delete = new Button();
    @FXML private TextField usernameTextField;
    @FXML private TextField search;
    @FXML private TextField F0 ;
    @FXML private MenuButton choose;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField N0;
    @FXML private TextField N1;
    @FXML private TextField N2;
    @FXML private TextField N3;
    @FXML private TextField N4;
    @FXML private TextField a0;
    @FXML private TextField a1;
    @FXML private TextField a2;
    @FXML private TextField search_montant = new TextField();
    Alert c = new Alert(Alert.AlertType.CONFIRMATION);
    Alert add = new Alert(Alert.AlertType.INFORMATION);

    @FXML private ChoiceBox choice = new ChoiceBox<>();
    @FXML private Label errorMessageLabel;
    @FXML Label test = new Label();
    @FXML Label l = new Label();
    @FXML private Label errorMessageLabel2;
    //new table
    @FXML ObservableList<Facture> list_facture = FXCollections.observableArrayList();


    //table client
    @FXML  TableView<Client> table=new TableView<Client>();
    //list des libelle des articles
    @FXML private TableView<Article> list=new TableView<>();

    @FXML ObservableList<Article> articles = FXCollections.observableArrayList();
    @FXML TableColumn<Article, String>  arc = new TableColumn<>( "Article");
    @FXML TableColumn<Article, String>  check = new TableColumn<>();
    @FXML TableColumn<Article, String>  txt = new TableColumn<>("Quantity");


    //table facture
    @FXML private TableView<Facture> table_Facture=new TableView<>();
    //facture column
    @FXML TableColumn<Facture, String>  NF_column = new TableColumn<>( "NumFact");
    @FXML TableColumn<Facture, String>  NC_column = new TableColumn<>( "Client");
    @FXML TableColumn<Facture, String>  ttc_column = new TableColumn<>( "Ttc");
    @FXML TableColumn<Facture, String>  Date_column = new TableColumn<>( "Date");
    //table article
    @FXML private TableView<Article> tableA=new TableView<>();
    //client column
    @FXML TableColumn<Client, String>X1 = new TableColumn<>( "ID");
    @FXML TableColumn<Client, String>X2 = new TableColumn<>( "Nom");
    @FXML TableColumn<Client, String>X3 = new TableColumn<>( "Address");
    @FXML TableColumn<Client, String>X4 = new TableColumn<>( "Matricule");
    @FXML TableColumn<Client, String>X5 = new TableColumn<>();

    //article  column
    @FXML TableColumn<Article, String>X1A = new TableColumn<>( "ID");
    @FXML TableColumn<Article, String>X2A = new TableColumn<>( "Libelle");
    @FXML TableColumn<Article, String>X3A = new TableColumn<>( "Prix");
    @FXML TableColumn<Article, String>X4A = new TableColumn<>( "TVA");
    //detail
    @FXML  TableView<Details> table_det =new TableView<>();
    @FXML TableColumn<Details, String>X1D = new TableColumn<>( "Numarticle");
    @FXML TableColumn<Details, String>X2D = new TableColumn<>( "Libelle");
    @FXML TableColumn<Details, String>X3D = new TableColumn<>( "Prix");
    @FXML TableColumn<Details, String>X4D = new TableColumn<>( "TVA");
    @FXML TableColumn<Details, String>X5D = new TableColumn<>( "Qnt");
    @FXML TableColumn<Details, String>X6D = new TableColumn<>( "HT");





    //client list
    @FXML private ObservableList<Client> data = FXCollections.observableArrayList();
    //facture list
    @FXML private ObservableList<Facture> dataF = FXCollections.observableArrayList();
    //article list
    @FXML private ObservableList<Article> dataAr = FXCollections.observableArrayList();
    //detail list
    @FXML ObservableList<Details> dets = FXCollections.observableArrayList();
    @FXML private ObservableList<String> choosecl = FXCollections.observableArrayList();
    @FXML MenuItem myItem;
    @FXML Label mnt;
    @FXML MenuBar myMenuBar;
    @FXML ComboBox<String> cF = new ComboBox<>();
    @FXML ComboBox<String> cM = new ComboBox<>();
    @FXML ComboBox<String> cdeF = new ComboBox<>();
    @FXML DatePicker dF ;
    @Override public void initialize(URL location, ResourceBundle resources) {
        //list.setStyle("-fx-table-cell-border-color:red;");
        list.setStyle("");
        //new table columns
        table_Facture.setEditable(true);
        NF_column.setOnEditCommit(
                (TableColumn.CellEditEvent<Facture, String> t) -> {
                    ((Facture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNumFact(Integer.parseInt(t.getNewValue()));
                    String sql = "update Facture set NumFact = '"+t.getNewValue()+"' where NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                    System.out.println(sql);
                    try {
                        Statement s = cnx();
                        s.executeUpdate(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });
        NC_column.setOnEditCommit(
                (TableColumn.CellEditEvent<Facture, String> t) -> {
                    ((Facture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setClient(Integer.parseInt(t.getNewValue()));
                    String sql = "update Facture set Numclient = '"+t.getNewValue()+"' where NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                    System.out.println(sql);
                    try {
                        Statement s = cnx();
                        s.executeUpdate(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });
      /*  Ttc.setCellValueFactory(new PropertyValueFactory<>("Ttc"));
        Ttc.setCellFactory(TextFieldTableCell.forTableColumn());

        Ttc.setOnEditCommit(
                (TableColumn.CellEditEvent<Facture, String> t) -> {
                    ((Facture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTtc(Integer.parseInt(t.getNewValue()));
                    String sql = "update Facture set ttc = '"+t.getNewValue()+"' where NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                    System.out.println(sql);
                    try {
                        Statement s = cnx();
                        s.executeUpdate(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });
        Date.setCellValueFactory(new PropertyValueFactory<Facture, String>("Date"));
*/






        table_Facture.setRowFactory(tv->{
            TableRow<Facture> row = new TableRow<>();

            row.setOnMouseClicked(event ->{
            if(event.getClickCount()==2){
                try {
                    Statement st = cnx();
                    //String x = String.valueOf();
                    String sql = "select detail.Numarticle,libelle,Prix,Qte ,HT,detail.TVA from detail,article where Detail.Numarticle= Article.Numarticle and  Detail.NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                    System.out.println(sql);
                    ResultSet r = st.executeQuery(sql);
                    dets = FXCollections.observableArrayList();
                        while (r.next())
                            dets.add(new Details(r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4), r.getInt(3) * r.getInt(4), 0.19 * r.getInt(3)));
                    System.out.println(dets.size());
                    table_det.setItems(dets);
                }

                catch (SQLException e) {
                    e.printStackTrace();}
            }
                System.out.println("clicks : "+event.getClickCount());
            if(event.getClickCount()==1){
                Date_column.setCellFactory(TextFieldTableCell.forTableColumn());

                Date_column.setOnEditCommit(
                        (TableColumn.CellEditEvent<Facture, String> t) -> {
                            ((Facture) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())).setDate(t.getNewValue());
                            String sql = "update Facture set date = '"+t.getNewValue()+"' where NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                            System.out.println(sql);
                            try {
                                Statement s = cnx();
                                s.executeUpdate(sql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        });
               /* NC_column.setCellFactory(TextFieldTableCell.forTableColumn());

                NC_column.setOnEditCommit(
                        (TableColumn.CellEditEvent<Facture, String> t) -> {
                            int y = Integer.parseInt(t.getNewValue());
                            ((Facture) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())).setClient(y);
                            String sql = "update Facture set NumClient = '"+t.getNewValue()+"' where NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                            System.out.println(sql);
                            try {
                                Statement s = cnx();
                                s.executeUpdate(sql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        });*/
            }
            });


            return row;
        });
        X1D.setCellValueFactory(new PropertyValueFactory<Details, String>("Numarticle"));
        X2D.setCellValueFactory(new PropertyValueFactory<Details, String>("Libelle"));
        X3D.setCellValueFactory(new PropertyValueFactory<Details, String>("prix"));
        X4D.setCellValueFactory(new PropertyValueFactory<Details, String>("Qte"));
        X5D.setCellValueFactory(new PropertyValueFactory<Details, String>("HT"));
        X6D.setCellValueFactory(new PropertyValueFactory<Details, String>("TVA"));


        // column client

        table.setEditable(true);
        X1.setCellValueFactory(new PropertyValueFactory<>("N_client"));
        X2.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        X3.setCellValueFactory(new PropertyValueFactory<>("Address"));
        X4.setCellValueFactory(new PropertyValueFactory<>("Mat_Fiscal"));
        X5.setCellValueFactory(new PropertyValueFactory<>("delete"));

        X2.setCellFactory(TextFieldTableCell.forTableColumn());
        X2.setOnEditCommit(
                (TableColumn.CellEditEvent<Client, String> t) -> {
                    ((Client) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNom(t.getNewValue());
                    String sql = "update client set Nom = '"+t.getNewValue()+"' where NumClient ="+table.getSelectionModel().getSelectedItem().getN_client();
                    System.out.println(sql);
                    try {
                        Statement s = cnx();
                        s.executeUpdate(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });


        //column facture
        NF_column.setCellValueFactory(new PropertyValueFactory<>("NumFact"));
        NC_column.setCellValueFactory(new PropertyValueFactory<>("Client"));
        ttc_column.setCellValueFactory(new PropertyValueFactory<>("Ttc"));
        Date_column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Date_column.setCellFactory(TextFieldTableCell.forTableColumn());
       /* Date_column.setOnEditCommit(
                (TableColumn.CellEditEvent<Facture, String> t) -> {
                    ((Facture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDate(t.getNewValue());
                    String sql = "update Facture set date = '"+t.getNewValue()+"' where NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
                    System.out.println(sql);
                    try {
                        Statement s = cnx();
                        s.executeUpdate(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                });*/


        arc.setCellValueFactory(new PropertyValueFactory<>("Libelle"));
        check.setCellValueFactory(new PropertyValueFactory<>("check"));
        txt.setCellValueFactory(new PropertyValueFactory<>("txt"));


        //column article
        X1A.setCellValueFactory(new PropertyValueFactory<>("Numarticle"));
        X2A.setCellValueFactory(new PropertyValueFactory<>("Libelle"));
        X3A.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        X4A.setCellValueFactory(new PropertyValueFactory<>("TVA"));

        //remplir table client
        Statement st = null;
        X1.setText("N°Client");
        X2.setText("Nom");
        X3.setText("Adresse");
        try {
            st = cnx();
            ResultSet rs = st.executeQuery("select * from client");
            while (rs.next()) {
                data.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                cF.getItems().add(rs.getString(2));
                cdeF.getItems().add(rs.getString(1));
                cM.getItems().add(rs.getString(2));
            }

            table.setItems(data);

            //remplir libelle article
            String temp = "select Libelle from article";
            System.out.println(temp);
            ResultSet ress = st.executeQuery(temp);
            while (ress.next()) {
                articles.add(new Article(ress.getString(1),new CheckBox()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.setItems(articles);
        //remplir facture
        try {
            Statement t = cnx();
            String temp = "select * from facture";
            //dataF= FXCollections.observableArrayList();
            //System.out.println(temp);
            ResultSet rs = t.executeQuery(temp);
            while (rs.next()) {
                dataF.add(new Facture(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table_Facture.setItems(dataF);
    }
    public void show(){
        try {
            Statement st = cnx();
            String x = String.valueOf(table_Facture.getSelectionModel().getSelectedItem().getNumFact());
            //System.out.println("num facture "+x);
            String sql = "select * from details where NumFact =  "+x;
            ResultSet r = st.executeQuery(sql);
            while(r.next()) System.out.println("N° Facture : "+r.getInt(1)+" , N° Article "+r.getInt(2)+", Qnt "+r.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @FXML public void exit(MouseEvent event) throws IOException {
                Platform.exit();
    }
    @FXML public void loadCustomerScreen (ActionEvent event) throws IOException,SQLException {
        // before  u have  to log in
        if (checkIfValidUser()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Statement statement = cnx();
            String css = this.getClass().getResource("../mycss.css").toExternalForm();
            scene.getStylesheets().add(css);

            /*String st = "show tables";
            statement.execute(st);
            while(statement.getResultSet().next())
            System.out.println(statement.getResultSet().getString(1));
            System.out.println(table.getSelectionModel());*/
           /* Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFactory = (
            table.setEditable(true);
            X1.setMinWidth(100);
            X1.setCellFactory(cellFactory);
            X1.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
                ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                        .setAddress(t.getNewValue());
            });
            /*TableColumn<Client, String> Name = new TableColumn<>("Name");
            Name.setMinWidth(100);
            Name.setCellValueFactory(new PropertyValueFactory<>("Name"));*/
           // table.getColumns().addAll(X1,X2);
          //  table.setItems(data);
           // System.out.println(table.getSelectionModel().getSelectedItem());
            /*vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(table);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);*/
            stage.setScene(scene);
            stage.show();
        }

            else {
            errorMessageLabel.setText("The username and password did not match");
            // empty the fields
            usernameTextField.setText("");
            passwordTextField.setText("");
        }
    }
    public void logout(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginForm.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../mycss.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    public void comeback(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../mycss.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    public void getClients(){
        choose.setText("Client");
        delete.setVisible(true);
        del_im.setVisible(true);
        table.setVisible(true);
        data = FXCollections.
                observableArrayList();
        Statement st= null;
        try {
            st = cnx();
            ResultSet  rs=st.executeQuery("select * from client");
            while(rs.next()){
                data.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(data);
        tableA.setVisible(false);
    }
    public void add() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("add.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
            Stage stage = (Stage) myMenuBar.getScene().getWindow();
            stage.setScene(scene);

    }
    public void addAr(ActionEvent t) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addAr.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.setScene(scene);
    }
    public void addF(ActionEvent t) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addFa.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.setScene(scene);
        String css = this.getClass().getResource("../mycss.css").toExternalForm();
        scene.getStylesheets().add(css);
        try{
            Statement s = cnx();
            String st = "select Nom from client";
            ResultSet x=s.executeQuery(st);
            choice.getItems().add("Client");
            //chcl.setVisible(true);
            while (x.next()) {
                choosecl.add(x.getString(1));
            }
            choice.setItems(choosecl);
            choice.show();
        } catch(SQLException e){
        e.printStackTrace();}
        //System.out.println(choice.);
        //chcl.setItems(choosecl);
        for(int i=0;i<choice.getItems().size();i++)
        System.out.println("2nd : "+choice.getItems().get(i));

    }
    public void DeleteClient()throws IOException{
        c.setTitle("Attention ");
        c.setContentText("Are you sure about Deleting this Client  ! ");
        Optional<ButtonType> ans = c.showAndWait();
        if(ans.get()==ButtonType.OK) {
            //String sql = "delete from client where client.NumClient = my.NumClient";
            if(table.getSelectionModel().isEmpty()){
                c.setContentText("You should select a client first ");
            }
            String x = String.valueOf(table.getSelectionModel().getSelectedItem().getN_client());
            String sql = "delete from client where client.NumClient = " + x;
            try {
                Statement s = cnx();
                s.executeUpdate(sql);
                ResultSet rs = s.executeQuery("select * from client");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + ";" + rs.getString(2));
                }
                getClients();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void DeleteFacture()throws IOException{
        //String sql = "delete from client where client.NumClient = my.NumClient";
        String x =String.valueOf(table.getSelectionModel().getSelectedItem().getN_client());
        String sql = "delete from client where client.NumClient = "+x;
        try{
            Statement s = cnx();
            s.executeUpdate(sql);
            ResultSet  rs=s.executeQuery("select * from client");
            while(rs.next()){
                System.out.println(rs.getInt(1)+";"+rs.getString(2));
            }
            getClients();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void DeleteArticle()throws IOException{
        c.setTitle("Attention");
        c.setContentText("Are you sure about Deleting this article  ! ");
        Optional<ButtonType> ans = c.showAndWait();
        if(ans.get()==ButtonType.OK) {
            //String sql = "delete from client where client.NumClient = my.NumClient";
            String x = String.valueOf(tableA.getSelectionModel().getSelectedItem().getNumarticle());
            String sql = "delete from article where Numarticle = " + x;
            try {
                Statement s = cnx();
                s.executeUpdate(sql);
                ResultSet rs = s.executeQuery("select * from article");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + ";" + rs.getString(2));
                }
                getAll_Article();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void clearC() {
            N0.clear();
            N1.clear();
            N2.clear();
            N3.clear();
            N4.clear();

       /* N0.setText(" ");
            N1.setText(" ");
            N2.setText(" ");
            N3.setText(" ");
            N4.setText(" ");*/
    }
    public void clearA() {

                a0.setText(" ");
               a1.setText(" ");
               a2.setText(" ");
        }
    public void clearF() {

        /*F0.setText(" ");
        a1.setText(" ");
        a2.setText(" ");*/
    }
    public void gotofactdetail()throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fact_detail.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) choose.getScene().getWindow();
        stage.setScene(scene);
        String css = this.getClass().getResource("../mycss.css").toExternalForm();
        scene.getStylesheets().add(css);
        table_Facture.setItems(dataF);

    }
    public void gotofiltrer()throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("F_de_C.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../mycss.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.setScene(scene);
    }
    public void gotomontant()throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Montant.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.setScene(scene);
        //cM.getItems().addAll("1");
    }
    public void afficherFacture_Client(){
        String date = dF.getValue().toString();
        String cf = cF.getSelectionModel().getSelectedItem();
       // System.out.println(cf);
        int tmp2 = 0;
        int tmp3=0;

        try {
            Statement s = cnx();
            String cl = "select NumClient from client where Nom = '" + cf + "'";
            System.out.println(cl);
            ResultSet r2 = s.executeQuery(cl);
            while (r2.next()) tmp2 = r2.getInt(1);
            System.out.println(tmp2 + ", " + date);
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
            try {
                String x = "select * from facture where NumClient = " + tmp2 + " and date = '"+ date +"'";
                System.out.println(x);
                Statement s2= cnx();
                ResultSet r = s2.executeQuery(x);
                while (r.next()) {
                list_facture.add(new Facture(r.getInt(1),r.getInt(2),r.getInt(3),r.getString(4)));
                tmp3= r.getInt(1);
                System.out.println("tmp "+tmp3);
                System.out.println("N° Facture : " + r.getInt(1) + " TTC = " + r.getInt(3));

            }
                System.out.println(list_facture.size());
                table_Facture.setItems(list_facture);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void afficherMontant(){
        String n = "select NumClient from Client where Nom = '"+cM.getSelectionModel().getSelectedItem()+"'";
      //  ArrayList<Integer>temp = new ArrayList<>();
        int tmp=0;
        try {
            Statement x = cnx();
            ResultSet s0 = x.executeQuery(n);
            //String sq ="select distinct NumClient, Nom, Adress, Matricule from client ,facture where sum(ttc) >  ";
            while (s0.next())  tmp = s0.getInt(1);
            String sql = "select sum(ttc) from Facture where NumClient = "+tmp+" and date <= '"+d2.getValue()+"' and date >= '"+d21.getValue()+"'" ;
            System.out.println(sql);
            ResultSet s = x.executeQuery(sql);
            while(s.next()) {
                mnt.setText("montant = " + s.getInt(1) + " DA");
               // temp.add(s.getInt(1));
                // System.out.println("array temp size : "+temp.size());
            }
            //int m=0;
           /* while(m<temp.size()){
            mon="insert into montant values("+tmp+","+temp.get(m)+")";
            x.executeUpdate(mon);
            m++;
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchCl()throws IOException,NullPointerException{

            tableA.setVisible(false);
            table.setVisible(true);
            String x = search.getText();
            choose.setText("Client");
            data = FXCollections.observableArrayList();
            Statement t = null;
            try {
                t = cnx();
                String temp = "select * from client where Nom like '" + x + "%' Or Nom like '%"+x+"%'";
                System.out.println(temp);
                ResultSet rs = t.executeQuery(temp);
                while (rs.next()) {
                    data.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            table.setItems(data);
        }
    public void goto_client_montant_sup()throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("superior.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("../mycss.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) myMenuBar.getScene().getWindow();
            stage.setScene(scene);

        }
    public void client_montant_sup() throws NullPointerException{
            System.out.println(search_montant.getText());
            data = FXCollections.observableArrayList();
        //double mont = Double.valueOf(search_montant.getText());
        String sql ="select distinct client.NumClient, Nom,Address,RC from client ,montant where client.NumClient = Montant.numClient and montant >"+search_montant.getText();
            try {
                Statement s = cnx();
                ResultSet res =s.executeQuery(sql);
                while (res.next()) data.add(new Client(res.getInt(1),res.getString(2),res.getString(3),res.getString(4)));
                table.setItems(data);

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(sql);

        }
    public void detail(){
        try {
            Statement st = cnx();
            //String x = String.valueOf(table_Facture.getSelectionModel().getSelectedItem().getNumFact());
            String sql = "select detail.Numarticle,libelle,Prix,Qte ,HT,detail.TVA from detail,article where Detail.Numarticle= Article.Numarticle and  Detail.NumFact ="+table_Facture.getSelectionModel().getSelectedItem().getNumFact();
            System.out.println(sql);
            ResultSet r = st.executeQuery(sql);
            while(r.next()) dets.add(new Details(r.getInt(1),r.getString(2),r.getInt(3),r.getInt(4),r.getInt(3)*r.getInt(4),0.19*r.getInt(3)));
                    //"N° Facture : "+r.getInt(1)+" , N° Article "+r.getInt(2)+", Qnt "+r.getInt(3));
            System.out.println(dets.size());
            table_det.setItems(dets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getAll_Article(){
        table.setVisible(false);
        tableA.setVisible(true);
        delete.setVisible(false);
        del_im.setVisible(false);
        X1A.setText("Numero");
        X2A.setText("Libelle");
        X3A.setText("Prix");
        X4A.setText("TVA");
        //choose.setText("Article");
        try {
            Statement t =cnx();
            String temp = "select * from article";
            System.out.println(temp);
            ResultSet  rs=t.executeQuery(temp);
            dataAr= FXCollections.observableArrayList();
            while(rs.next()){
                //System.out.println("first "+ rs.getInt(1));
                 dataAr.add(new Article(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableA.setItems(dataAr);
        System.out.println("columns : "+tableA.getItems().get(0).getNumarticle());
    }
    public void saveClient()throws SQLException {
        Myconnexion mine = new Myconnexion();
        Connection connection = mine.getConnection();
        //String sql2 = "CREATE TABLE CLIENT(NumClient int primary key, Nom varchar(20),Address varchar(30), RC varchar(10),Mat varchar(10))";
        Statement stmt = connection.createStatement();
        //stmt.executeUpdate(sql);
        //stmt.execute(sql2);
        ResultSet id = stmt.executeQuery("select NumClient from client");
        boolean x = false;
        while(id.next()) {
            if (id.getInt(1) == Integer.parseInt(N0.getText())) {
                x= true;
            } }
            if(!x){
                String sql = "INSERT INTO Client values('" + N0.getText() + "', '" + N1.getText() + "', '" + N2.getText() + "', '" + N3.getText() + "', '" + N4.getText() + "')";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                //errorMessageLabel2.setText("The Client : "+N1.getText()+" has been saved successfully ");
                add.setTitle("Successfully added ");
               // add.setContentText();
                add.setContentText("The Client : "+N1.getText()+" has been saved ! ");
                add.show();
                clearC();


            }
            else         add.setContentText("ID already exist ");
        add.show();



        ResultSet rs = stmt.executeQuery("select * from client");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4));

        connection.close();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public Statement cnx()throws SQLException{
        Myconnexion mine = new Myconnexion();
        Connection connection = mine.getConnection();
        return connection.createStatement();

    }
    public void saveFacture(){
        Statement s = null;
        try {
            s = cnx();
            String dat = d.getValue().toString();
            ResultSet id = s.executeQuery("select NumFact from facture");
            boolean x = false;
            while(id.next()) {
                if (id.getInt(1) == Integer.parseInt(F0.getText())) {
                   // x= true;
                } }
            int count =0;
            if(!x){
                System.out.println("size : "+getit().size());
                float ttc =0;
        while(count < getit().size() ) {
                String prix = "select Prix from article where Libelle = '" + getit().get(count).getLibelle() + "'";
                System.out.println(prix);
                //System.out.println(adeF.getSelectionModel().getSelectedItem());
                ResultSet getprix = s.executeQuery(prix);
                int t1 = 0;

                while (getprix.next()) {
                    t1 = getprix.getInt(1);
                }
                System.out.println("t1 : " + t1);
                System.out.println(getit().get(count).getTxt().getText());
                //Ht
                String ps = getit().get(count).getTxt().getText();
                System.out.println("ps "+ps);
                int p = t1 * Integer.parseInt(ps);
                 ttc = p + p * t1;
                System.out.println("p : " + p);
                System.out.println("t1 *0.19 " + t1 * 0.19);
                //add to details
                String numarticle = "select NumArticle from article where Libelle ='" + getit().get(count).getLibelle() + "'";
                System.out.println(numarticle);
                ResultSet resultSet = s.executeQuery(numarticle);
                int temp_num = 0;
                while (resultSet.next()) temp_num = resultSet.getInt(1);
                String sql2 = "Insert into detail values ( '" + F0.getText() + "', '" + temp_num + "', ' " + getit().get(count).getTxt().getText() + " ', '" + p + "', '" + t1 * 0.19 + "', '" + ttc + "')";
                System.out.println(sql2);
                s.executeUpdate(sql2);

                clearF();
                errorMessageLabel2.setText("Fac  N° : " + F0.getText() + " has been saved successfully ");
            System.out.println("count : "+count);
            count++;
        }
                String sql = "INSERT INTO facture values('" + F0.getText() + "', '" + cdeF.getSelectionModel().getSelectedItem() + "', '" + ttc + "', '" + dat + "')";
                System.out.println(sql);
                s.executeUpdate(sql);
                System.out.println(count);

            }
            else         errorMessageLabel2.setText("ID already exist ");
            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
clearF();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addFa.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("../mycss.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = new Stage();
            stage.setScene(scene);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public ArrayList<Article> getit(){
       ArrayList<Article>get = new ArrayList<>();
       int i =0;
       while(i<list.getItems().size()){
           if(list.getItems().get(i).getCheck().isSelected()){
                get.add(list.getItems().get(i));
           }
           i++;
       }
       System.out.println("i : "+i +" list "+list.getItems().size()+" get : "+get.size());
       return get;
   }
    public void saveArticle()throws SQLException {
        Statement stmt = cnx();
        ResultSet rs = stmt.executeQuery("select * from article");
        boolean x = false;
        ResultSet id = stmt.executeQuery("select Numarticle from article");
        while (id.next()) {
            if (id.getInt(1) == Integer.parseInt(a0.getText())) {
                x = true;
            }
        }
        if (!x) {
            double t = Integer.parseInt(a2.getText()) * 0.19;
            String sql = "INSERT INTO Article values('" + a0.getText() + "', '" + a1.getText() + "', '" + a2.getText() + "', '" + t + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            errorMessageLabel2.setText("The Article : " + a1.getText() + " has been saved successfully ");
            clearA();
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addAr.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
            }
            catch(Exception e){
                e.printStackTrace();
            }


        } else errorMessageLabel2.setText("ID already exist ");
        ResultSet rss = stmt.executeQuery("select * from article");
    }
    public void back(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("../mycss.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("");


    }
    public boolean checkIfValidUser() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.equals("User") & password.equals("123")) {
            return true;
        }
        else
        return false;
    }
}