package View;

import Transitions.*;
import View.Components.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

import static View.Components.Bullet.bulletURL;
import static View.Game.imageURL;

public class StartGame {
    public static boolean backExists = true;
    public static URL gameSoundURL;
    public static URL shootSoundURL;
    public static URL mainMenuFxmlURL;
    public static URL gameMenuFxmlURL;
    public static URL rocketOrBulletURL;
    public static URL backgroundURL;

    public static MediaPlayer mediaPlayer2;
    public Button backButton;

    public StartGame(){
        Media media = new Media(gameSoundURL.toString());
        mediaPlayer2 = new MediaPlayer(media);
    }

    public void initialize(){
        if(backExists){
            backButton.setDisable(false);
        }
        else {
            backButton.setDisable(true);
        }
    }

    public Scene scene;
    public Pane root;
    public Stage stage;

    public static Date startDate = new Date();

    public void backClicked(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(mainMenuFxmlURL);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Easy(javafx.event.ActionEvent event) throws IOException {
        Game.health = 10;
        Game.vulnerability = 50;
        Game.damage = 150;

        switchToGame(event);
    }
    public void Normal(javafx.event.ActionEvent event) throws IOException {
        Game.health = 5;
        Game.vulnerability = 100;
        Game.damage = 100;

        switchToGame(event);
    }

    public void Hard(javafx.event.ActionEvent event) throws IOException {
        Game.health = 2;
        Game.vulnerability = 150;
        Game.damage = 50;

        switchToGame(event);
    }

    private void switchToGame(javafx.event.ActionEvent event) throws IOException {
        MainMenuController.mediaPlayer.pause();

        root = FXMLLoader.load(gameMenuFxmlURL);
        Gyrocopter gyrocopter = new Gyrocopter(root);
        Bullet.root = Gyrocopter.root;
        Egg.root = Gyrocopter.root;
        MiniBoss.root = Gyrocopter.root;
        gyrocopter.setFill(new ImagePattern(new Image(String.valueOf(imageURL))));
        Bullet.gyrocopter = gyrocopter;
        GyrocopterAvatarAnimation.gyrocopter = gyrocopter;
        root.getChildren().add(gyrocopter);

        root.getChildren().get(0).setLayoutX(50);
        root.getChildren().get(0).setLayoutY(50);

        FlyingBoss flyingBoss = new FlyingBoss();
        BossFlyingAnimation.flyingBoss = flyingBoss;
        ShootAnimation.flyingBoss = flyingBoss;
        EggShootingAnimation.gyrocopter = gyrocopter;
        FlyingBossAttack.flyingBoss = flyingBoss;
        MiniBossFly.gyrocopter = gyrocopter;
        Rocket.gyrocopter = gyrocopter;
        Game.root = Gyrocopter.root;
        Rocket.root = Gyrocopter.root;
        RocketShootingAnimation.flyingBoss = flyingBoss;
        FlyingBossDeath.flyingBoss = flyingBoss;
        FlyingBossPhaseTwoAnimation.flyingBoss = flyingBoss;
        FlyingBossPhaseTwoAttack.flyingBoss = flyingBoss;
        FlyingBossPhaseTwoAttack.root = root;
        FlyingBossPhaseTwoAttack.gyrocopter = gyrocopter;
        FlyingBossPhaseTwoAnimation.root = Gyrocopter.root;
        FlyingBossPhaseTwoDeath.flyingBoss = flyingBoss;
        FlyingBossPhaseTwoDeathLoop.flyingBoss = flyingBoss;
        FlyingBossPhaseThreeAnimation.flyingBoss = flyingBoss;
        FlyingBossPhaseThreeAnimation.root = Gyrocopter.root;
        FlyingBossPhaseThreeAttack.flyingBoss = flyingBoss;
        FlyingBossPhaseThreeDeath.flyingBoss = flyingBoss;
        BackgroundAnimation.root = Gyrocopter.root;
        root.getChildren().add(flyingBoss);


        BossFlyingAnimation.root = this.root;
        FlyingBossPhaseThreeDeath.root = this.root;
        BossFlyingAnimation.stage = this.stage;
        BossFlyingAnimation.scene = this.scene;

        BossFlyingAnimation bossFlyingAnimation = new BossFlyingAnimation();
        bossFlyingAnimation.play();

//        BackgroundAnimation backgroundAnimation = new BackgroundAnimation();
//        backgroundAnimation.play();

        mediaPlayer2.setAutoPlay(true);

        root.getChildren().get(0).setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                if(Objects.equals(keyName, "Up")){
                    gyrocopter.moveUp();
                }
                if(Objects.equals(keyName, "Down")){
                    gyrocopter.moveDown();
                }
                if(Objects.equals(keyName, "Left")){
                    gyrocopter.moveLeft();
                }
                if(Objects.equals(keyName, "Right")){
                   gyrocopter.moveRight();
                }
                if(Objects.equals(keyName, "Space")){
                    if(!Gyrocopter.onRocket) {
                        Bullet bullet = new Bullet();
                        ShootAnimation shootAnimation = new ShootAnimation(bullet);
                        shootAnimation.play();
                        Media media = new Media(shootSoundURL.toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
//                        mediaPlayer.setAutoPlay(true);
                        mediaPlayer.play();
                    }
                    else{
                        Date endDate = new Date();
                        if(endDate.getTime() - startDate.getTime() >= 300) {
                            Rocket rocket = new Rocket();
                            RocketShootingAnimation rocketShootingAnimation = new RocketShootingAnimation(rocket);
                            rocketShootingAnimation.play();
                            startDate = endDate;
                        }
                    }
                }
                if(Objects.equals(keyName, "Tab")){
                    Gyrocopter.onRocket = !Gyrocopter.onRocket;
                    if(Game.circle.getCenterX() == 78)
                        Game.circle.setCenterX(33);
                    else
                        Game.circle.setCenterX(78);
                }
            }
        });
        Text text = new Text(25,700,"HP : ");
        Game.hp = new Text(65,700,Integer.toString(Game.health));
        Game.hp.setStyle("-fx-font-size: 20");
        root.getChildren().add(Game.hp);
        text.setStyle("-fx-font-size: 20");
        root.getChildren().add(text);

        Rectangle rectangle = new Rectangle(10,650,90,30);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(rocketOrBulletURL))));
        root.getChildren().add(rectangle);

        Game.circle = new Circle(78,643,5);
        Game.circle.setFill(Color.RED);
        root.getChildren().add(Game.circle);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        root.getChildren().get(0).requestFocus();
        stage.show();
    }
}
