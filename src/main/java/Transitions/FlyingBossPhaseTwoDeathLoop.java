package Transitions;

import View.Components.FlyingBoss;
import View.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import static Transitions.EggShootingAnimation.eggURL;
import static Transitions.FlyingBossPhaseTwoDeath.phaseTwoDeath;

public class FlyingBossPhaseTwoDeathLoop extends Transition {
    public static FlyingBoss flyingBoss;

    public FlyingBossPhaseTwoDeathLoop(){
        this.setCycleDuration(Duration.millis(200));
        this.setCycleCount(-1);

    }
    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 3);
        String address = String.valueOf(phaseTwoDeath) + "/Loop/" + frame + ".png";
        flyingBoss.setFill(new ImagePattern(new Image(String.valueOf(address))));

        flyingBoss.setX(flyingBoss.getX() - 4);
        if(flyingBoss.getX() <= -50){
            Game.phaseTwo = false;
            Game.phaseThree = true;
            this.stop();
            flyingBoss.setHeight(200);
            flyingBoss.setWidth(500);
            flyingBoss.setX(700);
            flyingBoss.setY(720 - flyingBoss.getHeight());
            FlyingBossPhaseThreeAnimation flyingBossPhaseThreeAnimation = new FlyingBossPhaseThreeAnimation();
            flyingBossPhaseThreeAnimation.play();
        }
    }
}
