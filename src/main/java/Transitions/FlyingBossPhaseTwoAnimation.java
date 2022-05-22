package Transitions;

import Model.Member;
import View.Components.*;
import View.Game;
import View.MainMenuController;
import View.Results;
import View.StartGame;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Transitions.BossFlyingAnimation.resultsFxmlURL;
import static View.Components.Bullet.gyrocopter;
import static View.Components.FlyingBoss.bossFlyingURL;

public class FlyingBossPhaseTwoAnimation extends Transition {
    public static Pane root;
    public static FlyingBoss flyingBoss;
    public static URL flyingBossPhaseTwo;
    public static ArrayList<Egg> eggs = new ArrayList<>();

    public Stage stage;
    public Scene scene;

    public static Date startDate = new Date();

    public FlyingBossPhaseTwoAnimation(){
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
        Egg egg1 = new Egg(flyingBoss,1);
        Egg egg2 = new Egg(flyingBoss,2);

        eggs.add(egg1);
        eggs.add(egg2);
    }

    @Override
    protected void interpolate(double frac) {
        if (flyingBoss.health >= 20000) {
            int frame = (int) Math.floor(frac * 15);

            String address = String.valueOf(flyingBossPhaseTwo) + frame + ".png";
            flyingBoss.setFill(new ImagePattern(new Image(String.valueOf(address))));
            flyingBoss.move();
            for (Egg egg : eggs) {
                egg.move();
                if (!Gyrocopter.onShield) {
                    if (egg.hasCollision(gyrocopter)) {
                        Gyrocopter.onShield = true;
                        Game.health -= 1;
                        Game.hp.setText(Integer.toString(Game.health));
                        GyrocopterAvatarAnimation gyrocopterAvatarAnimation = new GyrocopterAvatarAnimation();
                        gyrocopterAvatarAnimation.play();
                    }
                }
                for (Bullet bullet : Bullet.bullets) {
                    if (egg.hasCollisionWithBullet(bullet)) {
                        bullet.remove();
                        break;
                    }
                }
                for (Rocket rocket : Rocket.rockets) {
                    if (egg.hasCollisionWithRocket(rocket)) {
                        rocket.remove();
                        break;
                    }
                }
                Date endDate = new Date();

                if (endDate.getTime() - startDate.getTime() >= 7000) {
                    startDate = endDate;
                    FlyingBossPhaseTwoAttack flyingBossPhaseTwoAttack = new FlyingBossPhaseTwoAttack();
                    flyingBossPhaseTwoAttack.play();
                }
            }


            if (!Gyrocopter.onShield) {
                if (flyingBoss.hasCollision(gyrocopter)) {
                    Gyrocopter.onShield = true;
                    Game.health -= 1;
                    Game.hp.setText(Integer.toString(Game.health));
                    GyrocopterAvatarAnimation gyrocopterAvatarAnimation = new GyrocopterAvatarAnimation();
                    gyrocopterAvatarAnimation.play();
                }
            }
        }
        else {
            this.stop();
            flyingBoss.health += 20000;
            root.getChildren().remove(eggs.get(0));
            root.getChildren().remove(eggs.get(1));
            FlyingBossPhaseTwoDeath flyingBossPhaseTwoDeath = new FlyingBossPhaseTwoDeath();
            flyingBossPhaseTwoDeath.play();
        }
        if (loseCheck()) {
            try {
                switchToResultsMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void switchToResultsMenu() throws IOException {
        this.stop();
        StartGame.mediaPlayer2.pause();
        Results.hasWon = false;

        Game.phaseThree = false;
        Game.phaseTwo = false;
        Game.phaseOne = true;

        FlyingBossPhaseTwoAnimation.eggs = new ArrayList<>();

        if(MainMenuController.loggedInMember != null)
            changeScore();

        while(root.getChildren().size() > 0){
            root.getChildren().remove(0);
        }
        Button button = new Button("Game Over");
        button.setStyle("-fx-font-size: 20");
        button.setStyle("-fx-background-color: red");
        button.setLayoutX(500);
        button.setLayoutY(300);
        button.requestFocus();
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(resultsFxmlURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
        root.getChildren().add(button);
    }
    public Matcher getMatcher(String command, String regex){
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher;
    }
    private void changeScore() throws IOException {
        File file = new File("users.txt");

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder("");
        String line = bufferedReader.readLine();

        String fileRegex = "(?<username>.*) (?<password>.*) (?<score>-?\\d+)";
        while (line != null && !line.equals("")) {
            Matcher fileMatcher = getMatcher(line, fileRegex);
            fileMatcher.find();
            String fileUsername = fileMatcher.group("username");

            if(!Objects.equals(fileUsername, MainMenuController.loggedInMember.name)) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            line = bufferedReader.readLine();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        bufferedWriter.write(String.valueOf(stringBuilder));

        bufferedWriter.write(MainMenuController.loggedInMember.name +  " " + MainMenuController.loggedInMember.password + " " + (MainMenuController.loggedInMember.score - 10));

        bufferedWriter.newLine();

        bufferedWriter.close();

        MainMenuController.loggedInMember = new Member(MainMenuController.loggedInMember.name, MainMenuController.loggedInMember.score - 10, MainMenuController.loggedInMember.password);

    }
    private boolean loseCheck() {
        return Game.health <= 0;
    }
}
