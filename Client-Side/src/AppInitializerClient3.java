import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class AppInitializerClient3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("location"));
        primaryStage.setTitle("Messenger");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
