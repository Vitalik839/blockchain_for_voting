import Application.PageController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        PageController pageController = new PageController(primaryStage);
        pageController.showRegistrationForm();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
