package Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageController {
    private Stage stage;

    public PageController(Stage stage) {
        this.stage = stage;
    }

    public void showRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationForm(this);
        Scene registrationScene = new Scene(registrationForm.getRegistrationForm(), 400, 400);
        stage.setTitle("Blockchain Voting System - Registration");
        stage.setScene(registrationScene);
        stage.show();
    }

    public void showLoginForm() {
        LoginForm loginForm = new LoginForm(this);
        Scene loginScene = new Scene(loginForm.getLoginForm(), 400, 200);
        stage.setTitle("Blockchain Voting System - Login");
        stage.setScene(loginScene);
        stage.show();
    }

    public void showCandidatesPage(int id) {
        CandidatesPage candidatesPage = new CandidatesPage(this);
        Scene candidatesScene = new Scene(candidatesPage.getCandidatesPage(id), 500, 700);
        stage.setTitle("Blockchain Voting System - Candidates");
        stage.setScene(candidatesScene);
        stage.show();
    }
}
