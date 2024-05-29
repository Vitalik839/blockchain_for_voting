package Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Database.DatabaseManager;

import java.sql.SQLException;


public class LoginForm {
    private PageController pageController;

    public LoginForm(PageController pageController) {
        this.pageController = pageController;
    }

    public VBox getLoginForm() {
        String url = "jdbc:mysql://127.0.0.1:3306/login_schema";
        String username = "root";
        String PASSWORD = "vitalik2005";
        // Creating the labels and text fields
        Label idLabel = new Label("Ідентифікаційний код:");
        TextField idField = new TextField();

        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();

        // Creating the login button
        Button loginButton = new Button("Вхід");

        // Creating the registration button
        Button registerButton = new Button("Реєстрація");

        // Layout for the login form
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);

        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(loginButton, registerButton);

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridPane, buttonBox);

        vBox.setStyle("-fx-background-color: #5BABF0");

        registerButton.setOnAction(e -> pageController.showRegistrationForm());
        loginButton.setOnAction(e -> {
            try {
                DatabaseManager databaseManager = new DatabaseManager();
                databaseManager.connect(url, username, PASSWORD);

                pageController.showCandidatesPage(databaseManager.getUserId(Integer.parseInt(idField.getText())));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        return vBox;
    }
}
