package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static View.MainMenuController.startGameFxmlURL;
import static View.StartGame.mainMenuFxmlURL;

public class Results {
    public Parent root;
    public Scene scene;
    public Stage stage;
    public static boolean hasWon;

    @FXML
    public Button retryButton;
    @FXML
    public Button backToMainButton;
    @FXML
    public Text message;

    public void initialize(){
        if(hasWon){
            if(MainMenuController.loggedInMember != null) {
                message.setText("you have reached +10 points");
                message.setStyle("-fx-font-size: 20;" +
                        "    -fx-pref-height: 70;" +
                        "    -fx-pref-width: 350;" +
                        "    -fx-border-radius: 5;" +
                        "    -fx-background-color: #4eb245;");
            }
            retryButton.setStyle("-fx-font-size: 20;" +
                    "    -fx-pref-height: 70;" +
                    "    -fx-pref-width: 350;" +
                    "    -fx-border-radius: 5;" +
                    "    -fx-background-color: #4eb245;");

            backToMainButton.setStyle("-fx-font-size: 20;" +
                    "    -fx-pref-height: 70;" +
                    "    -fx-pref-width: 350;" +
                    "    -fx-border-radius: 5;" +
                    "    -fx-background-color: #4eb245;");
        }
        else {
            if(MainMenuController.loggedInMember != null) {
                message.setText("you have lost 10 points");
                message.setStyle("-fx-font-size: 20;" +
                        "    -fx-pref-height: 70;" +
                        "    -fx-pref-width: 350;" +
                        "    -fx-border-radius: 5;" +
                        "    -fx-background-color: #ff1916;");
            }
            retryButton.setStyle("-fx-font-size: 20;" +
                    "    -fx-pref-height: 70;" +
                    "    -fx-pref-width: 350;" +
                    "    -fx-border-radius: 5;" +
                    "    -fx-background-color: #ff1916;");

            backToMainButton.setStyle("-fx-font-size: 20;" +
                    "    -fx-pref-height: 70;" +
                    "    -fx-pref-width: 350;" +
                    "    -fx-border-radius: 5;" +
                    "    -fx-background-color: #ff1916;");
        }
    }
    public void RetryPressed(ActionEvent event) throws IOException {
        StartGame.backExists = false;
        root = FXMLLoader.load(startGameFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void BackToMainPressed(ActionEvent event) throws IOException {
        StartGame.backExists = true;
        root = FXMLLoader.load(mainMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
