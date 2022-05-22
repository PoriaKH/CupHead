package Transitions;

import View.Components.Egg;
import View.Components.Gyrocopter;
import View.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;
import java.util.Date;

public class EggShootingAnimation extends Transition {

    public static URL eggURL;
    public static Gyrocopter gyrocopter;
    public Egg egg;
    public static URL eggSoundURL;
    public MediaPlayer mediaPlayer;

    public EggShootingAnimation(Egg egg){
        this.egg = egg;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);

        Media media = new Media(eggSoundURL.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(mediaPlayer.getVolume() * 10);
    }

    @Override
    protected void interpolate(double frac) {
        egg.move();
        mediaPlayer.play();

        if(!Gyrocopter.onShield) {
            if (this.egg.hasCollision(gyrocopter)) {
                Gyrocopter.onShield = true;
                Game.health -= 1;
                Game.hp.setText(Integer.toString(Game.health));
                this.stop();
                egg.remove();
                GyrocopterAvatarAnimation gyrocopterAvatarAnimation = new GyrocopterAvatarAnimation();
                gyrocopterAvatarAnimation.play();
            }
        }
    }
}
