package View;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Game {
    public static URL imageURL;

    public static int health;
    public static int vulnerability;
    public static int damage;

    public static Pane root;
    public static Text hp;
    public static Circle circle;

    public static boolean phaseOne = true;
    public static boolean phaseTwo = false;
    public static boolean phaseThree = false;


    public void keyPressed(KeyEvent keyEvent) throws IOException {
        String code = String.valueOf(keyEvent.getCode());
        if(Objects.equals(code, "ESCAPE")) {
            System.out.println("switch to pause ( LOL )");
        }
    }

//    private void switchToPause(KeyEvent keyEvent) throws IOException {
//        root = FXMLLoader.load(pauseMenuFxmlURL);
//        stage = (Stage) ((Node)keyEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}
