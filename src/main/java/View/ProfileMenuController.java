package View;

import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {
    public static URL loginMenuFxmlURL;
    public static URL mainMenuFxmlURL;
    public static URL changeUsernameFxmlURL;
    public static URL changePasswordFxmlURL;

    public Parent root;
    public Scene scene;
    public Stage stage;

    public void backClicked(javafx.event.ActionEvent event) throws IOException {
        switchToMain(event);
    }
    public void switchToMain(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(mainMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeUsernameClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(changeUsernameFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changePasswordClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(changePasswordFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutClicked(javafx.event.ActionEvent event) throws IOException {
        MainMenuController.loggedInMember = null;
        root = FXMLLoader.load(loginMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteClicked(javafx.event.ActionEvent event) throws IOException {
        String username = MainMenuController.loggedInMember.name;


        File file = new File("users.txt");

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder("");
        String line = bufferedReader.readLine();

        String fileRegex = "(?<username>.*) (?<password>.*) (?<score>\\d+)";
        while (line != null && !line.equals("")) {
            Matcher fileMatcher = getMatcher(line, fileRegex);
            fileMatcher.find();
            String fileUsername = fileMatcher.group("username");

            if(!Objects.equals(fileUsername, username)) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            line = bufferedReader.readLine();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(String.valueOf(stringBuilder));

        bufferedWriter.close();
        switchToLogin(event);
        return;
    }
    public void switchToLogin(javafx.event.ActionEvent event) throws IOException {
        MainMenuController.loggedInMember = null;
        root = FXMLLoader.load(loginMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private Matcher getMatcher(String command, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher;
    }
}
