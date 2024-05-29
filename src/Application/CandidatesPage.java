package Application;

import Database.DatabaseManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;

import java.sql.SQLException;

public class CandidatesPage {
    private PageController pageController;
    public int selectedCandidateIndex = -1;
    public String [] bio = {"Волошин Павло Олександрович",
                            "Новаковський Віталій Володимирович",
                            "Бірчин Владислав Анатолійович",
                            "Філь Уляна Степанівна",
                            "Зінченко Марта Ігорівна"};

    public String [] batches = {"Ліберали",
                                "Правий Сектор",
                                "Радикальне Рішення",
                                "Українська Сила",
                                "Вільне Слово"};

    public CandidatesPage(PageController pageController) {
        this.pageController = pageController;
    }

    public VBox getCandidatesPage(int id_user) {
        String url = "jdbc:mysql://127.0.0.1:3306/login_schema";
        String username = "root";
        String PASSWORD = "vitalik2005";

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_CENTER);
        ToggleGroup toggleGroup = new ToggleGroup();

        for (int i = 0; i < 5; i++) {
            HBox candidateBox = new HBox(10);
            candidateBox.setAlignment(Pos.CENTER_LEFT);

            // Candidate image
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("photo_candidate.jpg")));
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            // Candidate details
            VBox detailsBox = new VBox(5);
            Text name = new Text(bio[i]);
            Text party = new Text(batches[i]);

            detailsBox.getChildren().addAll(name, party);

            // Candidate selection radio button
            RadioButton radioButton = new RadioButton("Вибрати");
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setUserData(i);

            radioButton.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    selectedCandidateIndex = (int) radioButton.getUserData();
                }
            });

            candidateBox.getChildren().addAll(imageView, detailsBox, radioButton);
            vbox.getChildren().add(candidateBox);
        }

        // Vote button
        Button voteButton = new Button("Проголосувати");
        voteButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        voteButton.setOnAction(e -> {
            try {
                DatabaseManager databaseManager = new DatabaseManager();
                databaseManager.connect(url, username, PASSWORD);

                databaseManager.userVote(id_user, selectedCandidateIndex+1);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (selectedCandidateIndex != -1) {
                System.out.println("Ви вибрали кандидата № " + (selectedCandidateIndex+1));
                // Можна додати додаткову логіку для обробки вибору
            } else {
                System.out.println("Виберіть кандидата перед голосуванням.");
            }
        });

        vbox.getChildren().add(voteButton);

        return vbox;
    }
}
