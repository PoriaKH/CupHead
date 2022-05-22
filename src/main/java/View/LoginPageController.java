package View;

import Model.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPageController {
    public static URL mainMenuFxmlURL;


    Parent root;
    Scene scene;
    Stage stage;

    @FXML
    public TextField usernameTF;
    @FXML
    public TextField passwordTF;
    @FXML
    public Text message;

    public void loginMouseAction(javafx.event.ActionEvent event) throws IOException {
        String fileRegex = "(?<username>.*) (?<password>.*) (?<score>-?\\d+)";
        File file = new File("users.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        line = bufferedReader.readLine();
        while(line != null && !line.equals(""))
        {
            Matcher fileMatcher = getMatcher(line, fileRegex);
            fileMatcher.find();
            String fileUsername = fileMatcher.group("username");
            String filePassword = fileMatcher.group("password");

            if(Objects.equals(fileUsername, usernameTF.getText())){
                if(Objects.equals(filePassword, passwordTF.getText())){
                    int score = Integer.parseInt(fileMatcher.group("score"));
                    MainMenuController.loggedInMember = new Member(usernameTF.getText(),score,passwordTF.getText());
                    switchToMain(event);
                    message.setText("");
                }
                message.setText("Username and password did not match!");
                return;
            }

            line = bufferedReader.readLine();
        }
        fileReader.close();

        message.setText("Username and password did not match!");
    }

    public void registerMouseClicked(MouseEvent mouseEvent) throws IOException {
        String userAndPassRegex = "[a-zA-Z\\d!@#$%^&*?<>.]+";
        if(!usernameTF.getText().matches(userAndPassRegex) || !passwordTF.getText().matches(userAndPassRegex)){
            message.setText("invalid username or password");
            return;
        }

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

            if(Objects.equals(fileUsername, usernameTF.getText())) {
                message.setText("username already exists");
                return;
            }

            stringBuilder.append(line);
            stringBuilder.append("\n");

            line = bufferedReader.readLine();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(String.valueOf(stringBuilder));

        bufferedWriter.write(usernameTF.getText() + " " + passwordTF.getText() + " 0");
        bufferedWriter.newLine();

        message.setText("user registered successfully");

        bufferedWriter.close();
    }
    public void guestMouseClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(mainMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMain(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(mainMenuFxmlURL);
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
