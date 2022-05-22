package View;

import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainMenuController {
    public static URL mainMenuSongURL;
    public static Member loggedInMember;
    public static URL profileMenuFxmlURL;
    public static URL scoreBoardFxmlURL;
    public static URL startGameFxmlURL;
    public static MediaPlayer mediaPlayer;

    public Parent root;
    public Scene scene;
    public Stage stage;

    @FXML
    public Button profile;
    @FXML
    public Button scoreBoard;

    public void initialize(){
        if(mediaPlayer != null){
//            mediaPlayer.pause();
            mediaPlayer.play();
        }
        if(mediaPlayer == null) {
            Media media = new Media(mainMenuSongURL.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
        }
        if(loggedInMember == null){
            profile.setDisable(true);
        }
        if(loggedInMember == null){
            scoreBoard.setDisable(true);
        }
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void profileClicked(javafx.event.ActionEvent event) throws IOException {
        switchToProfile(event);
    }
    public void switchToProfile(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(profileMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void boardClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(scoreBoardFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startGameClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(startGameFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
