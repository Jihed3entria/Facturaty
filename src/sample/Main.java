package sample;

import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 310, 466));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        // primaryStage.getIcons().add(new Image(JavaFXImageBuilder.class.getResourceAsStream("../img/icon.png")));
       // stage.getIcons().add(new Image(JavaFXIcons.class.getResourceAsStream("stackoverflow.jpg"))) ;

        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
