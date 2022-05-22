package Transitions;

import View.Components.Egg;
import View.Components.FlyingBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;

public class FlyingBossPhaseThreeAttack extends Transition {
    public static URL PhaseThreeAttack;
    public static FlyingBoss flyingBoss;
    public Egg egg;

    public FlyingBossPhaseThreeAttack(){
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);
    }
    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 17);
        String address = String.valueOf(PhaseThreeAttack) + frame + ".png";
        flyingBoss.setFill(new ImagePattern(new Image(String.valueOf(address))));

        if(frame == 17){
            this.egg = new Egg(flyingBoss,100);
            EggShootingAnimation eggShootingAnimation = new EggShootingAnimation(this.egg);
            eggShootingAnimation.play();
        }
    }
}
