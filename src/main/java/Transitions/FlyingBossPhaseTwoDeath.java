package Transitions;

import View.Components.FlyingBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;

public class FlyingBossPhaseTwoDeath extends Transition {
    public static FlyingBoss flyingBoss;
    public static URL phaseTwoDeath;

    public FlyingBossPhaseTwoDeath(){
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 7);
        String address = String.valueOf(phaseTwoDeath) + frame + ".png";
        flyingBoss.setFill(new ImagePattern(new Image(String.valueOf(address))));

        if(frame == 7){
            FlyingBossPhaseTwoDeathLoop flyingBossPhaseTwoDeathLoop = new FlyingBossPhaseTwoDeathLoop();
            flyingBossPhaseTwoDeathLoop.play();
        }
    }
}
