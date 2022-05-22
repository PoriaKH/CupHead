import Transitions.*;
import View.*;
import View.Components.Bullet;
import View.Components.FlyingBoss;
import View.Components.MiniBoss;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL address_login_page = new URL(App.class.getResource("fxml/loginPage.fxml").toExternalForm());
        ProfileMenuController.loginMenuFxmlURL = new URL(App.class.getResource("fxml/loginPage.fxml").toExternalForm());
        LoginPageController.mainMenuFxmlURL = new URL(App.class.getResource("fxml/mainMenu.fxml").toExternalForm());
        ProfileMenuController.mainMenuFxmlURL = new URL(App.class.getResource("fxml/mainMenu.fxml").toExternalForm());
        ProfileMenuController.changeUsernameFxmlURL = new URL(App.class.getResource("fxml/changeUsername.fxml").toExternalForm());
        MainMenuController.profileMenuFxmlURL = new URL(App.class.getResource("fxml/profileMenu.fxml").toExternalForm());
        ChangeUsername.profileMenuFxmlURL = new URL(App.class.getResource("fxml/profileMenu.fxml").toExternalForm());
        ChangePassword.profileMenuFxmlURL = new URL(App.class.getResource("fxml/profileMenu.fxml").toExternalForm());
        ProfileMenuController.changePasswordFxmlURL = new URL(App.class.getResource("fxml/changePassword.fxml").toExternalForm());
        MainMenuController.scoreBoardFxmlURL = new URL(App.class.getResource("fxml/scoreBoard.fxml").toExternalForm());
        ScoreBoard.mainMenuFxmlURL = new URL(App.class.getResource("fxml/mainMenu.fxml").toExternalForm());
        MainMenuController.startGameFxmlURL = new URL(App.class.getResource("fxml/startGame.fxml").toExternalForm());
        StartGame.mainMenuFxmlURL = new URL(App.class.getResource("fxml/mainMenu.fxml").toExternalForm());
        StartGame.gameMenuFxmlURL = new URL(App.class.getResource("fxml/game.fxml").toExternalForm());
        Game.imageURL = new URL(App.class.getResource("images/frames/images/red.png").toExternalForm());
        Bullet.bulletURL = new URL(App.class.getResource("images/frames/images/bullet.png").toExternalForm());
        FlyingBoss.bossFlyingURL = new URL(App.class.getResource("images/frames/BossFly/").toExternalForm());
        FlyingBossAttack.bossShootURL = new URL(App.class.getResource("images/frames/BossShoot/").toExternalForm());
        EggShootingAnimation.eggURL = new URL(App.class.getResource("images/frames/images/egg.png").toExternalForm());
        GyrocopterAvatarAnimation.gyrocopterAvatarURL = new URL(App.class.getResource("images/frames/avatar/").toExternalForm());
        BossFlyingAnimation.resultsFxmlURL = new URL(App.class.getResource("fxml/results.fxml").toExternalForm());
        MiniBoss.miniBossURL = new URL(App.class.getResource("images/frames/MiniBossFly/yellow/").toExternalForm());
        StartGame.rocketOrBulletURL = new URL(App.class.getResource("images/frames/RocketOrBullet.png").toExternalForm());
        BossFlyingAnimation.bossDeathURL = new URL(App.class.getResource("images/frames/PhaseOneDeath/").toExternalForm());
        FlyingBossPhaseTwoAnimation.flyingBossPhaseTwo = new URL(App.class.getResource("images/frames/PhaseTwoCharacter/").toExternalForm());
        FlyingBossPhaseTwoAttack.shootURL = new URL(App.class.getResource("images/frames/CupheadShoot/2.png").toExternalForm());
        FlyingBossPhaseTwoDeath.phaseTwoDeath = new URL(App.class.getResource("images/frames/PhaseTwoDeath/").toExternalForm());
        FlyingBossPhaseThreeAnimation.phaseThreeCharacter = new URL(App.class.getResource("images/frames/PhaseThreeCharacter/").toExternalForm());
        FlyingBossPhaseThreeAttack.PhaseThreeAttack = new URL(App.class.getResource("images/frames/PhaseThreeShoot/").toExternalForm());
        FlyingBossPhaseThreeDeath.flyingBossPhaseThreeDeathURL = new URL(App.class.getResource("images/frames/PhaseThreeDeath/").toExternalForm());
        StartGame.shootSoundURL = new URL(App.class.getResource("music/shoot.wav").toExternalForm());
        StartGame.gameSoundURL = new URL(App.class.getResource("music/Game.mp3").toExternalForm());
        EggShootingAnimation.eggSoundURL  = new URL(App.class.getResource("music/egg.wav").toExternalForm());
        StartGame.backgroundURL = new URL(App.class.getResource("images/frames/New/Background/").toExternalForm());
        MainMenuController.mainMenuSongURL = new URL(App.class.getResource("music/MainMenu.mp3").toExternalForm());

        Parent root = FXMLLoader.load(address_login_page);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
