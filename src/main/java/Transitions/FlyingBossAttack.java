package Transitions;

import View.Components.Egg;
import View.Components.FlyingBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;

public class FlyingBossAttack extends Transition {
    public static URL bossShootURL;
    public static FlyingBoss flyingBoss;
    public boolean justOneEgg = true;

    public FlyingBossAttack(){
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 11);
        String address = String.valueOf(bossShootURL) + frame + ".png";
        flyingBoss.setFill(new ImagePattern(new Image(String.valueOf(address))));

        if(frame == 7 && justOneEgg){
            justOneEgg = false;
            Egg egg = new Egg(flyingBoss,-1);
            EggShootingAnimation eggShootingAnimation = new EggShootingAnimation(egg);
            eggShootingAnimation.play();
        }
    }
}
