package View;

import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword {
    public static URL profileMenuFxmlURL;

    public Parent root;
    public Scene scene;
    public Stage stage;

    @FXML
    public TextField passwordTF;
    @FXML
    public Text message;


    public void changeClicked(MouseEvent mouseEvent) throws IOException {
        String userAndPassRegex = "[a-zA-Z\\d!@#$%^&*?<>.]+";
        if(!passwordTF.getText().matches(userAndPassRegex)){
            message.setText("invalid password!");
            return;
        }
        if(Objects.equals(passwordTF.getText(), MainMenuController.loggedInMember.password)){
            message.setText("choose a new password!");
            return;
        }

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

        bufferedWriter.write(MainMenuController.loggedInMember.name +  " " + passwordTF.getText() + " " + MainMenuController.loggedInMember.score);

        bufferedWriter.newLine();

        bufferedWriter.close();

        int score = MainMenuController.loggedInMember.score;
        MainMenuController.loggedInMember = new Member(username, score, passwordTF.getText());

        message.setText("Password has been successfully changed");
    }

    public void BackClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(profileMenuFxmlURL);
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
