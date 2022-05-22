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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreBoard {
    public static URL mainMenuFxmlURL;

    public Parent root;
    public Scene scene;
    public Stage stage;

    @FXML
    public VBox list;

    public void initialize() throws IOException {
        ArrayList<String> users = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

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
            int fileScore = Integer.parseInt(fileMatcher.group("score"));
            users.add(fileUsername);
            points.add(fileScore);

            line = bufferedReader.readLine();
        }
        fileReader.close();
        int userCounter = 1;
        int tempSize = points.size();
        for(int i = 0; i < points.size() + 1; i++){
            if(i == 10)
                break;

            int max = -1;
            int index = 0;
            for(int j = 0; j < points.size(); j++){
                if(points.get(j) > max){
                    max = points.get(j);
                    index = j;
                }
            }
            Text text = new Text(userCounter + "- " + users.get(index) + " : " + points.get(index));
            list.getChildren().add(text);
            list.getChildren().add(new Text("--------------------------------------------------------------------------------------------------------------------------------"));
            users.remove(index);
            points.remove(index);
            i = 0;
            userCounter++;
        }

    }

    public void backClicked(javafx.event.ActionEvent event) throws IOException {
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
