import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializerClient2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("assets/image/massenger.png"));
        primaryStage.setTitle("Messenger");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
