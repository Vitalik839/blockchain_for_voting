package Application;

import Database.DatabaseManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.SQLException;

public class RegistrationForm {
    private PageController pageController;


    public RegistrationForm(PageController pageController) {
        this.pageController = pageController;
    }
    public VBox getRegistrationForm() {
        String url = "jdbc:mysql://127.0.0.1:3306/login_schema";
        String username = "root";
        String PASSWORD = "vitalik2005";

        Label idLabel = new Label("Ідентифікаційний код:");
        TextField idField = new TextField();

        Label phoneLabel = new Label("Номер телефону:");
        TextField phoneField = new TextField();

        Label lastNameLabel = new Label("Прізвище:");
        TextField lastNameField = new TextField();

        Label firstNameLabel = new Label("Ім'я:");
        TextField firstNameField = new TextField();

        Label passwordLabel = new Label("Пароль:");
        TextField password = new PasswordField();

        Label passwordRepeatLabel = new Label("Повторіть пароль:");
        TextField password_repeat = new PasswordField();

        // Creating the buttons
        Button registerButton = new Button("Зареєструватися");
        Button loginButton = new Button("Вхід в акаунт");

        // Layout for the registration form
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);

        gridPane.add(phoneLabel, 0, 1);
        gridPane.add(phoneField, 1, 1);

        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameField, 1, 2);

        gridPane.add(firstNameLabel, 0, 3);
        gridPane.add(firstNameField, 1, 3);

        gridPane.add(passwordLabel, 0, 4);
        gridPane.add(password, 1, 4);

        gridPane.add(passwordRepeatLabel, 0, 5);
        gridPane.add(password_repeat, 1, 5);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(registerButton, loginButton);

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(gridPane, buttonBox);

        vBox.setStyle("-fx-background-color: #5BABF0; -fx-border-color: #000; -fx-border-width: 5px;");

        loginButton.setOnAction(e -> pageController.showLoginForm());
        registerButton.setOnAction(e -> {
        try {
            DatabaseManager databaseManager = new DatabaseManager();
            databaseManager.connect(url, username, PASSWORD);

            databaseManager.registerUser(Integer.parseInt(idField.getText()), Integer.parseInt(phoneField.getText()),
                    lastNameField.getText(), firstNameField.getText(), password.getText());

            pageController.showCandidatesPage(databaseManager.getUserId(Integer.parseInt(idField.getText())));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        });
        return vBox;
    }
}

