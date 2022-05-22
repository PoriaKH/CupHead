package Transitions;

import View.Components.FlyingBoss;
import View.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import static Transitions.BossFlyingAnimation.bossDeathURL;

public class FlyingBossDeath extends Transition {
    public static FlyingBoss flyingBoss;

    public FlyingBossDeath(){
        this.setCycleDuration(Duration.millis(1500));
        this.setCycleCount(1);
        FlyingBoss.isDying = true;
    }
    @Override
    protected void interpolate(double frac) {
        if(Game.phaseOne){
            Game.phaseOne = false;
            Game.phaseTwo = true;
        }

        int frame = (int) Math.floor(frac * 29);
        String address = String.valueOf(bossDeathURL) + frame + ".png";
        flyingBoss.setFill(new ImagePattern(new Image(String.valueOf(address))));

        if(frame == 29){
            FlyingBoss.isDying = false;
            flyingBoss.setWidth(150);
            flyingBoss.setHeight(150);
            FlyingBossPhaseTwoAnimation flyingBossPhaseTwoAnimation = new FlyingBossPhaseTwoAnimation();
            flyingBossPhaseTwoAnimation.play();
        }
    }
}
